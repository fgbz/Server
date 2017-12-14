package phalaenopsis.statistics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import phalaenopsis.statistics.entity.SatelliteAndReport;
import phalaenopsis.common.util.QueryUtil;
import phalaenopsis.statistics.entity.ContrastYear;
import phalaenopsis.statistics.entity.PatroAndReport;
import phalaenopsis.statistics.entity.ReportAndillegal;
import phalaenopsis.statistics.service.DataComparisonService;

/**
 * 统计分析模块
 * @author dongdongt
 *
 */
@Controller
@RequestMapping("/dataComparison/dataComparisonService")
public class DataComparisonController {
	
	@Autowired
	private DataComparisonService service;
	/**
	 * 17市统计上报潍坊案件数及违法用地面积数比对
	 * 参数设置一个查询条件的封装共用类
	 * @return
	 */
	@RequestMapping(value = "/getReportAndillegal", method = RequestMethod.POST)
	@ResponseBody
	public  List<ReportAndillegal> getReportAndillegal(@RequestBody QueryUtil query){
		return service.getReportAndillegal(query);
	}
	/**
	 * 省级巡查与统计上报数据比对
	 * @return
	 */
	@RequestMapping(value = "/getPatroAndReport", method = RequestMethod.POST)
	@ResponseBody
	public List<PatroAndReport> getPatroAndReport(@RequestBody QueryUtil query){
		return service.getPatroAndReport(query);
	}
	/**
	 * 卫片数据与统计上报数据比对
	 * @return
	 */
	@RequestMapping(value = "/getSatelliteAndReport", method = RequestMethod.POST)
	@ResponseBody
	public List<SatelliteAndReport> getSatelliteAndReport(@RequestBody QueryUtil query){
		return service.getSatelliteAndReport(query);
	}
	/**
	 * 17市分年度数据比对
	 * @return
	 */
	@RequestMapping(value = "/getContrastYear", method = RequestMethod.POST)
	@ResponseBody
	public List<ContrastYear> getContrastYear(@RequestBody QueryUtil query){
		return service.getContrastYear(query);
	}
}
