//package phalaenopsis.illegalclue.controller;
//
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import phalaenopsis.common.entity.Condition;
//import phalaenopsis.common.entity.Page;
//import phalaenopsis.common.entity.PagingEntity;
//import phalaenopsis.common.method.Tools.UUID64;
//import phalaenopsis.illegalclue.entity.ClueAudit;
//import phalaenopsis.illegalclue.entity.ClueDictionary;
//import phalaenopsis.illegalclue.entity.ClueEnd;
//import phalaenopsis.illegalclue.entity.ClueJudge;
//import phalaenopsis.illegalclue.entity.IllegalClue;
//import phalaenopsis.illegalclue.entity.ResultState;
//import phalaenopsis.illegalclue.service.IllegalClueService;
//
//@Controller
//@RequestMapping("/IllegalClueService")
//public class IllegalClueController {
//	
//	@Autowired
//	private IllegalClueService service;
//
//	/**
//	 * 获取违法线索列表
//	 * 
//	 * @param param
//	 * @return
//	 */
//	@RequestMapping(value = "/IllegalClues/get", method = RequestMethod.POST)
//	@ResponseBody
//	public PagingEntity<IllegalClue> getIllegalClues(@RequestBody Page page) {
//		return service.getIllegalClues(page);
//	}
//
//	/**
//	 * 保存或更新登记信息
//	 * 
//	 * @param illegalClue
//	 * @return
//	 */
//	@RequestMapping(value = "/IllegalClue/save", method = RequestMethod.POST)
//	@ResponseBody
//	public ResultState saveOrUpdateIllegalClue(@RequestBody IllegalClue illegalClue) {
//		return service.saveOrUpdateIllegalClue(illegalClue);
//	}
//	
//	/**
//	 * 删除登记
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping(value = "/IllegalClue/delete", method = RequestMethod.DELETE)
//	@ResponseBody
//	public ResultState deleteIllegalClue(@RequestBody String id)
//	{
//		return service.deleteIllegalClue(id);
//	}
//
//	/**
//	 * 保存或更新初判、办理、办结审核信息
//	 * 
//	 * @param clueAudit
//	 * @return
//	 */
//	@RequestMapping(value = "/ClueAudit/save", method = RequestMethod.POST)
//	@ResponseBody
//	public ResultState saveOrUpdateClueAudit(@RequestBody ClueAudit clueAudit) {
//		return service.saveOrUpdateClueAudit(clueAudit);
//	}
//
//	/**
//	 * 保存或更新初判信息
//	 * 
//	 * @param clueJudge
//	 * @return
//	 */
//	@RequestMapping(value = "/ClueJudge/save", method = RequestMethod.POST)
//	@ResponseBody
//	public ResultState saveOrUpdateClueJudge(@RequestBody ClueJudge clueJudge) {
//		return service.saveOrUpdateClueJudge(clueJudge);
//	}
//
//
//	/**
//	 * 保存或更新办结信息
//	 * 
//	 * @param clueHandle
//	 * @return
//	 */
//	@RequestMapping(value = "/ClueEnd/save", method = RequestMethod.POST)
//	@ResponseBody
//	public ResultState saveOrUpdateClueEnd(@RequestBody ClueEnd clueEnd) {
//		return service.saveOrUpdateClueEnd(clueEnd);
//	}
//
//	/**
//	 * 获取单个违法线索
//	 * 
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping(value="/IllegalClue/get", method=RequestMethod.GET)
//	@ResponseBody
//	public IllegalClue getIllegalClue(String id) {
//		return service.getIllegalClue(id);
//	}
//
//	/**
//	 * 查重
//	 * @param address
//	 * @param userName
//	 * @return
//	 */
//	@RequestMapping(value="/DuplicateIllegalClue/get", method= RequestMethod.GET)
//	@ResponseBody
//	public List<IllegalClue> getDuplicateIllegalClue(@RequestParam("address") String address,
//			@RequestParam("user") String userName) {
//		return null;
//	}
//
//
//	
////	/**
////	 * 获取违法线索字典
////	 * 
////	 * @return
////	 */
////	@RequestMapping(value = "/ClueDictionary/get", method = RequestMethod.GET)
////	@ResponseBody
////	public Map<String, List<ClueDictionary>> getClueDictionaryMap() {
////		return service.getClueDictionaryMap();
////	}
//	
//	/**
//	 * 获取违法线索字典
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/getClueDictionaryByType", method = RequestMethod.GET)
//	@ResponseBody
//	public List<ClueDictionary> getClueDictionaryByType(String type)
//	{
//		return service.getClueDictionaryByType(type);
//	}
//	/**
//	 * 获取转交办组织
//	 */
//	@RequestMapping(value = "/getHandleOrganizationList", method = RequestMethod.GET)
//	@ResponseBody
//	public List<Condition> getHandleOrganizationList(String id){
//		return service.getHandleOrganizationList(id);
//	}
//	//==================通用方法==========================
//	
//	/**
//	 * 获取UUID的值
//	 * @return
//	 */
//	@RequestMapping(value = "/getUUID", method = RequestMethod.GET)
//	@ResponseBody
//	public long getUUID()
//	{
//		return UUID64.newUUID64().getValue();
//	}
//	
//	/**
//	 * 获取当前服务器时间
//	 * @return
//	 */
//	@RequestMapping(value="/getCurrentTime", method= RequestMethod.GET)
//	@ResponseBody
//	public  Date getCurrentTime()
//	{
//		return  Calendar.getInstance().getTime();
//	}
//	
//	
//	
//}
