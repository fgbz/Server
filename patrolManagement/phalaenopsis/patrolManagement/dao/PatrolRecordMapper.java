package phalaenopsis.patrolManagement.dao;

import java.util.List;
import java.util.Map;

import phalaenopsis.common.entity.Organization;
import phalaenopsis.common.entity.User;
import phalaenopsis.patrolManagement.entity.PatrolLog;
import phalaenopsis.patrolManagement.entity.PatrolRecord;
import phalaenopsis.patrolManagement.entity.PatrolUserInfo;

public interface PatrolRecordMapper {

	int getAllRecordCount(Map<String, Object> map);

	List<PatrolRecord> getAllRecordList(Map<String, Object> map);

	int getRecordCount(Map<String, Object> map);

	List<PatrolRecord> getRecordList(Map<String, Object> map);
	
	List<PatrolLog> getListByCreateTime(PatrolLog patrolLog);

	List<PatrolLog> getSamePatrolRecord(Map<String, Object> mapSames);

	Double getSamePatrolDistance(Map<String, Object> mapSames);

	int deletePatrolRecords(Map<String, Object> map);
	
	List<PatrolLog> getHistoryList(PatrolLog patrolLog);
	
	int addEntity(PatrolLog patrolLog);
	
	int editEntity(PatrolLog patrolLog);
	
	long querySeq();

	PatrolLog selectPatrolById(String id);

	String getFirstPatrolId(PatrolLog patrolLog);

	List<Organization> getOrgInfoByName(Map<String, String> map);

	Organization getproOrg();

	List<PatrolUserInfo> getUserInfoByName(Map<String, String> map);

	int getOnlineNum(String organizationId);

	int getlineNum(String organizationId);

	List<User> getUserByOrgId(Map<String, Object> map);

	List<User> getOnlineUserByOrgId(String orgId);

	int getCurrentOnlineNum(String objectID);

	int getCurrentlineNum(String objectID);

	List<User> getUserByGrade(String orgId);

	int getUserCountByOrgId(Map<String, Object> map);

}
