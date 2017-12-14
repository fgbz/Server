package phalaenopsis.statistics.entity;

/**
 * 卫片数据与统计上报数据比对
 * @author chunl
 * 2017年8月7日下午2:41:27
 */
public class SatelliteContrast {
	
	/**
	 * 地市
	 */
	private String countyName;
	
	/**
	 * 巡查发现违法案件面积
	 */
	private Double  satelliteArea;

	/**
	 * 上报违法案件面积
	 */
	private Double reportLawCaseArea;
	
	/**
	 * 巡查发现违法案件件数
	 */
	private double satelliteNum;
	
	/**
	 * 上报违法案件件数
	 */
	private double reportLawCaseNum;

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public Double getSatelliteArea() {
		return satelliteArea;
	}

	public void setSatelliteArea(Double satelliteArea) {
		this.satelliteArea = satelliteArea;
	}

	public Double getReportLawCaseArea() {
		return reportLawCaseArea;
	}

	public void setReportLawCaseArea(Double reportLawCaseArea) {
		this.reportLawCaseArea = reportLawCaseArea;
	}

	public double getSatelliteNum() {
		return satelliteNum;
	}

	public void setSatelliteNum(double satelliteNum) {
		this.satelliteNum = satelliteNum;
	}

	public double getReportLawCaseNum() {
		return reportLawCaseNum;
	}

	public void setReportLawCaseNum(double reportLawCaseNum) {
		this.reportLawCaseNum = reportLawCaseNum;
	}
	
	
}
