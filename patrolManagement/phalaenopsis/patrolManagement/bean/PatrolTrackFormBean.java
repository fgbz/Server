package phalaenopsis.patrolManagement.bean;


import java.io.Serializable;
import java.util.List;

public class PatrolTrackFormBean implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2493826168202799133L;
	private List<PatrolTrackBean> patrolList ;
	private String patrolUserId;
	
	public List<PatrolTrackBean> getPatrolList() {
		return patrolList;
	}

	public void setPatrolList(List<PatrolTrackBean> patrolList) {
		this.patrolList = patrolList;
	}

	public String getPatrolUserId() {
		return patrolUserId;
	}

	public void setPatrolUserId(String patrolUserId) {
		this.patrolUserId = patrolUserId;
	}
	
	
	
}