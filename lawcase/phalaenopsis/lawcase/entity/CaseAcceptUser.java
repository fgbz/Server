package phalaenopsis.lawcase.entity;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 案件关联受理人
 */
public class CaseAcceptUser {
	/**
	 * 主键ID
	 * 
	 */
	@JsonProperty("ID")
	private String id;
	/**
	 * 案件ID
	 * 
	 */
	@JsonProperty("CaseID")
	private String caseID;
	/**
	 * 受理人ID
	 * 
	 */
	@JsonProperty("AcceptUserID")
	private String acceptUserID;
	/**
	 * 受理人名称
	 * 
	 */
	@JsonProperty("AcceptUserName")
	private String acceptUserName;
	/**
	 * 是否为主办人
	 * 
	 */
	@JsonProperty("IsMain")
	private Integer isMain;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCaseID() {
		return caseID;
	}
	public void setCaseID(String caseID) {
		this.caseID = caseID;
	}
	public String getAcceptUserID() {
		return acceptUserID;
	}
	public void setAcceptUserID(String acceptUserID) {
		this.acceptUserID = acceptUserID;
	}
	public String getAcceptUserName() {
		return acceptUserName;
	}
	public void setAcceptUserName(String acceptUserName) {
		this.acceptUserName = acceptUserName;
	}
	public Integer getIsMain() {
		return isMain;
	}
	public void setIsMain(Integer isMain) {
		this.isMain = isMain;
	}
	
}