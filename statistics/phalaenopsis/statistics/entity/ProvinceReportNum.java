package phalaenopsis.statistics.entity;

/**
 * 省级巡查与统计上报数据比对
 * @author chunl
 * 2017年8月7日下午3:24:46
 */
public class ProvinceReportNum {
    
	/**
	 * 地市
	 */
	private String countyName;
	
	/**
	 * 卫片发现案件面积
	 */
	private Double satelliteLawCaseArea;
	
	/**
	 * 上报违法案件面积
	 */
	private Double reportLawCaseArea;
	
	/**
	 * 卫片发现案件件数
	 */
	private Integer satelliteLawCaseNum;
	
	/**
	 * 上报违法案件件数
	 */
	private Integer reportLawCaseNum;

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public Double getSatelliteLawCaseArea() {
		return satelliteLawCaseArea;
	}

	public void setSatelliteLawCaseArea(Double satelliteLawCaseArea) {
		this.satelliteLawCaseArea = satelliteLawCaseArea;
	}

	public Double getReportLawCaseArea() {
		return reportLawCaseArea;
	}

	public void setReportLawCaseArea(Double reportLawCaseArea) {
		this.reportLawCaseArea = reportLawCaseArea;
	}

	public Integer getSatelliteLawCaseNum() {
		return satelliteLawCaseNum;
	}

	public void setSatelliteLawCaseNum(Integer satelliteLawCaseNum) {
		this.satelliteLawCaseNum = satelliteLawCaseNum;
	}

	public Integer getReportLawCaseNum() {
		return reportLawCaseNum;
	}

	public void setReportLawCaseNum(Integer reportLawCaseNum) {
		this.reportLawCaseNum = reportLawCaseNum;
	}
	
	
}
