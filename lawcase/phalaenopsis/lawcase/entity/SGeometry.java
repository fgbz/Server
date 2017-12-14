package phalaenopsis.lawcase.entity;

//import oracle.spatial.geometry.JGeometry;

public class SGeometry {
	
	/**
	 * 是否为realShape
	 */
	private boolean realShape;
	
	/**
	 * 主键Id
	 */
	private String id;
	
//	/**
//	 * 坐标
//	 */
//	private JGeometry geometry;

	public boolean isRealShape() {
		return realShape;
	}

	public void setRealShape(boolean realShape) {
		this.realShape = realShape;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

//	public JGeometry getGeometry() {
//		return geometry;
//	}
//
//	public void setGeometry(JGeometry geometry) {
//		this.geometry = geometry;
//	}
	
}
