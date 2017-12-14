/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.common.dao;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import phalaenopsis.common.entity.AppSettings;
import phalaenopsis.common.entity.TwoTuple;
import phalaenopsis.common.entity.analysis.AnalysisColumnItem;
import phalaenopsis.common.entity.analysis.AnalysisLayer;
import phalaenopsis.common.entity.analysis.AnalysisLayerGroup;
import phalaenopsis.common.entity.analysis.AnalysisResult;
import phalaenopsis.common.entity.analysis.AnalysisRowItem;
import phalaenopsis.common.entity.analysis.DisplayType;
import phalaenopsis.common.entity.analysis.Field;
import phalaenopsis.common.entity.analysis.SpotAnalysisResult;
import phalaenopsis.common.method.Tools.Linq;
import phalaenopsis.common.method.Tools.StrUtil;
import phalaenopsis.common.method.Tools.UUID64;
import phalaenopsis.common.service.analysis.QueryConvertBehavior;
import phalaenopsis.satellitegraph.entity.MultiPolygon;
import phalaenopsis.satellitegraph.entity.Polygon;
import phalaenopsis.satellitegraph.utils.ArcGISHelper.ArcGISAreaAndLengthsResult;
import phalaenopsis.satellitegraph.utils.ArcGISHelper.ArcGISAreaLengthParam;
import phalaenopsis.satellitegraph.utils.ArcGISHelper.HttpHelper;
import phalaenopsis.satellitegraph.utils.ArcGISHelper.SpotArcGISHelper;

/**
 * @author yangw786
 *
 * 2017年5月4日下午5:14:36
 */
@Repository("analysisDao")
public class AnalysisDao {
	@Resource
	private SqlSession sqlSession;
	
