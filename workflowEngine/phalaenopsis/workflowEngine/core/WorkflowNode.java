/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.workflowEngine.core;

/**
 * @author yangw786
 *
 * 2017年4月6日下午4:01:16
 * 流程节点的抽象基类
 */
public abstract class WorkflowNode implements IWorkflowNode {
	private String nodeID;
	
	
    /**
	 * @return the nodeID
	 */
	public String getNodeID() {
		return nodeID;
	}

	/**
	 * @param nodeID the nodeID to set
	 */
	public void setNodeID(String nodeID) {
		if (nodeID != null && nodeID.equals("")) {
			this.nodeID = nodeID;
		}
	}

    public void onPrevious(NodeFlowContext flowContext) {
        this.Previous(flowContext);
    }

    public void onNext(NodeFlowContext flowContext) {
        this.Next(flowContext);
    }

    /**
     * 流程退回到上一步时需要处理的业务方法
     */
    protected void Previous(NodeFlowContext flowContext) {
    }

    /// <summary>
    /// 流程流转到下一步时需要处理的业务方法
    /// </summary>
    /// <param name="flowContext"></param>
    protected abstract void Next(NodeFlowContext flowContext);
}
