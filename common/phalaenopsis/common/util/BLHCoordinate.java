package phalaenopsis.common.util;

/**
 * 经纬度、海拔 坐标系。
 * @author chunl
 * 2017年8月4日下午2:35:28
 */
public class BLHCoordinate {

	public BLHCoordinate(Double Longitude, Double Latitude, Double Altitude) {
		_Longitude = Longitude;
		_Latitude = Latitude;
		_Altitude = Altitude;
	}

	/**
	 * 经度。
	 */
	private Double Longitude;

	/**
	 *  纬度。
	 */
	private Double Latitude;

	/**
	 * 海拔。
	 */
	private Double Altitude;

	public Double getLongitude() {
		return _Longitude;
	}

	public void setLongitude(Double longitude) {
		_Longitude = longitude;
	}

	public Double getLatitude() {
		return _Latitude;
	}

	public void setLatitude(Double latitude) {
		_Latitude = latitude;
	}

	public Double getAltitude() {
		return _Altitude;
	}

	public void setAltitude(Double altitude) {
		_Altitude = altitude;
	}

	private Double _Longitude = 0.0;
	private Double _Latitude = 0.0;
	private Double _Altitude = 0.0;

}
