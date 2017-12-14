package phalaenopsis.patrolManagement.bean;

import java.io.Serializable;

public class PatrolTrackHistoryBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5066125485565816392L;
	/**
	 * 用户名称
	 */
	private String patrolUsername;
	/**
	 * 组织名称
	 */
	private String patrolOrgname;
	/**
	 * 时间
	 */
	private String patrolTime;
	/**
	 * 时间段
	 */
	private String patrolSpacer;
	
	/**
	 * 巡查总时间
	 */
	private String patrolTimetotal;
	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	
	private String patrolUserId;
	
	public String getPatrolUsername() {
		return patrolUsername;
	}
	public void setPatrolUsername(String patrolUsername) {
		this.patrolUsername = patrolUsername;
	}
	public String getPatrolOrgname() {
		return patrolOrgname;
	}
	public void setPatrolOrgname(String patrolOrgname) {
		this.patrolOrgname = patrolOrgname;
	}
	public String getPatrolTime() {
		return patrolTime;
	}
	public void setPatrolTime(String patrolTime) {
		this.patrolTime = patrolTime;
	}
	public String getPatrolSpacer() {
		return patrolSpacer;
	}
	public void setPatrolSpacer(String patrolSpacer) {
		this.patrolSpacer = patrolSpacer;
	}
	public String getPatrolTimetotal() {
		return patrolTimetotal;
	}
	public void setPatrolTimetotal(String patrolTimetotal) {
		this.patrolTimetotal = patrolTimetotal;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getPatrolUserId() {
		return patrolUserId;
	}
	public void setPatrolUserId(String patrolUserId) {
		this.patrolUserId = patrolUserId;
	}
	
	
	

}