package phalaenopsis.workflowEngine.core;

import phalaenopsis.workflowEngine.storage.IWorkflowStorage;
import phalaenopsis.workflowEngine.storage.WFInstance;

/**
 * 流程实例的运行时上下文信息
 * @author chunl
 *
 */
public final class InstanceRuntimeContext {
	
	/**
	 * 流程数据的存储程序
	 * @return
	 */
	private IWorkflowStorage storage;
	
	/**
	 * 流程实例的数据信息
	 * @return
	 */
	private WFInstance instanceData;

	public IWorkflowStorage getStorage() {
		return storage;
	}

	public void setStorage(IWorkflowStorage storage) {
		this.storage = storage;
	}

	public WFInstance getInstanceData() {
		return instanceData;
	}

	public void setInstanceData(WFInstance instanceData) {
		this.instanceData = instanceData;
	}

}
