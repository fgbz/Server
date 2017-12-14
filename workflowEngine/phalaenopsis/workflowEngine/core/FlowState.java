package phalaenopsis.workflowEngine.core;

/**
 * 流转状态
 * 
 * @author chunl
 *
 */
public interface FlowState {

	static final int Default = 0;
	
	static final int Break = 1;
	
	static final int Finish = 2;
	
	static final int RedirectNew = 3;
	
	static final int RedirectHistory = 4;
}
