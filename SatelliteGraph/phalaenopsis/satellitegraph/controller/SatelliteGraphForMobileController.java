package phalaenopsis.satellitegraph.controller;

import java.util.List;
import java.util.Map;

import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import phalaenopsis.common.entity.DataDictionaryItem;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.Paging;
import phalaenopsis.common.entity.Attachment.Attachment;
import phalaenopsis.satellitegraph.entity.MapSpot2016;
import phalaenopsis.satellitegraph.entity.MobileSpot;
import phalaenopsis.satellitegraph.entity.SpotNumberSpotID;
import phalaenopsis.satellitegraph.entity.SpotShapeInfo;
import phalaenopsis.satellitegraph.service.SatelliteGraphForMobileService;

/**
 * 移动端服务(卫片图斑部分)
 * @author gaofengt
 *
 */
@Controller
@RequestMapping("/SatelliteGraph/Mobile")
public class SatelliteGraphForMobileController {

	@Autowired
	private SatelliteGraphForMobileService service;
	
	/**
	 * 查询图斑列表   
	 * 
	 * @param string
	 * @return
	 * @deprecated  
	 */
	@RequestMapping(value = "/OilList", method = RequestMethod.POST)
	@ResponseBody
	public Paging<MobileSpot> oilList(@RequestBody String string) {
		return service.oilList(JSON.parseObject(string, Page.class));
	}
	
	/**
	 * 查询图斑基本信息 
	 */
	@RequestMapping(value = "/GetSpotBaseInfoByid", method = RequestMethod.GET)
	@ResponseBody
	public MapSpot2016 getSpotBaseInfoByid(@PathParam("id") long id) {
		return service.getSpotBaseInfoByid(id);
	}
	
	/**
	 * 上传图斑数据
	 */
	@RequestMapping(value = "/ReportMapSpot", method = RequestMethod.POST)
	@ResponseBody
	public boolean reportMapSpot(@RequestBody String string) {
		return service.reportMapSpot(Long.parseLong(JSON.parse(string).toString()));
	}
	
	/**
	 * 查询各个列表数组总数
	 */
	@RequestMapping(value = "/GetTabTotal", method = RequestMethod.GET)
	@ResponseBody
	public List<Integer> getTabTotal() {
		return service.getTabTotal();
	}
	
	/**
	 * 查询地图范围内图斑shape信息
	 */
	@RequestMapping(value = "/GetSpotShapeByBounds", method = RequestMethod.GET)
	@ResponseBody
	public List<SpotShapeInfo> getSpotShapeByBounds(@PathParam("bounds") String bounds) {
		return service.getSpotShapeByBounds(bounds);
	}
	
	/**
	 * 查询图斑号
	 */
	@RequestMapping(value = "/GetSpotNumbersByKey", method = RequestMethod.GET)
	@ResponseBody
	public List<SpotNumberSpotID> getSpotNumbersByKey(@PathParam("spotNumber") String spotNumber) {
		return service.getSpotNumbersByKey(spotNumber);
	}
	
	/**
	 * 获取单个图斑by图斑号
	 */
	@RequestMapping(value = "/GetSpotShapeInfoBySpotID", method = RequestMethod.GET)
	@ResponseBody
	public SpotShapeInfo getSpotShapeInfoBySpotID(@PathParam("spotID") long spotID) {
		return 	service.getSpotShapeInfoBySpotID(spotID);
	}
	
	/**
	 * 检查是否成功连接后台
	 */
	@RequestMapping(value = "/CheckConnection", method = RequestMethod.GET)
	@ResponseBody
	public boolean checkConnection() {
		return service.checkConnection();
	}
	
	/**
	 * 获取拍照的距离限制
	 */
	@RequestMapping(value = "/GetMinDistance", method = RequestMethod.GET)
	@ResponseBody
	public String getMinDistance() {
		return service.getMinDistance();
	}
	
	/**
	 * 保存图斑
	 */
	@RequestMapping(value = "/SaveMapSpot", method = RequestMethod.POST)
	@ResponseBody
	public boolean saveMapSpot(@RequestBody MapSpot2016 mapSpot) {
		return service.saveMapSpot(mapSpot);
	}
	
	/**
	 * 获取批准机关级别字典数据
	 */
	@RequestMapping(value = "/GetAllDictionaries", method = RequestMethod.GET)
	@ResponseBody
	public  List<DataDictionaryItem> getAllDictionaries() {
		return service.getAllDictionaries();
	}
	
	/**
	 * 获取附件
	 */
	@RequestMapping(value = "/GetAttachment", method = RequestMethod.GET)
	@ResponseBody
	public  Attachment getAttachment(@RequestBody String attID) {
		return service.getAttachment(attID);
	}
	
	/**
	 * 查询图斑流程
	 */
	@RequestMapping(value = "/NodeCanUpload", method = RequestMethod.GET)
	@ResponseBody
	public boolean nodeCanUpload(@RequestBody Map<String, Object> map) {
		long spotID  =(long) map.get("spotID");
		int  spottype = (int) map.get("spottype");
		return service.nodeCanUpload(spotID, spottype);
	}
	
	/**
	 * 20170410先判断是否有内网同步到外网的照片，如果有就允许上传
	 */
	@RequestMapping(value = "/HaveSynPhoto", method = RequestMethod.GET)
	@ResponseBody
	public boolean haveSynPhoto(@RequestBody long spotID) {
		return service.haveSynPhoto(spotID);
	}
}
