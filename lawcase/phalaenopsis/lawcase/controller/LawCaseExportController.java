package phalaenopsis.lawcase.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import phalaenopsis.common.method.Tools.StrUtil;
import phalaenopsis.lawcase.service.LawCaseExport;

@Controller
@RequestMapping("/LawCase/LawCaseService")
public class LawCaseExportController {

	@Autowired
	private LawCaseExport lawCaseExport;

	/**
	 * 导出调查报告
	 * 
	 * @param caseID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ExportSurveyReport", method = RequestMethod.GET)
	@ResponseBody
	public void exportSurveyReport(@PathParam("caseID") String caseID, HttpServletResponse response) throws Exception {
		lawCaseExport.exportSurveyReport(caseID, response);
	}

	/**
	 * 导出询问笔录
	 * 
	 * @param caseID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ExportInterrogationRecord", method = RequestMethod.GET)
	@ResponseBody
	public void exportInterrogationRecord(@PathParam("caseID") String caseID, HttpServletResponse response)
			throws Exception {
		lawCaseExport.exportInterrogationRecord(caseID, response);
	}

	/**
	 * 导出违法线索登记表
	 * 
	 * @param caseID
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/ExportIllegalClues", method = RequestMethod.GET)
	@ResponseBody
	public void exportIllegalClues(@PathParam("caseID") String caseID, @Context HttpServletResponse response)
			throws Exception {
		lawCaseExport.exportIllegalClues(caseID, response);
	}

	/**
	 * 导出立案呈批表
	 * 
	 * @param caseID
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/ExportBuild", method = RequestMethod.GET)
	@ResponseBody
	public void exportBuild(@PathParam("caseID") String caseID, @Context HttpServletResponse response)
			throws IOException {
		lawCaseExport.exportBuild(caseID, response);
	}

	/**
	 * 导出违法案件审理记录
	 * 
	 * @param caseID
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/ExportTrialRecord", method = RequestMethod.GET)
	@ResponseBody
	public void exportTrialRecord(@PathParam("caseID") String caseID, @Context HttpServletResponse response)
			throws IOException {
		lawCaseExport.exportTrialRecord(caseID, response);
	}

	/**
	 * 导出违法案件处理决定呈批表
	 * 
	 * @param caseID
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/ExportDeal", method = RequestMethod.GET)
	@ResponseBody
	public void exportDeal(@PathParam("caseID") String caseID, @Context HttpServletResponse response)
			throws IOException {
		lawCaseExport.exportDeal(caseID, response);
	}

	/**
	 * 导出行政处罚决定书
	 * 
	 * @param caseID
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/ExportPenaltyDecision", method = RequestMethod.GET)
	@ResponseBody
	public void exportPenaltyDecision(@PathParam("caseID") String caseID, @Context HttpServletResponse response)
			throws IOException {
		lawCaseExport.exportPenaltyDecision(caseID, response);
	}

	/**
	 * 导出行政处罚决定执行记录
	 * 
	 * @param caseID
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/ExportExecutePunishRecord", method = RequestMethod.GET)
	@ResponseBody
	public void exportExecutePunishRecord(@PathParam("caseID") String caseID, @Context HttpServletResponse response)
			throws IOException {
		lawCaseExport.exportExecutePunishRecord(caseID, response);
	}

	/**
	 * 导出结案呈批表
	 * 
	 * @param caseID
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/ExportEndCase", method = RequestMethod.GET)
	@ResponseBody
	public void exportEndCase(@PathParam("caseID") String caseID, @Context HttpServletResponse response)
			throws IOException {
		lawCaseExport.exportEndCase(caseID, response);
	}

	/**
	 * 下载模板导出
	 * 
	 * @param caseID
	 * @param response
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	@RequestMapping(value = "/ExportUnsaveDoc", method = RequestMethod.GET)
	@ResponseBody
	public void exportUnsaveDoc(@PathParam("keys") String keys, @PathParam("values") String values,
			@PathParam("docName") String docName, @Context HttpServletResponse response) throws UnsupportedEncodingException {
		// values = StrUtil.convertCharacter(values);
		values = new String(values.getBytes("utf-8"), "utf-8");
		// docName = StrUtil.convertCharacter(docName);
		docName = new String(docName.getBytes("utf-8"), "utf-8");
		lawCaseExport.exportUnsaveDoc(keys, values, docName, response);
	}

}
