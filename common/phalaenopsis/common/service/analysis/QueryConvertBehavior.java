/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.common.service.analysis;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import phalaenopsis.common.entity.AppSettings;
import phalaenopsis.common.entity.TwoTuple;
import phalaenopsis.common.entity.analysis.AnalysisColumnItem;
import phalaenopsis.common.entity.analysis.AnalysisRowItem;
import phalaenopsis.common.entity.analysis.CombinMode;
import phalaenopsis.common.entity.analysis.Field;
import phalaenopsis.common.method.Tools.Linq;
import phalaenopsis.common.method.Tools.StrUtil;
import phalaenopsis.common.method.Tools.ValComparator;
import phalaenopsis.satellitegraph.entity.GeometryPolygon;
import phalaenopsis.satellitegraph.entity.MultiPolygon;
import phalaenopsis.satellitegraph.entity.Polygon;
import phalaenopsis.satellitegraph.utils.ArcGISHelper.ArcGISAreaAndLengthsResult;
import phalaenopsis.satellitegraph.utils.ArcGISHelper.ArcGISAreaLengthParam;
import phalaenopsis.satellitegraph.utils.ArcGISHelper.ArcGISUnionParam;
import phalaenopsis.satellitegraph.utils.ArcGISHelper.HttpHelper;
import phalaenopsis.satellitegraph.utils.ArcGISHelper.SpotArcGISHelper;

/**
 * @author yangw786
 *
 * 2017年5月5日下午4:07:33
 */
public class QueryConvertBehavior extends ArcgisQueryBehavior {

	@Override
	public List<AnalysisRowItem> getAnalysisItems(List<Field> fields, String url, Polygon polygon,
			List<TwoTuple<String, Object>> statisticField, String geometryServer) {
		List<AnalysisRowItem> tempItems = super.getAnalysisItems(fields, url, polygon, statisticField, geometryServer);
		Collections.sort(tempItems, (AnalysisRowItem t1, AnalysisRowItem t2)-> ( t1.getArea() - t2.getArea() >0?1:-1 ));
		//Collections.sort(tempItems, new ValComparator());
		Collections.reverse(tempItems); // 倒序
		AnalysisRowItem maxRowItem = tempItems.get(0); //取第一个
		if (maxRowItem != null) {
			for (Field field : fields) {
				if (field.IsMaxDisplayColumn) {
					// maxRowItem.Columns.Where(c => c.Name == field.Alias)
					// statisticField.add(new
					// TwoTuple<String,Object>(field.Alias,
					// maxRowItem.Columns.Where(c => c.Name ==
					// field.Alias).First().Value));
					List<AnalysisColumnItem> list = Linq.extEqualsList(maxRowItem.Columns, "Name", field.Alias);
					AnalysisColumnItem a = Linq.firstOrDefault(list);
					Object v = null;
					if (a == null) {
						v = list.get(0).Value;
					} 
					
					statisticField.add(new TwoTuple<String, Object>(field.Alias, v));
				}
			}
		}
		// 查询结果中存在相交面积为0的记录不参与合并操作
		List<AnalysisRowItem> tempItemsArea = Linq.extEqualsList(tempItems, "Area", 0, ">");
		if (tempItemsArea.size() > 0) {
			tempItems = tempItemsArea;
		} else {
			AnalysisRowItem defaultRowItem = Linq.firstOrDefault(tempItems);
			for (AnalysisColumnItem a : defaultRowItem.Columns) {
				a.Value = "";
			}
			 
			tempItems = new ArrayList<AnalysisRowItem>();
			tempItems.add(defaultRowItem);
		}
		if (isNeedUnion == false)
			return tempItems;
		else {
			List<AnalysisRowItem> results = new ArrayList<AnalysisRowItem>();
			AnalysisRowItem unionItem = tempItems != null && tempItems.size() > 0 ? tempItems.get(0) : null;
			// 根据 field 中的配置进行转换后返回
			if (unionItem != null && fields != null && fields.size() > 0) {
				// 筛选分组字段集合
				// List<Field> unionFields = fields.Where(f => f.IsUnionKey ==
				// true).ToList();
				List<Field> unionFields = Linq.extEqualsList(fields, "IsUnionKey", true);
				// 筛选合并字段集合
				// List<Field> combineFields = fields.Where(f => f.IsUnionKey ==
				// false).ToList();
				List<Field> combineFields = Linq.extEqualsList(fields, "IsUnionKey", false);

				// 配置了分组字段时先分组再合并 IGrouping Map
				if (unionFields != null && unionFields.size() > 0) {
					// LinkedHashMap<Object,List<AnalysisRowItem>>
					// List<Map<Object, AnalysisRowItem>> templist =
					// tempItems.GroupBy(t =>
					// t.Columns.Where(c => c.Name ==
					// unionFields[0].Alias).First().Value).ToList();

					// List<Map<Object, List<AnalysisRowItem>>> templist=new
					// ArrayList<Map<Object, List<AnalysisRowItem>>>();
					LinkedHashMap<Object, List<AnalysisRowItem>> map = new LinkedHashMap<Object, List<AnalysisRowItem>>();
					for (AnalysisRowItem rowItem : tempItems) {
						for (AnalysisColumnItem aColumn : rowItem.Columns) {
							if (aColumn.Name.equals(unionFields.get(0).Alias)) {// 判断名称与分组名称是否相等
								if (map.containsKey(aColumn.Value)) {
									List<AnalysisRowItem> items = map.get(aColumn.Value);
									items.add(rowItem);
									map.put(aColumn.Value, items);
								} else {
									List<AnalysisRowItem> list = new ArrayList<AnalysisRowItem>();
									list.add(rowItem);
									map.put(aColumn.Value, list);
								}
							}
						}
					}

					int count = 0;
					GroupRowItems(map, count, unionFields, combineFields, results, geometryServer);
				} else {
					// 只配置了合并字段则直接进行合并
					// 构建需要合并的分析项坐标串集合对象
					MultiPolygon unionPolygon = new MultiPolygon();
					unionPolygon.Geometries.add(unionItem.Coordinates);
					// 默认unionItem为当前合并项中面积最大分析项，记载其合并字段集合信息
					HashMap<String, Object> staticField = new HashMap<String, Object>();
					for (Field field : combineFields) {

						// staticField.Add(field.Alias,
						// unionItem.Columns.Where(c => c.Name ==
						// field.Alias).First().Value);
						staticField.put(field.Alias,
								Linq.extEqualsList(unionItem.Columns, "Name", field.Alias).get(0).Value);
					}
					// skip 跳过几个 take 拿几个 List<AnalysisRowItem> tempItems
					// tempItems.Skip(1).ToList()
					List<AnalysisRowItem> skiptempItemsList = new ArrayList<AnalysisRowItem>();
					if (tempItems.size() > 1) {
						for (int i = 1; i < tempItems.size(); i++) {
							skiptempItemsList.add(tempItems.get(i));
						}
					}

					UnionRowItems(skiptempItemsList, combineFields, unionItem, unionPolygon, staticField);
					if (unionPolygon.Geometries.size() > 1) {
						unionItem.Coordinates = UnionRowItemCoordinates(unionPolygon, geometryServer);
						unionItem.Area = GetunionSpotArea(unionItem.Coordinates, geometryServer);
					}
					unionItem.Tag = staticField;
					results.add(unionItem);
				}
			}
			return results;
		}
	}
	
