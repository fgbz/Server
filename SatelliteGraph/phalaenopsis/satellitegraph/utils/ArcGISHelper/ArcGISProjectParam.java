package phalaenopsis.satellitegraph.utils.ArcGISHelper;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSON;

import phalaenopsis.satellitegraph.entity.MultiPolygon;
import phalaenopsis.satellitegraph.entity.Polygon;

/**
 * 采用 ArcGIS GeometryServer Project 方式 对空间信息进行在不同坐标系的坐标转换
 */
public class ArcGISProjectParam extends ArcGISRequestParam_SD {
	private MultiPolygon polygons;

	private String _inSR = "";

	private String _outSR = "";

	public ArcGISProjectParam(MultiPolygon mPolygons, String inSR, String outSR) {
		polygons = mPolygons;
		_inSR = inSR;
		_outSR = outSR;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{" + "geometryType" + ":" + "esriGeometryPolygon" + "," + "geometries" + ":[");

		for (Polygon polygon : polygons.getGeometries()) {
			String rings = JSON.toJSONString(polygon.getRings());
			sb.append("{" + "rings" + ":" + rings + "}");
			sb.append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]}");
		String geometries = sb.toString();

		return "f=" + _f + 
				"&inSR=" + _inSR + 
				"&outSR=" + _outSR + 
				"&geometries=" + geometries;
	}
	
	public List<NameValuePair> toList() {
		StringBuilder sb = new StringBuilder();
		sb.append("{" + "\"geometryType\"" + ":" + "\"esriGeometryPolygon\"" + "," + "\"geometries\"" + ":[");

		for (Polygon polygon : polygons.getGeometries()) {
			String rings = JSON.toJSONString(polygon.getRings());
			sb.append("{" + "\"rings\"" + ":" + rings + "}");
			sb.append(",");
		}
		sb.deleteCharAt(sb.length() - 1); 
		sb.append("]}");
		String geometries = sb.toString();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("f", _f));
		params.add(new BasicNameValuePair("inSR", _inSR));
		params.add(new BasicNameValuePair("outSR", _outSR));
		params.add(new BasicNameValuePair("geometries", geometries));
		return params;
	}
}
