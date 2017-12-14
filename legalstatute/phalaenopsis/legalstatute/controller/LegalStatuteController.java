package phalaenopsis.legalstatute.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import phalaenopsis.illegalclue.entity.ResultState;
import phalaenopsis.legalstatute.entity.LegalStatute;
import phalaenopsis.legalstatute.service.LegalStatuteService;

/**
 * 法律法规
 * @author jund
 * 2017年11月8日
 */
@RestController
@RequestMapping("/law/lawStatuteService")
public class LegalStatuteController {
	@Autowired
	private LegalStatuteService service;
	
	/**
	 * 保存法律法规
	 * @param LegalStatute
	 * @return
	 */
	@RequestMapping(value = "/saveLegalStatute",method = RequestMethod.POST)
	@ResponseBody
	public ResultState saveLegalStatute(@RequestBody String legalStatute){
		if(legalStatute == null || legalStatute.equals("")){
			return ResultState.Failed;
		}
		LegalStatute statute = JSONObject.parseObject(legalStatute, LegalStatute.class);
		return service.saveLegalStatute(statute);
	}
	
	/**
	 * 编辑法律法规
	 * @param LegalStatute
	 * @return
	 */
	@RequestMapping(value = "/editLegalStatute",method = RequestMethod.POST)
	@ResponseBody
	public ResultState editLegalStatute(@RequestBody String legalStatute){
		if(legalStatute == null || legalStatute.equals("")){
			return ResultState.Failed;
		}
		LegalStatute statute = JSONObject.parseObject(legalStatute, LegalStatute.class);
		return service.editLegalStatute(statute);
	}
	
	/**
	 * 上移下移法律法规
	 * @param LegalStatute
	 * @return
	 */
	@RequestMapping(value = "/moveLegalStatute",method = RequestMethod.GET)
	@ResponseBody
	public ResultState moveLegalStatute(String upOrDown, String parentId, String id) {
		return service.moveLegalStatute(upOrDown, parentId, id);
	}
	
	/**
	 * 删除法律法规
	 * @param String
	 * @return
	 */
	@RequestMapping(value = "/deleteLegalStatute",method = RequestMethod.DELETE)
	@ResponseBody
	public ResultState deleteLegalStatute(String id){
		if(id == null || id.equals("")){
			return ResultState.Failed;
		}
		return service.deleteLegalStatute(id);
	}
	
	/**
	 * 检索法律法规
	 * @param String
	 * @return
	 */
	@RequestMapping(value = "/getLegalStatuteList",method = RequestMethod.GET)
	@ResponseBody
	public List<LegalStatute> getLegalStatuteList(String condition){
		return service.getLegalStatuteList(condition);
	}
	
	/**
	 * 获取所有法律法规
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/getLegalTree",method = RequestMethod.GET)
	@ResponseBody
	public List<LegalStatute> getLegalTree(){
		return service.getLegalTree();
	}
}