	/// <summary>
    /// 分组分析项
    /// </summary>
    /// <param name="rowItems"></param>
    /// <param name="count"></param>
    /// <param name="unionFields"></param>
    /// <param name="combineFields"></param>
    /// <param name="results"></param>
    private void GroupRowItems(LinkedHashMap<Object,List<AnalysisRowItem>>  rowItems,Integer count, List<Field> unionFields, List<Field> combineFields, List<AnalysisRowItem> results, String geometryServer)
    {
        for (List<AnalysisRowItem>   itemG: rowItems.values())
        {
            if (itemG.size() > 1)
            {  
                //分组字段集合遍历完毕时开始进行合并操作              
                if (count == unionFields.size() - 1)
                {
                    count = 0;
                    //对字段值进行合并操作，resultItem为需要合并的对象
                   // AnalysisRowItem resultItem = itemG.First(); 
                   // AnalysisRowItem resultItemFirst=null;
                    AnalysisRowItem resultItem =null;
                    /*Iterator<Map.Entry<Object,AnalysisRowItem>> it=itemG.entrySet().iterator();
                    while(it.hasNext()){
                    	Map.Entry<Object,AnalysisRowItem> entry=it.next();
                    	resultItem=entry.getValue();
                    	break;
                    }*/
                    resultItem=Linq.firstOrDefault(itemG);
                    //AnalysisRowItem resultItem =itemG;
                    //构建需要合并的分析项坐标串集合对象
                    MultiPolygon unionPolygon = new MultiPolygon();
                    unionPolygon.Geometries.add(resultItem.Coordinates);

                    //默认resultItem为当前合并项中面积最大分析项，记载其合并字段集合信息
                    HashMap<String, Object> statisticField = new HashMap<String, Object>();
                    for (Field field : combineFields)
                    {
                    	List<AnalysisColumnItem> Columns=Linq.extEqualsList(resultItem.Columns, "Name", field.Alias);
                    	Object value=null;
                    	if(Columns!=null&&Columns.size()>0){
                    		value=Columns.get(0).Value;
                    	}
                       // statisticField.put(field.Alias, resultItem.Columns.Where(c => c.Name == field.Alias).First().Value);
                        statisticField.put(field.Alias,value);
                    }
                    //当前分组项集合条目大于1条时才进行合并
                    if (itemG.size() > 1)
                    {
                    	 List<AnalysisRowItem> skiptempItemsList=new ArrayList<AnalysisRowItem>();
                         for(int i=1;i<itemG.size();i++){
                        	 skiptempItemsList.add(itemG.get(i));
                         }
                        UnionRowItems(skiptempItemsList, combineFields, resultItem, unionPolygon, statisticField);
                        resultItem.Coordinates = UnionRowItemCoordinates(unionPolygon, geometryServer);
                        resultItem.Area = GetunionSpotArea(resultItem.Coordinates, geometryServer);
                    }
                    resultItem.Tag = statisticField;
                    results.add(resultItem);                                                 
                }
                else
                {
                    //分组字段集合尚未遍历完毕时继续进行合并
                    count += 1;
                    int index = count;
                   // List<Map<Object, AnalysisRowItem>> rowItem = itemG.GroupBy(r => r.Columns.Where(c => c.Name == unionFields[index].Alias).First().Value).ToList();
                    
                    
                    LinkedHashMap<Object,List<AnalysisRowItem>> map = new LinkedHashMap<Object,List<AnalysisRowItem>>();
					for (AnalysisRowItem rowItem : itemG) {
						for (AnalysisColumnItem aColumn : rowItem.Columns) {
							if (aColumn.Name.equals(unionFields.get(index).Alias)) {// 判断名称与分组名称是否相等
								if (map.containsKey(aColumn.Value)) {
									List<AnalysisRowItem> items = map.get(aColumn.Value);
									items.add(rowItem);
									map.put(aColumn.Value, items);
								} else {
									List<AnalysisRowItem> list = new ArrayList<AnalysisRowItem>();
									list.add(rowItem);
									map.put(aColumn.Value, list);
								}
							}
						}
					}
                    
                    GroupRowItems(map,count, unionFields, combineFields, results, geometryServer);
                }
            }
            else
            {
                count = 0;
                //当前分组项只有一条记录时默认改记录为当前合并项中面积最大分析项，记载其合并字段集合信息
                HashMap<String, Object> statisticField = new HashMap<String, Object>();
                for (Field field : combineFields)
                {
                    //statisticField.put(field.Alias, itemG.First().Columns.Where(c => c.Name == field.Alias).First().Value); 
                    List<AnalysisColumnItem> list=Linq.extEqualsList(Linq.firstOrDefault(itemG).Columns, "Name", field.Alias);
	               	 AnalysisColumnItem a=Linq.firstOrDefault(list);
	               	 Object v=null;
	               	 if(a==null){
	               		 v=list.get(0).Value;
	               	 }
                    statisticField.put(field.Alias,v);
                    
                }
               
                //itemG.First().Tag = statisticField;
                Linq.firstOrDefault(itemG).Tag=statisticField;
               // results.Add(itemG.First());
                results.add(Linq.firstOrDefault(itemG));
            }
        }
    }

