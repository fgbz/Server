package phalaenopsis.workflowEngine.core;

/**
 * 分支选择器
 * @author chunl
 *
 */
public interface IBranchSelector {
	
	/**
	 * 
	 * @param dataContext
	 * @param currentNode 当前节点
	 * @param currentBranch 流转到当前节点时，所处的分支
	 * @return 返回选择的分支name
	 */
	String selectBranch(NodeDataContext dataContext, String currentNode, String currentBranch);
	
}
