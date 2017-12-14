package phalaenopsis.visitSystem.controller;

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
import phalaenopsis.common.method.JsonResult;
import phalaenopsis.illegalclue.entity.ResultState;
import phalaenopsis.illegalclue.entity.ResultString;
import phalaenopsis.visitSystem.entity.XfDeals;
import phalaenopsis.visitSystem.entity.XfRegister;
import phalaenopsis.visitSystem.entity.XfRepeatItems;
import phalaenopsis.visitSystem.entity.XfResult;
import phalaenopsis.visitSystem.service.LetterCaseService;
import phalaenopsis.visitSystem.service.LetterCaseServicePartial;

/**
 * 信访
 * 
 * @author dongdongt
 *
 */
//@Controller
@RestController
@RequestMapping("/letterCase/letterCaseService")
public class LetterCaseController {
	// 自动注入service
	@Autowired
	private LetterCaseService service;
	
	@Autowired
	private LetterCaseServicePartial servicePartial;

	/**
	 * 查询列表
	 * 
	 * @param page
	 *            参数
	 * @return PagingEntity
	 */
	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	@ResponseBody
	public PagingEntity<XfRegister> getList(@RequestBody Page page) {
		return service.getList(page);
	}
	/**
	 *保存,提交或更新信访登记信息
	 * 
	 * @param letterCase
	 * @return resultState
	 */
	@RequestMapping(value = "/saveSumbit", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult saveSubmit(@RequestBody String register){
		XfRegister xfRegister=JSONObject.parseObject(register, XfRegister.class);
		return service.saveOrUpdateLetterCase(xfRegister);
	}
	/**
	 * 根据信访Id查询信访信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getLetterCaseById",method=RequestMethod.GET)
	@ResponseBody
	public XfRegister getLetterCaseById(Long id){
		return service.getLetterCaseById(id);
	}
	/**
	 * 根据信访Id查询重复信访详情列表
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getRepeatListByRegisterId",method=RequestMethod.GET)
	@ResponseBody
	public List<XfRepeatItems> getRepeatListByRegisterId(Long registerId){
		return service.getRepeatListByRegisterId(registerId);
	}
	/**
	 * 查询重复信访
	 * @param name 姓名
	 * @param idCard 身份证
	 * @return
	 */
	@RequestMapping(value="/getRepeatList",method=RequestMethod.GET)
	@ResponseBody
	public List<XfRegister> getRepeatList(String name,String idCard){
		return service.getRepeatList(name, idCard);
	}
	/**
	 * 保存 创建重复件信息
	 * @param register
	 * @return
	 */
	@RequestMapping(value = "/saveRepeat", method = RequestMethod.POST)
	@ResponseBody
	public ResultState saveRepeat(@RequestBody String xfRepeat){
		XfRepeatItems repeatItems=JSONObject.parseObject(xfRepeat, XfRepeatItems.class);
		return service.saveRepeat(repeatItems);
	} 
	/**
	 * 信访删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.DELETE)
	public ResultState delete(String id){
		return servicePartial.delete(id);
	}
	
	/**
	 * 审核
	 * @param xfDeals
	 * @return
	 */
	@RequestMapping(value="/audit",method=RequestMethod.POST)
	@ResponseBody
	public XfResult audit(@RequestBody XfDeals xfDeals){
		return servicePartial.audit(xfDeals);
	}
	
	/**
	 * 获取审核列表详情
	 * @param registerId
	 * @return
	 */
	@RequestMapping(value="/getXfDeals",method=RequestMethod.GET)
	public List<XfDeals> getXfDeals(String registerId){
		return servicePartial.getXfDeals(registerId);
	}
}
