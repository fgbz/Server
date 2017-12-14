package phalaenopsis.patrolManagement.bean;

import java.util.List;

public class PatrolTrackViewBean{
	private String falg;
	private String patrolUsername;
	private List<PatrolTrackEntityBean> patrolData;
	private String patrolOrgname;
	private String patrolDistance;
	public String getFalg() {
		return falg;
	}
	public void setFalg(String falg) {
		this.falg = falg;
	}
	public List<PatrolTrackEntityBean> getPatrolData() {
		return patrolData;
	}
	public void setPatrolData(List<PatrolTrackEntityBean> patrolData) {
		this.patrolData = patrolData;
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
	

}