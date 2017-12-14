package phalaenopsis.lawcase.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import phalaenopsis.lawcase.entity.CaseAcceptUser;
import phalaenopsis.lawcase.entity.CaseBaseInfo;
import phalaenopsis.lawcase.entity.LitigantInfo;


public interface ILawCaseServiceDao {

	/**
	 * 根据caseId获取基本信息数据
	 * 
	 * @param caseID
	 * @return
	 */
	CaseBaseInfo getCaseBaseInfo(@Param("caseID") String caseID);

	/**
	 * 更新CaseBaseInfo
	 * 
	 * @param info
	 * @return
	 */
	int updateCaseBaseInfo(CaseBaseInfo info);
	
	/**
	 * 获取对应案件的经办人
	 * @param caseID
	 * @return
	 */
	List<CaseAcceptUser> getAcceptUser(@Param("caseid") String caseID);

	
}
