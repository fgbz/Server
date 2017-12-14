package phalaenopsis.lawcase.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import phalaenopsis.common.entity.User;
import phalaenopsis.common.entity.Attachment.Attachment;
import phalaenopsis.lawcase.entity.CaseAcceptUser;
import phalaenopsis.lawcase.entity.CaseBaseInfo;
import phalaenopsis.lawcase.entity.DeliverLawCaseRelation;
import phalaenopsis.lawcase.entity.IllegalClues;
import phalaenopsis.lawcase.entity.LitigantInfo;
import phalaenopsis.lawcase.entity.Signature;

@Repository("iLawCaseDao")
public interface ILawCaseDao{

	/**
	 *  检查受理编号是否存在重复
	 * @param obj
	 * @return
	 */
	int checkRegisterCaseNo (CaseBaseInfo obj);
	/**
	 * 保存违法线索登记
	 * @param clue
	 * @return
	 */
	int saveOrUpdateCluesRegister(IllegalClues clue);
	/**
	 * 删除受理人信息
	 * @return
	 */
	int deleteCaseAcceptUser(String caseID);
	/**
	 * 保存受理人信息
	 * @return
	 */
	int saveCaseAcceptUser(List<CaseAcceptUser> list);
	
	/**
	 * 删除当事人信息 
	 * @param caseID
	 * @return
	 */
	int deleteLitigantInfo(String caseID);
	
	/**
	 * 通过ID删除当事人信息 
	 * @param id
	 * @return
	 */
	int deleteLitigantInfoByID(String id);
	
	/**
	 * 保存当事人信息 
	 * @param list
	 * @return
	 */
	int saveLitigantInfo(List<LitigantInfo> list);
	/**
	 * 保存案件信息
	 * @param obj
	 * @return
	 */
	int saveOrUpdateCaseBase(CaseBaseInfo obj);
	/**
	 * 获取签证流程数据
	 * @return
	 */
	List<Signature> getSignatureList(Map<String, Object> map);
	
	/**
	 * 保存签证信息
	 * @param list
	 * @return
	 */
	int saveSignature(List<Signature> list);
	/**
	 * 删除签证信息
	 * @param list
	 * @return
	 */
	int deleteSignature(List<Signature> list);
	
	/**
	 * 查询列表信息
	 * @param map
	 * @return
	 */
	List<CaseBaseInfo> getCaseBaseInfoList(Map<String, Object> map);
	
	/**
	 * 获取查询列表总数
	 * @return
	 */
	int getCaseBaseInfoTotal(Map<String, Object> map);
	/**
	 * 获取最大的数据版本号
	 * @param caseID
	 * @return
	 */
	int getMaxVersion(String caseID);
	
	/**
	 * 通过caseid获取案件基本信息
	 * @return
	 */
	CaseBaseInfo getCaseBaseByCaseID(Map<String, Object> map);
	
	/**
	 * 通过流程id获取caseId
	 */
	String getCaseIDByInstanceID(String instanceID);
	
	/**
	 * 通过caseId删除案件相关数据
	 */
	void deleteCase(String caseID);
	
	/**
	 * 查询线索登记表信息
	 * @param caseID
	 * @return
	 */
	IllegalClues getIllegalClues(String caseID);
	
	/**
	 * 签字信息
	 * @param caseID
	 * @return
	 */
	List<Signature> getSignatureByCaseID(String caseID);
	
	/**
	 * 通过关联ID查询附件信息
	 * @return
	 */
	List<Attachment> getAttachmentByLinkID(String caseID);
	
	/**
	 * 判断是否已转违法案件
	 * @param ids
	 * @return
	 */
	int isDeliverToLawCase(@Param("ids") List<String> ids);
	
	/**
	 * 保存转违法案件
	 * @return
	 */
	boolean saveOrUpdateDeliverLawCase(DeliverLawCaseRelation rel);
	/**
	 * @param caseBaseInfo
	 * @return
	 * 根据基本信息中caseID和Version查询当事人信息
	 */
	List<LitigantInfo> queryLitigantInfo(CaseBaseInfo caseBaseInfo);
	/**
	 * @param caseBaseInfo
	 * @return
	 * 根据基本信息中caseID查询受理人信息
	 */
	List<CaseAcceptUser> queryCaseAcceptUser(CaseBaseInfo caseBaseInfo);
	/**
	 * @param signature
	 * 结案节点保存签字信息
	 */
	void saveSignatureFromNodeByEndCase(Signature signature);
	/**
	 * @param signature
	 * 结案节点更新签字信息
	 */
	void updateSignatureFromNodeByEndCase(Signature signature);
	/**
	 * @param string
	 * @desc 退回 删除签字信息
	 */
	void deleteSignatureByBack(String string);
	/**
	 * @param string
	 * 根据ID获取签字信息
	 */
	Signature querySignature(String id);
	/**
	 * @param s
	 * 根据ID更新签字信息
	 */
	void updateSignature(Signature s);
	/**
	 * @param map
	 * @return
	 * 获取签字总数
	 */
	int getSignatureCount(Map<String, Object> map);
	/**
	 * @param sign
	 * 结案节点提交签字 保存或更新
	 */
	int saveOrUpdateSignature(Signature sign);
	/**
	 * @param map
	 * @return
	 * @desc 获取下一环节个数
	 */
	int getNextNodeCount(Map<String, Object> map);
	/**
	 * @param sign
	 * @return
	 * 获取创建人id
	 */
	String queryCreateUserId(Signature sign);
	/**
	 * @param signorg
	 * @return
	 * 根据组织机构ID获取用户ID
	 */
	List<User> getUserIdByOrganizationID(String orgId);
	/**
	 * @param roleid
	 * @return
	 * 根据角色ID获取用户ID
	 */
	List<String> getUserIdByRoleID(String roleid);
	/**
	 * @param caseID
	 * @return
	 * 根据caseID 查询law_caseacceptuser表获取到acceptuserID，再通过acceptuserID查询sys_user表中获取username
	 */
	List<CaseAcceptUser> getAcceptUserFromCaseId(String caseID);
	/**
	 * @param userId
	 * @return
	 * 通过userID获取username
	 */
	String getUserNameFromUserId(String userId);
	/**
	 * @param caseID
	 * @return
	 * 根据caseID获取违法发生时间
	 */
	Date getIllegalDataFromCaseId(String caseID);
	/**
	 * @param caseID
	 * @return
	 * 获取当事人
	 */
	List<LitigantInfo> getLitigantInfo(String caseID);
}
