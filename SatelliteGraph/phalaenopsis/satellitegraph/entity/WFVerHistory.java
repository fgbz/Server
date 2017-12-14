package phalaenopsis.satellitegraph.entity;

import phalaenopsis.workflowEngine.storage.WFHistory;

public class WFVerHistory  extends WFHistory{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 图斑流程还原的次数
	 */
	public int Version;

	public int getVersion() {
		return Version;
	}

	public void setVersion(int version) {
		Version = version;
	}
	
}
