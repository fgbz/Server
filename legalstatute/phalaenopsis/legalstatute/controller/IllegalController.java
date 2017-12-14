package phalaenopsis.legalstatute.controller;

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
import phalaenopsis.illegalclue.entity.ResultState;
import phalaenopsis.legalstatute.entity.Illegal;
import phalaenopsis.legalstatute.entity.LegalStatute;
import phalaenopsis.legalstatute.service.IllegalService;

/**
 * 违法行为
 * @author jund
 * 2017年11月13日
 */
@RestController
@RequestMapping("/law/illegalService")
public class IllegalController {
	@Autowired
	private IllegalService service;
	
	/**
	 * 保存违法行为
	 * @param Illegal
	 * @return
	 */
	@RequestMapping(value = "/saveIllegal",method = RequestMethod.POST)
	@ResponseBody
	public ResultState saveIllegal(@RequestBody String illegal){
		if(illegal == null || illegal.equals("")){
			return ResultState.Failed;
		}
		Illegal statute = JSONObject.parseObject(illegal, Illegal.class);
		return service.saveIllegal(statute);
	}
	
	/**
	 * 编辑违法行为
	 * @param Illegal
	 * @return
	 */
	@RequestMapping(value = "/editIllegal",method = RequestMethod.POST)
	@ResponseBody
	public ResultState editIllegal(@RequestBody String illegal){
		if(illegal == null || illegal.equals("")){
			return ResultState.Failed;
		}
		Illegal statute = JSONObject.parseObject(illegal, Illegal.class);
		return service.editIllegal(statute);
	}
	
	/**
	 * 删除违法行为
	 * @param String
	 * @return
	 */
	@RequestMapping(value = "/deleteIllegal",method = RequestMethod.DELETE)
	@ResponseBody
	public ResultState deleteIllegal(String illegalId){
		if(illegalId == null || illegalId.equals("")){
			return ResultState.Failed;
		}
		return service.deleteIllegal(illegalId);
	}
	
	/**
	 * 检索违法行为
	 * @param String
	 * @return
	 */
	@RequestMapping(value = "/getIllegalList",method = RequestMethod.POST)
	@ResponseBody
	public PagingEntity<Illegal> getIllegalList(@RequestBody Page page){
		return service.getIllegalList(page);
	}
}
