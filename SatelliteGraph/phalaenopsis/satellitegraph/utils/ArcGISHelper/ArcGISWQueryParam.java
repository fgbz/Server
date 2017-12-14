package phalaenopsis.satellitegraph.utils.ArcGISHelper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * 采用 ArcGIS GeometryServer Query 方式 传递where条件 对空间信息进行查询
 * 
 */
public class ArcGISWQueryParam extends ArcGISRequestParam_SD {
	/**
	 * 用于做识别查询的图形
	 */
	private String _geometry = "";

	/**
	 * 返回的图形属性字段 * 为所有字段
	 */
	private String _outFields = "";

	/**
	 * 查询条件
	 */
	private String _where = "1=1";

	public ArcGISWQueryParam(String fields, String whereC) {
		if (fields == null || fields.equals("")) {
			_outFields = "*";
		} else {
			_outFields = fields;
		}
		if (whereC != null && !whereC.equals("")) {
			_where += " and " + whereC;
		}
	}

	public String toString() {
		 String urlEncode = "";
		try {
			urlEncode = URLEncoder.encode(_where, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "f=" + _f + "&where=" + urlEncode + "&geometry=" + _geometry
				+ "&geometryType=" + _geometryType + "&inSR=" + _inSR + "&outFields=" + _outFields
				+ "&returnGeometry=true";
	}

}
