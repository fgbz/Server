package phalaenopsis.satellitegraph.entity;

/**
 * 图斑影像点之间的关系
 * 
 * @author chunl
 *
 */
public class MapSpotRelation {

	/**
	 * 主键ID
	 * 
	 */
	private long id;

	/**
	 * 影像点1
	 * 
	 */
	private long shadow1;

	/**
	 * 影像点2
	 * 
	 */
	private long shadow2;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getShadow1() {
		return shadow1;
	}

	public void setShadow1(long shadow1) {
		this.shadow1 = shadow1;
	}

	public long getShadow2() {
		return shadow2;
	}

	public void setShadow2(long shadow2) {
		this.shadow2 = shadow2;
	}



}
