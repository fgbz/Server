/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.workflowEngine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yangw786
 *
 * 2017年4月5日下午2:14:25
 * 定义工作流服务对客户端公开的操作
 */
public interface IWorkflowService {
	/**
	 * 发起一个新的流程
	 * @param mapID 要发起的流程的流程图ID
	 * @return 如果一个流程发起成功，则返回流程实例的ID
	 */
	WFObject start(String mapID);
	
	/**
	 * 流程实例流转到下一步 
	 * @param obj 要流转的流程实例
	 * @return 如果成功流转到下一步，则返回流转到的下一步的节点ID
	 */
	WFObject next(@RequestBody WFObject obj);
	
	/**
	 * 获取流程实例已经经过的节点顺序步骤
	 * @param instanceID
	 * @return
	 */
	String[] getHistoryNodeSequences(String instanceID);
	
	/**
	 * 获取流程实例当前所处的节点
	 * @param instanceID
	 * @return
	 */
	WFObject getCurrent(String instanceID);
	
	/**
	 * 获取流程图定义的开始节点
	 * @param mapID
	 * @return
	 */
	String getStartNode(String mapID);
	
	/**
	 * 获取流程图定义的所有结束节点
	 * @param mapID
	 * @return
	 */
	String[] getEndNodes(String mapID);
}
