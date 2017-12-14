package phalaenopsis.systemmanagement.controller;

import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;import org.apache.poi.ss.util.SSCellRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;

import phalaenopsis.illegalclue.entity.ResultState;
import phalaenopsis.systemmanagement.entity.SsConfig;
import phalaenopsis.systemmanagement.service.SettingService;
import phalaenopsis.visitSystem.entity.XfRegister;

/**
 * 系统设置
 * @author chunl
 *
 */
//@Controller
@RequestMapping("/Sys/Setting")
@RestController
public class SettingController {
	
	@Autowired
	private SettingService service;
	
	/**
	 * 获取配置信息
	 * @return
	 */
	@RequestMapping(value="/GetConfigs", method= RequestMethod.GET)
	@ResponseBody
	public Map<String, String> getConfigs()
	{
		return service.getConfigs();
	}
	
	
	@RequestMapping(value="/SetRatio", method = RequestMethod.GET)
	@ResponseBody
	public Boolean setRatio(@PathParam("ratio") double ratio)
	{
		return service.setRatio(ratio);
	}
	
	@RequestMapping(value="/")
	public Boolean setReportYear(@PathParam("year") int year)
	{
		return service.setReportYear(year);
	}
	/**
	 * 保存获取修改配置项信息
	 * @param config
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public ResultState saveOrUpdate(@RequestBody String config){
		SsConfig ssConfig=JSONObject.parseObject(config, SsConfig.class);
		return service.saveOrUpdate(ssConfig);
	}
	/**
	 * 查询所有配置项信息
	 * @return
	 */
	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, SsConfig> getList(){
		return service.getList();
	}

	@RequestMapping(value = "/caclShift", method = RequestMethod.GET)
	public String caclShift(@RequestParam(value = "JX", required = true) double JX , @RequestParam(value = "JY", required = true) double JY,
							@RequestParam(value = "PX", required = true) double PX, @RequestParam(value = "PY", required = true) double PY){

		return service.caclShift(JX, JY, PX,PY);
	}
}
