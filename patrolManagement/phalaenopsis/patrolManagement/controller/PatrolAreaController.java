package phalaenopsis.patrolManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.common.entity.TreeNode;
import phalaenopsis.common.entity.User;
import phalaenopsis.common.method.Basis;
import phalaenopsis.illegalclue.entity.ResultState;
import phalaenopsis.patrolManagement.entity.PatrolArea;
import phalaenopsis.patrolManagement.service.PatrolAreaService;

/**
 * 巡查区域
 * @author jund
 * 2017-9-14
 */
@RestController
@RequestMapping("/patrol/patrolService")
public class PatrolAreaController extends Basis {
	@Autowired
	private PatrolAreaService  patrolAreaService;
	
	/**
	 * 巡查区域列表
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/getAreaList",method = RequestMethod.POST)
	@ResponseBody
	public PagingEntity<PatrolArea> getAreaList(@RequestBody Page page){
		User user = getCurrentUser();
		return patrolAreaService.getAreaList(page,user);
	}
	
	/**
	 * 删除巡查区域
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteArea",method = RequestMethod.DELETE)
	@ResponseBody
	public ResultState deleteArea(String id){
		if(id == null || id.equals("")){
			return ResultState.Failed;
		}
		return patrolAreaService.deleteArea(id);
	}
	
	/**
	 * 获取巡查单位
	 * @return
	 */
	@RequestMapping(value = "/getPatrolOrg",method = RequestMethod.GET)
	@ResponseBody
	public List<TreeNode> getPatrolOrg(){
		User user = getCurrentUser();
		return patrolAreaService.getPatrolOrg(user);
	}
	
	/**
	 * 保存巡查区域
	 * @param patrolArea
	 * @return
	 */
	@RequestMapping(value = "/saveArea",method = RequestMethod.POST)
	@ResponseBody
	public ResultState saveArea(@RequestBody String patrolArea){
		if(patrolArea == null || patrolArea.equals("")){
			return ResultState.Failed;
		}
		PatrolArea patrolarea=JSONObject.parseObject(patrolArea, PatrolArea.class);
		return patrolAreaService.saveArea(patrolarea);
	}
}
