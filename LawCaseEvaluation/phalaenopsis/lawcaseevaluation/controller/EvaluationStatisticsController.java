/**
 * Description 评查统计服务类
 * Author lih
 * Date 2017-04-06
 */
package phalaenopsis.lawcaseevaluation.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import phalaenopsis.common.entity.Condition;
import phalaenopsis.lawcaseevaluation.entity.EvaluationPerson;
import phalaenopsis.lawcaseevaluation.entity.EvaluationResultAccountReport;
import phalaenopsis.lawcaseevaluation.entity.EvaluationResultReport;
import phalaenopsis.lawcaseevaluation.entity.ExportSchedule;
import phalaenopsis.lawcaseevaluation.entity.ExportScheduleTotal;
import phalaenopsis.lawcaseevaluation.entity.ReviewLawcase;
import phalaenopsis.lawcaseevaluation.service.EvaluationStatisticsService;

@Controller
@RequestMapping("/LawcaseEvaluation")
public class EvaluationStatisticsController {

    private static Logger Log = LoggerFactory.getLogger(EvaluationStatisticsController.class);
    
    @Autowired
    private EvaluationStatisticsService evaluationPersonService;
    
    /**
     * 获取国土资源执法监察案卷评查结果汇总表
     * @param code 执法单位code
     * @param year 年份
     * @return
     */
    @RequestMapping(value = "/GetAllEvaluationResultAccount",method = RequestMethod.GET) 
    @ResponseBody
    public List<ReviewLawcase> getAllEvaluationResultAccount(long code,  int year,long userid){
        return evaluationPersonService.getAllEvaluationResultAccount(code, year,userid);
    }
	/**
	 * 获取各市案卷一览表信息
	 * @param year 年份
	 * @return
	 */
    @RequestMapping(value = "/getStatisticSchedule",method = RequestMethod.GET) 
    @ResponseBody
	public ExportScheduleTotal getStatisticSchedule(int year){
			return evaluationPersonService.getStatisticSchedule(year);
	}
    /**
     * 导出各市各案卷一览表
     * @param year 年份
     * @return
     */
    @RequestMapping(value = "/ExportSchedule",method = RequestMethod.GET) 
    @ResponseBody
    public HttpServletResponse ExportSchedule(int year,@Context HttpServletResponse response){
    	return evaluationPersonService.ExportSchedule(year,response);
   }
    /**
     * 导出案卷明细表
     * @param year 年份
     * @param regionCode 执法单位
     * @param response HttpServletResponse
     * @return
     */
    @RequestMapping(value = "/ExportDetail",method = RequestMethod.GET) 
    @ResponseBody
    public HttpServletResponse ExportDetail(int year, long regionCode,long userid,@Context HttpServletResponse response){
    	return evaluationPersonService.ExportDetail(year, regionCode, userid,response);
    }
    
    /**
     * 根据年份和执法单位获取评查人员 
     * @param year
     * @param regionCode
     * @return
     */
    @RequestMapping(value = "/getUserByYearAndRegionCode",method = RequestMethod.GET) 
    @ResponseBody
    public List<EvaluationPerson> getUserByYearAndRegionCode(int year){
		return evaluationPersonService.getUserByYearAndRegionCode(year);	
    } 
}
