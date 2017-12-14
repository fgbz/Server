package phalaenopsis.statistics.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import org.apache.batik.transcoder.TranscoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.lowagie.text.DocumentException;
import phalaenopsis.common.util.QueryUtil;
import phalaenopsis.statistics.entity.CountyLawCaseEnd;
import phalaenopsis.statistics.entity.CountyReportArea;
import phalaenopsis.statistics.entity.IllegalCaseInvestigation;
import phalaenopsis.statistics.entity.LandCaseEvaluation;
import phalaenopsis.statistics.entity.LandCasePunish;
import phalaenopsis.statistics.entity.MineralCaseEvaluation;
import phalaenopsis.statistics.entity.MineralCasePunish;
import phalaenopsis.statistics.entity.MinistryPhoneNum;
import phalaenopsis.statistics.entity.ProviceIllegalLand;
import phalaenopsis.statistics.entity.ProvinceLandLawCase;
import phalaenopsis.statistics.entity.ProvinceLawCaseEnd;
import phalaenopsis.statistics.entity.ProvinceLetter;
import phalaenopsis.statistics.entity.ProvinceReportNum;
import phalaenopsis.statistics.entity.SatelliteContrast;
import phalaenopsis.statistics.service.StatisticsService;


/**
 * 统计接口 
 * @author chunl
 * 2017年8月7日下午2:01:18
 */
@RestController
@RequestMapping("/statistics/statisticsservice")
public class StatisticsController {
	@Autowired
	public StatisticsService service;


	/**
	 *  省级信访情况
	 * @param queryUtil
	 * @return
	 */
	@RequestMapping(value="/getProvinceLetterState", method = RequestMethod.POST)
	public List<ProvinceLetter> getProvinceLetterState(@RequestBody QueryUtil queryUtil) {
		return service.getProvinceLetterState(queryUtil);
	}

	/**
	 * 部省12336受理违法举报电话线索量
	 * @param queryUtil
	 * @return
	 */
	@RequestMapping(value="/getMinistryPhoneNum", method = RequestMethod.POST)
	public List<MinistryPhoneNum> getMinistryPhoneNum(@RequestBody QueryUtil queryUtil) {
		return service.getMinistryPhoneNum(queryUtil);
	}

	/**
	 * 全省发现土地违法案件数及面积
	 * @param queryUtil
	 * @return
	 */
	@RequestMapping(value="/getProvinceLandLawCase", method = RequestMethod.POST)
	public List<ProvinceLandLawCase> getProvinceLandLawCase(@RequestBody QueryUtil queryUtil) {
		return service.getProvinceLandLawCase(queryUtil);
	}

	/**
	 * 全省份用途违法用地面积比例
	 * @param queryUtil
	 * @return
	 */
	@RequestMapping(value="/getProvinceReportArea", method = RequestMethod.POST)
	public List<ProviceIllegalLand> getProvinceReportArea(@RequestBody QueryUtil queryUtil) {
		return service.getProvinceReportArea(queryUtil);
	}

	/**
	 * 全省本年立案结案情况

	 * @return
	 */
	@RequestMapping(value="/getProvinceLawCaseEnd", method = RequestMethod.POST)
	public List<ProvinceLawCaseEnd> getProvinceLawCaseEnd(@RequestBody QueryUtil queryUtil) {
		return service.getProvinceLawCaseEnd(queryUtil);
	}

	/**
	 * 市结案情况以及比例

	 * @return
	 */
	@RequestMapping(value="/getCountyLawCaseEnd", method = RequestMethod.POST)
	public List<CountyLawCaseEnd> getCountyLawCaseEnd(@RequestBody QueryUtil queryUtil) {
		return service.getCountyLawCaseEnd(queryUtil);
	}

	/**
	 * 市统计上报潍坊案件数及违法用地面积数比对
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/getCountyReportArea", method = RequestMethod.POST)
	public List<CountyReportArea> getCountyReportArea(@RequestBody Map<String, String> map) {
		return null;
	}

	/**
	 * 省级巡查与统计上报数据比对
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/getProvinceReportNum", method = RequestMethod.POST)
	public List<ProvinceReportNum> getProvinceReportNum(@RequestBody Map<String, String> map) {
		return null;
	}
	
	/**
	 * 卫片数据与统计上报数据比对
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/getSatelliteContrast", method = RequestMethod.POST)
	public List<SatelliteContrast> getSatelliteContrast(@RequestBody Map<String, String> map){
		return null;
	}

	/**
	 * 获取土地违法案件及查处情况统计
	 * 

	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getLandCaseEvaluationList", method = RequestMethod.POST)
	public List<LandCaseEvaluation> getLandCaseEvaluationList(@RequestBody Map map) {
		return service.getLandCaseEvaluationList(map);
	}

	/**
	 * 获取土地违法案件处分情况统计
	 * 

	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getLandCasePunishList", method = RequestMethod.POST)
	public List<LandCasePunish> getLandCasePunishList(@RequestBody QueryUtil query) {
		return service.getLandCasePunishList(query);
	}

	/**
	 * 获取矿产违法案件及查处情况统计
	 * 

	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getMineralCaseEvaluationList", method = RequestMethod.POST)
	public List<MineralCaseEvaluation> getMineralCaseEvaluationList(@RequestBody QueryUtil query) {
		return service.getMineralCaseEvaluationList(query);
	}

	/**
	 * 获取矿产违法案件处分情况统计
	 * 

	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getMineralCasePunishList", method = RequestMethod.POST)
	public  Map<String,Object> getMineralCasePunishList(@RequestBody QueryUtil query) {
		return service.getMineralCasePunishList(query);
	}

	/**
	 * 获取土地违法发现、制止情况统计
	 * 

	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getLandCasePreventList", method = RequestMethod.POST)
	public List<LandCaseEvaluation> getLandCasePreventList(@RequestBody QueryUtil query) {
		return service.getLandCasePreventList(query);
	}
	/**
	 * 统计分析导出word

	 */
	@RequestMapping(value = "/exportWord", method = RequestMethod.POST)
	@ResponseBody
	public void exportWord(HttpServletRequest request,HttpServletResponse response) throws DocumentException, IOException{
		request.setCharacterEncoding("UTF-8");
		String svg=new String(request.getParameter("svg").getBytes("ISO-8859-1"), "utf-8");
		String startTime=request.getParameter("startTime");
		String endTime=request.getParameter("endTime");
		String selectRegion=request.getParameter("selectRegion");
		String list=new String(request.getParameter("list").getBytes("ISO-8859-1"), "utf-8");
		String type=request.getParameter("type");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("svg", svg);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("selectRegion", selectRegion);
		map.put("list", list);
		map.put("type", type);
		service.exportWord(request, response, map);
	}
	/**
	 * 获取土地违法案件及查处情况统计表
	 * @param query
	 * @return
	 */
	@RequestMapping(value = "/getIllegalCaseInvestigationList", method = RequestMethod.POST)
	@ResponseBody
	public List<IllegalCaseInvestigation> getIllegalCaseInvestigationList(QueryUtil query){
		return service.getIllegalCaseInvestigationList(query);
	}
}
