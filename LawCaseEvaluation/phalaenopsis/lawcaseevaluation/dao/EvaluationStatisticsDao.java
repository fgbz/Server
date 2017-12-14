package phalaenopsis.lawcaseevaluation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import phalaenopsis.lawcaseevaluation.entity.EvaluationPerson;
import phalaenopsis.lawcaseevaluation.entity.EvaluationResultAccountReport;
import phalaenopsis.lawcaseevaluation.entity.ExportSchedule;
import phalaenopsis.lawcaseevaluation.entity.ReviewLawcase;

@Repository("evaluationStatisticsDao")
public class EvaluationStatisticsDao {
	
	@Resource
	private SqlSession sqlSession;
	
	/**
	 * 获取各市各案卷明细表
	 * @param map
	 * @return
	 */
	public List<ReviewLawcase> getAllEvaluationResultAccount(Map<String, Object> map) {
		try {
			return sqlSession.selectList("lawcaseEvaluation.getAllEvaluationResultAccount", map);
		} catch (Exception e) {
			return null;
		}
		
	}
	/**
	 * 获取各市案卷一览表信息
	 * @param year 年份
	 * @return
	 */
	public List<ExportSchedule> getStatisticSchedule(int year){
		try {
			return sqlSession.selectList("lawcaseEvaluation.getStatisticSchedule", year);
		} catch (Exception e) {
			return null;
		}
	}
    /**
     * 根据年份和执法单位获取评查人员 
     * @param year
     * @param regionCode
     * @return
     */
    public List<EvaluationPerson> getUserByYearAndRegionCode(int year){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("year", year);
    	try {
    		List<EvaluationPerson> list=  sqlSession.selectList("lawcaseEvaluation.getUserByYearAndRegionCode",map);
    		EvaluationPerson evaluationPerson =new EvaluationPerson();
    		evaluationPerson.setId(0);
    		evaluationPerson.setUserAccount("全部");
    		list.add(0, evaluationPerson);
    		return list;
		} catch (Exception e) {
			return null;
		}
    } 
}
