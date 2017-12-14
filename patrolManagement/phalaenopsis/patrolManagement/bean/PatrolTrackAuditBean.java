package phalaenopsis.patrolManagement.bean;


import java.io.Serializable;

public class PatrolTrackAuditBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5066125485565816392L;
	private String patrolUserId;
	private String startTime;
	private String endTime;
	public String getPatrolUserId() {
		return patrolUserId;
	}
	public void setPatrolUserId(String patrolUserId) {
		this.patrolUserId = patrolUserId;
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
	

}