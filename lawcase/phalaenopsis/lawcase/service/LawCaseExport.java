package phalaenopsis.lawcase.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.velocity.app.event.ReferenceInsertionEventHandler.referenceInsertExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import phalaenopsis.common.method.Tools.DateUtils;
import phalaenopsis.common.method.Tools.StrUtil;
import phalaenopsis.common.service.DataDictionaryService;
import phalaenopsis.lawcase.dao.ILawCaseDao;
import phalaenopsis.lawcase.entity.CaseBaseInfo;
import phalaenopsis.lawcase.entity.IllegalClues;
import phalaenopsis.lawcase.entity.InterrogationRecord;
import phalaenopsis.lawcase.entity.LitigantInfo;
import phalaenopsis.lawcase.entity.PenaltyDecision;
import phalaenopsis.lawcase.entity.PunishDocument;
import phalaenopsis.lawcase.entity.QuestionAndAnswer;
import phalaenopsis.lawcase.entity.Signature;
import phalaenopsis.lawcase.entity.SurveyDocument;
import phalaenopsis.lawcase.entity.TrialRecord;
import phalaenopsis.lawcase.entity.document.Document;
import phalaenopsis.lawcase.entity.execute.ExecuteDocument;
import phalaenopsis.lawcase.entity.execute.ExecutePunishRecord;
import phalaenopsis.lawcase.sign.SignDefinition;

@Service("lawCaseExport")
/**
 * 导出docx
 * 
 * @author dongdongt
 *
 */
public class LawCaseExport {

	@Autowired
	private ILawCaseDao dao;

	@Autowired
	private LawCaseDocument caseDocument;

	@Autowired
	private LawCaseService lawCaseService;

	@Autowired
	private DataDictionaryService dictionaryService;

	private String docPath;

	public LawCaseExport() {
		super();
		this.docPath = this.getClass().getResource("/phalaenopsis/lawcase").getPath();
		if (-1 != docPath.indexOf("/")) {
			docPath = docPath.substring(1);
		}
	}

	/**
	 * 处理字符串为空的情况
	 * 
	 * @param content
	 * @return
	 */
	private String convetStr(String content) {
		if (StrUtil.isNullOrEmpty(content))
			return "";
		else
			return content;
	}

