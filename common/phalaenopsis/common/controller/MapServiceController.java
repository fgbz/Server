package phalaenopsis.common.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import phalaenopsis.common.entity.AppSettings;
import phalaenopsis.common.entity.MapLayer;
import phalaenopsis.common.entity.OrganizationGrade;
import phalaenopsis.common.entity.User;
import phalaenopsis.common.entity.Identity.IdentifyCollection;
import phalaenopsis.common.entity.analysis.TerminalType;
import phalaenopsis.common.method.Basis;
import phalaenopsis.common.service.MapServiceService;
import phalaenopsis.satellitegraph.entity.Envelope;
import phalaenopsis.satellitegraph.entity.MapPoint;
import phalaenopsis.satellitegraph.entity.Polygon;

/**
 * 地图服务
 * @author chunl
 *
 */
@RestController
@RequestMapping("/Foundation/Map")
public class MapServiceController extends Basis {

	@Autowired
	private MapServiceService service;

	private static org.slf4j.Logger Logger = LoggerFactory.getLogger(MapServiceController.class);

	/**
	 * 获取数据库中存储的所有图层信息
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/GetMapLayerCollection", method = RequestMethod.GET)
	@ResponseBody
	public List<MapLayer> getMapLayerCollection(@PathParam("type") int type) {
		List<MapLayer> result = service.getMapLayerCollection(type);
		User currentUser = getCurrentUser();
		for(int i = 0; i < result.size(); i++) {
			if (null != currentUser.getMapLayerGrade() && 3 == currentUser.getMapLayerGrade() ){
				result.get(i).setLayerUrl(result.get(i).getLayerUrl()); 
			}
			else{
				result.get(i).setLayerUrl(result.get(i).MapedUrl); 
			}
		}
		return result;
	}

	/**
	 * 地图要素识别(对结果属性进行特殊封装)
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/MapFeatureIdentify", method = RequestMethod.POST)
	@ResponseBody
	public List<IdentifyCollection> MapFeatureIdentify(@RequestBody String request) {
		JSONObject jsonObject = JSONObject.parseObject(request);
		Integer type = Integer.parseInt(jsonObject.get("type") == null ? "0" : jsonObject.get("type").toString());
		TerminalType terminalType = TerminalType.getTerminalType(type);
		List<MapLayer> layers = JSON.parseArray(jsonObject.get("layers").toString(), MapLayer.class);
		MapPoint point = JSON.parseObject(jsonObject.get("point").toString(), MapPoint.class);
		Envelope mapExtent = JSON.parseObject(jsonObject.get("mapExtent").toString(), Envelope.class);
		 
		return service.MapFeatureIdentify(terminalType, layers,  point, mapExtent);
	
	}

	/**
	 * 要素识别(通过特定条件查询，采用post方式请求identify操作，根据传递要素参数来判断是进行面识别还是点识别) 
	 * 目前用于客户端分宗
	 * 并宗及移动端地图识别
	 * 
	 * @param layer
	 * @param polygon
	 * @param point
	 * @param mapExtent
	 * @param layerDefs
	 * @return
	 */
	@RequestMapping(value = "/FeatureIdentify", method = RequestMethod.POST)
	@ResponseBody
	public String featureIdentify(@RequestBody String json) {
		JSONObject jo = JSON.parseObject(json);
		Polygon polygon = JSON.parseObject(jo.getString("polygon"), Polygon.class);
		MapPoint point = JSON.parseObject(jo.getString("point"), MapPoint.class);
		Envelope mapExtent = JSON.parseObject(jo.getString("mapExtent"), Envelope.class);
		String layerDefs = jo.getString("layerDefs");
		String layer = new AppSettings().getExistingMapService();
		String identifyResults = service.featureIdentify(layer, polygon, point, mapExtent, layerDefs);
		return identifyResults;
	}
}
