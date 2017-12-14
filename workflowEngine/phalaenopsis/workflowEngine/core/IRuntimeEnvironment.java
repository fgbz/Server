package phalaenopsis.workflowEngine.core;

/**
 * 流程实例运行环境
 * @author chunl
 *
 */
public interface IRuntimeEnvironment {
	
	/**
	 * 创建流程实例运行时上下文
	 * @return
	 */
	InstanceRuntimeContext createContext();
	
}