	private void baseExport(Map<String, String> map, String name, HttpServletResponse response) {
		// 获取模块路径
		try {
			InputStream inputStream = new FileInputStream(docPath + "/wordTemplates/" + name);

			// 初始化doc对象
			HWPFDocument doc = new HWPFDocument(inputStream);
			Range range = doc.getRange();
			// 填充内容
			for (Map.Entry<String, String> item : map.entrySet()) {
				range.replaceText(item.getKey(), item.getValue());
			}
			// 日期
			response.setContentType("application/x-msdownload");
			response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(name, "utf-8"));
			OutputStream out = response.getOutputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			doc.write(baos);
			byte[] xlsBytes = baos.toByteArray();
			out.write(xlsBytes);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 导出调查报告
	 * 
	 * @param caseID
	 */
	public void exportSurveyReport(String caseID, HttpServletResponse response) throws Exception {
		// 首先获取调查报告信息内容
		String CaseReason = "", SurveyOrgan = "", Undertaker = "", SurveyTime = "", LitigantSituation = "",
				Address = "", IllegalTruth = "", LawlessNature = "", HandleSuggestion = "", Date = "";
		SurveyDocument surveyDocument = caseDocument.getSurveyDocument(caseID);

		if (null != surveyDocument) {
			JSONObject report = surveyDocument.getSurveyReport().getInfo();
			if (null != report) {
				CaseReason = report.getString("CaseReason") == null ? "" : report.getString("CaseReason");
				SurveyOrgan = report.getString("SurveyOrgan") == null ? "" : report.getString("SurveyOrgan");
				Undertaker = report.getString("Undertaker") == null ? "" : report.getString("Undertaker");
				SurveyTime = StrUtil.formatCNDate(report.getString("SurveyStartTime")) + "-"
						+ StrUtil.formatCNDate(report.getString("SurveyEndTime"));
				LitigantSituation = report.getString("LitigantSituation") == null ? ""
						: report.getString("LitigantSituation");
				Address = report.getString("Address") == null ? "" : report.getString("Address");
				IllegalTruth = report.getString("IllegalTruth") == null ? "" : report.getString("IllegalTruth");

				String lawlessNature = report.getString("LawlessNature");
				if (!StrUtil.isNullOrEmpty(lawlessNature)) {
					lawlessNature = dictionaryService.getDictionaryText(lawlessNature, "LawlessNature", "LawCase");
					LawlessNature = lawlessNature;
				} else {
					LawlessNature = "";
				}

				if ("1".equals(report.getString("CheckedNo"))) {
					HandleSuggestion = "责令退还非法占用的土地，没收在非法占用的土地上新建的建筑物和其他设施，并处罚款。";
				} else if ("2".equals(report.getString("CheckedNo"))) {
					HandleSuggestion = "责令退还非法占用的土地，限期拆除在非法占用的土地上新建的建筑物和其他设施，恢复土地原状，并处罚款。";
				} else {
					HandleSuggestion = report.getString("HandleSuggestion") == null ? ""
							: report.getString("HandleSuggestion");
				}

				Date = report.getString("Date") == null ? "" : StrUtil.formatCNDate(report.getString("Date"));
			}
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("{CaseReason}", CaseReason);
		map.put("{SurveyOrgan}", SurveyOrgan);
		map.put("{Undertaker}", Undertaker);
		map.put("{SurveyTime}", SurveyTime);
		map.put("{LitigantSituation}", LitigantSituation);
		map.put("{Address}", Address);
		map.put("{IllegalTruth}", IllegalTruth);
		map.put("{LawlessNature}", LawlessNature);
		map.put("{HandleSuggestion}", HandleSuggestion);
		map.put("{Date}", Date);

		baseExport(map, "调查报告.doc", response);

		// try {
		// // 获取模块路径
		// InputStream inputStream = new FileInputStream(docPath +
		// "/wordTemplates/调查报告.doc");
		// // 初始化doc对象
		// HWPFDocument doc = new HWPFDocument(inputStream);
		// Range range = doc.getRange();
		// // 填充内容
		//
		// // range.replaceText("{CaseReason}",
		// // report.getString("CaseReason") == null ? "" :
		// // report.getString("CaseReason"));
		// // range.replaceText("{SurveyOrgan}",
		// // report.getString("SurveyOrgan") == null ? "" :
		// // report.getString("SurveyOrgan"));
		// // range.replaceText("{Undertaker}",
		// // report.getString("Undertaker") == null ? "" :
		// // report.getString("Undertaker"));
		//
		// // String surveyTime =
		// // StrUtil.formatCNDate(report.getString("SurveyStartTime")) + "-"
		// // + StrUtil.formatCNDate(report.getString("SurveyEndTime")); //
		// // .getSurveyEndTime();//
		// // 调查时间
		// // range.replaceText("{SurveyTime}", surveyTime);// 调查时间
		// // range.replaceText("{LitigantSituation}",
		// // report.getString("LitigantSituation") == null ? "" :
		// // report.getString("LitigantSituation"));
		// range.replaceText("{Address}", report.getString("Address") == null ?
		// "" : report.getString("Address"));
		// range.replaceText("{IllegalTruth}",
		// report.getString("IllegalTruth") == null ? "" :
		// report.getString("IllegalTruth"));
		//
		// String lawlessNature = report.getString("LawlessNature");
		// if (!StrUtil.isNullOrEmpty(lawlessNature)) {
		// lawlessNature = dictionaryService.getDictionaryText(lawlessNature,
		// "LawlessNature", "LawCase");
		// //
		// dictionaryService.getDictionaryText(report.getString("LawlessNatureDetail"),
		// // "LawlessNature", "LawCase");
		// range.replaceText("{LawlessNature}", lawlessNature);
		// } else {
		// range.replaceText("{LawlessNature}", "");
		// }
		//
		// String suggestion = "";
		// if ("1".equals(report.getString("CheckedNo"))) {
		// suggestion = "责令退还非法占用的土地，没收在非法占用的土地上新建的建筑物和其他设施，并处罚款。";
		// } else if ("2".equals(report.getString("CheckedNo"))) {
		// suggestion = "责令退还非法占用的土地，限期拆除在非法占用的土地上新建的建筑物和其他设施，恢复土地原状，并处罚款。";
		// } else {
		// suggestion = report.getString("HandleSuggestion") == null ? "" :
		// report.getString("HandleSuggestion");
		// }
		// range.replaceText("{HandleSuggestion}", suggestion);
		// // 日期
		// response.setContentType("application/x-msdownload");
		// response.setHeader("content-disposition",
		// "attachment;filename=" + URLEncoder.encode("调查报告", "utf-8") +
		// ".doc");
		// OutputStream out = response.getOutputStream();
		// ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// doc.write(baos);
		// byte[] xlsBytes = baos.toByteArray();
		// out.write(xlsBytes);
		// out.close();
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	/**
	 * 导出询问笔录
	 * 
	 * @param caseID
	 * @param response
	 * @throws Exception
	 */
	public void exportInterrogationRecord(String caseID, HttpServletResponse response) throws Exception {
		SurveyDocument surveyDocument = caseDocument.getSurveyDocument(caseID);
		Document inRecord = surveyDocument.getInterrogationRecord();
		InterrogationRecord report = JSONObject.parseObject(inRecord.getInfo().toString(), InterrogationRecord.class);
		try {
			// 获取模块路径
			// String path = LawCaseExport.class.getResource("").getPath();
			InputStream inputStream = new FileInputStream(docPath + "/wordTemplates/询问笔录.doc");
			// 初始化doc对象
			HWPFDocument doc = new HWPFDocument(inputStream);
			Range range = doc.getRange();
			// 填充内容
			range.replaceText("{CaseReason}", report.getCaseReason() == null ? "" : report.getCaseReason());
			range.replaceText("{Year}", report.getYear() == null ? "" : report.getYear());
			range.replaceText("{Month}", report.getMonth() == null ? "" : report.getMonth());
			range.replaceText("{Day}", report.getDay() == null ? "" : report.getDay());
			range.replaceText("{StartHour}", report.getStartHour() == null ? "" : report.getStartHour());
			range.replaceText("{EndHour}", report.getEndHour() == null ? "" : report.getEndHour());
			range.replaceText("{Address}", report.getAddress() == null ? "" : report.getAddress());
			range.replaceText("{Questioner}", report.getQuestioner() == null ? "" : report.getQuestioner());
			range.replaceText("{QUnitAndPosition}",
					report.getqUnitAndPosition() == null ? "" : report.getqUnitAndPosition());
			range.replaceText("{Noter}", report.getNoter() == null ? "" : report.getNoter());
			range.replaceText("{NUnitAndPosition}",
					report.getnUnitAndPosition() == null ? "" : report.getnUnitAndPosition());
			range.replaceText("{Interrogee}", report.getInterrogee() == null ? "" : report.getInterrogee());
			range.replaceText("{Relationship}", report.getRelationship() == null ? "" : report.getRelationship());
			range.replaceText("{IDCardNo}", report.getIdCardNo() == null ? "" : report.getIdCardNo());
			range.replaceText("{IUnitAndPosition}",
					report.getiUnitAndPosition() == null ? "" : report.getiUnitAndPosition());
			range.replaceText("{AddressAndPhone}",
					report.getAddressAndPhone() == null ? "" : report.getAddressAndPhone());
			range.replaceText("{ShowIdentity}", report.getShowIdentity() == null ? "" : report.getShowIdentity());
			range.replaceText("{RightsAndObligations}",
					report.getRightsAndObligations() == null ? "" : report.getRightsAndObligations());
			// 询问记录
			List<QuestionAndAnswer> andAnswers = report.getQuestionRecord();
			String stringText = "";
			if (andAnswers.size() > 0) {
				for (QuestionAndAnswer answer : andAnswers) {
					stringText += "问:" + answer.getQuestion() + "答:" + answer.getAnswer();
				}
			}
			range.replaceText("{Text}", stringText == null ? "" : stringText);// 根据调查报告
			response.setContentType("application/x-msdownload");
			response.setHeader("content-disposition",
					"attachment;filename=" + URLEncoder.encode("询问笔录", "utf-8") + ".doc");
			OutputStream out = response.getOutputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			doc.write(baos);
			byte[] xlsBytes = baos.toByteArray();
			out.write(xlsBytes);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 导出违法线索登记表
	 * 
	 * @param caseID
	 * @return
	 */
	public void exportIllegalClues(String caseID, HttpServletResponse response) throws Exception {
		try {
			// 获取模块路径
			InputStream inputStream = new FileInputStream(docPath + "/wordTemplates/违法线索登记表.doc");
			// 查询出违法线索登记表信息
			IllegalClues clues = dao.getIllegalClues(caseID);// 查询出违法线索登记表信息
			clues.setSignatures(dao.getSignatureByCaseID(caseID));
			// 初始化doc对象
			HWPFDocument doc = new HWPFDocument(inputStream);
			Range range = doc.getRange();
			// 填充内容
			range.replaceText("{CluesCode}",
					convetStr(clues.getCluesCode()) == null ? "" : convetStr(clues.getCluesCode()));
			//获取线索来源字典数据
			String caseSourceName ="";
			if (!StrUtil.isNullOrEmpty(clues.getCaseSource())) {
				caseSourceName = dictionaryService.getDictionaryText(clues.getCaseSource(), "CaseSource", "LawCase");
			} 
			range.replaceText("{CaseSourceName}",caseSourceName);
			range.replaceText("{Person}", convetStr(clues.getPerson()) == null ? "" : convetStr(clues.getPerson()));
			range.replaceText("{Address}", convetStr(clues.getAddress()) == null ? "" : convetStr(clues.getAddress()));
			range.replaceText("{PhoneNum}",
					convetStr(clues.getPhonenum()) == null ? "" : convetStr(clues.getPhonenum()));
			range.replaceText("{IllegalAddress}",
					convetStr(clues.getIllegaladdress()) == null ? "" : convetStr(clues.getIllegaladdress()));
			range.replaceText("{CluesContent}",
					convetStr(clues.getCluescontent()) == null ? "" : convetStr(clues.getCluescontent()));
			// 获取经办人信息
			List<Signature> signatures = clues.getSignatures();
			if (signatures.size() > 0) {
				for (Signature signature : signatures) {
					if (signature.getSignedNode().equals("Operator")) {
						range.replaceText("{Operator}",
								convetStr(signature.getIdea()) == null ? "" : convetStr(signature.getIdea()));
						range.replaceText("{OperatorDate}",
								convetStr(DateUtils.formatDateStr(signature.getSignDate())) == null ? ""
										: convetStr(DateUtils.formatDateStr(signature.getSignDate())));// 日期格式化显示格式
					} else if (signature.getSignedNode().equals("DepartmentCharge")) {
						range.replaceText("{DepartmentCharge}",
								convetStr(signature.getIdea()) == null ? "" : convetStr(signature.getIdea()));
						range.replaceText("{DepartmentChargeDate}",
								convetStr(DateUtils.formatDateStr(signature.getSignDate())) == null ? ""
										: convetStr(DateUtils.formatDateStr(signature.getSignDate())));
					} else if (signature.getSignedNode().equals("BranchCharge")) {
						range.replaceText("{BranchCharge}",
								convetStr(signature.getIdea()) == null ? "" : convetStr(signature.getIdea()));
						range.replaceText("{BranchChargeDate}",
								convetStr(DateUtils.formatDateStr(signature.getSignDate())) == null ? ""
										: convetStr(DateUtils.formatDateStr(signature.getSignDate())));
					} else {
						range.replaceText("{BureauCharge}",
								convetStr(signature.getIdea()) == null ? "" : convetStr(signature.getIdea()));
						range.replaceText("{BureauChargeDate}",
								convetStr(DateUtils.formatDateStr(signature.getSignDate())) == null ? ""
										: convetStr(DateUtils.formatDateStr(signature.getSignDate())));
					}
				}
			}
			// 防止导出出现问题
			range.replaceText("{CluesCode}", "");
			range.replaceText("{CaseSourceName}", "");// 线索来源,从数据字典表中查询，后面完善
			range.replaceText("{Person}", "");
			range.replaceText("{Address}", "");
			range.replaceText("{PhoneNum}", "");
			range.replaceText("{IllegalAddress}", "");
			range.replaceText("{CluesContent}", "");
			range.replaceText("{Operator}", "");
			range.replaceText("{OperatorDate}", "");
			range.replaceText("{DepartmentCharge}", "");
			range.replaceText("{DepartmentChargeDate}", "");
			range.replaceText("{BranchCharge}", "");
			range.replaceText("{BranchChargeDate}", "");
			range.replaceText("{BureauCharge}", "");
			range.replaceText("{BureauChargeDate}", "");

			response.setContentType("application/x-msdownload");
			response.setHeader("content-disposition",
					"attachment;filename=" + URLEncoder.encode("违法线索登记表", "utf-8") + ".doc");
			OutputStream out = response.getOutputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			doc.write(baos);
			byte[] xlsBytes = baos.toByteArray();
			out.write(xlsBytes);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 导出立案呈批表
	 * 
	 * @param caseID
	 * @return
	 * @throws IOException
	 */
	public void exportBuild(String caseID, HttpServletResponse response) throws IOException {
		try {
			// 获取模块路径
			InputStream inputStream = new FileInputStream(docPath + "/wordTemplates/立案呈批表.doc");
			// 查询立案呈批表信息
			CaseBaseInfo baseInfo = lawCaseService.getCase(caseID);// 获取案件列表信息
			// 初始化doc对象
			HWPFDocument doc = new HWPFDocument(inputStream);
			Range range = doc.getRange();
			// 填充内容
			range.replaceText("{RegisterCaseNo}",
					convetStr(baseInfo.getRegisterCaseNo()) == null ? "" : convetStr(baseInfo.getRegisterCaseNo()));
			range.replaceText("{CaseReason}",
					convetStr(baseInfo.getCaseReason()) == null ? "" : convetStr(baseInfo.getCaseReason()));
			//
			String names = "";
			String phones = "";
			String address = "";
			String zipcodes = "";
			if (baseInfo.getLitigantInfos().size() > 0) {
				for (LitigantInfo info : baseInfo.getLitigantInfos()) {
					names += info.getPersonalName() + ",";
					phones += info.getPersonalPhone() + ",";
					address += info.getPersonalAddress() + ",";
					zipcodes += info.getPersonalZipCode() + ",";
				}
			}
			range.replaceText("{names}",
					names.substring(0, names.length() - 1) == null ? "" : names.substring(0, names.length() - 1));
			range.replaceText("{phones}",
					phones.substring(0, phones.length() - 1) == null ? "" : phones.substring(0, phones.length() - 1));
			range.replaceText("{address}", address.substring(0, address.length() - 1) == null ? ""
					: address.substring(0, address.length() - 1));
			range.replaceText("{zipcodes}", zipcodes.substring(0, zipcodes.length() - 1) == null ? ""
					: zipcodes.substring(0, zipcodes.length() - 1));
			String caseSource = dictionaryService.getDictionaryText(baseInfo.getCaseSource(), "CaseSource", "LawCase");
			range.replaceText("{CaseSource}", caseSource == null ? "" : caseSource);// 线索来源
			range.replaceText("{IllegalTruth}", baseInfo.getIllegalTruth() == null ? "" : baseInfo.getIllegalTruth());
			// 获取签字意见信息
			List<Signature> signatures = new ArrayList<Signature>();
			for (Signature sg : baseInfo.getSignatureList()) {
				if (sg.getTemplateID().equals(SignDefinition.Build)) {
					signatures.add(sg);
				}
			}
			if (signatures.size() > 0) {
				for (Signature signature : signatures) {
					if (signature.getSignedNode().equals("Operator")) {
						range.replaceText("{Operator}",
								convetStr(signature.getIdea()) == null ? "" : convetStr(signature.getIdea()));
						range.replaceText("{OperatorDate}",
								convetStr(DateUtils.formatDateStr(signature.getSignDate())) == null ? ""
										: convetStr(DateUtils.formatDateStr(signature.getSignDate())));// 日期格式化显示格式
					} else if (signature.getSignedNode().equals("DepartmentCharge")) {
						range.replaceText("{DepartmentCharge}",
								convetStr(signature.getIdea()) == null ? "" : convetStr(signature.getIdea()));
						range.replaceText("{DepartmentChargeDate}",
								convetStr(DateUtils.formatDateStr(signature.getSignDate())) == null ? ""
										: convetStr(DateUtils.formatDateStr(signature.getSignDate())));
					} else if (signature.getSignedNode().equals("BranchCharge")) {
						range.replaceText("{BranchCharge}",
								convetStr(signature.getIdea()) == null ? "" : convetStr(signature.getIdea()));
						range.replaceText("{BranchChargeDate}",
								convetStr(DateUtils.formatDateStr(signature.getSignDate())) == null ? ""
										: convetStr(DateUtils.formatDateStr(signature.getSignDate())));
					} else if (signature.getSignedNode().equals("BureauCharge")) {
						range.replaceText("{BureauCharge}",
								convetStr(signature.getIdea()) == null ? "" : convetStr(signature.getIdea()));
						range.replaceText("{BureauChargeDate}",
								convetStr(DateUtils.formatDateStr(signature.getSignDate())) == null ? ""
										: convetStr(DateUtils.formatDateStr(signature.getSignDate())));
					} else if (signature.getSignedNode().equals("BureauLeader")) {
						range.replaceText("{BureauLeader}",
								convetStr(signature.getIdea()) == null ? "" : convetStr(signature.getIdea()));
						range.replaceText("{BureauLeaderDate}",
								convetStr(DateUtils.formatDateStr(signature.getSignDate())) == null ? ""
										: convetStr(DateUtils.formatDateStr(signature.getSignDate())));
					} else if (signature.getSignedNode().equals("BureauManager")) {
						range.replaceText("{BureauManager}",
								convetStr(signature.getIdea()) == null ? "" : convetStr(signature.getIdea()));
						range.replaceText("{BureauManagerDate}",
								convetStr(DateUtils.formatDateStr(signature.getSignDate())) == null ? ""
										: convetStr(DateUtils.formatDateStr(signature.getSignDate())));
					}
				}
			}
			// 防止导出有问题
			range.replaceText("{RegisterCaseNo}", "");
			range.replaceText("{CaseReason}", "");
			range.replaceText("{names}", "");
			range.replaceText("{phones}", "");
			range.replaceText("{address}", "");
			range.replaceText("{zipcodes}", "");
			range.replaceText("{CaseSource}", "");// 线索来源
			range.replaceText("{IllegalTruth}", "");
			range.replaceText("{Operator}", "");
			range.replaceText("{OperatorDate}", "");
			range.replaceText("{DepartmentCharge}", "");
			range.replaceText("{DepartmentChargeDate}", "");
			range.replaceText("{BranchCharge}", "");
			range.replaceText("{BranchChargeDate}", "");
			range.replaceText("{BureauCharge}", "");
			range.replaceText("{BureauChargeDate}", "");
			range.replaceText("{BureauLeader}", "");
			range.replaceText("{BureauLeaderDate}", "");
			range.replaceText("{BureauManager}", "");
			range.replaceText("{BureauManagerDate}", "");

			response.setContentType("application/x-msdownload");
			response.setHeader("content-disposition",
					"attachment;filename=" + URLEncoder.encode("立案呈批表", "utf-8") + ".doc");
			OutputStream out = response.getOutputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			doc.write(baos);
			byte[] xlsBytes = baos.toByteArray();
			out.write(xlsBytes);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 导出审理记录
	 * 
	 * @param caseID
	 * @return
	 * @throws IOException
	 */
	public void exportTrialRecord(String caseID, HttpServletResponse response) throws IOException {
		try {
			// 获取模块路径
			InputStream inputStream = new FileInputStream(docPath + "/wordTemplates/违法案件审理记录.doc");
			// 获取违法案件审理记录
			Document trialRecord = caseDocument.getTrialRecord(caseID);
			JSONObject report = trialRecord.getInfo();
			TrialRecord record = JSON.parseObject(report.toString(), TrialRecord.class);
			// 初始化doc对象
			HWPFDocument doc = new HWPFDocument(inputStream);
			Range range = doc.getRange();
			// 填充内容
			range.replaceText("{CaseNameAndNo}", convetStr(record.getCaseNameAndNo()));
			range.replaceText("{TrialTime}", convetStr(DateUtils.formatDateStr(record.getTrialTime())));
			range.replaceText("{TrialAddress}", convetStr(record.getTrialAddress()));
			range.replaceText("{Host}", convetStr(record.getHost()));
			range.replaceText("{Recorder}", convetStr(record.getRecorder()));
			range.replaceText("{TrialPerson}", convetStr(record.getTrialPerson()));
			range.replaceText("{AttendStaff}", convetStr(record.getAttendStaff()));
			range.replaceText("{Undertaker}", convetStr(record.getUndertaker()));
			range.replaceText("{Record}", convetStr(record.getRecord()));
			range.replaceText("{TrialSuggestion}", convetStr(record.getTrialSuggestion()));
			response.setContentType("application/x-msdownload");
			response.setHeader("content-disposition",
					"attachment;filename=" + URLEncoder.encode("违法案件审理记录", "utf-8") + ".doc");
			OutputStream out = response.getOutputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			doc.write(baos);
			byte[] xlsBytes = baos.toByteArray();
			out.write(xlsBytes);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 导出处理决定呈批表
	 * 
	 * @param caseID
	 * @return
	 * @throws IOException
	 */
	public void exportDeal(String caseID, HttpServletResponse response) throws IOException {
		try {
			// 获取模块路径
			// String path = LawCaseExport.class.getResource("").getPath();
			InputStream inputStream = new FileInputStream(docPath + "/wordTemplates/违法案件处理决定呈批表.doc");
			// 获取信息
			CaseBaseInfo deal = lawCaseService.getCase(caseID);// 获取案件列表信息
			// 初始化doc对象
			HWPFDocument doc = new HWPFDocument(inputStream);
			Range range = doc.getRange();
			// 填充内容
			range.replaceText("{CaseName}", deal.getCaseReason() == null ? "" : deal.getCaseReason());// 案件名称...后面完善
			String caseSourceName ="";
			if (!StrUtil.isNullOrEmpty(deal.getCaseSource())) {
				caseSourceName = dictionaryService.getDictionaryText(deal.getCaseSource(), "CaseSource", "LawCase");
			} 
			range.replaceText("{CaseSource}", caseSourceName);// 案件来由...后面完善
			range.replaceText("{BuildTime}", DateUtils.formatDateStr(deal.getBuildTime()) == null ? ""
					: DateUtils.formatDateStr(deal.getBuildTime()));
			range.replaceText("{CaseNo}", deal.getRegisterCaseNo() == null ? "" : deal.getRegisterCaseNo());
			String names = "";
			String phones = "";
			String address = "";
			String zipcodes = "";
			if (deal.getLitigantInfos().size() > 0) {
				for (LitigantInfo info : deal.getLitigantInfos()) {
					names += info.getPersonalName() + ",";
					phones += info.getPersonalPhone() + ",";
					address += info.getPersonalAddress() + ",";
					zipcodes += info.getPersonalZipCode() + ",";
				}
			}
			range.replaceText("{names}",
					names.substring(0, names.length() - 1) == null ? "" : names.substring(0, names.length() - 1));
			range.replaceText("{phones}",
					phones.substring(0, phones.length() - 1) == null ? "" : phones.substring(0, phones.length() - 1));
			range.replaceText("{address}", address.substring(0, address.length() - 1) == null ? ""
					: address.substring(0, address.length() - 1));
			range.replaceText("{zipcodes}", zipcodes.substring(0, zipcodes.length() - 1) == null ? ""
					: zipcodes.substring(0, zipcodes.length() - 1));
			range.replaceText("{DealIllegalTruth}",
					deal.getDealIllegalTruth() == null ? "" : deal.getDealIllegalTruth());
			// 获取签字意见信息
			List<Signature> signatures = new ArrayList<Signature>();
			for (Signature sg : deal.getSignatureList()) {
				if (sg.getTemplateID().equals(SignDefinition.Deal)) {
					signatures.add(sg);
				}
			}
			if (signatures.size() > 0) {
				for (Signature signature : signatures) {
					if (signature.getSignedNode().equals("Operator")) {
						range.replaceText("{Operator}", signature.getIdea() == null ? "" : signature.getIdea());
						range.replaceText("{OperatorDate}", DateUtils.formatDateStr(signature.getSignDate()) == null
								? "" : DateUtils.formatDateStr(signature.getSignDate()));// 日期格式化显示格式
					} else if (signature.getSignedNode().equals("DepartmentCharge")) {
						range.replaceText("{DepartmentCharge}", signature.getIdea() == null ? "" : signature.getIdea());
						range.replaceText("{DepartmentChargeDate}",
								DateUtils.formatDateStr(signature.getSignDate()) == null ? ""
										: DateUtils.formatDateStr(signature.getSignDate()));
					} else if (signature.getSignedNode().equals("BranchCharge")) {
						range.replaceText("{BranchCharge}", signature.getIdea() == null ? "" : signature.getIdea());
						range.replaceText("{BranchChargeDate}", DateUtils.formatDateStr(signature.getSignDate()) == null
								? "" : DateUtils.formatDateStr(signature.getSignDate()));
					} else if (signature.getSignedNode().equals("BureauCharge")) {
						range.replaceText("{BureauCharge}", signature.getIdea() == null ? "" : signature.getIdea());
						range.replaceText("{BureauChargeDate}", DateUtils.formatDateStr(signature.getSignDate()) == null
								? "" : DateUtils.formatDateStr(signature.getSignDate()));
					} else if (signature.getSignedNode().equals("BureauLeader")) {
						range.replaceText("{BureauLeader}", signature.getIdea() == null ? "" : signature.getIdea());
						range.replaceText("{BureauLeaderDate}", DateUtils.formatDateStr(signature.getSignDate()) == null
								? "" : DateUtils.formatDateStr(signature.getSignDate()));
					} else if (signature.getSignedNode().equals("BureauManager")) {
						range.replaceText("{BureauManager}", signature.getIdea() == null ? "" : signature.getIdea());
						range.replaceText("{BureauManagerDate}",
								DateUtils.formatDateStr(signature.getSignDate()) == null ? ""
										: DateUtils.formatDateStr(signature.getSignDate()));
					}
				}
			}
			// 防止导出
			range.replaceText("{CaseName}", deal.getCaseReason() == null ? "" : deal.getCaseReason());// 案件名称...后面完善
			range.replaceText("{CaseSource}", deal.getCaseSource() == null ? "" : deal.getCaseSource());// 案件来由...后面完善
			range.replaceText("{BuildTime}", DateUtils.formatDateStr(deal.getBuildTime()) == null ? ""
					: DateUtils.formatDateStr(deal.getBuildTime()));
			range.replaceText("{CaseNo}", "");
			range.replaceText("{names}",
					names.substring(0, names.length() - 1) == null ? "" : names.substring(0, names.length() - 1));
			range.replaceText("{phones}",
					phones.substring(0, phones.length() - 1) == null ? "" : phones.substring(0, phones.length() - 1));
			range.replaceText("{address}", address.substring(0, address.length() - 1) == null ? ""
					: address.substring(0, address.length() - 1));
			range.replaceText("{zipcodes}", zipcodes.substring(0, zipcodes.length() - 1) == null ? ""
					: zipcodes.substring(0, zipcodes.length() - 1));
			range.replaceText("{DealIllegalTruth}", "");
			range.replaceText("{Operator}", "");
			range.replaceText("{OperatorDate}", "");
			range.replaceText("{DepartmentCharge}", "");
			range.replaceText("{DepartmentChargeDate}", "");
			range.replaceText("{BranchCharge}", "");
			range.replaceText("{BranchChargeDate}", "");
			range.replaceText("{BureauCharge}", "");
			range.replaceText("{BureauChargeDate}", "");
			range.replaceText("{BureauLeader}", "");
			range.replaceText("{BureauLeaderDate}", "");
			range.replaceText("{BureauManager}", "");
			range.replaceText("{BureauManagerDate}", "");
			response.setContentType("application/x-msdownload");
			response.setHeader("content-disposition",
					"attachment;filename=" + URLEncoder.encode("违法案件处理决定呈批表", "utf-8") + ".doc");
			OutputStream out = response.getOutputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			doc.write(baos);
			byte[] xlsBytes = baos.toByteArray();
			out.write(xlsBytes);
			out.close();
			// TODO Auto-generated catch block
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 导出行政处罚决定书
	 * 
	 * @param caseID
	 * @return
	 * @throws IOException
	 */
	public void exportPenaltyDecision(String caseID, HttpServletResponse response) throws IOException {
		try {
			// 获取模块路径
			// String path = LawCaseExport.class.getResource("").getPath();
			InputStream inputStream = new FileInputStream(docPath + "/wordTemplates/行政处罚决定书.doc");
			// 获取内容
			PunishDocument document = caseDocument.GetPunishDocument(caseID);
			Document docPenalty = document.getPenaltyDecision();
			// PenaltyDecision penaltyDecision =
			// JSONObject.toJavaObject(docPenalty.getInfo(),
			// PenaltyDecision.class);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			PenaltyDecision penaltyDecision = gson.fromJson(docPenalty.getInfo().toJSONString(), PenaltyDecision.class);
			// 时间格式转换
			// 立案时间
			String timeStr = penaltyDecision.getBuildTime().substring(6, penaltyDecision.getBuildTime().length() - 2);
			String timeBuild = timeStr.substring(0, timeStr.length() - 5);
			Date dateBuild = new Date(Long.parseLong(timeBuild));
			// 表单落款日期
			String timeStr1 = penaltyDecision.getDate().substring(6, penaltyDecision.getDate().length() - 2);
			String timeDate = timeStr1.substring(0, timeStr1.length() - 5);
			Date date = new Date(Long.parseLong(timeDate));
			// 通知日期
			Date dateNotifyTime = null;
			if (penaltyDecision.getNotifyTime() != null) {
				String timeStr2 = penaltyDecision.getNotifyTime().substring(6,
						penaltyDecision.getNotifyTime().length() - 2);
				String timeNotifyTime = timeStr2.substring(0, timeStr2.length() - 5);
				dateNotifyTime = new Date(Long.parseLong(timeNotifyTime));
			}
			// 初始化doc对象
			HWPFDocument doc = new HWPFDocument(inputStream);
			Range range = doc.getRange();
			/**** 填充内容 **********/
			range.replaceText("{No}", penaltyDecision.getNo() == null ? "" : penaltyDecision.getNo());
			range.replaceText("{Litigant}", penaltyDecision.getLitigant() == null ? "" : penaltyDecision.getLitigant());
			range.replaceText("{BuildTime}",
					DateUtils.formatDateStr(dateBuild) == null ? "" : DateUtils.formatDateStr(dateBuild));
			range.replaceText("{CaseName}", penaltyDecision.getCaseName() == null ? "" : penaltyDecision.getCaseName());
			range.replaceText("{IllegalActs}",
					penaltyDecision.getIllegalActs() == null ? "" : penaltyDecision.getIllegalActs());
			range.replaceText("{Regulations}",
					penaltyDecision.getRegulations() == null ? "" : penaltyDecision.getRegulations());
			range.replaceText("{Evidences}",
					penaltyDecision.getEvidences() == null ? "" : penaltyDecision.getEvidences());
			range.replaceText("{NotifyTime}",
					DateUtils.formatDateStr(dateNotifyTime) == null ? "" : DateUtils.formatDateStr(dateNotifyTime));
			range.replaceText("{Disclaimer}",
					penaltyDecision.getDisclaimer() == null ? "" : penaltyDecision.getDisclaimer());
			range.replaceText("{PenaltyBasis}",
					penaltyDecision.getPenaltyBasis() == null ? "" : penaltyDecision.getPenaltyBasis());
			range.replaceText("{PenaltyContent}",
					penaltyDecision.getPenaltyContent() == null ? "" : penaltyDecision.getPenaltyContent());
			range.replaceText("{PenaltyPerformance}",
					penaltyDecision.getPenaltyPerformance() == null ? "" : penaltyDecision.getPenaltyPerformance());
			range.replaceText("{ReconsiderationDepartment}", penaltyDecision.getReconsiderationDepartment() == null ? ""
					: penaltyDecision.getReconsiderationDepartment());
			range.replaceText("{ReconsiderationDeadline}", penaltyDecision.getReconsiderationDeadline() == null ? ""
					: penaltyDecision.getReconsiderationDeadline());
			range.replaceText("{Contacts}", penaltyDecision.getContacts() == null ? "" : penaltyDecision.getContacts());
			range.replaceText("{Phone}", penaltyDecision.getPhone() == null ? "" : penaltyDecision.getPhone());
			range.replaceText("{Address}", penaltyDecision.getAddress() == null ? "" : penaltyDecision.getAddress());
			range.replaceText("{Date}", DateUtils.formatDateStr(date) == null ? "" : DateUtils.formatDateStr(date));

			// 防止导出有问题
			range.replaceText("{No}", "");
			range.replaceText("{Litigant}", "");
			range.replaceText("{BuildTime}", "");
			range.replaceText("{CaseName}", "");
			range.replaceText("{IllegalActs}", "");
			range.replaceText("{Regulations}", "");
			range.replaceText("{Evidences}", "");
			range.replaceText("{NotifyTime}", "");
			range.replaceText("{Disclaimer}", "");
			range.replaceText("{PenaltyBasis}", "");
			range.replaceText("{PenaltyContent}", "");
			range.replaceText("{PenaltyPerformance}", "");
			range.replaceText("{ReconsiderationDepartment}", "");
			range.replaceText("{ReconsiderationDeadline}", "");
			range.replaceText("{Contacts}", "");
			range.replaceText("{Phone}", "");
			range.replaceText("{Address}", "");
			range.replaceText("{Date}", "");
			/**** 填充内容 **********/
			response.setContentType("application/x-msdownload");
			response.setHeader("content-disposition",
					"attachment;filename=" + URLEncoder.encode("行政处罚决定书", "utf-8") + ".doc");
			OutputStream out = response.getOutputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			doc.write(baos);
			byte[] xlsBytes = baos.toByteArray();
			out.write(xlsBytes);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 导出行政处罚决定执行记录
	 * 
	 * @param caseID
	 * @return
	 * @throws IOException
	 */
	public void exportExecutePunishRecord(String caseID, HttpServletResponse response) throws IOException {
		try {
			// 获取模块路径
			InputStream inputStream = new FileInputStream(docPath + "/wordTemplates/行政处罚决定执行记录.doc");
			// 获取内容
			ExecuteDocument document = caseDocument.getExecuteDocument(caseID);
			Document document2 = document.getPunishExecutionRecord();
			ExecutePunishRecord prd = JSON.parseObject(document2.getInfo().toString(), ExecutePunishRecord.class);
			// 初始化doc对象
			HWPFDocument doc = new HWPFDocument(inputStream);
			Range range = doc.getRange();
			// 填充内容
			range.replaceText("{CaseNameAndNo}", convetStr(prd.getCaseNameAndNo()));
			range.replaceText("{Litigant}", convetStr(prd.getLitigant()));
			range.replaceText("{PenaltyContent}", convetStr(prd.getPenaltyContent()));
			range.replaceText("{ExecuteRecord}", convetStr(prd.getExecuteRecord()));
			range.replaceText("{Date}", convetStr(DateUtils.formatDateStr(prd.getDate())));
			response.setContentType("application/x-msdownload");
			response.setHeader("content-disposition",
					"attachment;filename=" + URLEncoder.encode("行政处罚决定执行记录", "utf-8") + ".doc");
			OutputStream out = response.getOutputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			doc.write(baos);
			byte[] xlsBytes = baos.toByteArray();
			out.write(xlsBytes);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 导出结案呈批表
	 * 
	 * @param caseID
	 * @return
	 * @throws IOException
	 */
	public void exportEndCase(String caseID, HttpServletResponse response) throws IOException {
		try {
			// 获取模块路径

			InputStream inputStream = new FileInputStream(docPath + "/wordTemplates/结案呈批表.doc");
			// 获取内容
			CaseBaseInfo endCase = lawCaseService.getCase(caseID);// 获取结案信息
			String litigants = "";
			String phones = "";
			if (endCase.getLitigantInfos().size() > 0) {
				for (LitigantInfo info : endCase.getLitigantInfos()) {
					litigants += info.getPersonalName() + ",";
					phones += info.getPersonalPhone() + ",";
				}
			}
			// 初始化doc对象
			HWPFDocument doc = new HWPFDocument(inputStream);
			Range range = doc.getRange();
			// 填充内容
			range.replaceText("{CaseName}", endCase.getCaseReason() == null ? "" : endCase.getCaseReason());
			String caseSource = dictionaryService.getDictionaryText(endCase.getCaseSource(), "CaseSource", "LawCase");
			range.replaceText("{CaseSource}", caseSource == null ? "" : caseSource);
			range.replaceText("{CaseNo}", endCase.getRegisterCaseNo() == null ? "" : endCase.getRegisterCaseNo());
			range.replaceText("{Litigants}", litigants.substring(0, litigants.length() - 1) == null ? ""
					: litigants.substring(0, litigants.length() - 1));
			range.replaceText("{Phones}",
					phones.substring(0, phones.length() - 1) == null ? "" : phones.substring(0, phones.length() - 1));
			range.replaceText("{CaseBrief}", endCase.getCaseBrief() == null ? "" : endCase.getCaseBrief());
			range.replaceText("{PunishmentContent}",
					endCase.getPunishmentContent() == null ? "" : endCase.getPunishmentContent());
			range.replaceText("{ExecutiveCondition}",
					endCase.getExecutiveCondition() == null ? "" : endCase.getExecutiveCondition());
			range.replaceText("{BuildTime}", DateUtils.formatDateStr(endCase.getBuildTime()) == null ? ""
					: DateUtils.formatDateStr(endCase.getBuildTime()));
			// 获取签字意见信息
			List<Signature> signatures = new ArrayList<Signature>();
			for (Signature sg : endCase.getSignatureList()) {
				if (sg.getTemplateID().equals(SignDefinition.EndCase)) {
					signatures.add(sg);
				}
			}
			if (signatures.size() > 0) {
				for (Signature signature : signatures) {
					if (signature.getSignedNode().equals("Operator")) {
						range.replaceText("{Operator}", signature.getIdea() == null ? "" : signature.getIdea());
						range.replaceText("{OperatorDate}", DateUtils.formatDateStr(signature.getSignDate()) == null
								? "" : DateUtils.formatDateStr(signature.getSignDate()));// 日期格式化显示格式
					} else if (signature.getSignedNode().equals("DepartmentCharge")) {
						range.replaceText("{DepartmentCharge}", signature.getIdea() == null ? "" : signature.getIdea());
						range.replaceText("{DepartmentChargeDate}",
								DateUtils.formatDateStr(signature.getSignDate()) == null ? ""
										: DateUtils.formatDateStr(signature.getSignDate()));
					} else if (signature.getSignedNode().equals("BranchCharge")) {
						range.replaceText("{BranchCharge}", signature.getIdea() == null ? "" : signature.getIdea());
						range.replaceText("{BranchChargeDate}", DateUtils.formatDateStr(signature.getSignDate()) == null
								? "" : DateUtils.formatDateStr(signature.getSignDate()));
					} else if (signature.getSignedNode().equals("BureauCharge")) {
						range.replaceText("{BureauCharge}", signature.getIdea() == null ? "" : signature.getIdea());
						range.replaceText("{BureauChargeDate}", DateUtils.formatDateStr(signature.getSignDate()) == null
								? "" : DateUtils.formatDateStr(signature.getSignDate()));
					} else if (signature.getSignedNode().equals("BureauLeader")) {
						range.replaceText("{BureauLeader}", signature.getIdea() == null ? "" : signature.getIdea());
						range.replaceText("{BureauLeaderDate}", DateUtils.formatDateStr(signature.getSignDate()) == null
								? "" : DateUtils.formatDateStr(signature.getSignDate()));
					} else if (signature.getSignedNode().equals("BureauManager")) {
						range.replaceText("{BureauManager}", signature.getIdea() == null ? "" : signature.getIdea());
						range.replaceText("{BureauManagerDate}",
								DateUtils.formatDateStr(signature.getSignDate()) == null ? ""
										: DateUtils.formatDateStr(signature.getSignDate()));
					}
				}
			}
			// 防止导出有问题
			range.replaceText("{CaseName}", "");
			range.replaceText("{CaseSource}", "");
			range.replaceText("{CaseNo}", "");
			range.replaceText("{Litigants}", "");
			range.replaceText("{Phones}", "");
			range.replaceText("{CaseBrief}", "");
			range.replaceText("{PunishmentContent}", "");
			range.replaceText("{ExecutiveCondition}", "");
			range.replaceText("{BuildTime}", "");
			range.replaceText("{Operator}", "");
			range.replaceText("{OperatorDate}", "");
			range.replaceText("{DepartmentCharge}", "");
			range.replaceText("{DepartmentChargeDate}", "");
			range.replaceText("{BranchCharge}", "");
			range.replaceText("{BranchChargeDate}", "");
			range.replaceText("{BureauCharge}", "");
			range.replaceText("{BureauChargeDate}", "");
			range.replaceText("{BureauLeader}", "");
			range.replaceText("{BureauLeaderDate}", "");
			range.replaceText("{BureauManager}", "");
			range.replaceText("{BureauManagerDate}", "");

			response.setContentType("application/x-msdownload");
			response.setHeader("content-disposition",
					"attachment;filename=" + URLEncoder.encode("结案呈批表", "utf-8") + ".doc");
			OutputStream out = response.getOutputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			doc.write(baos);
			byte[] xlsBytes = baos.toByteArray();
			out.write(xlsBytes);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 下载模板导出
	 * 
	 * @param keys
	 * @param values
	 * @param docName
	 * @param response
	 */
	public void exportUnsaveDoc(String keys, String values, String docName, HttpServletResponse response) {
		try {
			String templateFileName = docPath + "/wordTemplates/" + docName;
			String docFileName = docName.substring(0, docName.indexOf("."));
			if (new File(templateFileName).exists()) {
				InputStream inputStream = new FileInputStream(templateFileName);
				// 初始化doc对象
				HWPFDocument doc = new HWPFDocument(inputStream);
				// 表单赋值
				values = (values.replace("\\r\\n", "\r\n")).replace("//g", "");
				String[] fieldNames = keys.split(",");
				String[] fieldValues = values.split(",");
				Range range = doc.getRange();
				if (fieldNames != null && fieldValues != null) {
					for (int i = 0; i < fieldNames.length; i++) {
						String name = fieldNames[i];
						String value = "";
						if (fieldValues.length > i) {
							value = fieldValues[i];
						}

						range.replaceText("{" + name + "}", value);
//						if (-1 == value.indexOf("\\r\\n")) {
//							range.replaceText("{" + name + "}", value);
//						} else {
//							// value.replace(target, replacement)
//							// value.replace("\\r\\n", (char)11);
//							StringBuffer replacetext = new StringBuffer();
//							String[] strings = value.split("\\r\\n");
//							for (String string : strings) {
//								replacetext.append(string + (char) 11);
//							}
//							range.replaceText("{" + name + "}", replacetext.toString());
//						}
					}
					response.setContentType("application/x-msdownload");
					response.setHeader("content-disposition",
							"attachment;filename=" + URLEncoder.encode(docFileName, "utf-8") + ".doc");
					OutputStream out = response.getOutputStream();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					doc.write(baos);
					byte[] xlsBytes = baos.toByteArray();
					out.write(xlsBytes);
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