	@SuppressWarnings("unchecked")
	public List<AnalysisResult> analysis(Map<String,Object> map) {
		DecimalFormat df = new DecimalFormat("#.00"); 
		
		List<AnalysisResult> results = new ArrayList<AnalysisResult>();
		Polygon polygon = new Polygon(); 
		
		Map<String,Object> mapPolygon = (Map<String, Object>) map.get("polygon");
		polygon.setRings((List<ArrayList<Double[]>>) mapPolygon.get("rings"));
	  
		AnalysisLayerGroup analysisLayerGroup = null;

		// step one: 根据业务类型和终端类型，获取需要分析的图层集合
		List<AnalysisLayerGroup> tempGroup = sqlSession.selectList("analysisMapper.queryAnalysisLayerGroup", map);
		if (tempGroup != null && tempGroup.size() > 0) {
			analysisLayerGroup = tempGroup.get(0);
		}

		// step two: 根据获取到的图层集合进行叠加分析
		if (analysisLayerGroup != null) {
			Map analysisParam = new HashMap<String, Object>();
			analysisParam.put("GeometryServer", analysisLayerGroup.GeometryServer);
			double spotArea = getSpotArea(analysisLayerGroup.GeometryServer, polygon);

			for (AnalysisLayer layer : analysisLayerGroup.Layers) {
				AnalysisResult resultItem = new AnalysisResult();
				
				resultItem.LayerAttributes.LayerName = layer.Name;
				resultItem.LayerAttributes.LayerUrl = layer.Url;
				if (!StrUtil.isNullOrEmpty(layer.MappingUrl)) {
					resultItem.LayerAttributes.LayerMappingUrl = layer.MappingUrl;
				}
				resultItem.LayerAttributes.LayerType = layer.Type;
				List<TwoTuple<String, Object>> statisticField = new ArrayList<TwoTuple<String, Object>>();

				QueryConvertBehavior behavior = new QueryConvertBehavior();
				behavior.initBehaviorParam(analysisParam);
				behavior.isNeedUnion = layer.IsNeedUnion; 
				resultItem.LayerName = layer.Alias;   

                if (layer.Star == Field.Star) {
                    resultItem.Items = behavior.getAnalysisItems(new ArrayList<Field>(), layer.Url, polygon, statisticField, analysisLayerGroup.GeometryServer);
                } else {
                    resultItem.Items = behavior.getAnalysisItems(layer.FieldCollection, layer.Url, polygon, statisticField, analysisLayerGroup.GeometryServer);
                }
                // 返回列头的名称
				if (resultItem.Items != null && resultItem.Items.size() > 0) {
					resultItem.ColumnNames = Linq.extSelect(resultItem.Items.get(0).Columns, "Name");
				}
                if (behavior.isNeedUnion) {
                    if (analysisLayerGroup.DisplayType != DisplayType.List) {
                        for (TwoTuple<String, Object> item : statisticField) {
                            resultItem.MaxDisplayColumn.add(new AnalysisColumnItem( "套合面积最大" + item.first,item.second ));
                            resultItem.ColumnNames.add("套合面积最大" + item.first);
                        } 

						double sumItemArea = 0.0; // resultItem.Items.Sum(it => it.Area)
						for (AnalysisRowItem items : resultItem.Items) {
							sumItemArea += items.Area;
						}
                        resultItem.MaxDisplayColumn.add(new AnalysisColumnItem("套合面积(亩)",resultItem.Items.size() > 0 && resultItem.Items.get(0).Coordinates != null ? Math.abs(Double.parseDouble(df.format(sumItemArea)))+"" : null ));
                        resultItem.MaxDisplayColumn.add(new AnalysisColumnItem("套合比例",resultItem.Items.size() > 0 && resultItem.Items.get(0).Coordinates != null ? Math.abs(Double.parseDouble(df.format((sumItemArea * 666) / spotArea * 100))) + "%" : null ));
                        
                        if (Math.abs(Double.parseDouble(df.format((sumItemArea * 666) / spotArea * 100))) > 100) {
                            //resultItem.MaxDisplayColumn.Last().Value = "100%";
                            resultItem.MaxDisplayColumn.get(resultItem.MaxDisplayColumn.size()-1).Value = "100%";
                        }

                        //暂时将综合统计项放在每个图层分析结果的第一条记录中，便于综合统计项与其他字段横向排列
                        resultItem.ColumnNames.add("套合面积(亩)");
                        resultItem.ColumnNames.add("套合比例");
                        if (resultItem.Items.size() > 0) {
                            //resultItem.Items.get(0).Columns.AddRange(resultItem.MaxDisplayColumn);
                            resultItem.Items.get(0).Columns.addAll(resultItem.MaxDisplayColumn);
                        }
                    } else {
                    	resultItem.ColumnNames.add("所占面积(亩)");
                        resultItem.ColumnNames.add("所占比例");
                        if (resultItem.Items != null) {
                            double totalArea = 0.0;
                            double totalRatio = 0.0;
                            for (AnalysisRowItem row : resultItem.Items) {
								row.Columns.add(
										new AnalysisColumnItem("所占面积(亩)", Double.parseDouble(df.format(row.Area))));
								row.Columns.add(new AnalysisColumnItem("所占比例",
										Math.abs(Double.parseDouble(df.format(row.Area * 666 / spotArea * 100)))));
								if (Math.abs(Double.parseDouble(df.format(row.Area * 666 / spotArea * 100))) > 100) {
									// row.Columns.Last().Value = "100%";
									row.Columns.get(row.Columns.size() - 1).Value = "100";
								}
                                totalArea += Math.abs(Double.parseDouble(df.format(row.Area)));
                                totalRatio += Math.abs(Double.parseDouble(df.format(row.Area * 666 / spotArea * 100)));
                            }
                            //AnalysisRowItem lastItem = resultItem.Items.Last();
							AnalysisRowItem lastItem = resultItem.Items.get(resultItem.Items.size() - 1);
                            //默认分析项面积和占图斑总面积的比例大于99.9%时表示图斑完全在分析图层地块之内，最后一项的面积和比例通过减法来计算
                             
                            double sumItemArea666 = 0.0; //resultItem.Items.Sum(it => it.Area * 666) 
							for (AnalysisRowItem items : resultItem.Items) {
								sumItemArea666 = sumItemArea666 + items.Area * 666;
							}
                            
                            if (sumItemArea666 / spotArea >= 0.999) {
                                totalArea = totalArea - Math.abs(Double.parseDouble(df.format(lastItem.Area)));
                                totalRatio = totalRatio - Math.abs(Double.parseDouble(df.format(lastItem.Area * 666 / spotArea * 100)));
                                //Math.round(spotArea / 666, 2) - totalArea, 2)
                                lastItem.Columns.get(lastItem.Columns.size() - 2).Value = Math.abs(Double.parseDouble(df.format((Double.parseDouble(df.format(spotArea / 666)) - totalArea))));
                                //Math.round(spotArea / 666, 2) - totalArea, 2)
                                //[lastItem.Columns.Count - 1]
                                lastItem.Columns.get(lastItem.Columns.size() - 1).Value = Double.parseDouble(df.format(100 - totalRatio));
                                if (Double.parseDouble(df.format(100 - totalRatio)) > 100) {
                                    lastItem.Columns.get(lastItem.Columns.size() - 1).Value = "100";
                                }
                            }
                            
							List<AnalysisRowItem> removeItems = new ArrayList<AnalysisRowItem>();
							for (AnalysisRowItem item : resultItem.Items) {
								List<AnalysisColumnItem> Columns = item.getColumns();
								String val = Columns.get(Columns.size() - 1).Value.toString();
								if ("0".equals(val)) {
									removeItems.add(item);
								}
							}

							if (removeItems != null) {
								for (int i = 0; i < removeItems.size(); i++) {
									resultItem.Items.remove(removeItems.get(i));
								}
							} 
						}
					}
				}
				resultItem.SpotArea = Math.abs(spotArea);
				results.add(resultItem);
			}
		} else {

			throw new NotImplementedException("相应业务下，找不到对应终端 图斑分析配置模型");
		}
		return results;
	}
	
