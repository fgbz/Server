package phalaenopsis.illegalclue.workflownodes;

import phalaenopsis.workflowEngine.core.NodeFlowContext;
import phalaenopsis.workflowEngine.core.WorkflowNode;

/**
 * 办理（初判）
 * @author chunl
 *
 */
public class JudgeNode extends WorkflowNode{

	@Override
	protected void Next(NodeFlowContext flowContext) {
		//登记时就执行初判,登记和初判统一为登记节点
	}
}
