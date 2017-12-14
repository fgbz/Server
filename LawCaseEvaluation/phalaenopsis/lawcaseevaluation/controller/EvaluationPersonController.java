/**
 * Description 评查人员服务类
 * Author lih
 * Date 2017-04-06
 */
package phalaenopsis.lawcaseevaluation.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import phalaenopsis.common.entity.OpResult;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.common.method.Tools.UUID64;
import phalaenopsis.lawcaseevaluation.entity.*;
import phalaenopsis.lawcaseevaluation.service.EvaluationPersonService;

@Controller
@RequestMapping("/LawcaseEvaluation")
public class EvaluationPersonController {

	private static Logger Log = LoggerFactory.getLogger(EvaluationPersonController.class);

	@Autowired
	private EvaluationPersonService evaluationPersonService;

	/**
	 * 获取评查人员列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/GetEvaluationPersons", method = RequestMethod.POST)
	@ResponseBody
	public PagingEntity<EvaluationPerson> getEvaluationPersons(@RequestBody Page page) {
		return evaluationPersonService.getEvaluationPersons(page);
	}

	/**
	 * 获取评查人员信息
	 * @param userid 评查人主键id
	 * @return
	 */
	@RequestMapping(value = "/GetEvaluationPerson", method = RequestMethod.GET)
	@ResponseBody
	public EvaluationPerson getEvaluationPerson(long userid) {
		return evaluationPersonService.getEvaluationPerson(userid);
	}

	/**
	 * 保存或新增评查人员信息 1 id为null或空时，新增 2 id不为null或空时，修改
	 * 
	 * @return
	 */
	@RequestMapping(value = "/EvaluationPerson/Save", method = RequestMethod.POST)
	@ResponseBody
	public int saveEvaluationPerson(@RequestBody EvaluationPerson user) {
		// 新增
		if (user.getId() == -1) {
			//检查账号是否重复
			if (evaluationPersonService.isEvaPersonExist(user.getUserAccount(), user.getBelongedYear())) {
				int isRepeat = 461;
				OpResult opResult = new OpResult(isRepeat);
				return opResult.Code;
			}
			//为导入人员对象赋值
			ImportingEvaluationPerson importingEvaluationPerson = new ImportingEvaluationPerson();
			importingEvaluationPerson.setAccount(user.getUserAccount());
			importingEvaluationPerson.setCityName(user.getCityName());
			importingEvaluationPerson.setContract(user.getContract());
			importingEvaluationPerson.setDuty(user.getDuty());
			importingEvaluationPerson.setName(user.getName());
			importingEvaluationPerson.setRegionCode(user.getRegionCode());
			importingEvaluationPerson.setRemark(user.getRemark());
			importingEvaluationPerson.setYear(user.getBelongedYear() + "");
			List<ImportingEvaluationPerson> list = new ArrayList<ImportingEvaluationPerson>();
			list.add(importingEvaluationPerson);
			long id = UUID64.newUUID64().getValue();
			
			return evaluationPersonService.addEvaluationUser(list, user.getRegionAreaCode(), id,user.getBelongedYear());
		}
		// 修改
		else {
			return evaluationPersonService.updateEvaluationUser(user);
		}

	}

	/**
	 * 获取市级行政区划
	 * @return
	 */
	@RequestMapping(value = "/GetRegionList", method = RequestMethod.POST)
	@ResponseBody
	public List<SSRegion> getRegionList() {
		List<SSRegion> list = evaluationPersonService.getRegionList();
		return list;
	}

	/**
	 * 删除评查人员
	 * @param userid 评查人员id
	 * @param year 年份
	 * @return
	 */
	@RequestMapping(value = "/DeleteEvaluationPerson", method = RequestMethod.GET)
	@ResponseBody
	public int deleteEvaluationPerson(long userid,int year) {
		return evaluationPersonService.deleteEvaluationPerson(userid,year);
	}

	/**
	 * 获取评查区域list
	 * @param year 年份
	 * @return
	 */
	@RequestMapping(value = "/GetEvaluationAreaList", method = RequestMethod.GET)
	@ResponseBody
	public List<EvaluationArea> getEvaluationAreaList(int year) {
		return evaluationPersonService.getEvaluationAreaList(year);
	}

	/**
	 * 获取各个区域下所属的人员
	 * @param year 年份
	 * @return
	 */
	@RequestMapping(value = "/GetUserListInArea", method = RequestMethod.GET)
	@ResponseBody
	public List<SSRegion> getUserListInArea(int year) {
		return evaluationPersonService.getUserListInArea(year);
	}
	
	/**
	 * 分配区域人员
	 * @param list 分配人员集合
	 * @param allocationRegionCode  分配区域的code
	 * @param year 年份
	 * @return
	 */
	@RequestMapping(value = "/allocationEvaluationUse", method = RequestMethod.POST)
	@ResponseBody
	public int allocationEvaluationUser(@RequestBody List<EvaluationAreaUserMap> list,long allocationRegionCode,int year){
		return evaluationPersonService.allocationEvaluationUser(list,allocationRegionCode,year);	
	}
	/**
	 * 获取所有案件评查的年份
	 * @return
	 */
	@RequestMapping(value = "/getAllEvaluationYear", method = RequestMethod.POST)
	@ResponseBody
	public List<Integer> getAllEvaluationYear(){
		return evaluationPersonService.getAllEvaluationYear();
	}
	/**
	 * 获取未开始的案件评查的年份
	 * @return
	 */
	@RequestMapping(value = "/getNotWorkingEvaluationYear", method = RequestMethod.POST)
	@ResponseBody
	public List<Integer> getNotWorkingEvaluationYear(){
		return evaluationPersonService.getNotWorkingEvaluationYear();		
	}
	/**
	 * 通过行政区划和年份获取userid集合
	 * 
	 * @param regionCode 行政区划
	 * @param year 年份
	 * @return
	 */
	@RequestMapping(value = "/getUserIdsByYearAndRegion", method = RequestMethod.GET)
	@ResponseBody
	public EvaluationArea getUserIdsByYearAndRegion(long regionCode, int year) {
		EvaluationArea evaluationArea =new EvaluationArea();
		evaluationArea.setIds(evaluationPersonService.getUserIdsByYearAndRegion(regionCode, year));
		return evaluationArea;
	}
}
