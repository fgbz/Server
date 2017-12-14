package phalaenopsis.lawcase.dao;

import phalaenopsis.lawcase.entity.CaseDocument;


public interface ILawCaseDocumentDao extends IBaseDao<CaseDocument>{
	/**
	 * 保存行政处罚决定执行记录
	 * @param executePunishRecord
	 * @return
	 */
	int saveOrUpdateExecutePunishRecord(CaseDocument caseDocument);
	/**
	 * 获取执行节点所有文书(根据Id)
	 * @param caseID
	 * @param punishexecutionrecord 文档类型
	 * @return
	 */
	CaseDocument getExecuteDocument(String caseID,Integer type);
}
