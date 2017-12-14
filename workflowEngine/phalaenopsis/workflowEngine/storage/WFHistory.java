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
 * 2017年4月5日下午1:12:28
 * 流程流转历史
 */
public class WFHistory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 数据标识ID
	 */
    public String ID;

    /**
     * 流程实例ID
     */
    public String InstanceID;

    /**
     * 本次流转的节点
     */
    public String NodeID;

    /**
     * 流程节点流转顺序
     */
    public int Sequence;

	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(String iD) {
		ID = iD;
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
	 * @return the sequence
	 */
	public int getSequence() {
		return Sequence;
	}

	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(int sequence) {
		Sequence = sequence;
	}
}
