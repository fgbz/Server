package phalaenopsis.illegalclue.workflownodes;


import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.ManualAssembly;
import phalaenopsis.common.method.cache.WFCache;
import phalaenopsis.illegalclue.entity.Clue;
import phalaenopsis.illegalclue.entity.ClueEnd;
import phalaenopsis.illegalclue.service.IllegalClueServiceNew;
import phalaenopsis.workflowEngine.core.NodeFlowContext;
import phalaenopsis.workflowEngine.core.WorkflowNode;

/**
 * 办结
 * @author chunl
 *
 */
public class EndNode extends WorkflowNode{

	@Override
	protected void Next(NodeFlowContext flowContext) {
		Clue  clue=(Clue) WFCache.get(Basis.getAuthId()+"EndNode");
		ClueEnd clueEnd=clue.getcEnd();
		IllegalClueServiceNew service = ManualAssembly.getAssembly("illegalClueServiceNew");
		service.saveOrUpdateClueEnd(clueEnd);
	}

}
