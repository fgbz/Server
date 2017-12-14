package phalaenopsis.satellitegraph.utils.ArcGISHelper;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSON;

import phalaenopsis.satellitegraph.entity.MultiPolygon;
import phalaenopsis.satellitegraph.entity.Polygon;

public class ArcGISUnionParam extends ArcGISRequestParam_SD {
	private MultiPolygon polygons;

	public ArcGISUnionParam(MultiPolygon unionPolygon) {
		polygons = unionPolygon;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{" + "geometryType" + ":" + "esriGeometryPolygon" + "," + "geometries" + ":[");

		for (Polygon polygon : polygons.getGeometries()) {
			String rings = JSON.toJSONString(polygon.getRings());
			sb.append("{" + "rings" + ":" + rings + "}");
			sb.append(",");
		}
		sb.delete(1, sb.length());
		sb.append("]}");
		String geometries = sb.toString();

		return "f=" + _f + "&sr=" + _inSR + "&geometries=" + geometries;
	}

	public List<NameValuePair> toList() {
		StringBuilder sb = new StringBuilder();
		sb.append("{" + "geometryType" + ":" + "esriGeometryPolygon" + "," + "geometries" + ":[");

		for (Polygon polygon : polygons.getGeometries()) {
			String rings = JSON.toJSONString(polygon.getRings());
			sb.append("{" + "rings" + ":" + rings + "}");
			sb.append(",");
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append("]}");
		String geometries = sb.toString();

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("f", _f));// format设置成json
		params.add(new BasicNameValuePair("sr", _inSR));
		params.add(new BasicNameValuePair("geometries", geometries));
		return params;
	}
}
