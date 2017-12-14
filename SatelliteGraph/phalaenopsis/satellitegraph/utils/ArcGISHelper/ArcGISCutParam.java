package phalaenopsis.satellitegraph.utils.ArcGISHelper;

import com.alibaba.fastjson.JSON;

import phalaenopsis.satellitegraph.entity.MapPolyline;
import phalaenopsis.satellitegraph.entity.Polygon;

/**
 * 用于做切割的线段
 */
public class ArcGISCutParam extends ArcGISRequestParam_SD {
	private Polygon _polygons;

	private String _cutter = "";

	public ArcGISCutParam(MapPolyline polyline, Polygon polygons) {
		_polygons = polygons;
		String rings = JSON.toJSONString(polyline.getPaths());
		_cutter = "{" + "paths" + ":" + rings + "}";
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{" + "geometryType" + ":" + "esriGeometryPolygon" + "," + "geometries" + ":[");
		String rings = JSON.toJSONString(_polygons.getRings());
		sb.append("{" + "rings" + ":" + rings + "}");
		sb.append(",");
		sb.delete(1, sb.length() - 1);
		sb.append("]}");
		String geometries = sb.toString();
		return "f=" + _f + "&cutter=" + _cutter + "&sr=" + _inSR + "&target=" + geometries;
	}
}
