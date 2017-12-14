package phalaenopsis.workflowEngine.controller;

import static org.springframework.util.Assert.notNull;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import phalaenopsis.common.method.Basis;
import phalaenopsis.workflowEngine.core.WorkflowEngineInstance;

/**
 * 定义工作流服务对客户端公开的操作
 * 
 * @author chunl
 *
 */
public class WorkflowService  extends Basis{

	@Autowired
	private WorkflowEngineInstance engine;

	/**
	 * 发起一个新的流程
	 * 
	 * @param mapID
	 *            要发起的流程的流程图ID
	 * @return 如果一个流程发起成功，则返回流程实例的ID
	 *         此处如果不包装请求，那么客户端post请求内容mapID的时候，WCF会直接拒绝该请求，因为请求的正文不是json格式（
	 *         同时参数又不是Stream）， 因为json数据类型规定了字符串必须加双引号，
	 *         所以客户端post的mapID就必须要写成"mapID"才是合法的json数据类型字符串
	 */
	@RequestMapping(value = "/Start", method = RequestMethod.POST)
	@ResponseBody
	public WFObject start(@RequestBody String mapID) {
		notNull(mapID);
		return engine.start(mapID);
	}

	/**
	 * 流程实例流转到下一步
	 * 
	 * @param obj
	 *            要流转的流程实例
	 * @return 如果成功流转到下一步，则返回流转到的下一步的节点ID
	 */

	public WFObject next(@RequestBody String map) {
		WFObject obj = new WFObject();
		//obj.setInstanceID(map.get("InstanceID"));
		notNull(obj);
		return engine.next(obj);
	}

	@RequestMapping(value = "/GetHistoryNodeSequences", method = RequestMethod.GET)
	@ResponseBody
	public String[] getHistoryNodeSequences(String instanceID) {
		notNull(instanceID);
		return engine.getHistoryNodeSequences(instanceID);
	}

	@RequestMapping(value = "/GetCurrent", method = RequestMethod.GET)
	@ResponseBody
	public WFObject getCurrent(String instanceID) {
		notNull(instanceID);
		return engine.getCurrent(instanceID);
	}

	/**
	 * 获取所有起始节点
	 */
	@RequestMapping(value = "/GetStartNode", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getStartNode(String mapID) {
		Map<String, Object> map =new HashMap<String, Object>();
		notNull(mapID);
		String result =engine.getStartNode(mapID);
		map.put("result", result);
		return map;
	}
	


	/**
	 * 获取所有结束节点
	 */
	@RequestMapping(value = "/GetEndNodes", method = RequestMethod.GET)
	@ResponseBody
	public String[] getEndNodes(String mapID) {
		notNull(mapID);
		String[] result = engine.getEndNodes(mapID);
		return result;
	}

}
