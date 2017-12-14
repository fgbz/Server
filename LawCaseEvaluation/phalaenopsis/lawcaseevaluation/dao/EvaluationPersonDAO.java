package phalaenopsis.lawcaseevaluation.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.faces.flow.ReturnNode;

import org.apache.ibatis.session.SqlSession;
import org.aspectj.apache.bcel.generic.RETURN;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.OpResult;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.common.method.Tools.UUID64;
import phalaenopsis.lawcaseevaluation.entity.EvaluationArea;
import phalaenopsis.lawcaseevaluation.entity.EvaluationAreaUserMap;
import phalaenopsis.lawcaseevaluation.entity.EvaluationPerson;
import phalaenopsis.lawcaseevaluation.entity.SSRegion;

@Repository("evaluationPersonDAO")
public class EvaluationPersonDAO {

	@Resource
	private SqlSession sqlSession;

	/**
	 * 获取分页的角色列表
	 * 
	 * @param conditions
	 * @return
	 */
	public PagingEntity<EvaluationPerson> getEvaluationPersons(Page page) {
		try {
			Map<String, Object> conditions = new HashMap<String, Object>();
			// 获取查询条件的map对象
			for (Condition condition : page.getConditions()) {
				if (condition.getKey().equals("Year")) {
					conditions.put("Year", Integer.parseInt(condition.getValue()));
				}

				if (condition.getKey().equals("Name")) {
					conditions.put("Name", (String) condition.getValue());
				}
			}

			// 1,根据条件一共查询到的数据条数
			int count = sqlSession.selectOne("lawcaseEvaluation.getEvaPersonCount", conditions);

			conditions.put("startRow", page.getPageSize() * (page.getPageNo() - 1) + 1);
			conditions.put("endRow", page.getPageSize() * page.getPageNo());

			// 2, 查询到当前页数的数据
			List<EvaluationPerson> list = sqlSession.selectList("lawcaseEvaluation.getEvaPersons", conditions);

			PagingEntity<EvaluationPerson> pEvaluationPerson = new PagingEntity<EvaluationPerson>();
			pEvaluationPerson.setPageCount(count);
			// 由于计算pageCount存在整除的情况，所以计算的时候先减1在除以pageSize
			int pageCount = 0 == count ? 1 : (count - 1) / page.getPageSize() + 1;

			pEvaluationPerson.setPageNo(page.getPageNo());
			pEvaluationPerson.setPageSize(page.getPageSize());
			pEvaluationPerson.setPageCount(pageCount);
			pEvaluationPerson.setRecordCount(count);
			pEvaluationPerson.setCurrentList(list);

			return pEvaluationPerson;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 获取评查人员信息
	 * 
	 * @param userid
	 *            用户id
	 * @return
	 */
	public EvaluationPerson getEvaluationPerson(long userid) {
		try {
			return sqlSession.selectOne("lawcaseEvaluation.getEvaPerson", userid);
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 判断评查人员是否存在 当前年份的评查人员账号不允许重复
	 * 
	 * @param account
	 *            人员账号
	 * @param year
	 *            年份
	 * @return
	 */
	public boolean isEvaPersonExist(String account, int year) {
		boolean result = false;
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("Account", account);
		conditions.put("Year", year);

		int count = sqlSession.selectOne("lawcaseEvaluation.checkEvaPerson", conditions);
		if (count > 0) {
			result = true;
		}

		return result;
	}

	/**
	 * 获取市区行政区划
	 * 
	 * @return
	 */
	public List<SSRegion> getRegionList() {
		try {
			return sqlSession.selectList("lawcaseEvaluation.getRegionList");
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 增加人员区域关联
	 * 
	 * @param areaList
	 * @param id
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean addPeopleArea(List<String> areaList, long id) {
		boolean flag = false;

		List<EvaluationAreaUserMap> listMap = new ArrayList<EvaluationAreaUserMap>();
		// 将对象放入list
		for (String str : areaList) {
			EvaluationAreaUserMap map = new EvaluationAreaUserMap();
			// 主键id
			map.setId(UUID64.newUUID64().getValue());
			// 行政区划code
			map.setRegionID(Long.parseLong(str));
			// 评查人员id
			map.setEvaluationUserID(id);
			listMap.add(map);
		}
		// 先删除当前人员与区域的关联关系
		sqlSession.delete("lawcaseEvaluation.deleteEvaluationAreaUserMapById", id);
		// 插入人员与区域的关联关系
		if (areaList.size() > 0) {
			sqlSession.insert("lawcaseEvaluation.addEvaluationPersonsMap", listMap);
		}
		flag = true;
		return flag;
	}

	/**
	 * 修改人员
	 * 
	 * @param user
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean updateEvaluationUser(EvaluationPerson user) {
		int result = 0;
		// 修改系统用户表
		result += sqlSession.update("lawcaseEvaluation.updateSsUser", user);
		// 修改评查人员表
		result += sqlSession.update("lawcaseEvaluation.updateEvaluationUser", user);
		return result > 0 ? true : false;
	}

	/**
	 * 通过id删除人员信息
	 * 
	 * @param userid
	 * @return
	 */
	@Transactional
	public int deleteEvaluationPerson(long id) {
		int num = 0;
		// 删除区域人员关联表
		num += sqlSession.delete("lawcaseEvaluation.deleteEvaluationAreaUserMapById", id);
		// 删除评查人员表
		num += sqlSession.delete("lawcaseEvaluation.deleteEvaluationUserById", id);
		return num > 0 ? OpResult.Success : OpResult.Failed;
	}

	/**
	 * 获取评查区域list
	 * 
	 * @param year
	 *            年份
	 * @return
	 */
	public List<EvaluationArea> getEvaluationAreaList(int year) {
		try {
			return sqlSession.selectList("lawcaseEvaluation.getEvaluationAreaList", year);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取各个区域下所属的人员
	 * 
	 * @param year
	 *            年份
	 * @return
	 */
	public List<SSRegion> getUserListInArea(int year) {
		try {
			return sqlSession.selectList("lawcaseEvaluation.getUserListInArea", year);
		} catch (Exception e) {
			return new ArrayList<SSRegion>();
		}
	}

	/**
	 * 分配区域人员
	 * 
	 * @param list
	 * @return
	 */
	@Transactional
	public int allocationEvaluationUser(List<EvaluationAreaUserMap> list, long allocationRegionCode, int year) {
		for (EvaluationAreaUserMap eva : list) {
			eva.setId(UUID64.newUUID64().getValue());
			eva.setRegionID(allocationRegionCode);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("allocationRegionCode", allocationRegionCode);
		map.put("year", year);
		// 删除区域人员关联表
		sqlSession.delete("lawcaseEvaluation.deleteEvaluationUserMapByAreaId", map);
		if (list.size() > 0) {
			sqlSession.insert("lawcaseEvaluation.addEvaluationPersonsMap", list);
		}
		return OpResult.Success;
	}

	/**
	 * 导入评查人员
	 * @param list 人员区域list
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public int importEvaluationUser(List<EvaluationAreaUserMap> list) {
		sqlSession.insert("lawcaseEvaluation.addEvaluationPersonsMap", list);
		return OpResult.Success;
	}

	/**
	 * 获取所有案件评查的年份
	 * 
	 * @return s
	 */
	public List<Integer> getAllEvaluationYear() {
		try {
			return sqlSession.selectList("lawcaseEvaluation.getAllEvaluationYear");
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取未开始的案件评查的年份
	 * 
	 * @return
	 */
	public List<Integer> getNotWorkingEvaluationYear() {
		try {
			// return
			// sqlSession.selectList("lawcaseEvaluation.getNotWorkingEvaluationYear");
			// 逻辑变更,取所有案件评查的年份
			return sqlSession.selectList("lawcaseEvaluation.getAllEvaluationYear");
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 通过行政区划和年份获取userid
	 * 
	 * @param regionCode
	 * @param year
	 * @return
	 */
	public String getUserIdsByYearAndRegion(long regionCode, int year) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("region", regionCode);
			map.put("year", year);
			String string = sqlSession.selectOne("lawcaseEvaluation.getUserIdsByYearAndRegion", map);
			return string;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 查询当前人员是否已经开始工作
	 * 
	 * @param userid
	 * @param year
	 * @return
	 */
	public int getUserWorkingCountByID(long userid, int year) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userid", userid);
			map.put("year", year);
			return sqlSession.selectOne("lawcaseEvaluation.getUserWorkingCountByID", map);
		} catch (Exception e) {
			return 1;
		}
	}
}
