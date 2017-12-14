package phalaenopsis.satellitegraph.utils.ArcGISHelper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSON;

import phalaenopsis.satellitegraph.entity.MapPoint;
import phalaenopsis.satellitegraph.entity.Polygon;
import phalaenopsis.satellitegraph.utils.ExtensionMethods;

public class ArcGISIdentifyParam extends ArcGISRequestParam_SD {
	/**
	 * 用于做识别查询的图形
	 */
	private String _geometry = "";

	private String _layerDefs = "";

	/**
	 * 用于识别查询时地图Extent参数传递
	 */
	private String _mapExtent = "";

	public ArcGISIdentifyParam(Polygon polygon, MapPoint point, String mapExtent, String layerDefs) {
		if (point != null) {
			_geometry = "{\"x\":" + point.X + ",\"y\":" + point.Y + "}";
			_geometryType = "esriGeometryPoint";
		} else if (polygon != null) {
			String rings = JSON.toJSONString(polygon.getRings());

			_geometry = "{" + "spatialReference" + ":" + _inSR + "," + "rings" + ":" + rings + "}";
			_geometryType = "esriGeometryPolygon";
		}
		_mapExtent = mapExtent;
		_layerDefs = layerDefs;
	}

	public String toString1() {
		return "f=" + _f + 
				"&geometry=" + ExtensionMethods.EscapeLongString1(_geometry) + 
				"&geometryType="+ _geometryType + 
				"&mapExtent=" + StringEscapeUtils.escapeHtml(_mapExtent) + 
				"&tolerance=" + 1+ 
				"&imageDisplay=" + StringEscapeUtils.escapeHtml("575,729,96") + "&layers=" + "all"+ 
				"&returnGeometry=" + true + 
				"&layerDefs="+ (_layerDefs != null ? StringEscapeUtils.escapeHtml(_layerDefs) : _layerDefs);
	}
	
	public String toString() {
		return "f=" + _f + 
				"&geometry=" + ExtensionMethods.EscapeLongString(_geometry) + 
				"&geometryType="+ _geometryType + 
				"&mapExtent=" + StringEscapeUtils.escapeHtml(_mapExtent) + 
				"&tolerance=" + 1+ 
				"&imageDisplay=" + StringEscapeUtils.escapeHtml("575,729,96") + "&layers=" + "all"+ 
				"&returnGeometry=" + true + 
				"&layerDefs="+ (_layerDefs != null ? StringEscapeUtils.escapeHtml(_layerDefs) : _layerDefs);
	}

	public List<NameValuePair> toList() {

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("f", _f));
		params.add(new BasicNameValuePair("geometry", ExtensionMethods.EscapeLongString(_geometry)));
		params.add(new BasicNameValuePair("geometryType", _geometryType));
		params.add(new BasicNameValuePair("mapExtent", StringEscapeUtils.escapeHtml(_mapExtent)));
		params.add(new BasicNameValuePair("tolerance", "1"));
		params.add(new BasicNameValuePair("imageDisplay", StringEscapeUtils.escapeHtml("575,729,96")));
		params.add(new BasicNameValuePair("layers", "all"));
		params.add(new BasicNameValuePair("returnGeometry", "true"));
		params.add(new BasicNameValuePair("layerDefs",
				(_layerDefs != null ? StringEscapeUtils.escapeHtml(_layerDefs) : _layerDefs)));
		return params;
	}
}
