package phalaenopsis.illegalclue.controller;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import phalaenopsis.common.method.Tools.StrUtil;
import phalaenopsis.common.method.cache.WFCache;
import phalaenopsis.illegalclue.entity.Clue;
import phalaenopsis.illegalclue.entity.ClueAudit;
import phalaenopsis.illegalclue.entity.ClueEnd;
import phalaenopsis.illegalclue.entity.ClueJudge;
import phalaenopsis.illegalclue.service.IllegalClueServiceNew;
import phalaenopsis.workflowEngine.controller.WFObject;
import phalaenopsis.workflowEngine.controller.WorkflowService;
import phalaenopsis.workflowEngine.core.WorkflowEngineInstance;

@Controller
@RequestMapping("/IllegalClueWorkflow")
public class ClueWorkflowController extends WorkflowService {

	@Autowired
	private WorkflowEngineInstance engine;

	@Autowired
	private IllegalClueServiceNew service;

	private final String MapID = "IllegalClue";

	/**
	 * 下一步
	 * 
	 * @param obj
	 * @return
	 */
	@Autowired
	private HttpServletRequest request;

	//@SuppressWarnings("static-access")
	@RequestMapping(value = "/next", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public WFObject next(@RequestBody String obj) {

		WFObject wfObject = JSON.parseObject(obj, WFObject.class);
		Clue illegalClue = JSON.parseObject(wfObject.getServerData().toString(), Clue.class);
		JSONObject jsonObject = JSON.parseObject(wfObject.getServerData().toString());
		String currentNode = wfObject.getCurrentNode();
		//线索初判信息
		if (!StrUtil.isNullOrEmpty(jsonObject.get("mClueJudge").toString())) {
			String strClueJudge = jsonObject.get("mClueJudge").toString();
			illegalClue.setmClueJudge(JSON.parseObject(strClueJudge, ClueJudge.class));
		}
		//线索初判审核信息
		if(jsonObject.get("cAudit") != null){
			String strClueAudit = jsonObject.get("cAudit").toString();
			illegalClue.setcAudit(JSON.parseObject(strClueAudit, ClueAudit.class));
		}
		//线索办结信息
		if(jsonObject.get("cEnd")!=null){
			String strClueEnd = jsonObject.get("cEnd").toString();
			illegalClue.setcEnd(JSON.parseObject(strClueEnd, ClueEnd.class));
		}
		WFCache.put(getAuthId()+currentNode, illegalClue);
		WFObject next = engine.next(wfObject);
		service.updateNode(next);
		return next;
//		else if("JudgeAuditNode".equals(wfObject.getCurrentNode())){
//			JSONObject jsonObject = JSON.parseObject(wfObject.getServerData().toString());
//			String strClueJudge = jsonObject.get("mClueJudge").toString();
//			String strClueAudit = jsonObject.get("ClueAudit").toString();
//			ClueAudit illegalClue = jsonObject.parseObject(strClueAudit, ClueAudit.class);
//			ClueJudge clueJudge = jsonObject.parseObject(strClueJudge, ClueJudge.class);
//			illegalClue.setmClueJudge(clueJudge);
//			WFCache.put(getAuthId()+wfObject.getCurrentNode(), illegalClue);
//			WFObject next = engine.next(wfObject);
//			service.updateNode(next);
//			
//			return next;
//		}
//		WFCache.put(getAuthId() + currentNode, illegalClue);
//		WFObject next = engine.next(wfObject);
//		service.updateNode(next);
//		return next;
//		
//		if ("RegistNode".equals(wfObject.getCurrentNode()) || StrUtil.isNullOrEmpty(wfObject.getCurrentNode())) { // 基本信息和初判
//
//			// if (StrUtil.isNullOrEmpty(wfObject.getInstanceID())){
//			// WFObject neWfObject = engine.start(MapID);
//			// illegalClue.setInstanceID(neWfObject.getInstanceID());
//			// illegalClue.setNode(neWfObject.getCurrentNode());
//			// }
//
//			
//			
//		} else if ("JudgeAuditNode".equals(wfObject.getCurrentNode())) {
//			Clue illegalClue = JSON.parseObject(wfObject.getServerData().toString(), Clue.class);
//			WFCache.put(getAuthId() + illegalClue.getNode(), illegalClue);
//			WFObject next = engine.next(wfObject);
//			service.updateNode(next);
//
//			return next;
//		}
//
//		return null;
	}
}
