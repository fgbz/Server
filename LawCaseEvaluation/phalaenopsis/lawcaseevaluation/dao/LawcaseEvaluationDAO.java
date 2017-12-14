package phalaenopsis.lawcaseevaluation.dao;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.aspectj.weaver.ast.Var;
import org.springframework.stereotype.Repository;

import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.OpResult;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.common.method.Tools.UUID64;
import phalaenopsis.lawcaseevaluation.entity.CaseGradeCount;
import phalaenopsis.lawcaseevaluation.entity.FirstTrialLawcase;
import phalaenopsis.lawcaseevaluation.entity.KeyValueEntity;
import phalaenopsis.lawcaseevaluation.entity.ReviewLawcase;
import phalaenopsis.lawcaseevaluation.entity.ReviewState;
import phalaenopsis.lawcaseevaluation.entity.ScoreGrade;
import phalaenopsis.lawcaseevaluation.entity.SearchYearsAndRegions;
import phalaenopsis.lawcaseevaluation.entity.caseDetail.SurveryStandard;
import phalaenopsis.lawcaseevaluation.entity.caseDetail.SurveyContent;
import phalaenopsis.lawcaseevaluation.entity.caseDetail.SurveyProject;

@Repository("lawcaseEvaluationDAO")
public class LawcaseEvaluationDAO {

