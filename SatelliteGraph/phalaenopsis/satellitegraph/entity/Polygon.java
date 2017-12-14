package phalaenopsis.satellitegraph.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


public class Polygon extends Geometry {
	
	@JsonProperty("rings")
	public List<ArrayList<Double[]>> rings;

	public List<ArrayList<Double[]>> getRings() {
		return rings;
	}

	public void setRings(List<ArrayList<Double[]>> rings) {
		this.rings = rings;
	}
	
	public Polygon()
	{
		this.rings  = new ArrayList<ArrayList<Double[]>>();
	}
}
