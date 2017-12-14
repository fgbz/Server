package phalaenopsis.allWeather.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;

import phalaenopsis.allWeather.bean.WeatherBean;
import phalaenopsis.allWeather.entity.ResultSwLog;
import phalaenopsis.allWeather.entity.SwAuditspot;
import phalaenopsis.allWeather.entity.SwLog;
import phalaenopsis.allWeather.entity.SwMapSpotAuditStatistic;
import phalaenopsis.allWeather.entity.SwMapSpotStatistic;
import phalaenopsis.allWeather.entity.SwMapspot;
import phalaenopsis.allWeather.entity.YearAndPeriod;
import phalaenopsis.allWeather.service.AllWeatherReportService;
import phalaenopsis.common.entity.DataDictionaryItem;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.common.method.JsonResult;
import phalaenopsis.common.service.DataDictionaryService;
import phalaenopsis.illegalclue.entity.ResultState;

/**
 * 省，市审核信息
 * @author dongdongt
 *
 */
@Controller
@RequestMapping("/allweather/allweatherservice")
public class AllWeatherCityController {
	
	@Autowired
	private AllWeatherReportService service;
	@Autowired
	private DataDictionaryService dicService;
	/**
	 * 省市级别审核
	 * @param swAudits
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdateAduit", method = RequestMethod.POST)
	@ResponseBody
	public ResultState saveAduit(@RequestBody String auditspot){
		SwAuditspot sw=JSONObject.parseObject(auditspot,SwAuditspot.class);
		return service.saveAudit(sw);
	}
	/**
	 * 省市级别批量审核
	 * @param swAudits
	 * @return
	 */
	@RequestMapping(value = "/saveAduits", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult saveAduits(@RequestBody String swLog){
		SwLog  log=JSONObject.parseObject(swLog, SwLog.class);
		return service.saveAudits(log);
	}
	/**
	 * 获取历史核查期数
	 * @return
	 */
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public List<YearAndPeriod> getHistoryPeroid(){
		return service.getHistoryPeroid();
	}
	/**
	 * 获取历史下发期数
	 * @return
	 */
	@RequestMapping(value = "/getHistoryxfPeroid", method = RequestMethod.GET)
	@ResponseBody
	public List<YearAndPeriod> getHistoryxfPeroid(){
		return service.getHistoryxfPeroid();
	}
	/**
	 * 进度标识
	 * @param getAllPeriodPro
	 * @return
	 */
	@RequestMapping(value = "/getAllPeriodPro", method = RequestMethod.GET)
	@ResponseBody
	public  List<ResultSwLog> getAllPeriodPro(){
		List<ResultSwLog> lsit = new ArrayList<ResultSwLog>();
		ResultSwLog rs = service.getReportQueryList(null);
		lsit.add(rs);
		List<DataDictionaryItem> listdac = dicService.getDataDictionaryItems("AllWeather", "Focus");
		if(listdac!=null&&listdac.size()>0){
			for(DataDictionaryItem dataDictionaryItem:listdac){
				ResultSwLog resultSwLog =service.getReportQueryList(dataDictionaryItem.getValue());
				resultSwLog.setMark(dataDictionaryItem.getValue());
				lsit.add(resultSwLog);
			}
		}
		return lsit;
	}
	
	/**
	 * 省市列表
	 * @param swAudits
	 * @return
	 */
	@RequestMapping(value = "/allPeriodGraphotoQueryList", method = RequestMethod.POST)
	@ResponseBody
	public  ResultSwLog getAllPeriodGraphotoQueryList(@RequestBody String mark){
		return service.getReportQueryList(mark);
	}
	/**
	 * 省市回退
	 * @param auditspot
	 * @return
	 */
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@ResponseBody
	public boolean returnMap(@RequestBody String swLog){
		SwLog  log=JSONObject.parseObject(swLog, SwLog.class);
		return service.updateSwLog(log);
	}
	
	/**
	 * 获取图斑信息列表
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/getByRegionId", method = RequestMethod.POST)
	@ResponseBody
	public PagingEntity<SwMapspot> getAllPeriodReport(@RequestBody WeatherBean weatherBean) {
		return service.getSwMapspotByRegion(weatherBean);
	}
	/**
	 * 获取图斑统计列表
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/getAllPeriodStatisticQueryList", method = RequestMethod.POST)
	@ResponseBody
	public PagingEntity<SwMapspot> getAllPeriodStatisticQueryList(@RequestBody Page page) {
		return service.getSwMapspotStatistic(page);
	}
	/**
	 * 市级上报到省级
	 * @param auditspot
	 * @return
	 */
	@RequestMapping(value = "/saveAppear", method = RequestMethod.POST)
	@ResponseBody
	public boolean saveAppear(@RequestBody String mark){
		return service.saveAppear(mark);
	}
	/**
	 * 获取图斑举证情况统计表
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/getLegalproofStatisticList",method=RequestMethod.POST)
	@ResponseBody
	public PagingEntity<SwMapSpotStatistic> getLegalproofStatisticList(@RequestBody Page page){
		return service.getLegalproofStatisticList(page);
	}
	/**
	 * 获取图斑审核情况统计表
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/getAuditStatisticList",method=RequestMethod.POST)
	@ResponseBody
	public PagingEntity<SwMapSpotAuditStatistic> getAuditStatisticList(@RequestBody Page page){
		return service.getAuditStatisticList(page);
	}
}