	/**
	 * 获取当前评查用户初评查询条件
	 * 
	 * @param flag
	 *            初评为true
	 * @param account
	 *            用户id
	 * @return
	 */
	public Map<Integer, List<KeyValueEntity>> getFirstEvaluationCondition(String account, boolean flag) {
		Map<Integer, List<KeyValueEntity>> map = new LinkedHashMap<Integer, List<KeyValueEntity>>();
		List<SearchYearsAndRegions> list = null;
		try {
			if (flag) {
				list = sqlSession.selectList("lawcaseEvaluation.getYearsAndAreas", account);
			} else {
				list = sqlSession.selectList("lawcaseEvaluation.getReviewYearsAndAreas");
			}

			// 封装结果集,得到年份与执法单位的关联关系
			if (list != null && list.size() > 0) {
				for (SearchYearsAndRegions item : list) {
					// 如果map中存在该年份,则add
					if (map.containsKey(item.getYear())) {
						if (item.getRegionid() != null) {
							map.get(item.getYear()).add(new KeyValueEntity(item.getRegionid(), item.getRegionName()));
						}
					} else {
						List<KeyValueEntity> regions = new ArrayList<KeyValueEntity>();
						regions.add(new KeyValueEntity("0", "全部"));
						if (item.getRegionid() != null) {
							regions.add(new KeyValueEntity(item.getRegionid(), item.getRegionName()));
						}
						map.put(item.getYear(), regions);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return map;
	}

	/**
	 * 将前端传入的查询条件转化map
	 * 
	 * @param page
	 * @return
	 */
	private Map<String, Object> getParam(Page page) {
		Map<String, Object> conditions = new HashMap<String, Object>();

		try {
			for (Condition condition : page.getConditions()) {
				if (condition.getKey().equals("Account")) {
					conditions.put("Account", (String) condition.getValue());
				} else if (condition.getKey().equals("Year")) {
					conditions.put("Year", Integer.parseInt(condition.getValue()));
				} else if (condition.getKey().equals("RegionCode")) {
					conditions.put("RegionCode", (String) condition.getValue());
				} else if (condition.getKey().equals("CaseName")) {
					conditions.put("CaseName", (String) condition.getValue());
				} else if (condition.getKey().equals("CaseCode")) {
					conditions.put("CaseCode", (String) condition.getValue());
				} else if (condition.getKey().equals("ReviewState")) {
					conditions.put("ReviewState", ReviewState.getReviewState((String) condition.getValue()));
				} else if (condition.getKey().equals("PraType")) {
					conditions.put("PraType", (String) condition.getValue());
				}
			}
		} catch (Exception e) {

		}

		return conditions;
	}

	/**
	 * 获取初评列表
	 * 
	 * @param page
	 * @return
	 */
	public PagingEntity<FirstTrialLawcase> getFirstTrialLawcases(Page page) {

		Map<String, Object> conditions = getParam(page);
		// 查询条件中是否含有评查状态
		if (conditions.containsKey("PraType")) {
			if (conditions.get("PraType").equals("1")) {
				// 未初评
				conditions.put("noPra", null);
			} else {
				// 已初评
				conditions.put("noPra", "pra");
			}
		}

		try {
			// 1,根据条件一共查询到的数据条数
			int count = sqlSession.selectOne("lawcaseEvaluation.getFirstTrialLawcaseCount", conditions);

			conditions.put("startRow", page.getPageSize() * (page.getPageNo() - 1) + 1);
			conditions.put("endRow", page.getPageSize() * page.getPageNo());

			// 2, 查询到当前页数的数据
			List<FirstTrialLawcase> list = sqlSession.selectList("lawcaseEvaluation.getFirstTrialLawcases", conditions);

			PagingEntity<FirstTrialLawcase> pFirstTrialLawcase = new PagingEntity<FirstTrialLawcase>();
			pFirstTrialLawcase.setPageCount(count);

			int pageCount = 0 == count ? 1 : (count - 1) / page.getPageSize() + 1; // 由于计算pageCount存在整除的情况，所以计算的时候先减1在除以pageSize

			pFirstTrialLawcase.setPageNo(page.getPageNo());
			pFirstTrialLawcase.setPageSize(page.getPageSize());
			pFirstTrialLawcase.setPageCount(pageCount);
			pFirstTrialLawcase.setRecordCount(count);
			pFirstTrialLawcase.setCurrentList(list);

			return pFirstTrialLawcase;
		} catch (Exception e) {
			return null;
		}

	}

	@Resource
	private SqlSession sqlSession;

	/**
	 * 得到复查列表数据
	 * 
	 * @param page
	 * @return
	 */
	public PagingEntity<ReviewLawcase> getReviewLawcases(Page page) {
		Map<String, Object> conditions = getParam(page);
		if (conditions.containsKey("ReviewState")) {
			if (conditions.get("ReviewState") == ReviewState.UnKnow) {
				// 初评未完成
				conditions.put("noPra", "noPra");
			} else {
				// 其他
				conditions.put("noPra", null);
			}
		}
		try {
			// 1,根据条件一共查询到的数据条数
			int count = sqlSession.selectOne("lawcaseEvaluation.getReviewLawcasesCount", conditions);

			conditions.put("startRow", page.getPageSize() * (page.getPageNo() - 1) + 1);
			conditions.put("endRow", page.getPageSize() * page.getPageNo());

			// 2, 查询到当前页数的数据
			List<ReviewLawcase> list = sqlSession.selectList("lawcaseEvaluation.getReviewLawcases", conditions);

			PagingEntity<ReviewLawcase> entities = new PagingEntity<ReviewLawcase>();
			entities.setPageCount(count);

			int pageCount = 0 == count ? 1 : (count - 1) / page.getPageSize() + 1; // 由于计算pageCount存在整除的情况，所以计算的时候先减1在除以pageSize

			entities.setPageNo(page.getPageNo());
			entities.setPageSize(page.getPageSize());
			entities.setPageCount(pageCount);
			entities.setRecordCount(count);
			entities.setCurrentList(list);

			return entities;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 得到初评信息
	 * 
	 * @param caseid
	 *            案件id
	 * @param userId
	 *            用户id
	 * @return
	 */
	public FirstTrialLawcase getFirstTrialLawcase(String caseid, String userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("caseId", caseid);
		map.put("userId", userId);

		FirstTrialLawcase survey = sqlSession.selectOne("lawcaseEvaluation.getFirstTrialSurvey", map);
		// 默认展开第一项
		for (SurveyProject surveyProject : survey.getCaseJudge().getSurveyProjects()) {
			surveyProject.setChecked(true);
			break;
		}
		return survey;
	}

	/**
	 * 保存初评评分
	 * @param lawcase
	 * @return
	 */
	public int saveFirstTrialLawcase(FirstTrialLawcase lawcase) {

		int result = 0;
		List<FirstTrialLawcase> lawcases = new ArrayList<FirstTrialLawcase>();

		// 1,赋值UUID64
		if (lawcase.getId() == 0) {
			lawcase.setId(UUID64.newUUID64().getValue());
		}
		//2 创建每一子项的得分表主键
		for (SurveyProject surveyProject : lawcase.getCaseJudge().getSurveyProjects()) {
			for (SurveyContent surveyContent : surveyProject.getSurveyContents()) {
				for (SurveryStandard surveyStandard : surveyContent.getSurveryStandards()) {
					surveyStandard.setScoreId(UUID64.newUUID64().getValue());
				}
			}
		}

		try {
			//锁,防止并发
			synchronized (this) {

					// 3,初评人数不能操作3个
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("caseId", lawcase.getCeLawCaseAccountId());
					map.put("userId", lawcase.getEvaluationUserId());
					//判断该案件已评查的人数
					lawcases = sqlSession.selectList("lawcaseEvaluation.getFirstTrialSurveyCount", map);

					if (lawcases.size() > 2) {
						return OpResult.Overflow;
					}
					
				// 4,如果是最后一人提交
				if (2 == lawcases.size()) {
					//3个评查人都提交
					if (lawcases.get(0).isFirstSubmitted() && lawcases.get(1).isFirstSubmitted()) {
						
						//是否有一个在70分一下 
						boolean	flagbottom=false;
						if(lawcases.get(0).getGrade() ==ScoreGrade.UnGrade||lawcases.get(1).getGrade() ==ScoreGrade.UnGrade|| lawcase.getGrade()==ScoreGrade.UnGrade ){
							flagbottom=true;
						}
						//是否在不在分数段内
						boolean flagStatus=false;
						if(lawcases.get(0).getGrade()!=lawcases.get(1).getGrade()||lawcases.get(1).getGrade()!=lawcase.getGrade()){
							flagStatus=true;
						}
						//只要三个初评分数中 有一个分数是不合格的（70分以下,或着不在一个分数段内，就需要复核
						if (flagbottom||flagStatus){
							//判断是否选中不符合评查标准
							if (lawcase.getReviewState() != ReviewState.unMatched) {
								lawcase.setReviewState(ReviewState.NeedReview);
							}
						} else {
							if (lawcase.getReviewState() != ReviewState.unMatched) {
								lawcase.setReviewState(ReviewState.UnNeedReview);
							}
						}
					}
				}

				// 5,保存初评数据
				result = sqlSession.insert("lawcaseEvaluation.saveOrUpdateFirstTrialSurvey", lawcase);

			}
		} catch (Exception e) {
			return OpResult.Failed;
		}
		return OpResult.Success;
	}

	/**
	 * 判断已经提交该案件的数量
	 * 
	 * @param accountId
	 *            案件id
	 * @param accountId
	 *            用户id
	 * @return
	 */
	public int getFirstTrialSurveyCount(long accountId, long userid) {
		try {
			List<FirstTrialLawcase> lawcases = new ArrayList<FirstTrialLawcase>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("caseId", accountId);
			map.put("userId", userid);
			lawcases = sqlSession.selectList("lawcaseEvaluation.getFirstTrialSurveyCount", map);
			// 同一案件只需3人评查
			if (lawcases.size() > 2) {
				return OpResult.Overflow;
			}
		} catch (Exception e) {
			return OpResult.Failed;
		}
		return OpResult.Success;
	}

	/**
	 * 统计当前年度优秀，合格，不合格，需要复核的数量
	 * 
	 * @param year
	 * @return
	 */
	public CaseGradeCount getCaseGradeCount(int year) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			// Calendar a = Calendar.getInstance();
			map.put("year", year);
			// 调用存储过程
			sqlSession.selectOne("lawcaseEvaluation.getCaseGradeCount", map);
			// 获取结果集
			List<CaseGradeCount> object = (List<CaseGradeCount>) map.get("result");
			return object.get(0);
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 复核完结
	 * 
	 * @param caseid
	 *            案件id
	 * @return
	 */
	public int compeleteReview(long caseid) {

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("caseid", caseid);
			map.put("reviewState", ReviewState.Reviewed);
			// 更新案件的状态
			int result = sqlSession.update("lawcaseEvaluation.compeleteReview", map);
			if (result > 0) {
				return OpResult.Success;
			}
		} catch (Exception e) {
			return OpResult.Failed;
		}
		return OpResult.Failed;
	}

	/**
	 * 通过id获取复核信息
	 * 
	 * @param id
	 * @return
	 */
	public ReviewLawcase getReviewInfoById(long id) {

		try {
			ReviewLawcase reviewLawcase = sqlSession.selectOne("lawcaseEvaluation.getReviewInfoById", id);
			return reviewLawcase;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 保存复核
	 * 
	 * @param lawcase
	 * @return
	 */
	public int saveReviewLawcase(FirstTrialLawcase lawcase) {
		try {
			sqlSession.update("lawcaseEvaluation.saveReviewLawcase", lawcase);
			return OpResult.Success;
		} catch (Exception e) {
			return OpResult.Failed;
		}
	}

}
