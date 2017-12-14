/**
 * 
 */
package phalaenopsis.satellitegraph.entity;

/**
 * @author gaofengt
 *
 *         2017年4月25日下午4:50:24 图斑坐标（移动端）
 */
public class SpotSDE {

	/**
	 * 关联的MapSpot主键
	 */
	public long MID;

	/**
	 * 空间坐标
	 */
	public String Shape;

	public long getMID() {
		return MID;
	}

	public void setMID(long mID) {
		MID = mID;
	}

	public String getShape() {
		return Shape;
	}

	public void setShape(String shape) {
		Shape = shape;
	}
}
