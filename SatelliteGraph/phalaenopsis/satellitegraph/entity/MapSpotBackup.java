package phalaenopsis.satellitegraph.entity;

import com.alibaba.fastjson.JSON;

/**
 * 分宗、并宗前的图斑备份
 * 
 * @author chunl
 *
 */
public class MapSpotBackup {

	/**
	 * 数据ID
	 * 
	 */
	private long id;
	
	
	/**
	 * 原始图斑ID
	 * 
	 */
	private long mapSpotID;

	/**
	 * 原始图斑的业务类型
	 * 
	 */
	private String bizType;

	/**
	 * 图斑数据
	 * 
	 */
	private String entity;

	/**
	 * 坐标
	 * 
	 */
	private Polygon shape;
	
	/**
	 * 坐标在数据库存储的是clob类型，Polygon
	 */
	private String strShape;

	public String getStrShape() {
		return strShape;
	}

	public void setStrShape(String strShape) {
		this.strShape = strShape;
		this.shape = JSON.parseObject(strShape, Polygon.class);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMapSpotID() {
		return mapSpotID;
	}

	public void setMapSpotID(long mapSpotID) {
		this.mapSpotID = mapSpotID;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public Polygon getShape() {
		return shape;
	}

	public void setShape(Polygon shape) {
		this.shape = shape;
	}


}
