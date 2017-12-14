package phalaenopsis.common.entity;

import java.io.Serializable;
import java.util.List;

public class Properties implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String id;
	
	public String name;
	
	public double hc_middle_x;
	
	public double hc_middle_y;
	
	public List<Double> cp;
}
