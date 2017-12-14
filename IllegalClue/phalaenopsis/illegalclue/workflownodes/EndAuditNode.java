/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.illegalclue.workflownodes;

import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.ManualAssembly;
import phalaenopsis.common.method.cache.WFCache;
import phalaenopsis.illegalclue.entity.Clue;
import phalaenopsis.illegalclue.entity.ClueAudit;
import phalaenopsis.illegalclue.entity.ClueEnd;
import phalaenopsis.illegalclue.service.IllegalClueServiceNew;
import phalaenopsis.workflowEngine.core.NodeFlowContext;
import phalaenopsis.workflowEngine.core.WorkflowNode;

/**
 * @author yangw786
 *
 * 2017年6月30日上午11:10:13
 */
public class EndAuditNode extends WorkflowNode{

	@Override
	protected void Next(NodeFlowContext flowContext) {
		Clue  clue=(Clue) WFCache.getData(Basis.getAuthId()+"EndAuditNode");
		ClueAudit clueEnd=clue.getCendAudit();
		IllegalClueServiceNew service = ManualAssembly.getAssembly("illegalClueServiceNew");
		service.saveOrUpdateClueAudit(clueEnd);
	}
}
