package phalaenopsis.lawcase.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.json.Json;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import org.apache.poi.hdgf.streams.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.Paging;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.common.entity.Attachment.Attachment;
import phalaenopsis.lawcase.entity.CaseBaseInfo;
import phalaenopsis.lawcase.entity.CaseStatisticItem;
import phalaenopsis.lawcase.entity.HistoryCase;
import phalaenopsis.lawcase.entity.IllegalClues;
import phalaenopsis.lawcase.entity.LitigantInfo;
import phalaenopsis.lawcase.entity.RequestItem;
import phalaenopsis.lawcase.entity.SignLink;
import phalaenopsis.lawcase.entity.Signature;
import phalaenopsis.lawcase.entity.execute.ExecuteDocument;
import phalaenopsis.lawcase.entity.execute.ExecutePunishRecord;
import phalaenopsis.lawcase.entity.document.DocumentType;
import phalaenopsis.lawcase.entity.execute.ExecuteDocument;
import phalaenopsis.lawcase.entity.execute.ExecutePunishRecord;
import phalaenopsis.lawcase.service.LawCaseDocument;
import phalaenopsis.lawcase.service.LawCaseExport;
import phalaenopsis.lawcase.service.LawCaseService;
import phalaenopsis.satellitegraph.entity.Polygon;

@Controller
@RequestMapping("/LawCase/LawCaseService")
public class LawCaseServiceController {
	@Autowired
	private LawCaseDocument lawCaseDocument;

	@Autowired
	private LawCaseExport exportService;

	@Autowired
	private LawCaseService lawCaseService;



	/**
	 * 获取签字流程
	 * 
	 * @return
	 */
	@RequestMapping(value = "/GetSignLink", method = RequestMethod.GET)
	@ResponseBody
	public List<SignLink> getSignLink() {
		List<SignLink> result = lawCaseService.getSignLink();
		return result;
	}

	// /**
	// * 供日常巡查使用：根据ID列表获取案件列表
	// *
	// * @param ids
	// * @return
	// */
	// @RequestMapping(value = "/getCasesByIDs", method = RequestMethod.GET)
	// @ResponseBody
	// public boolean getCasesByIDs(String ids) {
	// return false;
	// }

	/**
	 * 检查受理编号是否存在重复
	 * 
	 * @param obj
	 * @return
	 */
	@RequestMapping(value = "/CheckRegisterCaseNo", method = RequestMethod.POST)
	@ResponseBody
	public boolean checkRegisterCaseNo(@RequestBody String obj) {
		CaseBaseInfo info = JSONObject.parseObject(obj, CaseBaseInfo.class);
		return lawCaseService.checkRegisterCaseNo(info);
	}

	/**
	 * 保存线索（线索节点）信息
	 * 
	 * @param obj
	 * @param clue
	 * @return
	 */
	@RequestMapping(value = "/SaveAcceptInfo", method = RequestMethod.POST)
	@ResponseBody
	public String saveIllegalClues(@RequestBody String obj) {
		RequestItem requestItem = JSON.parseObject(obj, RequestItem.class);
		return lawCaseService.saveCaseInfo(requestItem.getObj(), requestItem.getClue());
	}

	/**
	 * 保存案件（线索节点之后的节点）信息
	 * 
	 * @param obj
	 * @return TODO 20170601修改value值有误将SaveCaseBase改为SaveCaseInfo
	 */
	@RequestMapping(value = "/SaveCaseInfo", method = RequestMethod.POST)
	// @RequestMapping(value = "/SaveAcceptInfo", method = RequestMethod.POST)
	@ResponseBody
	public String saveCaseInfo(@RequestBody String obj) {
		CaseBaseInfo info = JSONObject.parseObject(obj, CaseBaseInfo.class);
		return lawCaseService.saveCaseInfo(info);
	}

	/**
	 * 保存线索信息（从线索到立案）
	 * 
	 * @param obj
	 * @param clue
	 * @return
	 */
	@RequestMapping(value = "/SaveCaseBaseNew", method = RequestMethod.POST)
	@ResponseBody
	public String saveCaseNew(@RequestBody RequestItem requestItem) {
		return lawCaseService.saveCaseNew(requestItem.getObj(), requestItem.getClue());
	}

//	/**
//	 * 查询案件表
//	 * 
//	 * @param conditions
//	 * @return
//	 */
//	@RequestMapping(value = "/GetCaseList", method = RequestMethod.POST)
//	@ResponseBody
//	public List<CaseBaseInfo> getCaseList(Dictionary<String, String> conditions) {
//		return null;
//	}

