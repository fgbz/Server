package phalaenopsis.satellitegraph.utils.ArcGISHelper;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * 
 * 计算相应面积和长度
 */
public class ArcGISAreaLengthParam extends ArcGISRequestParam_SD {

	private String _coordiates = "";

	public ArcGISAreaLengthParam(String coordiatesJson) {
		_coordiates = coordiatesJson;
	}

	public String toString() {
		return "f=" + _f + "&sr=" + _inSR + "&polygons=" + _coordiates;
	}

	public List<NameValuePair> toList() {

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("f", _f));
		params.add(new BasicNameValuePair("sr", _inSR));
		params.add(new BasicNameValuePair("polygons", _coordiates));
		return params;
	}
}
