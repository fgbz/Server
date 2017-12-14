/**
 * Description 案件评查功能服务操作类
 * Author lih
 * Date 2017-04-06
 */
package phalaenopsis.lawcaseevaluation.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.lawcaseevaluation.entity.CaseGradeCount;
import phalaenopsis.lawcaseevaluation.entity.EvaluationArea;
import phalaenopsis.lawcaseevaluation.entity.EvaluationResult;
import phalaenopsis.lawcaseevaluation.entity.FirstEvaluationCondition;
import phalaenopsis.lawcaseevaluation.entity.FirstTrialLawcase;
import phalaenopsis.lawcaseevaluation.entity.LawcaseAccount;
import phalaenopsis.lawcaseevaluation.entity.ReviewLawcase;
import phalaenopsis.lawcaseevaluation.service.LawcaseEvaluationService;

@Controller
@RequestMapping("/LawcaseEvaluation")
public class LawcaseEvaluationController {

    private static Logger Log = LoggerFactory.getLogger(LawcaseEvaluationController.class);
    
    /**
     * 获取案件台账集合
     * 目前设计台账的导入是没有查看导入结果界面的，该服务可能暂不使用
     * @return
     */
    @RequestMapping(value = "/GetLawcaseAccounts",method = RequestMethod.POST) 
    @ResponseBody
    public PagingEntity<LawcaseAccount> getLawcaseAccounts(@RequestBody Page page){
        return null;
    }
    
    /**
     * 获取当前评查人员查询条件集合，年份、评审区域
     * @param account 用户id
     * @param flag 初评为true
     * @return
     */
    @RequestMapping(value = "/GetFirstEvaluationCondition",method = RequestMethod.GET) 
    @ResponseBody
    public List<FirstEvaluationCondition> getFirstEvaluationCondition(String account,boolean flag) {
        return lawcaseEvaluationService.getFirstEvaluationCondition(account,flag);
    }
    
    /**
     * 获取初评案件集合
     * 1、只查询属于自己评查区域内的案件
     * 2、评查完，已提交的案件不需要被查询出来
     * @return
     */
    @RequestMapping(value = "/GetFirstTrialLawcases",method = RequestMethod.POST) 
    @ResponseBody
    public PagingEntity<FirstTrialLawcase> getFirstTrialLawcases(@RequestBody Page page){
        return lawcaseEvaluationService.getFirstTrialLawcases(page);
    }
    
    /**
     * 获取复核案件集合
     * 复核权限的用户可以查看所有案件的初评情况
     * @return
     */
    @RequestMapping(value = "/GetReviewLawcases",method = RequestMethod.POST) 
    @ResponseBody
    public PagingEntity<ReviewLawcase> getReviewLawcases(@RequestBody Page page){
        return lawcaseEvaluationService.getReviewLawcases(page);
    }
    
    /**
     * 获得要进行初评的案件
     * @param caseid 案件id
     * @param userId 用户id
     * @return
     */
    @RequestMapping(value = "/FirstTrialLawcase/Get",method = RequestMethod.GET) 
    @ResponseBody
    public FirstTrialLawcase getFirstTrialLawcase(String caseid, String userId){
        return lawcaseEvaluationService.getFirstTrialLawcase(caseid, userId);
    }
    
    /**
     * 保存初评案件
     * @return
     */
    @RequestMapping(value = "/FirstTrialLawcase/Save",method = RequestMethod.POST) 
    @ResponseBody
    public int saveFirstTrialLawcase(@RequestBody FirstTrialLawcase lawcase){
//    	FirstTrialLawcase firstTrialLawcase	=JSON.parseObject(lawcase, FirstTrialLawcase.class);
        return lawcaseEvaluationService.saveFirstTrialLawcase(lawcase);
    }
    
    /**
     * 提交初评案件
     * @return
     */
    @RequestMapping(value = "/FirstTrialLawcase/Submit",method = RequestMethod.PUT) 
    @ResponseBody
    public int submitFirstTrialLawcase(@RequestBody String userid, @PathParam("caseid") String caseid){
        return 0;
    }
    
    /**
     * 获得要进行复核的案件
     * @return
     */
    @RequestMapping(value = "/ReviewLawcase/Get",method = RequestMethod.GET) 
    @ResponseBody
    public ReviewLawcase getReviewLawcase(@PathParam("caseid") String caseid){
        return null;
    }
    
