package phalaenopsis.lawcase.controller;

import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import phalaenopsis.lawcase.entity.PunishDocument;
import phalaenopsis.lawcase.entity.SurveyDocument;
import phalaenopsis.lawcase.entity.document.Document;
import phalaenopsis.lawcase.entity.execute.ExecuteDocument;
import phalaenopsis.lawcase.service.LawCaseDocument;

@Controller		 
@RequestMapping("/LawCase/LawCaseService")
public class LawCaseDocumentController {
	
	@Autowired
	private LawCaseDocument lawCaseDocument;
	
	/**
	 * 保存调查报告
	 * 
	 * @param info
	 * @return
	 */
	@RequestMapping(value = "/SaveSurveyReportDoc", method = RequestMethod.POST)
	@ResponseBody
	public String saveSurveyReportDoc(@RequestBody Document info) {	
		return lawCaseDocument.saveSurveyReportDoc(info);
	}

	/**
	 * 获得调查节点所有表单信息
	 * 
	 * @param caseID
	 * @return
	 */
	@RequestMapping(value = "/GetSurveyDocument", method = RequestMethod.GET)
	@ResponseBody
	public SurveyDocument getSurveyDocument(@RequestParam("caseID") String caseID) {
		return lawCaseDocument.getSurveyDocument(caseID);
	}

	
	
	/**
	 * 保存行政处罚决定执行记录
	 * 
	 * @param info
	 * @return
	 */
	@RequestMapping(value = "/SaveExecutePunishRecordDoc", method = RequestMethod.POST)
	@ResponseBody
	public String saveExecutePunishRecordDoc(@RequestBody Document info) {

		return lawCaseDocument.saveExecutePunishRecordDoc(info);

	}

	/**** end *****/
	/**
	 * 保存违法案件审理记录文书
	 * 
	 * @param info
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/SaveTrialRecord", method = RequestMethod.POST)
	@ResponseBody
	public String saveTrialRecord(@RequestBody Document  info) throws Exception {
		return lawCaseDocument.saveTrialRecord(info);
	}

	/**
	 * 获得违法案件审理记录文书
	 * 
	 * @param caseID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/GetTrialRecord", method = RequestMethod.GET)
	@ResponseBody
	public Document getTrialRecord(@RequestParam("caseID") String caseID){
		return lawCaseDocument.getTrialRecord(caseID);
	}



	/**
	 * 保存询问笔录表单
	 * 
	 * @param info
	 * @return
	 */
	@RequestMapping(value = "/SaveInterrogationRecordDoc", method = RequestMethod.POST)
	@ResponseBody
	public String saveInterrogationRecordDoc(@RequestBody Document info) {
		return lawCaseDocument.saveInterrogationRecordDoc(info);
	}
	

	/**
	 * 保存行政处罚决定书
	 * 
	 * @param info
	 * @return
	 */
	@RequestMapping(value = "/SavePenaltyDecisionDoc", method = RequestMethod.POST)
	@ResponseBody
	public String savePenaltyDecisionDoc(@RequestBody Document info) {
		return lawCaseDocument.savePenaltyDecisionDoc(info);
	}

	/**** 执行节点表单操作 *****/
	/**
	 * 获取执行节点所有文书
	 * 
	 * @param caseID
	 * @return
	 */
	@RequestMapping(value = "/GetExecuteDocument", method = RequestMethod.GET)
	@ResponseBody
	public ExecuteDocument GetExecuteDocument(String caseID) {
		return lawCaseDocument.getExecuteDocument(caseID);
	}

	
	/**
	 * 获取处罚决定节点所有文书
	 * @param caseID
	 * @return
	 */
	@RequestMapping(value = "/GetPunishDocument", method = RequestMethod.GET)
	@ResponseBody
	public PunishDocument GetPunishDocument(@PathParam("caseID") String caseID) {
		return lawCaseDocument.GetPunishDocument(caseID);
	}
	
	

}
