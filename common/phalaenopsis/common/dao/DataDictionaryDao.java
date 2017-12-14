package phalaenopsis.common.dao;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;
//import com.vividsolutions.jts.io.geojson.GeoJsonWriter;
import com.vividsolutions.jts.io.gml2.GMLReader;

import phalaenopsis.common.entity.CRS;
import phalaenopsis.common.entity.DataDictionaryItem;
import phalaenopsis.common.entity.Features;
import phalaenopsis.common.entity.GeoJsonMap;
import phalaenopsis.common.entity.Point;
import phalaenopsis.common.entity.Properties;
import phalaenopsis.common.entity.Region;
import phalaenopsis.common.entity.Region4GML;
import phalaenopsis.common.entity.User;
import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.DouglasPeucker;
import phalaenopsis.common.method.cache.RegionMapCache;

@Repository("dataDataDictionary")
public class DataDictionaryDao extends Basis {

	@Resource
	private SqlSession sqlSession;

	/**
	 * 获取所有数据字典
	 * 
	 * @return
	 */
	public List<DataDictionaryItem> getAllDictionaries() {
		return sqlSession.selectList("datadictionary.getAllDictionaries");
	}

	public List<Region> getAllRegions() {
		return sqlSession.selectList("datadictionary.getAllRegions");
	}

	public List<Region> getRegions(boolean authorized) {
		return sqlSession.selectList("datadictionary.getRegions");
	}

	public List<Region> getCityRegions() {
		return sqlSession.selectList("datadictionary.getCityRegions");
	}

	/**
	 * 
	 * 
	 * @param cityCode
	 * @return
	 */
	public List<Region> getCountyRegionsByCity(int cityCode) {
		List<Region> result = null;
		if (0 == cityCode)
			result = new ArrayList<Region>();
		else {
			result = sqlSession.selectList("datadictionary.getCountyRegionsByCity", cityCode);
		}
		return result;
	}

	public List<Region> getOrganRegion(List<Region> regions) {
		List<Region> result = new ArrayList<Region>();
		for (Region reg : regions) {
			int regionId = reg.RegionID;

			if (reg.regionType == 1) {
				regionId = (int) reg.RegionID / 10;
			}

			Region r = sqlSession.selectOne("datadictionary.getOrganRegion", regionId);
			if (null == r && r.RegionID > 0) {
				result.add(r);
			}
		}

		return result;
	}

	public List<Region> GetRegionsByIDs(int[] ids) {
		if (0 == ids.length) {
			return new ArrayList<Region>();
		}

		return sqlSession.selectList("datadictionary.getRegionsByIDs", ids);
	}

