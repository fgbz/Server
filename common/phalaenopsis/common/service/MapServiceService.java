package phalaenopsis.common.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.apache.xmlbeans.impl.jam.mutable.MPackage;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import phalaenopsis.common.dao.MapServiceDao;
import phalaenopsis.common.entity.MapLayer;
import phalaenopsis.common.entity.Identity.IdentifyCollection;
import phalaenopsis.common.entity.Identity.IdentifyField;
import phalaenopsis.common.entity.Identity.IdentifyItem;
import phalaenopsis.common.entity.analysis.TerminalType;
import phalaenopsis.common.method.Tools.Linq;
import phalaenopsis.satellitegraph.entity.Envelope;
import phalaenopsis.satellitegraph.entity.MapPoint;
import phalaenopsis.satellitegraph.entity.Polygon;
import phalaenopsis.satellitegraph.utils.ArcGISHelper.ArcGISIdentifyParam;
import phalaenopsis.satellitegraph.utils.ArcGISHelper.HttpHelper;

@Service("mapServiceService")
public class MapServiceService {

	@Autowired
	private MapServiceDao mapServiceDao;

	public List<MapLayer> getMapLayerCollection(int type) {
		return mapServiceDao.getMapLayerCollection(type);
	}

	public String featureIdentify(String layer, Polygon polygon, MapPoint point, Envelope mapExtent, String layerDefs) {
		String result = null;
		String Extent = mapExtent.getXMin() + "," + mapExtent.getYMin() + "," + mapExtent.getXMax() + ","
				+ mapExtent.getYMax();
		if (layer != null) {
			ArcGISIdentifyParam param = null;
			if (polygon != null) {
				param = new ArcGISIdentifyParam(polygon, null, Extent, layerDefs);
			} else if (point != null) {
				param = new ArcGISIdentifyParam(null, point, Extent, layerDefs);
			} else {
				return "";
			}
			if (layer.contains("/MapServer")) {
				int removeIndex = layer.indexOf("/MapServer");
				layer = layer.substring(0, removeIndex);
			}

			String url = layer + "/MapServer/identify";

			HttpEntity httpEntity = HttpHelper.HttpPost(url, param.toList());
			try {
				result = EntityUtils.toString(httpEntity);
				ObjectMapper mapper = new ObjectMapper();
				result = mapper.writeValueAsString(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public List<IdentifyCollection> MapFeatureIdentify(TerminalType terminalType, List<MapLayer> layers, MapPoint point,
			Envelope mapExtent) {

		List<IdentifyCollection> result = new ArrayList<IdentifyCollection>();
		String Extent = mapExtent.XMin + "," + mapExtent.YMin + "," + mapExtent.XMax + "," + mapExtent.YMax;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("terminalType", terminalType);
		map.put("layers", layers);
		List<MapLayer> mapLayers = mapServiceDao.getIdentifyFields(map);

		try {
			if (mapLayers != null && 0 != mapLayers.size()) {
				ArcGISIdentifyParam param = new ArcGISIdentifyParam(null, point, Extent, null);
				for (MapLayer layer : mapLayers) {
					String layerUrl = layer.getLayerUrl(); //.LayerUrl;
					int removeIndex = layerUrl.indexOf("/MapServer");
					String url = layerUrl.substring(0, removeIndex) + "/MapServer/identify?" + param.toString1();
					HttpEntity httpEntity = HttpHelper.HttpGet(url); // HttpCommunicator.
					// JSONArray ja = null;
					// JSONObject json;
;					String strUtils = EntityUtils.toString(httpEntity);
					JSONObject json = JSONObject.parseObject(strUtils);
					JSONArray results = JSON.parseArray(json.getString("results"));

				//	MapLayer fieldItems = Linq.extEquals(mapLayers, "LayerID", layer.ID);

					for (Object re : results) {
						JSONObject token = (JSONObject) re;
						IdentifyCollection fieldCollection = new IdentifyCollection();
						fieldCollection.setIdentifyID(layer.getLayerID());
						Polygon Coordinates = token.getObject("geometry", Polygon.class);
						fieldCollection.setCoordinates(Coordinates);
						fieldCollection.getCoordinates().setType("polygon");
						JSONObject Attributes = (JSONObject) token.get("attributes");

						if (layer != null && layer.getIdentifyField() != null
								&& layer.getIdentifyField().size() != 0) {
							for (IdentifyField field : layer.getIdentifyField()) {
								IdentifyItem identifyItem = new IdentifyItem();
								identifyItem.setName(field.getAlias());
								field.getName();
								identifyItem.setValue(Attributes.getString(field.getName()));

								if (identifyItem.getValue().equals("Null") || identifyItem.getValue().equals("空")) {
									identifyItem.setValue("");
								}
								fieldCollection.items.add(identifyItem);
							}
						} else { // 字段表中对应图层没有配置显示字段时默认显示全部字段
							
							if (Attributes.containsKey("OBJECTID")){
								IdentifyItem identifyItem = new IdentifyItem();
								identifyItem.setName("OBJECTID");
								identifyItem.setValue(Attributes.get("OBJECTID").toString());
								fieldCollection.items.add(identifyItem);
								Attributes.remove("OBJECTID");
							}
							
							for (Entry<String, Object> entry : Attributes.entrySet()) {
								IdentifyItem identifyItem = new IdentifyItem();
								identifyItem.setName(entry.getKey());
								identifyItem.setValue(entry.getValue().equals("Null") || entry.getValue().equals("空")
										? "" : entry.getValue().toString());
								fieldCollection.items.add(identifyItem);
							}
						}
						result.add(fieldCollection);
					}

					if (terminalType == TerminalType.Pad || terminalType == TerminalType.Phone)
						break;
				}
			}
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}

		return result;
	}
}
