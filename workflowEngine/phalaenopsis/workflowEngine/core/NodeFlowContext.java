package phalaenopsis.workflowEngine.core;
/**
 * 
 * @author chunhongl
 *
 * 2017年4月6日下午7:55:21
 * 
 *  流程节点流转上下文
 */
public class NodeFlowContext {
	private IWorkflowInstance instance;

	NodeFlowContext(IWorkflowInstance instance) {
		this.instance = instance;
	}

	/**
	 * 当前的流程图ID
	 */
	public String getMapID() {
		return instance.getMap().getMapID();
	}

	/**
	 * 流程实例的当前节点
	 * 
	 * @return
	 */
	public String getCurrentNode() {
		return instance.getCurrent();
	}

	/**
	 * 获取流转到该节点或从该节点流出时的数据上下文
	 * 
	 * @return
	 */
//	public NodeDataContext getDataContext() {
//		return instance.getDataContext();
//	}

	/**
	 * 流程流转的类型
	 */
	public int flowType;
	/**
	 * 流转状态
	 */
	public int State;

	/**
	 * 要重定向的节点
	 */
	public String RedirectedNode;

	private void checkState() {
		if (State != FlowState.Default)
			return;
	}

	/**
	 * 中断流程执行，阻止流程流转到下一个节点，并使流程实例的流转结果返回null
	 */
	public void breakFlow() {
		checkState();
		State = FlowState.Break;
	}

	/**
	 * 强制流程立即结束
	 */
	public void finish() {
		checkState();
		State = FlowState.Finish;
	}

	/**
	 * 在流程流转到下一步的过程中，将流程重定向到新的节点
	 * 
	 * @param nextNode
	 */
	public void redirectNew(String nextNode) {
		if (flowType == FlowType.Previous)
			return;
		checkState();

		if (nextNode == null || nextNode.equals(""))
			return;
		if (!instance.getMap().containsNode(nextNode))
			return;
		RedirectedNode = nextNode;
		State = FlowState.RedirectNew;
	}

	/**
	 * 在流程退回到上一步的过程中， 将流程重定向到某个已经流转的历史节点。 如果请求重定向的节点不在历史流转记录中，则忽略。
	 * 
	 * @param historyNode
	 */
	public void redirectHistory(String historyNode) {
		if (flowType == FlowType.Next)
			return;
		checkState();

		if (historyNode == null || historyNode.equals(""))
			return;
		if (!instance.getMap().containsNode(historyNode))
			return;

		RedirectedNode = historyNode;
		State = FlowState.RedirectHistory;
	}

	public int getFlowType() {
		return flowType;
	}

	public void setFlowType(int flowType) {
		this.flowType = flowType;
	}

	public int getState() {
		return State;
	}

	public String getRedirectedNode() {
		return RedirectedNode;
	}

}

