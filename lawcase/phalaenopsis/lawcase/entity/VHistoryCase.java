package phalaenopsis.lawcase.entity;

/**
 * HistoryCase查询列表视图
 * 
 */
public class VHistoryCase {
	/**
	 * 主键ID
	 * 
	 */
	private String id;
	/**
	 * 项目地址完整地址
	 * 
	 */
	private String projectAddress;
	/**
	 * 违法用地类型
	 * 
	 */
	private String landTypeText;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProjectAddress() {
		return projectAddress;
	}
	public void setProjectAddress(String projectAddress) {
		this.projectAddress = projectAddress;
	}
	public String getLandTypeText() {
		return landTypeText;
	}
	public void setLandTypeText(String landTypeText) {
		this.landTypeText = landTypeText;
	}
	
}