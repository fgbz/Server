/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.common.service.analysis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import phalaenopsis.common.entity.AppSettings;
import phalaenopsis.common.entity.TwoTuple;
import phalaenopsis.common.entity.analysis.AnalysisColumnItem;
import phalaenopsis.common.entity.analysis.AnalysisRowItem;
import phalaenopsis.common.entity.analysis.FeatureSet;
import phalaenopsis.common.entity.analysis.Field;
import phalaenopsis.common.entity.analysis.FieldItem;
import phalaenopsis.common.entity.analysis.Graphic;
import phalaenopsis.common.method.Tools.StrUtil;
import phalaenopsis.satellitegraph.entity.MultiPolygon;
import phalaenopsis.satellitegraph.entity.Polygon;
import phalaenopsis.satellitegraph.utils.ArcGISHelper.ArcGISAreaAndLengthsResult;
import phalaenopsis.satellitegraph.utils.ArcGISHelper.ArcGISAreaLengthParam;
import phalaenopsis.satellitegraph.utils.ArcGISHelper.ArcGISIntersectParam;
import phalaenopsis.satellitegraph.utils.ArcGISHelper.ArcGISQueryParam;
import phalaenopsis.satellitegraph.utils.ArcGISHelper.HttpHelper;
import phalaenopsis.satellitegraph.utils.ArcGISHelper.SpotArcGISHelper;

/**
 * @author yangw786
 *
 * 2017年5月5日下午4:12:42
 * 采用ArcGIS图层中Query方法进行分析
 */
public class ArcgisQueryBehavior extends BaseAnalysisBehavior {

	private String method = "query";

    private String geometryServer = ""; 
     
	@Override
	public void initBehaviorParam(Map<String, Object> param) {
		   geometryServer = (String)param.get("GeometryServer");
	}
	
	@Override
	public List<AnalysisRowItem> getAnalysisItems(List<Field> fields, String url, Polygon polygon,
			List<TwoTuple<String, Object>> statisticField, String geometryServer) {
		List<AnalysisRowItem> results = new ArrayList<AnalysisRowItem>();

        FeatureSet responseFeature = getFeatureSet(fields, url, polygon);
        // 没有相交的要素
        if (responseFeature.features == null || responseFeature.features.size() == 0) {
            AnalysisRowItem rowItem = getEmptyRowItem(responseFeature.fields, fields);
            results.add(rowItem);
            return results;
        } else {
            ArcGISIntersectParam intersectParam = getRowItemAndIntersectParam(fields, polygon, results, responseFeature);

            MultiPolygon multiPolygon = getIntersectGeometry(intersectParam);
            //获取相交坐标串为空时代表没有交集，对应数据属性结果不传递到前端
            //int size=multiPolygon.Geometries
              //Linq.extEqualsList(multiPolygon.Geometries);
              
              //for()
            List<Polygon> Geometries=multiPolygon.Geometries;
            List<Polygon> newGeometries=new LinkedList<Polygon>();
            for(Polygon p: Geometries){
            	int size=p.getRings().size();
            	if(size>0){
            		newGeometries.add(p);
            	}
            }
              
           // if (multiPolygon.Geometries.size() > 0 && multiPolygon.Geometries.Where(g=>g.Rings.Count>0).Count() > 0)
            if (multiPolygon.Geometries.size() > 0 && newGeometries.size() > 0)
            {

                ArcGISAreaAndLengthsResult areaAndLength = getArea(multiPolygon);
                if (areaAndLength != null)
                {
                    int index = 0;
                    for (AnalysisRowItem rowItem : results)
                    {
                        rowItem.Area = areaAndLength.getAreas().get(index) / 666;
                        rowItem.Coordinates = multiPolygon.getGeometries().get(index);

                        index++;
                    }
                }
            } else {
                results.clear();
                AnalysisRowItem rowItem = getEmptyRowItem(responseFeature.fields, fields);
                results.add(rowItem);
            }
        }
        
		return results;
	}
	
	
    /**
     * 填充数据属性结果，并获取用于做相交分析的参数，通过此参数来获取相交的面积与坐标串
     * @param fields
     * @param polygon
     * @param results
     * @param responseFeature
     * @return
     */
    private ArcGISIntersectParam getRowItemAndIntersectParam(List<Field> fields, Polygon polygon, 
    		List<AnalysisRowItem> results, FeatureSet responseFeature) {
        ArcGISIntersectParam intersectParam = new ArcGISIntersectParam(polygon);

        for (Graphic graphic : responseFeature.getFeatures()) {
            AnalysisRowItem rowItem = new AnalysisRowItem();
            rowItem.Columns = new ArrayList<AnalysisColumnItem>();
            if (fields.size() == 0) {
				for (Map.Entry<String, Object> kv : graphic.getAttributes().entrySet()) {
					AnalysisColumnItem item = new AnalysisColumnItem();
					item.Name = kv.getKey();
					item.Value = kv.getValue(); 
					rowItem.Columns.add(item);
				}
			} else {
                for (Field field : fields) {
                    AnalysisColumnItem item = new AnalysisColumnItem();
                    item.Name = field.Alias;
                    if (graphic.attributes.containsKey(field.Name)) {
                        item.Value = graphic.attributes.get(field.Name);
                    } 
                    rowItem.Columns.add(item);
                }
            }

            intersectParam.AddPolygon(graphic.getGeometry());
            results.add(rowItem);
        }
        return intersectParam;
    }

