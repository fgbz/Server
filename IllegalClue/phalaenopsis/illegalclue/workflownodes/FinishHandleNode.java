package phalaenopsis.illegalclue.workflownodes;

import phalaenopsis.workflowEngine.core.NodeFlowContext;
import phalaenopsis.workflowEngine.core.WorkflowNode;

public class FinishHandleNode extends WorkflowNode{

	@Override
	protected void Next(NodeFlowContext flowContext) {
		//审核结束、转办、交办
	}
}
