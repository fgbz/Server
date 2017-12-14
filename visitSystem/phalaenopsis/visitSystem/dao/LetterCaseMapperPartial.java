package phalaenopsis.visitSystem.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import phalaenopsis.visitSystem.entity.XfDeals;
import phalaenopsis.visitSystem.entity.XfRegister;

public interface LetterCaseMapperPartial {

	XfDeals getXfDeal(Map<String, Object> map);

	int saveXfDeal(XfDeals xfDeals);

	int updateXfRegisterAudit(Map<String, Object> map);

	List<XfDeals> getXfDeals(@Param("registerid") String registerId);
	
	String getParentOrgId(@Param("registerid") Long registerId, @Param("orgId") String id);
	
	String getNextOrg(@Param("registerid") Long registerId, @Param("grade") int orgGrade);

	int deleteXfDeals(@Param("registerid") String registerId);
}
