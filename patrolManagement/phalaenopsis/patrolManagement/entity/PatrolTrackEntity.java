package phalaenopsis.patrolManagement.entity;

import java.io.Serializable;
import java.util.Date;

public class PatrolTrackEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _id;
	private PatrolTrackLocation location = new PatrolTrackLocation();
	private String patrol_usr_id;
	private String patrol_org_id;
	private Date post_time;
	private Double distance;
	private Double patrol_direction;
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
	public Date getPost_time() {
		return post_time;
	}
	public void setPost_time(Date post_time) {
		this.post_time = post_time;
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
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public boolean getFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	 

}