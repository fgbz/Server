package phalaenopsis.illegalclue.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import phalaenopsis.common.entity.Condition;
import phalaenopsis.illegalclue.entity.Clue;
import phalaenopsis.illegalclue.entity.ClueAudit;
import phalaenopsis.illegalclue.entity.ClueDictionary;
import phalaenopsis.illegalclue.entity.ClueEnd;
import phalaenopsis.illegalclue.entity.ClueJudge;
import phalaenopsis.illegalclue.entity.IllegalClueStatistic;

@Repository("iIllegalClueDaoNew")
public interface IIllegalClueDaoNew {

	int saveClue(Clue clue);

	int updateClue(Clue clue);

	List<ClueDictionary> getClueDictionaries();

	// List<ClueDictionary> getInvalidDictionaries();

	List<Condition> getHandleOrganizationList(@Param("id") String id);

	Clue getClue(@Param("id") String id);

	ClueJudge getClueJudge(@Param("id") String id);

	int isExistClue(@Param("id") String id);

	// 判断属于范围内是否有线索初判
	int isExistInnerClueJudge(String id);

	// 判断不属于范围内是否有线索初判
	int isExistOuterClueJudge(String id);

	/**
	 * @param clueJudge
	 * @desc 保存 属于范围（内容）
	 */
	void saveInnerClueJudge(ClueJudge clueJudge);

	void saveOuterClueJudge(ClueJudge clueJudge);

	/**
	 * @param clueJudge
	 * @desc 更新 不属于范围（内容）
	 */
	void updateInnerClueJudge(ClueJudge clueJudge);

	void updateOuterClueJudge(ClueJudge clueJudge);

	/**
	 * @param clueJudge
	 */// 保存 id
	void saveInnerClueJudgeId(ClueJudge clueJudge);

	/**
	 * @param clueJudge
	 */// 保存 id
	void saveOuterClueJudgeId(ClueJudge clueJudge);

	/**
	 * @param clueJudge
	 */// 更新clue表中的type
	void updateInnerClueJudgeId(ClueJudge clueJudge);

	/**
	 * @param clueJudge
	 */// 更新clue表中的type
	void updateOuterClueJudgeId(ClueJudge clueJudge);

	/**
	 * @param clueJudge
	 */
	void insertclue_illegal(ClueJudge clueJudge);

	/**
	 * @param clueJudge
	 */
	void insertclue(ClueJudge clueJudge);

	/**
	 * @param clueId
	 */
	void updateclue(ClueJudge clueJudge);

	/**
	 * @param clueJudge
	 */
	void insertclue_invalid(ClueJudge clueJudge);

	/**
	 * @param clueJudge
	 */
	void updateclue_illegal(ClueJudge clueJudge);

	/**
	 * @param clueJudge
	 */
	void updateclue_invalid(ClueJudge clueJudge);

	/**
	 * 新增违法审核记录
	 * */
	int saveClueAudit(ClueAudit clueAudit);

	/**
	 * 修改违法审核记录
	 * */
	int updateClueAudit(ClueAudit clueAudit);

	/**
	 * 新增办结记录
	 * */
	int saveClueEnd(ClueEnd clueEnd);

	/**
	 * 修改办结记录
	 * */
	int updateClueEnd(ClueEnd clueEnd);

	/**
	 * 根据线索id查询
	 * @param id
	 * @return
	 */
	ClueEnd getClueEndByClueID(@Param("id") String id);

	/**
	 * 根据ID查询审核记录
	 * @param map
	 * @return
	 */
	ClueAudit getClueAuditByClueID(@Param("id") String id);

	/**
	 * 根据ID查询审核记录 1-初判 2.办结
	 * @param map
	 * @return
	 */
	List<ClueAudit> getClueAuditListByClueID(@Param("id") String id);

	int getClueCount(Map<String, Object> map);

	List<Clue> getClues(Map<String, Object> map);

	/**
	 * 查询list(饼图)
	 * @param clue
	 * @return
	 */
	List<Clue> getClueList(Clue clue);

	/**
	 * @param cluex
	 * @return
	 * @desc 获取初判类型
	 */
	int getClueType(String id);

	/**
	 * @param clue
	 * @return
	 * @desc 获取属于范围内
	 */
	ClueJudge getClueJudgeFromShuYu(Clue clue);

	/**
	 * @param clue
	 * @return
	 * @desc 获取不属于范围内
	 */
	ClueJudge getClueJudgeFromNotShuYu(Clue clue);

	/**
	 * @author chiz
	 * @desc 违法线索统计
	 */

	List<IllegalClueStatistic> getIllegalClueStatistic(IllegalClueStatistic illegalClueStatistic);

	/**
	 * 更新CLue表中Node节点
	 * @param id
	 * @param currentNode
	 * @return
	 */
	int updateCurrentNode(@Param("instanceId") String id, @Param("node") String node);

	/**
	 * 根据前缀来获取最大的举报编号
	 * @param prefixNum 前缀
	 * @return 返回获取最大的举报编号
	 */
	String getNum(@Param("prefixNum") String prefixNum);

	int deleteClue(@Param("id") String id);

	int deleteClueAudit(@Param("id") String id);

	int deleteClueClose(@Param("id") String id);

	int deleteClueIllegal(@Param("id") String id);

	int deleteClueInvalid(@Param("id") String id);
}
