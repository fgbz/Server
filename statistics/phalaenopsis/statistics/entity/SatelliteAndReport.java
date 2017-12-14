package phalaenopsis.statistics.entity;

/**
 * 卫片数据与统计上报数据比对
 * @author dongdongt
 *
 */
public class SatelliteAndReport {
	/**
	 * 区域名称
	 */
	private String regionName;
	/**
	 * 卫片发现案件数量
	 */
	private Integer satelliteCount;
	/**
	 * 卫片发现案件面积
	 */
	private Integer satelliteArea;
	/**
	 * 上报发现案件数量
	 */
	private Integer reportCount;
	/**
	 * 上报发现案件面积
	 */
	private Integer reportArea;
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public Integer getSatelliteCount() {
		return satelliteCount;
	}
	public void setSatelliteCount(Integer satelliteCount) {
		this.satelliteCount = satelliteCount;
	}
	public Integer getSatelliteArea() {
		return satelliteArea;
	}
	public void setSatelliteArea(Integer satelliteArea) {
		this.satelliteArea = satelliteArea;
	}
	public Integer getReportCount() {
		return reportCount;
	}
	public void setReportCount(Integer reportCount) {
		this.reportCount = reportCount;
	}
	public Integer getReportArea() {
		return reportArea;
	}
	public void setReportArea(Integer reportArea) {
		this.reportArea = reportArea;
	}
}
