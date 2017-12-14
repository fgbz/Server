package phalaenopsis.lawcase.entity;

/**
 * 受理人（签字人）信息
 * 
 */
public class ReceiveUserInfo {
	/**
	 * 人员ID / 单位ID
	 * 
	 */
	private String id;
	/**
	 * 代理人ID
	 * 
	 */
	private String agentID;
	/**
	 * 名称
	 * 
	 */
	private String name;
	/**
	 * Name
	 * 
	 */
	private String parentID;
	/**
	 * 是否是用户
	 * 
	 */
	private String isPeople;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAgentID() {
		return agentID;
	}
	public void setAgentID(String agentID) {
		this.agentID = agentID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentID() {
		return parentID;
	}
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}
	public String getIsPeople() {
		return isPeople;
	}
	public void setIsPeople(String isPeople) {
		this.isPeople = isPeople;
	}
	
}