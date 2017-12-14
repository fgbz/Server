package phalaenopsis.lawcase.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import phalaenopsis.lawcase.dao.IDocumentDao;
import phalaenopsis.lawcase.dao.ILawCaseDao;
import phalaenopsis.lawcase.dao.ILawCaseServiceDao;
import phalaenopsis.lawcase.entity.CaseBaseInfo;
import phalaenopsis.lawcase.entity.CaseDocument;
import phalaenopsis.lawcase.entity.PunishDocument;
import phalaenopsis.lawcase.entity.SurveyDocument;
import phalaenopsis.lawcase.entity.TrialRecord;
import phalaenopsis.lawcase.entity.document.Document;
import phalaenopsis.lawcase.entity.document.DocumentType;
import phalaenopsis.lawcase.entity.execute.ExecuteDocument;

/**
 * 
 * @author dongdongt
 *
 */
@Service("lawCaseDocument")
public class LawCaseDocument {
	// @Autowired
	// private ILawCaseDocumentDao dao;

	@Autowired
	private IDocumentDao documentDao;

	@Autowired
	private ILawCaseDao caseDao;

	@Autowired
	private ILawCaseServiceDao caseServiceDao;

	/**
	 * 保存行政处罚决定执行记录
	 * 
	 * @param info
	 * @return
	 */
	 public String saveExecutePunishRecordDoc(Document info)
     {
		 if(info.getId()==null || info.getId().isEmpty())//判断
		 {
			 info.setId(UUID.randomUUID().toString());
		 }

		 CaseDocument doc=CaseDocument.GetCaseDocument(info);
		 documentDao.saveOrUpdate(doc);
		return JSON.toJSONString(info.getId());
	}


	 /**
	  * 获取执行节点所有文书
	  * @param caseID
	  * @return
	  */
	 public  ExecuteDocument getExecuteDocument(String caseID){
		ExecuteDocument doc=new ExecuteDocument();
		//查询执行节点的所有文书
		CaseDocument punishExecutionRecord=documentDao.getDocument(caseID, DocumentType.PunishExecutionRecord.getValue());  //.getExecuteDocument(caseID, DocumentType.PunishExecutionRecord.getValue());
		if(punishExecutionRecord==null){
			return null;
		}
		Document de=new Document ();
		de = de.GetDocument(punishExecutionRecord);//序列化对象
		doc.setPunishExecutionRecord(de);//给对象赋值
		return doc;
	}

	/**
	 * 保存行政处罚决定书
	 * 
	 * @param info
	 * @return
	 */
	public String savePenaltyDecisionDoc(Document info) {
		if (info.getId() == null || info.getId().isEmpty())// 判断是否为null
		{
			info.setId(UUID.randomUUID().toString());
		}
		CaseDocument doc = CaseDocument.GetCaseDocument(info);// 序列化
		documentDao.saveOrUpdate(doc);// 保存
		return JSONObject.toJSONString(info.getId());// 返回
	}

	/**
	 * 获取处罚决定节点所有文书
	 * 
	 * @return
	 * @throws Exception
	 */
	public PunishDocument getPunishDocument(String caseID) {
		PunishDocument doc = new PunishDocument();
		CaseDocument penaltyDecision = documentDao.getDocument(caseID,
				DocumentType.AdministrativePenaltyDecisionBook.getValue());
		Document pd = new Document();
		pd.GetDocument(penaltyDecision);// 序列化对象
		doc.setPenaltyDecision(pd);// 给对象赋值
		return doc;
	}

	/**
	 * 保存违法案件审理记录文书
	 * 
	 * @param info
	 * @return
	 */
	public String saveTrialRecord(Document info) {
		if (info.getId() == null || info.getId().isEmpty())// 判断是否为null
		{
			info.setId(UUID.randomUUID().toString());
			CaseDocument doc = CaseDocument.GetCaseDocument(info);// 序列化
			documentDao.saveOrUpdate(doc);// 保存
		}
		return JSON.toJSONString(info.getId());
	}

	/**
	 * 获得违法案件审理记录文书
	 * 
	 * @param caseID
	 * @return
	 * @throws Exception
	 */
	public Document getTrialRecord(String caseID) {
		CaseDocument trialRecord = documentDao.getDocument(caseID, DocumentType.TrialRecord.getValue());
		if(trialRecord!=null){
			Document doc = new Document();
			doc=doc.GetDocument(trialRecord);
			return doc;
		}
		else{
			return null;
		}
	}

	/**
	 * 获得调查节点所有表单信息
	 * 
	 * @param caseID
	 * @return
	 */
	public SurveyDocument getSurveyDocument(String caseID) {
		SurveyDocument doc = new SurveyDocument();
		// 询问笔录
		CaseDocument interrogationRecord = documentDao.getDocument(caseID, DocumentType.InterrogationRecord.getValue());
		Document ig = new Document();
		doc.setInterrogationRecord(ig.GetDocument(interrogationRecord));
		// 调查报告
		CaseDocument surveyReport = documentDao.getDocument(caseID, DocumentType.SurveyReport.getValue());
		Document sr = new Document();
		doc.setSurveyReport(sr.GetDocument(surveyReport));
		return doc;
	}

	/**
	 * 保存询问笔录表单
	 * 
	 * @param info
	 * @return
	 */
	public String saveInterrogationRecordDoc(Document info) {
		if (info.getId() == null || info.getId().isEmpty())// 判断是否为null
		{
			info.setId(UUID.randomUUID().toString());
			CaseDocument doc = CaseDocument.GetCaseDocument(info);// 序列化
			documentDao.saveOrUpdate(doc);// 保存
		}
		return JSON.toJSONString(info.getId());
	}

	/**
	 * 保存调查报告
	 * 
	 * @param info
	 * @return
	 */
	@Transactional
	public String saveSurveyReportDoc(Document info) {
		if (info.getId() == null || info.getId().isEmpty())// 判断是否为null
		{
			info.setId(UUID.randomUUID().toString());
		}
		CaseBaseInfo caseInfo = new CaseBaseInfo();
		caseInfo.setCaseID(info.getCaseID());
		caseInfo.setLawlessNature(info.getInfo().getString("LawlessNature"));
		caseInfo.setLawlessNatureDetail(info.getInfo().getString("LawlessNatureDetail"));
		int result = caseServiceDao.updateCaseBaseInfo(caseInfo);
		if (0 == result) {
			throw new RuntimeException(); //回滚事务
		}
		CaseDocument doc = CaseDocument.GetCaseDocument(info);
		documentDao.saveOrUpdate(doc);
		return JSON.toJSONString(info.getId());
	}

	/**
	 * 
	 * @param caseID
	 * @return
	 */
	public PunishDocument GetPunishDocument(String caseID) {
		PunishDocument doc = new PunishDocument();
		// 查询处罚决定节点所有文书
		CaseDocument punishExecutionRecord = documentDao.getDocument(caseID,
				DocumentType.AdministrativePenaltyDecisionBook.getValue()); // .getExecuteDocument(caseID,
																			// DocumentType.PunishExecutionRecord.getValue());
		if(punishExecutionRecord==null){
			return null;
		}
		Document de = new Document();
		de = de.GetDocument(punishExecutionRecord);// 序列化对象
//		 doc.setPunishExecutionRecord(de);//给对象赋值
		doc.setPenaltyDecision(de);
		return doc;
	}
}
