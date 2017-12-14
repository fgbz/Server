package phalaenopsis.statistics.entity;

/**
 * 省级巡查与统计上报数据比对
 * @author dongdongt
 *
 */
public class PatroAndReport {
	/**
	 * 区域名称
	 */
	private String regionName;
	/**
	 * 卫片发现案件数量
	 */
	private Integer patroCount;
	/**
	 * 卫片发现案件面积
	 */
	private Integer patroArea;
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
	public Integer getPatroCount() {
		return patroCount;
	}
	public void setPatroCount(Integer patroCount) {
		this.patroCount = patroCount;
	}
	public Integer getPatroArea() {
		return patroArea;
	}
	public void setPatroArea(Integer patroArea) {
		this.patroArea = patroArea;
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
