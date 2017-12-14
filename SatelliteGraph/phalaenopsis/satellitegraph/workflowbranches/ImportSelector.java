package phalaenopsis.satellitegraph.workflowbranches;

import phalaenopsis.workflowEngine.core.IBranchSelector;
import phalaenopsis.workflowEngine.core.NodeDataContext;

public class ImportSelector implements IBranchSelector{

	@Override
	public String selectBranch(NodeDataContext dataContext, String currentNode, String currentBranch) {
		
//		if ("LevelTwo".equals(currentNode))
//		{
//			return "branchTwo";
//		}
//		
//		if ("LevelThreeTwo".equals(currentNode))
//		{
//			return "branchFive";
//		}
//		
		
		
		return dataContext.getServerData().toString();
	}
	
}
