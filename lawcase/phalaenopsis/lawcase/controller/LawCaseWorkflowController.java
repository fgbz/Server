package phalaenopsis.lawcase.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import phalaenopsis.common.method.cache.WFCache;
import phalaenopsis.lawcase.service.LawCaseWorkflowService;
import phalaenopsis.workflowEngine.controller.WFObject;
import phalaenopsis.workflowEngine.controller.WorkflowService;

@Controller
@RequestMapping("/LawCase/Workflow")
public class LawCaseWorkflowController extends WorkflowService  {

	@Autowired
	private LawCaseWorkflowService workflowService;
	
	@Autowired
	private HttpServletRequest request;

	
	/**
	 * 流程实例流转到下一步
	 * 
	 * @param obj
	 *            要流转的流程实例
	 * @return 如果成功流转到下一步，则返回流转到的下一步的节点ID
	 */
	@RequestMapping(value = "/Next", method = RequestMethod.POST)
	@ResponseBody
	@Override
	public WFObject next(@RequestBody String map) {
		WFObject wfObject = new WFObject();
		JSONObject jsonObject = JSON.parseObject(map);
		JSONObject jsonNamedDatas = JSON.parseObject(jsonObject.getString("NamedDatas"));
		//MapSpot2016 mapSpot2016 = JSON.parseObject(jsonNamedDatas.getString("MapSpotData"), MapSpot2016.class);
		String currentNode = jsonObject.getString("CurrentNode"); // map.get("CurrentNode").toString();
		wfObject.setCurrentNode(currentNode);
		wfObject.setInstanceID(jsonObject.getString("InstanceID"));
		wfObject.setIsFinished(Boolean.parseBoolean(jsonObject.getString("IsFinished")));
		
		WFCache.put(getAuthId()+currentNode, jsonNamedDatas);
		
		
//		String namedDatas = map.get("NamedDatas").toString();
//		JSONObject jsonObject = JSON.parseObject(namedDatas);
//		String doc = jsonObject.getString("SurveyReportDoc");
//		Document document = JSON.parseObject(doc, Document.class);
//		// wfObject.setNamedDatas(namedDatas);
		return workflowService.next(wfObject);

	}
	/**
	 * 退回上一步流程
	 * @return
	 */
	@RequestMapping(value = "/Previous", method = RequestMethod.POST)
	@ResponseBody
    public WFObject previous(@RequestBody String map){
		WFObject wfObject = new WFObject();
		JSONObject jsonObject = JSON.parseObject(map);
		JSONObject jsonNamedDatas = JSON.parseObject(jsonObject.getString("NamedDatas"));
		String currentNode = jsonObject.getString("CurrentNode"); // map.get("CurrentNode").toString();
		wfObject.setCurrentNode(currentNode);
		wfObject.setInstanceID(jsonObject.getString("InstanceID"));
		wfObject.setIsFinished(Boolean.parseBoolean(jsonObject.getString("IsFinished")));
		WFCache.put(getAuthId()+currentNode, jsonNamedDatas);
		return workflowService.Previous(wfObject);
	}
}