	/**
	 * 案件列表分页查询
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param conditions
	 * @param sortProperty
	 * @param direction
	 * @return
	 */
	@RequestMapping(value = "/GetCasePgList", method = RequestMethod.POST)
	//Page page
	@ResponseBody
	public PagingEntity<CaseBaseInfo> getCaseList(@RequestBody String obj) {
		Page page = JSONObject.parseObject(obj, Page.class);
		return lawCaseService.getCaseList(page.getPageNo(), page.getPageSize(), page.getConditions(),
				page.getSortProperty(), page.getDirection());
	}

	/**
	 * 供日常巡查使用：根据ID列表获取案件列表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/GetCasesByIDs", method = RequestMethod.POST)
	@ResponseBody
	public List<CaseBaseInfo> getCasesByIDs(@RequestBody List<String> ids) {

		return lawCaseService.getCasesByIDs(ids);
	}

	/**
	 * 获取日常巡查中巡查路线关联的图斑信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/GetRelatedSpots", method = RequestMethod.GET)
	@ResponseBody
	public List<CaseBaseInfo> getRelatedSpots(List<String> ids) {
		return null;
	}



	/**
	 * 案件查询
	 * 
	 * @param caseID
	 * @return
	 */
	@RequestMapping(value = "/GetCase", method = RequestMethod.GET)
	@ResponseBody
	public CaseBaseInfo getCase(String caseID) {
		return lawCaseService.getCase(caseID);
	}

	/**
	 * 通过流程id获取caseId
	 * 
	 * @param instanceID
	 * @return
	 */
	@RequestMapping(value = "/GetCaseIDByInstanceID", method = RequestMethod.GET)
	@ResponseBody
	public String getCaseIDByInstanceID(String instanceID) {
		return lawCaseService.getCaseIDByInstanceID(instanceID);
	}

	/**
	 * 删除案件
	 * 
	 * @param caseID
	 * @param instanceID
	 * @return
	 */
	@RequestMapping(value = "/DeleteCase", method = RequestMethod.POST)
	//String caseID, String instanceID 将参数改成map接收，否则解析不到
	@ResponseBody
	public boolean deleteCase(@RequestBody Map<String, String> map) {
		String caseID = map.get("caseID");
		String instanceID = map.get("instanceID");
		return lawCaseService.deleteCase(caseID, instanceID);
	}

	/**
	 * 查询线索登记表信息
	 * 
	 * @param caseID
	 * @return
	 */
	@RequestMapping(value = "/GetIllegalClues", method = RequestMethod.GET)
	@ResponseBody
	public IllegalClues getIllegalClues(String caseID) {
		return lawCaseService.getIllegalClues(caseID);
	}

	/**
	 * 保存当事人信息
	 * 
	 * @param litigantInfos
	 * @return
	 */
	@RequestMapping(value = "/SaveLitigantInfos", method = RequestMethod.POST)
	@ResponseBody
	public List<LitigantInfo> saveLitigantInfos(@RequestBody List<LitigantInfo> litigantInfos) {
		return lawCaseService.saveLitigantInfos(litigantInfos);
	}

	/**
	 * 删除当事人信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/DeleteLitigantInfo", method = RequestMethod.PUT)
	@ResponseBody
	public boolean deleteLitigantInfo(String id) {
		return lawCaseService.deleteLitigantInfo(id);
	}

	/**
	 * 
	 * @param currIDs
	 * @param lastIDs
	 * @param reason
	 * @return
	 * @desc 退回签字流程
	 */						  
	@RequestMapping(value = "/SendBackSignature", method = RequestMethod.POST)
	@ResponseBody
	public boolean sendBackSignature(@RequestBody String streason) {
//		@RequestBody List<String> currIDs,@RequestBody List<String> lastIDs, @RequestBody String reason
		JSONObject object =JSON.parseObject(streason);//获取前端参进行解析
		String currID =object.get("currIDs").toString();
		currID = currID.substring(1,currID.length()-1);
		String[] arr = currID.split("\"");
		List<String> currIDs=new ArrayList<>();
		for(String b : arr){
			if(b!="" && !b.equals("") && !b.equals(",")){
				currIDs.add(b);
			}
		}
		String lastID=object.get("lastIDs").toString();
		lastID = lastID.substring(1,lastID.length()-1);
		String[] arrs = lastID.split("\"");
		List<String> lastIDs=new ArrayList<>();
		for(String b : arrs){
			if(b!=null && !b.equals("") && !b.equals(",")){
				lastIDs.add(b);
			}
		}
		String reason=object.get("reason").toString();
		return lawCaseService.sendBackSignature(currIDs, lastIDs, reason);
	}

	/**
	 * 
	 * @param sign
	 * @return
	 * @desc 保存签字信息
	 */
	@RequestMapping(value = "/SaveSignature", method = RequestMethod.POST)
	@ResponseBody
	public String saveSignature(@RequestBody String sign) {
		// return lawCaseService.getIllegalClues(caseID);
		Signature s = JSONObject.parseObject(sign,Signature.class);
		return lawCaseService.saveSignatureByEndCase(s);
	}

