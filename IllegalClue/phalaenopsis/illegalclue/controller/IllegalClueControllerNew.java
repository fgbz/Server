package phalaenopsis.illegalclue.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.resource.spi.RetryableUnavailableException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.illegalclue.entity.Clue;
import phalaenopsis.illegalclue.entity.ClueAudit;
import phalaenopsis.illegalclue.entity.ClueDictionary;
import phalaenopsis.illegalclue.entity.ClueEnd;
import phalaenopsis.illegalclue.entity.IllegalClueStatistic;
import phalaenopsis.illegalclue.entity.ResultState;
import phalaenopsis.illegalclue.entity.ResultString;
import phalaenopsis.illegalclue.service.IllegalClueServiceNew;

@RestController
@RequestMapping("/IllegalClueService")
public class IllegalClueControllerNew {

	@Autowired
	private IllegalClueServiceNew service;

	/**
	 * 获取违法线索列表
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/IllegalClues/get", method = RequestMethod.POST)
	@ResponseBody
	public PagingEntity<Clue> getIllegalClues(@RequestBody Page page) {
		return service.getClues(page);
	}
	
	/**
	 * 违法线索删除功能
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/IllegalClue/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public ResultState deleteIllegalClue(@RequestParam("id") String id){
		return service.deleteIllegalClue(id);
	}

	/**
	 * 保存或更新登记信息
	 * 
	 * @param illegalClue
	 * @return
	 */
	@RequestMapping(value = "/IllegalClue/save", method = RequestMethod.POST)
	@ResponseBody
	public ResultState saveIllegalClue(@RequestBody Clue clue) {
		

		return service.saveIllegalClue(clue);
	}

	/**
	 * 获取单个违法线索
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/IllegalClue/get", method = RequestMethod.GET)
	@ResponseBody
	public Clue getIllegalClue(String id) {
		Clue getclue=service.getClue(id);
//		return service.getClue(id);
		return getclue;
	}
	
	/**
	 * 获取违法线索字典
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ClueDictionary/get", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, List<ClueDictionary>> getClueDictionaries() {
		return service.getClueDictionaries();
	}

	/**
	 * 保存线索初判信息
	 * 
	 * @param clueJudge
	 * @return
	 */
//	@RequestMapping(value = "/ClueJudge/save", method = RequestMethod.POST)
//	@ResponseBody
//	public ResultState saveOrUpdateClueJudge(@RequestBody ClueJudge clueJudge) {
//		return service.saveOrUpdateClueJudge(clueJudge);
//	}
	/**
	 * 提交审核
	 * @param clueEnd
	 * @return
	 */
	@RequestMapping(value = "/ClueEnd/save", method = RequestMethod.POST)
	@ResponseBody
	public ResultString saveOrUpdateClueEnd(@RequestBody String clueEnd){
		ClueEnd  cend=JSONObject.parseObject(clueEnd, ClueEnd.class);
		return service.saveOrUpdateClueEnd(cend);
	}
	/**
	 * 保存或修改线索审核信息
	 * @author chiz
	 * @param clueAudit
	 * @return
	 */
	@RequestMapping(value = "/ClueAudit/save", method = RequestMethod.POST)
	@ResponseBody
	public ResultString saveOrUpdateClueJudge(@RequestBody String clueAudit) {
		ClueAudit clueAt = JSONObject.parseObject(clueAudit, ClueAudit.class);
		return service.saveOrUpdateClueAudit(clueAt);
	}
	/**
	 * 保存办结审核信息
	 * @return
	 */
	@RequestMapping(value="/ClueEnd/saveCheck",method=RequestMethod.POST)
	@ResponseBody
	//这个方法是原来界面的暂存方法，后面要这个功能的话要进行修改
	public ResultString saveOrUpdateClueEndCheck(@RequestBody String clueEnd){
		ClueEnd  cend=JSONObject.parseObject(clueEnd, ClueEnd.class);
		return service.saveOrUpdateClueEnd(cend);
	}
	/**
	 * 违法线索统计
	 * @author chiz
	 * @param clueAudit
	 * @return
	 */
	@RequestMapping(value = "/IllegalClue/statistic", method = RequestMethod.GET)
	@ResponseBody
	public String selectIllegalClueStatistic(@RequestParam(value="startTime",required=false)String startTime,@RequestParam(value="endTime",required=false)String endTime) {
		IllegalClueStatistic illegalClueStatistic=new IllegalClueStatistic();
		if(StringUtils.isNotBlank(startTime)){
			illegalClueStatistic.setStartTime(Long.parseLong(startTime));
		}
		if(StringUtils.isNotBlank(endTime)){
			illegalClueStatistic.setEndTime(Long.parseLong(endTime));
		}	
		return  JSON.toJSONString(service.getIllegalClueStatistic(illegalClueStatistic));
	}
	

	// ==================通用方法==========================

	/**
	 * 获取UUID的值
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getUUID", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> getUUID() {
		Map<String, String> map = new HashMap<String, String>();
		String s = UUID.randomUUID().toString();
		map.put("id", s);
		return map;
	}


	/**
	 * 获取当前服务器时间
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getCurrentTime", method = RequestMethod.GET)
	@ResponseBody
	public long getCurrentTime() {
		return Calendar.getInstance().getTime().getTime();
	}

	/**
	 * 获取转交办组织
	 */
	@RequestMapping(value = "/getHandleOrganizationList", method = RequestMethod.GET)
	@ResponseBody
	public List<Condition> getHandleOrganizationList(String id) {
		return service.getHandleOrganizationList(id);
	}
	
	

}
