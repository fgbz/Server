package phalaenopsis.common.entity;

import java.io.Serializable;
import java.util.List;

public class GeoJsonMap implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String type;
	
	public CRS crs;
	
	public List<Features> features;
}



