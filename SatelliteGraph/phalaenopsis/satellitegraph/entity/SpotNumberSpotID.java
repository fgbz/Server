/**
 * 
 */
package phalaenopsis.satellitegraph.entity;

import java.io.Serializable;

/**
 * @author gaofengt
 *
 *         2017年4月21日下午2:02:31 //移动端地图过滤 图斑号和图斑ID
 */
public class SpotNumberSpotID implements Serializable {

	/**
	 * 图斑ID
	 */

	private long SpotID;

	/**
	 * 图斑号
	 */

	private String SpotNumber;

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
}
