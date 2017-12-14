/**
 * 
 */
package phalaenopsis.satellitegraph.entity;

import java.io.Serializable;

/**
 * @author gaofengt
 *
 *         2017年4月21日下午1:21:36 移动端 图斑shape信息
 */
public class SpotShapeInfo implements Serializable {

	/**
	 * 图斑ID
	 */

	public long SpotID;

	/**
	 * 图斑号
	 */

	public String SpotNumber;

	/**
	 * 图斑类型
	 */

	public MobileSpotType SpotType;
	
	/**
	 * 图斑shape信息
	 */

	public String Shape;

	public long getSpotID() {
		return SpotID;
	}

	public void setSpotID(long spotID) {
		SpotID = spotID;
	}

	public String getSpotNumber() {
		return SpotNumber;
	}

	public void setSpotNumber(String spotNumber) {
		SpotNumber = spotNumber;
	}

	public MobileSpotType getSpotType() {
		return SpotType;
	}

	public void setSpotType(MobileSpotType spotType) {
		SpotType = spotType;
	}

	public String getShape() {
		return Shape;
	}

	public void setShape(String shape) {
		Shape = shape;
	}

	
}