    /**
     * 保存复核案件
     * @return
     */
    @RequestMapping(value = "/ReviewLawcase/Save",method = RequestMethod.POST) 
    @ResponseBody
    public int saveReviewLawcase(@RequestBody FirstTrialLawcase lawcase){
        return lawcaseEvaluationService.saveReviewLawcase(lawcase);
    }
    
    /**
     * 提交复核案件
     * @return
     */
    @RequestMapping(value = "/ReviewLawcase/Submit",method = RequestMethod.PUT) 
    @ResponseBody
    public int submitReviewLawcase(@RequestBody String userid, @PathParam("caseid") String caseid){
        return 0;
    }
    
    /**
     * 获取当前登录用户是否能上报案件的状态
     * @return 0 未提交 1 已提交 2 已上报 
     */
    @RequestMapping(value = "/GetReportStatus",method = RequestMethod.GET) 
    @ResponseBody
    public int getReportStatus(@RequestBody String userid) {
        return 0;
    }
    
    /**
     * 获取评审区域和人员关系
     * @return
     */
    @RequestMapping(value = "/GetEvaluationAreas",method = RequestMethod.GET) 
    @ResponseBody
    public List<EvaluationArea> getEvaluationAreas(@RequestBody String year) {
        return null;
    }
    
    /**
     * 保存评审区域和人员关系
     * @return
     */
    @RequestMapping(value = "/EvaluationArea/Save",method = RequestMethod.PUT) 
    @ResponseBody
    public int saveEvaluationAreas(@RequestBody EvaluationArea evaluationArea) {
        return 0;
    }
    /**
	 * 判断已经提交该案件的数量
	 * @param accountId 案件id
	 * @param userid 用户id
	 * @return
	 */
    @RequestMapping(value = "/getFirstTrialSurveyCount",method = RequestMethod.GET) 
    @ResponseBody
	public int getFirstTrialSurveyCount(long accountId,long userid){
		return lawcaseEvaluationService.getFirstTrialSurveyCount(accountId,userid);
	}
    
    /**
     * 获取当年优秀、合格、不合格、需要复查的案件数量
     * @param year 年份
     * @return
     */
    @RequestMapping(value = "/CaseGradeCount",method = RequestMethod.GET) 
    @ResponseBody
    public CaseGradeCount getCaseGradeCount(int year){
    	return lawcaseEvaluationService.getCaseGradeCount(year);
    }
    
    /**
     * 复核完结
     * @param caseid 案件id
     * @return
     */
    @RequestMapping(value = "/compeleteReview",method = RequestMethod.GET) 
    @ResponseBody
    public int compeleteReview(long caseid){
		return lawcaseEvaluationService.compeleteReview(caseid);	
    }
    
	/**
	 * 通过id获取复核信息
	 * 
	 * @param id
	 * @return
	 */
    @RequestMapping(value = "/getReviewInfoById",method = RequestMethod.GET) 
    @ResponseBody
	public ReviewLawcase getReviewInfoById(long id) {
		return lawcaseEvaluationService.getReviewInfoById(id);
	}
    
  /**
   *  导出复核
   * @param caseid 案件id
   * @param xh 序号
   * @param response HttpServletResponse对象
   */
    @RequestMapping(value = "/ExportReviewList",method = RequestMethod.GET) 
    @ResponseBody
    public void ExportReviewList(long caseid,int xh,@Context HttpServletResponse response){
    	 lawcaseEvaluationService.ExportReviewList(caseid, xh, response);
    }
    
    /**
     * 导出初评
     * @param accountId 案件id
     * @param userid 用户id
     * @param name 初评人姓名
     * @param item 该初评案件内容
     * @param response HttpServletResponse对象
     * @throws IOException
     */
    @RequestMapping(value = "/ExportFirstTrialSurvey",method = RequestMethod.GET) 
    @ResponseBody
    public void ExportFirstTrialSurvey( String accountId,String userid,String name,String item,@Context HttpServletResponse response) throws IOException{
    	lawcaseEvaluationService.ExportFirstTrialSurvey(accountId,userid,name,item,response);
    }
    @Autowired
    private LawcaseEvaluationService lawcaseEvaluationService;
}
