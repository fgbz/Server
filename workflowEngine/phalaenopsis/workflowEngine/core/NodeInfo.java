package phalaenopsis.workflowEngine.core;

/**
 * 
 * @author chunhongl
 *
 * 2017年4月6日下午4:26:35
 * 
 * 工作流节点的基本信息
 */
class NodeInfo {

	/**
	 * 
	 * @param nodeID
	 * 节点的ID
	 * @param classType
	 * 节点对应的类的类型
	 * @param nodeType
	 * 节点的类型
	 */
	public NodeInfo(String nodeID, Class<?> classType, int nodeType) {
		this.NodeID = nodeID;
		this.NodeType = classType;
		if ((nodeType & WorkflowNodeType.Start) == WorkflowNodeType.Start)
			this.IsStart = true;
		if ((nodeType & WorkflowNodeType.End) == WorkflowNodeType.End)
			this.IsEnd = true;
	}

	/**
	 * 节点的ID
	 */
	public String NodeID;



	/**
	 * 节点的类型
	 */
	public Class<?> NodeType;

	/**
	 * 是否是流程开始节点
	 */
	public boolean IsStart;

	/**
	 * 是否是流程结束节点
	 */
	public boolean IsEnd;

	/**
	 * 是否为分支节点
	 */
	public boolean IsBranch;

	/**
	 * 节点的分支选择器
	 */
	public Class<?> BranchSelector;

	public String getNodeID() {
		return NodeID;
	}

	public void setNodeID(String nodeID) {
		NodeID = nodeID;
	}

	public boolean isIsStart() {
		return IsStart;
	}

	public void setIsStart(boolean isStart) {
		IsStart = isStart;
	}

	public boolean isIsEnd() {
		return IsEnd;
	}

	public void setIsEnd(boolean isEnd) {
		IsEnd = isEnd;
	}

	public boolean isIsBranch() {
		return IsBranch;
	}

	public void setIsBranch(boolean isBranch) {
		IsBranch = isBranch;
	}

	public Class<?> getBranchSelector() {
		return BranchSelector;
	}

	public void setBranchSelector(Class<?> branchSelector) {
		BranchSelector = branchSelector;
	}
	public Class<?> getNodeType() {
		return NodeType;
	}

	public void setNodeType(Class<?> nodeType) {
		NodeType = nodeType;
	}
}
