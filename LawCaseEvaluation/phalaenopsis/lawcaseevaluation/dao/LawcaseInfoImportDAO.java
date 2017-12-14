package phalaenopsis.lawcaseevaluation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import phalaenopsis.common.entity.OpResult;
import phalaenopsis.common.entity.Role;
import phalaenopsis.common.entity.User;
import phalaenopsis.common.method.Tools.SHA;
import phalaenopsis.lawcaseevaluation.entity.*;

@Repository("lawcaseInfoImportDAO")
public class LawcaseInfoImportDAO {
	@Resource
	private SqlSession sqlSession;

	/**
	 * 获取当前年度评查标准的ID
	 * 
	 * @param year
	 *            年份
	 * @return
	 */
	public int getPcbzIdByYear(int year) {
		try {
			return sqlSession.selectOne("lawcaseEvaluation.getPcbzIdByYear", year);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 获取区县的市级code
	 * 
	 * @param regionCode
	 *            区县的行政区划代码
	 * @return
	 */
	public String getParentRegionCode(String regionCode) {
		try {
			return sqlSession.selectOne("lawcaseEvaluation.getParentRegionCode", regionCode);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 插入案件台账
	 * 
	 * @param year
	 *            年份
	 * @param list
	 *            案件台账集合
	 * @return
	 */
	@Transactional
	public boolean addLawcaseAccount(List<LawcaseAccount> list, int year) {
//		// 删除当前年度的案件
//		sqlSession.delete("lawcaseEvaluation.deleteLawcaseAccountByYear", year);
		// 插入当前年度案件
		sqlSession.insert("lawcaseEvaluation.addLawcaseAccount", list);
		return true;
	}

	/**
	 * 插入案件评查人员
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean addLawcasePersons(List<EvaluationPerson> list, boolean isImport, int year) {
			// 导入时
			if (isImport) {
				// 删除评查人员表
				sqlSession.delete("lawcaseEvaluation.deleteEvaluationUserByYear", year);
			}
			// 插入评查人员
			sqlSession.insert("lawcaseEvaluation.addEvaluationPersons", list);
		return true;
	}

	/**
	 * 删除人员区域关联表
	 * 
	 * @param year
	 *            年份
	 * @return
	 */
	@Transactional
	public boolean deleteMapImport(int year) {
		try {
			sqlSession.delete("lawcaseEvaluation.deleteMapImport", year);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 判断该年度案件是否开始评查
	 * 
	 * @param year
	 * @return
	 */
	public int checkLawcaseAccountWork(int year) {
		try {
			return sqlSession.selectOne("lawcaseEvaluation.checkLawcaseAccountWork", year);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 判断该年度人员是否开始工作
	 * 
	 * @param year
	 * @return
	 */
	public int checkUserWork(int year) {
		try {
			return sqlSession.selectOne("lawcaseEvaluation.checkUserWork", year);
		} catch (Exception e) {
			return 0;
		}
	}

}
