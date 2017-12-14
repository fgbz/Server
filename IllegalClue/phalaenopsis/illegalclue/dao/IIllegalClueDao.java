//package phalaenopsis.illegalclue.dao;
//
//import java.util.List;
//import java.util.Map;
//
//import phalaenopsis.common.entity.Condition;
//
//import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Update;
//import org.springframework.stereotype.Repository;
//
//import phalaenopsis.illegalclue.entity.ClueAudit;
//import phalaenopsis.illegalclue.entity.ClueDictionary;
//import phalaenopsis.illegalclue.entity.ClueEnd;
//import phalaenopsis.illegalclue.entity.ClueJudge;
//import phalaenopsis.illegalclue.entity.IllegalClue;
//import phalaenopsis.illegalclue.entity.ResultState;
//import phalaenopsis.satellitegraph.entity.WFVerHistory;
//import phalaenopsis.satellitegraph.entity.WFVerInstance;
//@Repository("iIllegalClueDao")
//public interface IIllegalClueDao {
//
//	/**
//	 * 根据条件查询违法线索列表
//	 * @param map
//	 * @return
//	 */
//	List<IllegalClue> getIllegalClues(Map<String, Object> map);
//	
//	/**
//	 * 查询列表中查询一共多少条数据
//	 * @param map
//	 * @return
//	 */
//	int getIllegalClueCount(Map<String, Object> map);
//
//	/**
//	 * 查询所有违法线索字典数据，已经包含上下级关系
//	 * @return
//	 */
//	List<ClueDictionary> getClueDictionaries();
//
//	/**
//	 * 保存基本信息表单
//	 * @param illegalClue
//	 * @return
//	 */
//	int saveIllegalClue(IllegalClue illegalClue);
//
//	String getNum(@Param("prefixNum") String prefixNum);
//
//	/**
//	 * 保存或更新初判表单
//	 * @param clueJudge
//	 * @return
//	 */
//	int saveOrUpdateClueJudge(ClueJudge clueJudge);
//
//	/**
//	 * 保存或更新审核表单
//	 * @param clueAudit
//	 * @return
//	 */
//	int saveOrUpdateClueAudit(ClueAudit clueAudit);
//
//	List<Condition> getHandleOrganizationList(String id);
//
//	/**
//	 * 更新CL_CLue表中Node节点
//	 * @param id
//	 * @param currentNode
//	 * @return
//	 */
//	int updateCurrentNode(@Param("instanceId") long id, @Param("currentNode") String currentNode);
//
//
//	int deleteIllegalClue(@Param("id") String id);
//
//	/**
//	 * 保存或更新办结表单
//	 * @param clueEnd
//	 * @return
//	 */
//	int saveOrUpdateClueEnd(ClueEnd clueEnd);
//
//	/**
//	 * 获取基本信息
//	 * @param id
//	 * @return
//	 */
//	IllegalClue getIllegalClue(@Param("id") String id);
//	
//	//获取初判信息
//	ClueJudge getClueJudgeByClueId(String clueId);
//	
//	/**
//	 * 获取审核表单
//	 * @param clueId
//	 * @return
//	 */
//	List<ClueAudit> getClueAuditByClueId(@Param("clueId") long clueId);
//	
//	/**
//	 * 获取办结表单
//	 * @param clueId
//	 * @return
//	 */
//	ClueEnd getClueEndByClueId(@Param("clueId") long clueId);
//	
//	
//	
//	//根据流程实例Id获取线索列表
//	IllegalClue getCluesByInstanceID(String instanceID);
//
//	int isExistClue(@Param("id") String id);
//
//	int updateIllegalClue(IllegalClue illegalClue);
//
//}