    /**
     * 获取存在交集的要素
     * @param fields
     * @param url
     * @param polygon
     * @return
     */
	private FeatureSet getFeatureSet(List<Field> fields, String url, Polygon polygon) {
        ArcGISQueryParam param = null;
        if (fields != null && fields.size() > 0) {
        	String f = "";
        	for (Field field : fields) {
				f += field.Name;
				f += ",";
			}  
        	if(f.length() > 1) { 
        		f = f.substring(0, f.length() - 1);
        	}
            param = new ArcGISQueryParam(f, polygon);
        }
        else {
            param = new ArcGISQueryParam("*", polygon);
        }

        // 以下 get请求会有字符串超长的情况
        //string uri = url.Trim('/') + "/" + _method + "?" + param.ToString();
        //string remoteArcgisResult = HttpCommunicator.GetJson<string>(uri);

        String uri = StrUtil.trim(url, "/") + "/" + method; 
        String result = HttpHelper.sendPost(uri, param.toString());
        
        FeatureSet response = null;
		try { 
			response = JSON.toJavaObject(JSON.parseObject(result), FeatureSet.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
         
        return response;
    }
	
	/*protected AnalysisRowItem getEmptyRowItem(List<FieldItem> alias, List<Field> fields) {
        AnalysisRowItem rowItem = new AnalysisRowItem();
        rowItem.Columns = new ArrayList<AnalysisColumnItem>();
        if (fields.size() > 0) {
        	for (Field field : fields) {
				AnalysisColumnItem item = new AnalysisColumnItem();
                item.Name = field.Alias;
                item.Value = "";
                rowItem.Columns.add(item);
            }
        }
        else {
        	for (FieldItem fieldItem : alias) {
        		AnalysisColumnItem item1 = new AnalysisColumnItem();
                item1.Name = fieldItem.Alias;
                item1.Value = "";
                rowItem.Columns.add(item1);
			} 
        }
        return rowItem;
    }*/
	
	/// <summary>
    /// 如果没有相交的要素，获取返回用于界面显示的空对象集合
    /// </summary>
    /// <param name="alias">服务返回的图层属性字段集合</param>
    /// <param name="fields">配置的字段集合</param>
    /// <returns></returns>
    protected AnalysisRowItem getEmptyRowItem(List<FieldItem> alias, List<Field> fields)
    {
        AnalysisRowItem rowItem = new AnalysisRowItem();
        rowItem.Columns = new ArrayList<AnalysisColumnItem>();
        if (fields.size() > 0)
        {
            for (Field field : fields)
            {
                AnalysisColumnItem item = new AnalysisColumnItem();
                item.Name = field.Alias;
                item.Value = "";
                rowItem.Columns.add(item);
            }
        }
        else
        {
           /* alias.ForEach(field =>
            {
                AnalysisColumnItem item1 = new AnalysisColumnItem();
                item1.Name = field.Alias;
                item1.Value = string.Empty;
                rowItem.Columns.Add(item1);
            });*/
            
            for(FieldItem fieldItem:alias){
            	AnalysisColumnItem item1 = new AnalysisColumnItem();
            	item1.Name = fieldItem.alias;
                item1.Value = "";
                rowItem.Columns.add(item1);
            }
        }
        return rowItem;
    }
	
	 /// <summary>
    /// 获取相交的坐标串
    /// </summary>
    /// <param name="intersectParam"></param>
    /// <returns></returns>
    private MultiPolygon getIntersectGeometry(ArcGISIntersectParam intersectParam) {
    	//string url = string.Format(@"{0}/intersect", _geometryServer.Trim('/'));
       // String url = string.Format(@"{0}/intersect",geometryServer.trim());
        
        String url = StrUtil.trim(geometryServer, "/") + "/intersect";

        //string uri = url.Trim('/') + "/?" + intersectParam.ToString();
       // String uri = url.Trim('/');
        String uri = StrUtil.trim(url, "/");

        //MultiPolygon multiPolygon = HttpCommunicator.GetJson<MultiPolygon>(uri);
        //请求数据类型
        //  HttpCommunicator http = new HttpCommunicator(Nefarian.Utility.HttpCommunicator.ContentType.Form);
        // MultiPolygon multiPolygon = http.Post<MultiPolygon>(uri, intersectParam.ToString());
        
        /*MultiPolygon multiPolygon=null;
        String result=HttpUtil.httpClientPostJson(uri, intersectParam.toString());  
        JSONObject json = JSON.parseObject(result);
		multiPolygon = JSON.toJavaObject(json, MultiPolygon.class);*/
         
		MultiPolygon multiPolygon = null;
		try {
			String result = HttpHelper.sendPost(uri, intersectParam.toString());
			multiPolygon = JSON.toJavaObject(JSON.parseObject(result), MultiPolygon.class);
		} catch (ParseException e) {
			e.printStackTrace();
		} 

        return multiPolygon;
    }
 
	/**
	 * 填充数据属性结果，并获取用于做相交分析的参数，通过此参数来获取相交的面积与坐标串
	 * @param fields
	 * @param polygon
	 * @param results
	 * @param responseFeature
	 * @return
	 */
    private ArcGISIntersectParam GetRowItemAndIntersectParam(List<Field> fields, Polygon polygon, List<AnalysisRowItem> results, FeatureSet responseFeature) {
        ArcGISIntersectParam intersectParam = new ArcGISIntersectParam(polygon);

    	for(Graphic graphic : responseFeature.getFeatures()) {
            AnalysisRowItem rowItem = new AnalysisRowItem();
            rowItem.Columns = new ArrayList<AnalysisColumnItem>();
            if (fields.size() == 0)
            {
                for (Map.Entry<String,Object> kv : graphic.getAttributes().entrySet())
                {
                    AnalysisColumnItem item = new AnalysisColumnItem();
                    item.Name = kv.getKey();
                    item.Value = kv.getValue();
                    
                    rowItem.Columns.add(item);
                }
            }
            else
            {
                for (Field field : fields)
                {
                    AnalysisColumnItem item = new AnalysisColumnItem();
                    item.Name = field.Alias;
                    if (graphic.attributes.containsKey(field.Name))
                    {
                        item.Value = graphic.attributes.get(field.Name);
                    }

                    rowItem.Columns.add(item);
                }
            }

            intersectParam.AddPolygon(graphic.getGeometry());

            results.add(rowItem);
        }
        return intersectParam;
    }
    
    /// <summary>
    /// 获取相交的面积
    /// </summary>
    /// <param name="polygon"></param>
    /// <returns></returns>
    public ArcGISAreaAndLengthsResult getArea(MultiPolygon polygon)
    {
      //  MultiPolygon projectPolygon = SpotArcGISHelper.ProjectAction(_geometryServer.Trim('/'), polygon, ConfigurationManager.AppSettings["GeoWKID"], ConfigurationManager.AppSettings["ProjWKID"]);

    	AppSettings appSetting=new AppSettings();
    	String geoWKID=appSetting.getGeoWKID();
    	String ProjWKID=appSetting.getProjWKID();
        MultiPolygon projectPolygon = SpotArcGISHelper.projectAction(StrUtil.trim(geometryServer, "/"), polygon, geoWKID,ProjWKID);

        if (projectPolygon == null){
            return null;
        }   
            
       //string url = string.Format(@"{0}/areasAndLengths", _geometryServer.Trim('/'));
        String url = StrUtil.trim(geometryServer, "/") + "/areasAndLengths";


       // String coordinates = JsonConvert.SerializeObject(projectPolygon.Geometries);//对象序列化json
        String coordinates = JSONObject.toJSONString(projectPolygon.Geometries);

        ArcGISAreaLengthParam param = new ArcGISAreaLengthParam(coordinates);

        //string uri = url.Trim('/') + "/?" + param.ToString();
        //ArcGISAreaAndLengthsResult areas = HttpCommunicator.GetJson<ArcGISAreaAndLengthsResult>(uri);

        String uri = StrUtil.trim(url, "/");
        /*HttpCommunicator http = new HttpCommunicator(Nefarian.Utility.HttpCommunicator.ContentType.Form);
        ArcGISAreaAndLengthsResult areas = http.Post<ArcGISAreaAndLengthsResult>(uri, param.ToString());*/
        ArcGISAreaAndLengthsResult areas = null;
        try {
		        HttpEntity httpEntity = HttpHelper.HttpPost(uri, param.toList());        			
				JSONObject json;				
				json = JSON.parseObject(EntityUtils.toString(httpEntity));
				areas = JSON.toJavaObject(json, ArcGISAreaAndLengthsResult.class);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
        
        
        //ArcGISAreaAndLengthsResult areas=null;
        //String result=HttpUtil.httpClientPostJson(uri, param.toString());  
       // JSONObject json = JSON.parseObject(result);
        //areas = JSON.toJavaObject(json, ArcGISAreaAndLengthsResult.class);

        return areas;
    }
}
