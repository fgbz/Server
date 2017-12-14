package phalaenopsis.workflowEngine.core;

/**
 * 流程节点
 * @author chunl
 *
 */
public interface IWorkflowNode {
	
	/**
	 * 流程节点在某个流程图中的唯一ID
	 * @param nodeId
	 */
	void setNodeID(String nodeId);
	
	String getNodeID();
	
	/**
	 * 通知流程节点，流程即将流转到下一步
	 * @param flowContext
	 */
	void onNext(NodeFlowContext flowContext);
	
	/**
	 * 通知流程节点，流程即将退回到上一步
	 * @param flowContext
	 */
	void onPrevious(NodeFlowContext flowContext);
}
