package phalaenopsis.patrolManagement.bean;

import java.util.List;
import java.util.Map;

public class PatrolTrackEntity{
	private String falg;
	private String patrolUsername;
	private List<Map> data; 
	private String patrolOrgname;
	private String patrolDistance;
	private String startTime;
	private String endTime;
	private String timeSpace;
	public String getFalg() {
		return falg;
	}
	public void setFalg(String falg) {
		this.falg = falg;
	}
	public String getPatrolOrgname() {
		return patrolOrgname;
	}
	public void setPatrolOrgname(String patrolOrgname) {
		this.patrolOrgname = patrolOrgname;
	}
	public String getPatrolDistance() {
		return patrolDistance;
	}
	public void setPatrolDistance(String patrolDistance) {
		this.patrolDistance = patrolDistance;
	}
	public String getPatrolUsername() {
		return patrolUsername;
	}
	public void setPatrolUsername(String patrolUsername) {
		this.patrolUsername = patrolUsername;
	}
	public List<Map> getData() {
		return data;
	}
	public void setData(List<Map> data) {
		this.data = data;
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
	public String getTimeSpace() {
		return timeSpace;
	}
	public void setTimeSpace(String timeSpace) {
		this.timeSpace = timeSpace;
	}
	

}