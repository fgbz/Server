/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.workflowEngine.controller;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import phalaenopsis.common.entity.Condition;

/**
 * @author yangw786
 *
 * 2017年4月5日下午2:07:37
 * 工作流数据契约对象
 */
public class WFObject {
	public WFObject() {
        this.ServerNamedDatas = new ArrayList<Condition>();
    }
	
	
	public JSONObject Data;
	
	public JSONObject getData() {
		return Data;
	}

	public void setData(JSONObject data) {
		Data = data;
	}

	public JSONObject getServerData() {
		return ServerData;
	}


	/**
	 * 流程实例ID
	 */
	public String InstanceID;
	
	/**
	 * 流程实例当前所处的节点
	 */
	public String CurrentNode;
	
	/**
	 * 流程是否已经结束
	 */
	public boolean IsFinished;
	
	/**
	 * 获取或设置在本次流转中，服务端传递给流程节点的数据
	 */
	public JSONObject ServerData;
	
	public void setServerData(JSONObject serverData) {
		ServerData = serverData;
	}

	public Object  NamedDatas;

	public Object getNamedDatas() {
		return NamedDatas;
	}

	public void setNamedDatas(Object namedDatas) {
		NamedDatas = namedDatas;
	}


	/**
	 * 获取或设置在本次流转中，服务端传递给流程节点的多个命名的数据 
	 */

	public List<Condition> ServerNamedDatas;



	/**
	 * @return the instanceID
	 */
	public String getInstanceID() {
		return InstanceID;
	}

	/**
	 * @param instanceID the instanceID to set
	 */
	public void setInstanceID(String instanceID) {
		InstanceID = instanceID;
	}

	/**
	 * @return the currentNode
	 */
	public String getCurrentNode() {
		return CurrentNode;
	}

	/**
	 * @param currentNode the currentNode to set
	 */
	public void setCurrentNode(String currentNode) {
		CurrentNode = currentNode;
	}

	/**
	 * @return the isFinished
	 */
	public boolean isIsFinished() {
		return IsFinished;
	}

	/**
	 * @param isFinished the isFinished to set
	 */
	public void setIsFinished(boolean isFinished) {
		IsFinished = isFinished;
	}


	/**
	 * @return the serverNamedDatas
	 */
	public List<Condition> getServerNamedDatas() {
		return ServerNamedDatas;
	}

	/**
	 * @param serverNamedDatas the serverNamedDatas to set
	 */
	public void setServerNamedDatas(List<Condition> serverNamedDatas) {
		ServerNamedDatas = serverNamedDatas;
	}
	
	
}
