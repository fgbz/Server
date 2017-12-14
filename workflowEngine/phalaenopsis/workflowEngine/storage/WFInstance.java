/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.workflowEngine.storage;

import java.io.Serializable;

/**
 * @author yangw786
 *
 * 2017年4月5日下午1:08:23
 * 流程实例信息
 */
public class WFInstance implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 流程实例ID
	 */
	public String InstanceID;

    /**
     * 流程图ID 
     */
    public String MapID;

    /**
     *  当前节点ID 
     */
    public String NodeID;

    /**
     * 流程实例状态 
     */
    public int State;
    
    /**
     * 当前节点所处的流程步骤指针
     */
    public int SequencePtr;

    /**
     * 流程实例对所有节点的共享数据 
     */
    public String InstanceData;

    /**
     * 流程流转时传递给下一个节点的数据
     */
    public String FlowData;

    /**
     *  流程流转的分支路线信息 
     */
    public String Route;
    
    /**
     * 用于数据库操作字段，没任何意义。
     */
    public int count;
    
    public Integer version;
    
    

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

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
	 * @return the mapID
	 */
	public String getMapID() {
		return MapID;
	}

	/**
	 * @param mapID the mapID to set
	 */
	public void setMapID(String mapID) {
		MapID = mapID;
	}

	/**
	 * @return the nodeID
	 */
	public String getNodeID() {
		return NodeID;
	}

	/**
	 * @param nodeID the nodeID to set
	 */
	public void setNodeID(String nodeID) {
		NodeID = nodeID;
	}

	/**
	 * @return the state
	 */
	public int getState() {
		return State;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		State = state;
	}

	/**
	 * @return the sequencePtr
	 */
	public int getSequencePtr() {
		return SequencePtr;
	}

	/**
	 * @param sequencePtr the sequencePtr to set
	 */
	public void setSequencePtr(int sequencePtr) {
		SequencePtr = sequencePtr;
	}

	/**
	 * @return the instanceData
	 */
	public String getInstanceData() {
		return InstanceData;
	}

	/**
	 * @param instanceData the instanceData to set
	 */
	public void setInstanceData(String instanceData) {
		InstanceData = instanceData;
	}

	/**
	 * @return the flowData
	 */
	public String getFlowData() {
		return FlowData;
	}

	/**
	 * @param flowData the flowData to set
	 */
	public void setFlowData(String flowData) {
		FlowData = flowData;
	}

	/**
	 * @return the route
	 */
	public String getRoute() {
		return Route;
	}

	/**
	 * @param route the route to set
	 */
	public void setRoute(String route) {
		Route = route;
	} 
}
