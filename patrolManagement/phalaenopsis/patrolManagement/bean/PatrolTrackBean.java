package phalaenopsis.patrolManagement.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

public class PatrolTrackBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5066125485565816392L;
	/**
	 * 用户ID
	 */
	private String patrolUserId;
	/**
	 * 组织ID
	 */
	private String patrolOrgId;
	/**
	 * 经度
	 */
	private double xpos;
	/**
	 * 经度维度
	 */
	private double ypos;
	/**
	 * 时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:ss:mm") 
	private Date patrolTime;
	
	private String tempPatrolTime;
	/**
	 * 方向
	 */
	private Double patrolDirection;
	
	private boolean flag;
	
	public String getPatrolUserId() {
		return patrolUserId;
	}
	public void setPatrolUserId(String patrolUserId) {
		this.patrolUserId = patrolUserId;
	}
	public String getPatrolOrgId() {
		return patrolOrgId;
	}
	public void setPatrolOrgId(String patrolOrgId) {
		this.patrolOrgId = patrolOrgId;
	}
	public double getXpos() {
		return xpos;
	}
	public void setXpos(double xpos) {
		this.xpos = xpos;
	}
	public double getYpos() {
		return ypos;
	}
	public void setYpos(double ypos) {
		this.ypos = ypos;
	}
	public Date getPatrolTime() {
		return patrolTime;
	}
	public void setPatrolTime(Date patrolTime) {
		this.patrolTime = patrolTime;
	}
	public Double getPatrolDirection() {
		return patrolDirection;
	}
	public void setPatrolDirection(Double patrolDirection) {
		this.patrolDirection = patrolDirection;
	}
	public String getTempPatrolTime() {
		return tempPatrolTime;
	}
	public void setTempPatrolTime(String tempPatrolTime) {
		this.tempPatrolTime = tempPatrolTime;
	}
	public boolean getFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	

}