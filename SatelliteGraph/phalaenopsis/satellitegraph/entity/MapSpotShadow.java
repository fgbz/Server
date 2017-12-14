package phalaenopsis.satellitegraph.entity;

import java.util.Date;

/**
 * 图斑分宗、并宗的所有过程影像点
 * @author chunl
 *
 */
public class MapSpotShadow {
	
	/**
	 * 影像点ID
	 */
	private long shadowID;
	
	/**
	 * 该影像点对应的图斑数据ID
	 */
	private long mapSpotID;
	
	/**
	 * 影像点的类型
	 */
	private Integer shadowType;
	
	/**
	 * 是否为叶子节点
	 */
	private boolean isLeaf;
	
	/**
	 * 影像点对应图斑的业务类型
	 */
	private String bizType;

	public long getShadowID() {
		return shadowID;
	}

	public void setShadowID(long shadowID) {
		this.shadowID = shadowID;
	}

	public long getMapSpotID() {
		return mapSpotID;
	}

	public void setMapSpotID(long mapSpotID) {
		this.mapSpotID = mapSpotID;
	}

	public Integer getShadowType() {
		return shadowType;
	}

	public void setShadowType(Integer shadowType) {
		this.shadowType = shadowType;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
}