    /// <summary>
    /// 合并分析项
    /// </summary>
    /// <param name="rowItems"></param>
    /// <param name="combineFields"></param>
    /// <param name="resultItem"></param>
    private void UnionRowItems(List<AnalysisRowItem> rowItems, List<Field> combineFields, AnalysisRowItem resultItem, MultiPolygon unionPolygon, HashMap<String, Object> statisticField)
    {
        double maxArea = resultItem.Area;
        for (AnalysisRowItem row : rowItems)
        {
            //记载合并项中面积最大分析项的合并字段集合信息
            if (maxArea < row.Area)
            {
                maxArea = row.Area;
                statisticField.clear();
                for (Field field : combineFields)
                {
                	List<AnalysisColumnItem> Columns=Linq.extEqualsList(row.Columns, "Name", field.Alias);
                	Object value=null;
                	if(Columns!=null&&Columns.size()>0){
                		value=Columns.get(0).Value;
                	}
                 //   statisticField.Add(field.Alias, row.Columns.Where(c => c.Name == field.Alias).First().Value);
                    statisticField.put(field.Alias, value);

                }
            }
            for (AnalysisColumnItem column : row.Columns)
            {
                for (Field field : combineFields)
                {
                    if (column.Name == field.Alias)
                    {
                    	List<AnalysisColumnItem> Columns=Linq.extEqualsList(resultItem.Columns, "Name", field.Alias);
                    	Object value=null;
                    	if(Columns!=null&&Columns.size()>0){
                    		value=Columns.get(0).Value;
                    	}
                        if (field.CombinMode == CombinMode.Combination)//0 组合，值
                        {
                           // resultItem.Columns.Where(c => c.Name == field.Alias).First().Value += "," + column.Value;
                        	value += "," + column.Value;
                        }
                        else if (field.CombinMode == CombinMode.Add) //相加值
                        {
                            //resultItem.Columns.Where(c => c.Name == field.Alias).First().Value += column.Value.ToString();
                            value += column.Value.toString();
                        }
                        else if (field.CombinMode == CombinMode.Avg) //平均值
                        {
                            //resultItem.Columns.Where(c => c.Name == field.Alias).First().Value += column.Value.ToString();
                            value += column.Value.toString();
                        }
                        break;
                    }
                }
            }
            //resultItem.Area += row.Area;
            unionPolygon.Geometries.add(row.Coordinates);
        }
        //单独对结果中需要求取平均值的字段进行平均化
        for (Field field : combineFields)
        {
            if (field.CombinMode == CombinMode.Avg)
            {
            	List<AnalysisColumnItem> Columns=Linq.extEqualsList(resultItem.Columns, "Name", field.Alias);
            	Object value=null;
            	if(Columns!=null&&Columns.size()>0){
            		value=Columns.get(0).Value;
            	}
                //resultItem.Columns.Where(c => c.Name == field.Alias).First().Value = double.Parse(resultItem.Columns.Where(c => c.Name == field.Alias).First().Value.ToString()) / (rowItems.Count() + 1);
                value = Double.parseDouble(value.toString()) / (rowItems.size() + 1);

            }
        }
    }

