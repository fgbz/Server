package phalaenopsis.patrolManagement.entity;

import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;


public class PatrolTrackLocation implements Serializable {
    private String type = "Point";
    private List coordinates = new ArrayList();
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(List coordinates) {
		this.coordinates = coordinates;
	}
    

}