	public GeoJsonMap getRegionMap(String regiontype) {
		User currentUser = getCurrentUser();
		GeoJsonMap geoJsonMap = null;
		geoJsonMap = (GeoJsonMap) RegionMapCache.getRegionMap(currentUser, regiontype);
		
		if(null != geoJsonMap) 
			return geoJsonMap;
		
		List<Region4GML> regions = new ArrayList<Region4GML>();
		List<String> countyReg = new ArrayList<String>();   //regionId
		List<String> cityReg = new ArrayList<String>(); 		//ParentId
		List<String> regName = new ArrayList<String>();       //RegionName
		
		for (Region region : currentUser.regions) {
			countyReg.add(Integer.toString(region.RegionID));
			cityReg.add(Integer.toString(region.ParentID));
			regName.add(region.RegionName);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("OrgGrade", currentUser.OrgGrade);
		map.put("countyReg", countyReg);
		map.put("cityReg", cityReg);
		map.put("cityRegion", currentUser.regions[0].ParentID); 
		map.put("regiontype", regiontype);
		map.put("regName", regName);

		regions = sqlSession.selectList("datadictionary.getRegionMap", map);

		geoJsonMap = GMLToGeoJson(regions);
		RegionMapCache.setRegionMap(currentUser, regiontype, geoJsonMap);
		
		return geoJsonMap;
	}

	private GeoJsonMap GMLToGeoJson(List<Region4GML> source) {
		GeoJsonMap result = new GeoJsonMap();
		result.type = "FeatureCollection";
		CRS crs = new CRS();
		crs.type = "name";
		result.crs = crs;
		result.features = new ArrayList<Features>();

		for (Region4GML child : source) {
			Features feature = new Features();
			feature.type = "Feature";
			feature.id = child.code;

			Properties p = new Properties();
			p.id = child.code;
			p.name = child.name;
			p.hc_middle_x = 0.56;
			p.hc_middle_y = 0.44;

			p.cp = new ArrayList<Double>();
			feature.properties = p;

//			try {
//
//				String gmlStr = new String(child.gML);
//				gmlStr = GmlPointReduction(gmlStr);
//
//				//gmlStr = GmlPointReduction(gmlStr);
//				GMLReader gmlGeo = new GMLReader();
//				Geometry GMLGeoData = gmlGeo.read(gmlStr, new GeometryFactory());
//
//				GeoJsonWriter writer = new GeoJsonWriter();
//				String GeoJsonStr = writer.write(GMLGeoData);
//
//				if (GMLGeoData instanceof Polygon) {
//					Coordinate[] corr = ((Polygon) GMLGeoData).getCoordinates();
//					List<Double> doubles = new ArrayList<Double>();
//					doubles.add(corr[0].x);
//					doubles.add(corr[0].y);
//					feature.properties.cp = doubles;
//				} else if (GMLGeoData instanceof MultiPolygon) {
//					Coordinate[] corr = ((MultiPolygon) GMLGeoData).getCoordinates();
//					List<Double> doubles = new ArrayList<Double>();
//					doubles.add(corr[0].x);
//					doubles.add(corr[0].y);
//					feature.properties.cp = doubles;
//				} else if (GMLGeoData instanceof LineString) {
//					Coordinate[] corr = ((LineString) GMLGeoData).getCoordinates();
//					List<Double> doubles = new ArrayList<Double>();
//					doubles.add(corr[0].x);
//					doubles.add(corr[0].y);
//					feature.properties.cp = doubles;
//				} else if (GMLGeoData instanceof MultiLineString) {
//					Coordinate[] corr = ((MultiLineString) GMLGeoData).getCoordinates();
//					List<Double> doubles = new ArrayList<Double>();
//					doubles.add(corr[0].x);
//					doubles.add(corr[0].y);
//					feature.properties.cp = doubles;
//
//				} else {
//
//				}
//
//				feature.geometry = GeoJsonStr;
//				result.features.add(feature);
//
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (SAXException | ParserConfigurationException e1) {
//				e1.printStackTrace();
//			}
		}
 
		return result;
	}

	private String GmlPointReduction(String gml) {
		String result = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			InputStream iStream = new ByteArrayInputStream(gml.getBytes());
			Document document = builder.parse(iStream);
			NodeList returnNodes = document.getElementsByTagName("gml:coordinates");
			String pointStr = "";
			String newPointStr = "";
			if (returnNodes.getLength() > 0) {
				pointStr = returnNodes.item(0).getTextContent();
				String[] spStrings = pointStr.split(" ");
				List<String> pointList = Arrays.asList(spStrings);
				if (pointList.size() > 2) {
					//pointList.remove(pointList.size() - 1);
					
					List<Point> points = new ArrayList<Point>();
					for (int i = 0; i < pointList.size() - 1; i++) {
						String[] singlepoint = pointList.get(i).split(",");
						Point p = new Point();
						p.x = Double.parseDouble(singlepoint[0]);
						p.y = Double.parseDouble(singlepoint[1]);
						points.add(p);
					}
					
					

					List<Point> resPoint = DouglasPeucker.reduction(points, 0.02);
					
					
					
					resPoint.add(resPoint.get(0));
					List<String> liststr = new ArrayList<String>();
					for (Point item : resPoint) {
						String temp = item.x + "," + item.y;
						liststr.add(temp);
					}

					for (int i = 0; i < liststr.size(); i++) {
						if (i < liststr.size() - 1) {
							newPointStr += liststr.get(i) + " ";
						} else {
							newPointStr += liststr.get(i);
						}
					}
				} else {
					newPointStr = returnNodes.item(0).getTextContent();
				}
			}

			result = gml.replace(pointStr, newPointStr);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean saveDictionary(DataDictionaryItem item) {
		return sqlSession.insert("datadictionary.saveDictionary", item) > 0;
	}

	public boolean deleteDictionary(List<Long> ids) {
		return  sqlSession.delete("datadictionary.deleteDictionary", ids) >0;
	}

	public List<DataDictionaryItem> listDataDictionary(Map<String, Object> map) {
		return  sqlSession.selectList("datadictionary.listDataDictionary", map);
	}

	public int countDataDictionary(Map<String, Object> map) {
		return sqlSession.selectOne("datadictionary.countDataDictionary", map);
	}

	public int getMaxIdPlus() {
		return sqlSession.selectOne("datadictionary.getMaxIdPlus");
	}
}
