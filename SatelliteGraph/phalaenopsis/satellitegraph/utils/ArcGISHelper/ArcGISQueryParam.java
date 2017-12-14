package phalaenopsis.satellitegraph.utils.ArcGISHelper;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSON;

import phalaenopsis.satellitegraph.entity.Polygon;

/**
 * ArcGIS Mapserver Query RESTFULL请求 参数类 用于 esriSpatialRelIntersects 相交
 */
public class ArcGISQueryParam extends ArcGISRequestParam_SD {

	/**
	 * 用于做相交查询的图形
	 */
	private String _geometry = "";

	/**
	 * 返回的图形属性字段 * 为所有字段
	 */
	private String _outFields = "";

	/**
	 * 返回的相交的图形
	 */
	private String _returnGeometry = "";

	/**
	 * 定义空间操作 默认为 esriSpatialRelIntersects 相交
	 */
	private String _spatialRel = "";

	/**
	 * ArcGIS Mapserver Query RESTFULL请求 参数类 用于 esriSpatialRelIntersects 相交
	 * 
	 * @param outFields
	 * @param polygon
	 */
	public ArcGISQueryParam(String outFields, Polygon polygon) {
		_returnGeometry = "true";
		_spatialRel = "esriSpatialRelIntersects";
		_outFields = outFields;

		String rings = JSON.toJSONString(polygon.getRings());

		_geometry = "{" + "\"spatialReference\"" + ":" + _inSR + "," + "\"rings\"" + ":" + rings + "}";
	}

	/**
	 * 转换为 query 条件字符串
	 */
	public String toString() {
		return "f=" + _f + "&geometry=" + _geometry + "&geometryType=" + _geometryType + "&inSR=" + _inSR
				+ "&outFields=" + _outFields + "&returnGeometry=" + _returnGeometry + "&spatialRel=" + _spatialRel;
	}

	public List<NameValuePair> toList() {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("f", _f));
		params.add(new BasicNameValuePair("geometry", _geometry));
		params.add(new BasicNameValuePair("geometryType", _geometryType));
		
		params.add(new BasicNameValuePair("inSR", _inSR));
		params.add(new BasicNameValuePair("outFields", _outFields));
		params.add(new BasicNameValuePair("returnGeometry", _returnGeometry));
		params.add(new BasicNameValuePair("spatialRel", _spatialRel));
		return params;
	}
}