	/**
	 * 该方法暂时未调用
	 * 根据行政区划名称获取其位置数据
	 * @author chiz
	 *
	 * */
	public Polygon getRegeionShape(String regionName) {
		// String url = System.Configuration.ConfigurationManager.AppSettings["QuyuJiedaoService"];
		 
		String url = "";
        /* if (string.IsNullOrEmpty(url))
             return null;*/
         if(StringUtils.isBlank(url)){
        	 return null;
		 }

         StringBuilder builder = new StringBuilder();
         builder.append("f=json");
         builder.append("&returnGeometry=true");
         String regionQu = regionName.contains("_") ? regionName.split("_")[0] : regionName;
         String regionJie = regionName.contains("_") ? regionName.split("_")[1] : "";
         
         
        /* if (string.IsNullOrEmpty(regionJie))
         {
             url += "/0/query?";
             builder.Append("&outFields=SSQ");
             builder.Append("&where=SSQ = '" + regionQu + "'");
         }
         else
         {
             url += "/1/query?";
             builder.Append("&outFields=SSQ,SSJ");
             builder.Append("&where=SSQ='" + regionQu + "'and SSJ ='" + regionJie + "'");
         }*/
         
         if(StringUtils.isBlank(regionJie)){
        	 url += "/0/query?";
             builder.append("&outFields=SSQ");
             builder.append("&where=SSQ = '" + regionQu + "'");
		 }else{
			 url += "/1/query?";
             builder.append("&outFields=SSQ,SSJ");
             builder.append("&where=SSQ='" + regionQu + "'and SSJ ='" + regionJie + "'");	 
		 }
         

         builder.append("&spatialRel=esriSpatialRelIntersects");
         builder.append("&outSR={\"wkt\":\"PROJCS[\\\"Transverse Mercator\\\",GEOGCS[\\\"Transverse Mercator\\\",DATUM[\\\"Krasovsky\\\",SPHEROID[\\\"Krasovsky\",6378245.0,298.3]],PRIMEM[\\\"Greenwich\",0.0],UNIT[\\\"Degree\\\",0.0174532925199433]],PROJECTION[\\\"Gauss_Kruger\\\"],PARAMETER[\\\"False_Easting\\\",500000.0],PARAMETER[\\\"False_Northing\\\",-3000000.0],PARAMETER[\\\"Central_Meridian\\\",114.0],PARAMETER[\\\"Scale_Factor\\\",1.0],PARAMETER[\\\"Latitude_Of_Origin\\\",0.0],UNIT[\\\"Meter\\\",1.0]]\"}");
         //string param = "f=json&returnGeometry=true&outFields=SSQ&where=SSQ%20%3D%20%27%E7%BB%8F%E6%B5%8E%E5%BC%80%E5%8F%91%E5%8C%BA%27&spatialRel=esriSpatialRelIntersects&outSR=%7B%22wkt%22%3A%22PROJCS%5B%5C%22Transverse%20Mercator%5C%22%2CGEOGCS%5B%5C%22Transverse%20Mercator%5C%22%2CDATUM%5B%5C%22Krasovsky%5C%22%2CSPHEROID%5B%5C%22Krasovsky%5C%22%2C6378245%2E0%2C298%2E3%5D%5D%2CPRIMEM%5B%5C%22Greenwich%5C%22%2C0%2E0%5D%2CUNIT%5B%5C%22Degree%5C%22%2C0%2E0174532925199433%5D%5D%2CPROJECTION%5B%5C%22Gauss_Kruger%5C%22%5D%2CPARAMETER%5B%5C%22False_Easting%5C%22%2C500000%2E0%5D%2CPARAMETER%5B%5C%22False_Northing%5C%22%2C-3000000%2E0%5D%2CPARAMETER%5B%5C%22Central_Meridian%5C%22%2C114%2E0%5D%2CPARAMETER%5B%5C%22Scale_Factor%5C%22%2C1%2E0%5D%2CPARAMETER%5B%5C%22Latitude_Of_Origin%5C%22%2C0%2E0%5D%2CUNIT%5B%5C%22Meter%5C%22%2C1%2E0%5D%5D%22%7D";
         //System.Web.HttpUtility.UrlEncode(regionName);
         String param = builder.toString();
         /*FeatureSet featureSet = HttpCommunicator.GetJson<FeatureSet>(url + param);

         if (featureSet != null && featureSet.Features.Count > 0)
         {
             Polygon result = featureSet.Features.First().Geometry;
             result.Type = "polygon";
             result.SpatialReference = featureSet.SpatialReference;
             return result;
         }*/

         return null;
	}
	
