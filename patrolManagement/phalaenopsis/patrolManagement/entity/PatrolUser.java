package phalaenopsis.patrolManagement.entity;

import java.io.Serializable;

import phalaenopsis.patrolManagement.bean.PatrolTrackEntityBean;

/**
 * 巡查人员及组织号
 * @author jund
 * 2017-10-12
 */
public class PatrolUser implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 所属级别 1区县 2市 3省
	 */
	private Integer grade;
	/**
	 * 用户姓名
	 */
	private String userName;
	/**
	 * 省级组织id
	 */
	private String proOrgId;
	/**
	 * 省级组织名称
	 */
	private String proOrgName;
	/**
	 * 省级组织在线人数
	 */
	private int proOnlineNum;
	/**
	 * 省总人数
	 */
	private int prolineNum;
	/**
	 * 市级组织id
	 */
	private String cityOrgId;
	/**
	 * 市级组织名称
	 */
	private String cityOrgName;
	/**
	 * 市级组织在线人数
	 */
	private int cityOnlineNum;
	/**
	 * 市级总人数
	 */
	private int citylineNum;
	/**
	 * 区县级组织id
	 */
	private String countyOrgId;
	/**
	 * 区县级组织名称
	 */
	private String countyOrgName;
	/**
	 * 区县级组织在线人数
	 */
	private int countyOnlineNum;
	/**
	 * 区县级总人数
	 */
	private int countylineNum;
	
	/**
	 * 是否在线
	 */
	public String isOnline;
	/**
	 * 坐标
	 */
	public PatrolTrackEntityBean patrolTrackEntityBean;
	
	/**
	 * 当前级组织id
	 *//*
	private String ObjectId;
	*//**
	 * 当前级组织名称
	 *//*
	private String name;
	
	*//**
	 * 当前父级组织id
	 *//*
	private String parentID;
	*//**
	 * 在线人数
	 *//*
	private int onlineNum;
	*//**
	 * 离线人数
	 *//*
	private int offlineNum;
	*//**
	 * 总人数
	 *//*
	private int lineNum;*/
	
	/*public int getLineNum() {
		return lineNum;
	}
	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}*/
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	/*public int getOnlineNum() {
		return onlineNum;
	}
	public void setOnlineNum(int onlineNum) {
		this.onlineNum = onlineNum;
	}
	public int getOfflineNum() {
		return offlineNum;
	}
	public void setOfflineNum(int offlineNum) {
		this.offlineNum = offlineNum;
	}
	public String getObjectId() {
		return ObjectId;
	}
	public void setObjectId(String objectId) {
		ObjectId = objectId;
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
	}*/
	public String getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}
	public PatrolTrackEntityBean getPatrolTrackEntityBean() {
		return patrolTrackEntityBean;
	}
	public void setPatrolTrackEntityBean(PatrolTrackEntityBean patrolTrackEntityBean) {
		this.patrolTrackEntityBean = patrolTrackEntityBean;
	}
	public String getProOrgId() {
		return proOrgId;
	}
	public void setProOrgId(String proOrgId) {
		this.proOrgId = proOrgId;
	}
	public String getProOrgName() {
		return proOrgName;
	}
	public void setProOrgName(String proOrgName) {
		this.proOrgName = proOrgName;
	}
	public String getCityOrgId() {
		return cityOrgId;
	}
	public void setCityOrgId(String cityOrgId) {
		this.cityOrgId = cityOrgId;
	}
	public String getCityOrgName() {
		return cityOrgName;
	}
	public void setCityOrgName(String cityOrgName) {
		this.cityOrgName = cityOrgName;
	}
	public String getCountyOrgId() {
		return countyOrgId;
	}
	public void setCountyOrgId(String countyOrgId) {
		this.countyOrgId = countyOrgId;
	}
	public String getCountyOrgName() {
		return countyOrgName;
	}
	public void setCountyOrgName(String countyOrgName) {
		this.countyOrgName = countyOrgName;
	}
	public int getProOnlineNum() {
		return proOnlineNum;
	}
	public void setProOnlineNum(int proOnlineNum) {
		this.proOnlineNum = proOnlineNum;
	}
	
	public int getCityOnlineNum() {
		return cityOnlineNum;
	}
	public void setCityOnlineNum(int cityOnlineNum) {
		this.cityOnlineNum = cityOnlineNum;
	}
	
	public int getCountyOnlineNum() {
		return countyOnlineNum;
	}
	public void setCountyOnlineNum(int countyOnlineNum) {
		this.countyOnlineNum = countyOnlineNum;
	}
	public int getProlineNum() {
		return prolineNum;
	}
	public void setProlineNum(int prolineNum) {
		this.prolineNum = prolineNum;
	}
	public int getCitylineNum() {
		return citylineNum;
	}
	public void setCitylineNum(int citylineNum) {
		this.citylineNum = citylineNum;
	}
	public int getCountylineNum() {
		return countylineNum;
	}
	public void setCountylineNum(int countylineNum) {
		this.countylineNum = countylineNum;
	}
	
}
