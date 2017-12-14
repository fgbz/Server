package phalaenopsis.patrolManagement.entity;

import java.io.Serializable;

/**
 * 巡查人员及组织号
 * @author jund
 * 2017-10-12
 */
public class PatrolUserInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 用户ID
	 */
	private String userID;
	
	/**
	 * 用户姓名
	 */
	private String userName;
	
	/**
	 * 所属组织部门ID
	 */
	private String organizationID;
	
	/**
	 * 所属组织部门名称
	 */
	private String organizationName;
	
	/**
	 * 所属组织机构级别
	 */
	private  int orgGrade;
	
	/**
	 * 所属组织机构级别
	 */
	private  String parentId;
	
	/**
	 * online
	 */
	private String isOnline;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrganizationID() {
		return organizationID;
	}

	public void setOrganizationID(String organizationID) {
		this.organizationID = organizationID;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public int getOrgGrade() {
		return orgGrade;
	}

	public void setOrgGrade(int orgGrade) {
		this.orgGrade = orgGrade;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}
	
	
}
