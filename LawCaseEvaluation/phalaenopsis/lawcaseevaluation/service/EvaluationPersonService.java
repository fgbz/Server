package phalaenopsis.lawcaseevaluation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import phalaenopsis.common.entity.OpResult;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.lawcaseevaluation.dao.EvaluationPersonDAO;
import phalaenopsis.lawcaseevaluation.dao.LawcaseInfoImportDAO;
import phalaenopsis.lawcaseevaluation.entity.*;

@Service("evaluationPersonService")
public class EvaluationPersonService {
	
	/**
	 * 获取评查人员列表
	 * @param page
	 * @return
	 */
	public PagingEntity<EvaluationPerson> getEvaluationPersons(Page page) {
		return evaluationPersonDAO.getEvaluationPersons(page);
	}
	
	/**
	 * 通过id获取评查人员情况
	 * @param userid 人员id
	 * @return
	 */
	public EvaluationPerson getEvaluationPerson(long userid) {
		return evaluationPersonDAO.getEvaluationPerson(userid);
	}

	/**
	 * 获取市区行政区划
	 * 
	 * @return
	 */
	public List<SSRegion> getRegionList() {
		return evaluationPersonDAO.getRegionList();
	}

	/**
	 * 新增案件评查人员
	 * @param peopleList 人员信息
	 * @param areaList 区域集合 
	 * @param id 主键id
	 * @param year 年份
	 * @return
	 */
	@Transactional
	public int addEvaluationUser(List<ImportingEvaluationPerson> peopleList, List<String> areaList, long id, int year) {
		//插入人员信息
		if (lawcaseInfoImportService.importEvaluationPerson(peopleList, id, false) == OpResult.Success) {
			//分配区域
			if (evaluationPersonDAO.addPeopleArea(areaList, id)) {
				return OpResult.Success;
			}
		}
		return OpResult.Failed;
	}

	/**
	 * 修改人员
	 * 
	 * @param user
	 * @return
	 */
	@Transactional
	public int updateEvaluationUser(EvaluationPerson user) {
			//修改人员信息
			if (evaluationPersonDAO.updateEvaluationUser(user)) {
				//分配区域人员
				if (evaluationPersonDAO.addPeopleArea(user.getRegionAreaCode(), user.getId())) {
					return OpResult.Success;
				}
			}
		
		return OpResult.Failed;

	}

	/**
	 * 判断评查人员是否存在 传入年份的评查人员账号不允许重复
	 * 
	 * @param account 账号
	 * @param year 年份
	 * @return
	 */
	public boolean isEvaPersonExist(String account, int year) {
		return evaluationPersonDAO.isEvaPersonExist(account, year);
	}

	/**
	 * 通过id删除人员信息
	 * 
	 * @param userid
	 * @return
	 */
	public int deleteEvaluationPerson(long userid,int year) {
		//判断该人员是否已经开始评查
		if(evaluationPersonDAO.getUserWorkingCountByID(userid, year)>0){
			// 人员已开始工作，不能删除
			int isWorking = 471;
			OpResult opResult = new OpResult(isWorking);
			return opResult.Code;
		}
		return evaluationPersonDAO.deleteEvaluationPerson(userid);
	}

	/**
	 * 获取评查区域list
	 * 
	 * @return
	 */
	public List<EvaluationArea> getEvaluationAreaList(int year) {
		List<EvaluationArea> list = evaluationPersonDAO.getEvaluationAreaList(year);
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getNames() != null) {
				//人员姓名集合
				String strNames[] = list.get(i).getNames().split(",");
				//人员行政区划集合
				String strRegions[] = list.get(i).getUserRegionNames().split(",");
				//人员姓名+人员行政区划  拼接
				String pageShowNames = "";
				for (int j = 0; j < strNames.length; j++) {
					if (j != strNames.length - 1) {
						pageShowNames += strNames[j] + "(" + strRegions[j] + "),";
					} else {
						pageShowNames += strNames[j] + "(" + strRegions[j] + ")";
					}
				}
				list.get(i).setPageShowNames(pageShowNames);
			}
		}
		return list;
	}

	/**
	 * 获取各个区域下所属的人员
	 * @param year 年份
	 * @return
	 */
	public List<SSRegion> getUserListInArea(int year) {
		List<SSRegion> list = evaluationPersonDAO.getUserListInArea(year);
		return list;
	}

	/**
	 * 分配区域人员
	 * 
	 * @param list
	 * @return
	 */
	public int allocationEvaluationUser(List<EvaluationAreaUserMap> list, long allocationRegionCode, int year) {
		if (lawcaseInfoImportDAO.checkUserWork(year) > 0) {
			// 人员已开始工作，不能导入，只能新增
			int isWorking = 471;
			OpResult opResult = new OpResult(isWorking);
			return opResult.Code;
		}
		return evaluationPersonDAO.allocationEvaluationUser(list, allocationRegionCode, year);
	}

	/**
	 * 获取所有案件评查的年份
	 * 
	 * @return
	 */
	public List<Integer> getAllEvaluationYear() {
		return evaluationPersonDAO.getAllEvaluationYear();
	}

	/**
	 * 获取未开始的案件评查的年份
	 * 
	 * @return
	 */
	public List<Integer> getNotWorkingEvaluationYear() {
		return evaluationPersonDAO.getNotWorkingEvaluationYear();
	}

	/**
	 * 通过行政区划和年份获取userid
	 * 
	 * @param regionCode 行政区划id
	 * @param year 年份
	 * @return
	 */
	public String getUserIdsByYearAndRegion(long regionCode, int year) {
		return evaluationPersonDAO.getUserIdsByYearAndRegion(regionCode, year);
	}

	@Autowired
	private EvaluationPersonDAO evaluationPersonDAO;
	@Autowired
	private LawcaseInfoImportService lawcaseInfoImportService;
	@Autowired
	private LawcaseInfoImportDAO lawcaseInfoImportDAO;
}
