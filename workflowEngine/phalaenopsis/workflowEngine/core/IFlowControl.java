package phalaenopsis.workflowEngine.core;

/**
 * 流转控制器
 * 
 * @author chunl
 *
 */
public interface IFlowControl {
	
	/**
	 * 上一步
	 * @param instance
	 * @param flowContext
	 * @return
	 */
	String previous(IWorkflowInstance instance, NodeFlowContext flowContext);
	
}
