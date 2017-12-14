package phalaenopsis.satellitegraph.controller;

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

import phalaenopsis.satellitegraph.entity.MapSpot2016;
import phalaenopsis.satellitegraph.entity.OperationLog;
import phalaenopsis.satellitegraph.service.SatelliteWorkflowService;
import phalaenopsis.workflowEngine.controller.WFObject;
import phalaenopsis.workflowEngine.core.WorkflowEngineInstance;

@Controller
@RequestMapping("/SatelliteGraph/Workflow")
public class SatelliteWorkflowController {

	@Autowired
	private WorkflowEngineInstance engine;

	@Autowired
	private SatelliteWorkflowService satelliteWorkflowService;

	public static final String FLOWDATA = "FLOWDATA";

	/**
	 * 区县、市级上报/省级审核完毕
	 * 
	 * @param regionID
	 * @return
	 */
	@RequestMapping(value = "/Report", method = RequestMethod.PUT)
	@ResponseBody
	@Transactional
	public boolean report(@RequestBody String regionID) {
		int region = regionID.equals("") ? 0 : Integer.parseInt(regionID);
		return satelliteWorkflowService.report(region);
	}

	/**
	 * 描述 20170414完善该方法 退回
	 * 
	 * @param regionID
	 * @param log
	 * @return
	 */
	@RequestMapping(value = "/Back", method = RequestMethod.PUT)
	@ResponseBody
	public boolean back(@RequestBody String parm) {
		JSONObject jsonObject = JSON.parseObject(parm);
		Integer regionID = jsonObject.getIntValue("regionID");
		OperationLog log = JSON.parseObject(jsonObject.getString("log"),OperationLog.class);
		return satelliteWorkflowService.back(regionID, log);
	}

	@RequestMapping(value = "/Start", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public WFObject start(@RequestBody String mapID) {
		mapID = "MapSpot";
		WFObject object = engine.start(mapID);
		return object;
	}

	@Autowired
	private HttpServletRequest request;

	@RequestMapping(value = "/Next", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public WFObject next( @RequestBody String obj) {
		// 保存上下文数据
		JSONObject jsonObject = JSON.parseObject(obj);
		JSONObject jsonNamedDatas = JSON.parseObject(jsonObject.getString("NamedDatas"));
		MapSpot2016 mapSpot2016 = JSON.parseObject(jsonNamedDatas.getString("MapSpotData"), MapSpot2016.class);
		String tag = jsonNamedDatas.getString("Tag");
		request.setAttribute("MapSpotData", mapSpot2016);
		request.setAttribute("Tag", tag);

		JSONObject jsonMapSpotData = JSON.parseObject(jsonNamedDatas.getString("MapSpotData"));
		WFObject wfObject = JSON.parseObject(jsonMapSpotData.getString("WFObject"), WFObject.class);
		wfObject.getNamedDatas();
		WFObject next = engine.next(wfObject);
		updateNode(next);
		updateVersion(next.InstanceID);
		return next;
	}

	@RequestMapping(value = "/Back", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public WFObject previous() {
		String instanceId = request.getParameter("InstanceID");
		String currentNode = request.getParameter("CurrentNode");

		WFObject obj = new WFObject();
		obj.setInstanceID(instanceId);
		obj.setCurrentNode(currentNode);

		WFObject next = engine.previous(obj);

		return next;
	}

	@RequestMapping(value = "/GetHistoryNodeSequences", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public String[] getHistoryNodeSequences(String instanceID) {
		String[] nodeSequences = engine.getHistoryNodeSequences(instanceID);
		return nodeSequences;
	}

	@RequestMapping(value = "/GetCurrent", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public WFObject getCurrent(String instanceID) {
		WFObject currentWF = engine.getCurrent(instanceID);
		return currentWF;
	}

	@RequestMapping(value = "/Test", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public WFObject test(String instanceID) {
		return null;
	}

	@RequestMapping(value = "/GetStartNode", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public String getStartNode(String mapID) {
		String StartNode = engine.getStartNode(mapID);
		return StartNode;
	}

	@RequestMapping(value = "/GetEndNodes", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public String[] getEndNodes(String mapID) {
		String[] endNodes = engine.getEndNodes(mapID);
		return endNodes;
	}

	public void updateNode(WFObject next) {
		satelliteWorkflowService.updateNode(next);
	}

	private void updateVersion(String instanceID) {
		satelliteWorkflowService.updateVersion(instanceID);
	}

	// @RequestMapping(value = "/Report",method = RequestMethod.GET)
	// @ResponseBody
	// public boolean report() {
	// return true;
	// }
}