    /// <summary>
    /// 合并坐标串
    /// </summary>
    /// <param name="unionPolygon"></param>
    /// <returns></returns>
    private Polygon UnionRowItemCoordinates(MultiPolygon unionPolygon, String geometryServer)
    {
        //String url = String.Format(@"{0}/union", geometryServer.Trim('/'));
        
        String url = StrUtil.trim(geometryServer, "/") + "/union";

        ArcGISUnionParam param = new ArcGISUnionParam(unionPolygon);

        //string uri = url.Trim('/') + "/?" + param.ToString();

        //GeometryPolygon polygons = HttpCommunicator.GetJson<GeometryPolygon>(uri);

       // String uri = url.Trim('/');
        String uri = StrUtil.trim(url, "/");
        
        
       /* HttpCommunicator http = new HttpCommunicator(Nefarian.Utility.HttpCommunicator.ContentType.Form);
        GeometryPolygon polygons = http.Post<GeometryPolygon>(uri, param.ToString());
        if (polygons == null)
            return null;
        return polygons.Geometry;*/
        GeometryPolygon geometryPolygon=null;
        try {
		        HttpEntity httpEntity = HttpHelper.HttpPost(uri, param.toList());        			
				JSONObject json=JSON.parseObject(EntityUtils.toString(httpEntity));				
				//json = 
				geometryPolygon = JSON.toJavaObject(json, GeometryPolygon.class);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        return geometryPolygon.Geometry;
    }

	private double GetunionSpotArea(Polygon unionPolygon, String geometryServer) {
    	DecimalFormat df = new DecimalFormat("#.00"); 
        MultiPolygon multiPolygon = new MultiPolygon();
        multiPolygon.Geometries = new ArrayList<Polygon>();
        multiPolygon.Geometries.add(unionPolygon);
        
        AppSettings appSetting=new AppSettings();
    	String geoWKID=appSetting.getGeoWKID();
    	String ProjWKID=appSetting.getProjWKID();
        MultiPolygon projectPolygon = SpotArcGISHelper.projectAction(StrUtil.trim(geometryServer, "/"), multiPolygon, geoWKID,ProjWKID);

        if (projectPolygon == null)
            return 0;
 
        String url = StrUtil.trim(geometryServer, "/") + "/areasAndLengths";
 
        String coordinates=JSONObject.toJSONString(projectPolygon.Geometries);

        ArcGISAreaLengthParam param = new ArcGISAreaLengthParam(coordinates); 
        String uri = StrUtil.trim(url, "/");
         
        ArcGISAreaAndLengthsResult areas = null;
        try {
		      String result = HttpHelper.sendPost(uri, param.toString());
		      areas = JSONObject.parseObject(result, ArcGISAreaAndLengthsResult.class);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
        return Double.parseDouble(df.format(Linq.firstOrDefault(areas.Areas)/666));
    }  
}
