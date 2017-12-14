package phalaenopsis.statistics.entity;

/**
 * 市统计上报潍坊案件数及违法用地面积数比对
 * @author dongdongt
 *
 */
public class ReportAndillegal {
	/**
	 * 区域名称
	 */
	private String regionName;
	/**
	 * 上报案件数量
	 */
	private Integer reportCount;
	/**
	 * 违法用地面积
	 */
	private Integer illegalArea;
	public Integer getReportCount() {
		return reportCount;
	}
	public void setReportCount(Integer reportCount) {
		this.reportCount = reportCount;
	}
	public Integer getIllegalArea() {
		return illegalArea;
	}
	public void setIllegalArea(Integer illegalArea) {
		this.illegalArea = illegalArea;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
}
