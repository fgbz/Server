/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.illegalclue.workflowbranches;

import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.cache.WFCache;
import phalaenopsis.illegalclue.entity.Clue;
import phalaenopsis.illegalclue.entity.ClueAudit;
import phalaenopsis.illegalclue.entity.ClueEnd;
import phalaenopsis.workflowEngine.core.IBranchSelector;
import phalaenopsis.workflowEngine.core.NodeDataContext;

/**
 * @author yangw786
 *
 * 2017年6月30日上午11:09:32
 */
public class EndAuditSelector extends Basis  implements IBranchSelector {

	/* (non-Javadoc)
	 * @see phalaenopsis.workflowEngine.core.IBranchSelector#selectBranch(phalaenopsis.workflowEngine.core.NodeDataContext, java.lang.String, java.lang.String)
	 */
	@Override
	public String selectBranch(NodeDataContext dataContext, String currentNode, String currentBranch) {
		// TODO Auto-generated method stub
		Clue clue = (Clue)WFCache.getData(Basis.getAuthId()+ currentNode);
		ClueAudit clueEnd=clue.getCendAudit();
		if(clueEnd.getStatus()==0){
			//不同意，回退到登记节点
			//TODO
			return "back";
		}else{
			return "end";
		}
	}

}
