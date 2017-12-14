package phalaenopsis.satellitegraph.utils.ArcGISHelper;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSON;

import phalaenopsis.satellitegraph.entity.MultiPolygon;
import phalaenopsis.satellitegraph.entity.Polygon;

/**
 * 采用 ArcGIS GeometryServer Difference 方式 获取除相交部分外的部分的请求参数
 */
public class ArcGISDifferenceParam extends ArcGISRequestParam_SD {
	private MultiPolygon polygons;

	/**
	 * 用于做外相交查询的图形
	 */
	private String _geometry = "";

	public ArcGISDifferenceParam(Polygon polygon) {
		polygons = new MultiPolygon();
		polygons.geometryType = "esriGeometryPolygon";

		String rings = JSON.toJSONString(polygon.getRings());

		_geometry = "{" + "geometryType" + ":" + "esriGeometryPolygon" + "," + "geometry" + ":{" + "rings" + ":" + rings
				+ "}}";
	}

	public void AddPolygon(Polygon polygon) {
		polygons.Geometries.add(polygon);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{" + "geometryType" + ":" + "esriGeometryPolygon" + "," + "geometries" + ":[");
		for (int i = 0; i < polygons.getGeometries().size(); i++) {
			Polygon polygon = polygons.getGeometries().get(i);
			String rings = JSON.toJSONString(polygon.getRings());
			sb.append("{" + "rings" + ":" + rings + "}");
			sb.append(",");
		}
		sb.delete(1, sb.length());
		sb.append("]}");
		String geometries = sb.toString();

		return "f=" + _f + "&sr=" + _inSR + "&geometry=" + _geometry + "&geometries=" + geometries;
	}

	public List<NameValuePair> toList() {
		StringBuilder sb = new StringBuilder();
		sb.append("{" + "geometryType" + ":" + "esriGeometryPolygon" + "," + "geometries" + ":[");
		for (int i = 0; i < polygons.getGeometries().size(); i++) {
			Polygon polygon = polygons.getGeometries().get(i);
			String rings = JSON.toJSONString(polygon.getRings());
			sb.append("{" + "rings" + ":" + rings + "}");
			sb.append(",");
		}
		sb.delete(1, sb.length());
		sb.append("]}");
		String geometries = sb.toString();

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("f", _f));
		params.add(new BasicNameValuePair("sr", _inSR));
		params.add(new BasicNameValuePair("geometry", _geometry));
		params.add(new BasicNameValuePair("geometries", geometries));
		return params;
	}
}