	/*public SpotAnalysisResult getAnalysisResult(String id, AnalysisSourceType source) {
		return null;
	}*/
	public SpotAnalysisResult getAnalysisResult(String id, int source) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("id",id);
		map.put("source", source);
		SpotAnalysisResult result=sqlSession.selectOne("analysisMapper.getAnalysisResult", map);
		return result;
	}

	public long saveAnalysisResult(SpotAnalysisResult anresult) {
		//判断是否已经保存过
		if(anresult.getId()==0){
			anresult.setId(UUID64.newUUID64().getValue());
		}
		anresult.setTiming(new Date());
		sqlSession.insert("analysisMapper.saveAnalysisResult",anresult);
		return  anresult.getId();
	}

	public boolean deleteAnalysisResult(long id) {
		int result=sqlSession.delete("analysisMapper.deleteAnalysisResult", id);
		return result>0?true:false;
	}
	private double getSpotArea(String geometry, Polygon polygon) {
        MultiPolygon multiPolygon = new MultiPolygon();
        multiPolygon.Geometries = new ArrayList<Polygon>();
        multiPolygon.Geometries.add(polygon);
        //去掉尾部'/'
        geometry = StrUtil.trim(geometry, "/");
        AppSettings appSettings = new AppSettings();
        MultiPolygon projectPolygon = SpotArcGISHelper.projectAction(geometry, multiPolygon, appSettings.getGeoWKID(), appSettings.getProjWKID());
      //  String url = String.format("{0}/areasAndLengths", geometry);
        String url=geometry+"/areasAndLengths";
           
        String coordinateJson = JSON.toJSONString(projectPolygon.Geometries);
        ArcGISAreaLengthParam param = new ArcGISAreaLengthParam(coordinateJson);
        
        HttpEntity httpEntity = HttpHelper.HttpPost(url, param.toList());
        ArcGISAreaAndLengthsResult areas = null;
		try {
			JSONObject json = JSON.parseObject(EntityUtils.toString(httpEntity));
			areas = JSON.toJavaObject(json, ArcGISAreaAndLengthsResult.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(areas != null && areas.Areas.size() > 0) { 
			 return areas.Areas.get(0);
		}
		return Double.NaN;
    }
}
