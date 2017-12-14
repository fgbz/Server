package phalaenopsis.patrolManagement.bean;

import java.util.Map;

import phalaenopsis.patrolManagement.entity.PatrolTrackLocation;

public class PatrolTrackEntityBean{
	private PatrolTrackLocation location = new PatrolTrackLocation();
	private String patrol_usr_id;
	private String patrol_org_id;
	private Map post_time;
	private Double distance;
	private Double patrol_direction;
	private String dateHMS;
	private boolean flag;
	
	public PatrolTrackLocation getLocation() {
		return location;
	}
	public void setLocation(PatrolTrackLocation location) {
		this.location = location;
	}
	public String getPatrol_usr_id() {
		return patrol_usr_id;
	}
	public void setPatrol_usr_id(String patrol_usr_id) {
		this.patrol_usr_id = patrol_usr_id;
	}
	public String getPatrol_org_id() {
		return patrol_org_id;
	}
	public void setPatrol_org_id(String patrol_org_id) {
		this.patrol_org_id = patrol_org_id;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public Double getPatrol_direction() {
		return patrol_direction;
	}
	public void setPatrol_direction(Double patrol_direction) {
		this.patrol_direction = patrol_direction;
	}
	public Map getPost_time() {
		return post_time;
	}
	public void setPost_time(Map post_time) {
		this.post_time = post_time;
	}
	public String getDateHMS() {
		return dateHMS;
	}
	public void setDateHMS(String dateHMS) {
		this.dateHMS = dateHMS;
	}
	public boolean getFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	

}