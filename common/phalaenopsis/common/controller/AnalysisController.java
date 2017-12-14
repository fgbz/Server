/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import phalaenopsis.allWeather.entity.SwAuditspot;
import phalaenopsis.common.entity.analysis.AnalysisResult;
import phalaenopsis.common.entity.analysis.SpotAnalysisResult;
import phalaenopsis.common.service.analysis.AnalysisService;
import phalaenopsis.satellitegraph.entity.Polygon;

/**
 * @author yangw786
 *
 * 2017年5月5日上午11:56:56
 */
@Controller
@RequestMapping("/Foundation/AnalysisService")
public class AnalysisController {

	@Autowired
	private AnalysisService analysisService;
	
	/***
	 *  /// <summary>
        /// 根据不同的设备与不同的业务类型，提供不同的分析结果
        /// </summary>
        /// <param name="businessType">业务类型</param>
        /// <param name="terminalType">终端类型</param>
        /// <param name="Polygon">被分析的坐标对象 arcgis 对象</param>
         * autor:chiz
         * time:2017年5月31日10:53:32
	 * 
	 * */
	@RequestMapping(value="/analysis/geometry", method = RequestMethod.POST)
	@ResponseBody
	public List<AnalysisResult> analysis(@RequestBody Map<String,Object> map) {
		return analysisService.analysis(map);
	}
	
	/**
	 * 根据行政区划名称获取其位置数据
	 * 调用Arcgis服务获取
	 * autor:chiz
	 * time:2017年5月31日10:53:13
	 * */
	@RequestMapping(value="/analysis/getRegeionShape", method = RequestMethod.GET)
	@ResponseBody
	public Polygon getRegeionShape(@RequestParam(value="regionName",required=false) String regionName){
		return analysisService.getRegeionShape(regionName);
	}
	
	
	
	/**
	 *根据查询条件
	 * autor:chiz
	 * time:2017年5月31日10:53:13
	 * */
	@RequestMapping(value="/GetAnalysisResult", method = RequestMethod.GET)
	@ResponseBody
	public SpotAnalysisResult getAnalysisResult(@RequestParam(value="id",required=false)String id,@RequestParam(value="source",required=false)int source){
		return analysisService.getAnalysisResult(id, source);
	}
	
	/**
	 *获取已经保存的分析结果
	 * autor:chiz
	 * time:2017年5月31日10:53:13
	 * */
	@RequestMapping(value="/SaveAnalysisResult", method = RequestMethod.POST)
	@ResponseBody
	public long saveAnalysisResult(@RequestBody String anresult){
		SpotAnalysisResult sar=JSONObject.parseObject(anresult,SpotAnalysisResult.class);
		return analysisService.saveAnalysisResult(sar);
	}
	
	
	/**
	 *删除图斑分析结果
	 * autor:chiz
	 * time:2017年5月31日10:53:13
	 * */
	@RequestMapping(value="/DeleteAnalysisResult", method = RequestMethod.POST)
	@ResponseBody
	public boolean deleteAnalysisResult(@RequestParam(value="businessId",required=false)String businessId){
		return analysisService.deleteAnalysisResult(businessId);
	}
}
