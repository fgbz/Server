package phalaenopsis.workflowEngine.core;

/**
 * 流程流转的类型
 * @author chunl
 *
 */
public interface FlowType {
	
	/**
	 * 下一步
	 */
	static final int Next = 0;
	
	/**
	 * 上一步
	 */
	static final int Previous = 1;
}
