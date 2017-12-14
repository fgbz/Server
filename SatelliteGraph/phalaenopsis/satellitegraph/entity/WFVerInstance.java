package phalaenopsis.satellitegraph.entity;

import phalaenopsis.workflowEngine.storage.WFInstance;

public class WFVerInstance  extends WFInstance{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 图斑流程还原的次数
	 */
	public Integer Version;

	public Integer getVersion() {
		return Version;
	}

	public void setVersion(Integer version) {
		Version = version;
	}
}