	/**
	 * 提交签字流程
	 * 
	 * @param sign
	 * @param signLinkID
	 * @param nextNode
	 * @return
	 */
	@RequestMapping(value = "/SubmitSignature", method = RequestMethod.POST)
	@ResponseBody
	public List<Signature> submitSignature(@RequestBody String signs) {
		JSONObject object =JSON.parseObject(signs);//获取前端参进行解析
		String data =object.get("sign").toString();
		Signature sign =JSONObject.parseObject(data,Signature.class);
		String signLinkID=object.get("signLinkID").toString();
		String nextNode =String.valueOf(object.get("nextNode"));
		return lawCaseService.submitSignature(sign, signLinkID, nextNode);
	}

	/**
	 * 档案目录 获取整个案件所有节点的附件
	 * 
	 * @param caseID
	 * @return
	 */
	@RequestMapping(value = "/GetCaseAttachments", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, List<Attachment>> getCaseAttachments(@PathParam("caseID") String caseID) {
		return lawCaseService.getCaseAttachments(caseID);
	}

	/**
	 * 判断是否已转违法案件
	 * 
	 * @param ids
	 * @return
	 */	
	@RequestMapping(value = "/IsDeliverToLawCase", method = RequestMethod.POST)
	@ResponseBody
	public boolean isDeliverToLawCase(@RequestBody List<String> ids) {
		return lawCaseService.isDeliverToLawCase(ids);
	}

	/**
	 * 保存转案件
	 * 
	 * @param requestItem
	 * @return
	 */
	@RequestMapping(value = "/SaveDeliverLawCase", method = RequestMethod.POST)
	@ResponseBody
	public String saveDeliverLawCase(@RequestBody String str) {
		RequestItem requestItem = JSON.parseObject(str, RequestItem.class);
		return lawCaseService.saveDeliverLawCase(requestItem.getRel(), requestItem.getObj(), requestItem.getClue());
	}

	/**
	 * 历史案件
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param conditions
	 * @param sortProperty
	 * @param direction
	 * @return
	 */
	@RequestMapping(value = "/GetHistoryCaseList", method = RequestMethod.PUT)
	@ResponseBody
	public PagingEntity<HistoryCase> getHistoryCaseList(Page page) {
		return null;
	}

	/**
	 * 保存历史案件
	 * 
	 * @param info
	 * @return
	 */
	@RequestMapping(value = "/SaveHistoryCase", method = RequestMethod.PUT)
	@ResponseBody
	public String saveHistoryCase(HistoryCase info) {
		return null;
	}

	/**
	 * 获取历史案件
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/GetHistoryCase", method = RequestMethod.GET)
	@ResponseBody
	public HistoryCase getHistoryCase(String id) {
		return null;
	}

	/**
	 * 删除历史案件
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/DeleteHistoryCase", method = RequestMethod.GET)
	@ResponseBody
	public boolean deleteHistoryCase(String id) {
		return false;

	}

	/**
	 * 导入历史案件信息
	 * 
	 * @param info
	 * @return
	 */
	@RequestMapping(value = "/ImportHistoryCaseCoordinate", method = RequestMethod.POST)
	@ResponseBody
	public boolean importHistoryCaseCoordinate(HistoryCase info) {
		return false;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/GetHistoryCaseShape", method = RequestMethod.POST)
	@ResponseBody
	public Polygon getHistoryCaseShape(String id) {
		return null;
	}

	/**
	 * 
	 * @param info
	 * @return
	 */
	@RequestMapping(value = "/DeleteHistoryCaseCoordinate", method = RequestMethod.POST)
	@ResponseBody
	public boolean deleteHistoryCaseCoordinate(HistoryCase info) {
		return false;
	}

	
	
	/**
	 * 
	 * @param conditions
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@RequestMapping(value = "/GetStatistic", method = RequestMethod.POST)
	@ResponseBody
	public Paging<CaseStatisticItem> getStatistic(Dictionary<String, String> conditions, Date beginDate, Date endDate) {
		return null;
	}

	/**
	 * 违法用地分类统计模板
	 * 
	 * @param statisticType
	 * @param beginDate
	 * @param endDate
	 * @param region
	 * @return
	 */
	@RequestMapping(value = "/ExportStatistics", method = RequestMethod.POST)
	@ResponseBody
	public Stream exportStatistics(@RequestBody String statisticType, @RequestBody Date beginDate,
			@RequestBody Date endDate, @RequestBody String region) {
		// //map
		// Map<String, String> statMap=new HashMap<String, String>();
		// statMap.put("type", statisticType);
		// statMap.put("pageNo", "1");
		// statMap.put("pageSize", "999");
		// //如果region不为null 加到map中
		// if(region!=null && !region.equals("")){
		// statMap.put("region", region);
		// }
		return null;
	}

}
