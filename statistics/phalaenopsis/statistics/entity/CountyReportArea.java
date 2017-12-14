package phalaenopsis.statistics.entity;

/**
 * 市统计上报潍坊案件数及违法用地面积数比对
 * @author chunl
 * 2017年8月7日下午4:05:46
 */
public class CountyReportArea {
	
	/**
	 * 地市
	 */
	private String countyName; 
	
	/**
	 * 本年上报违法案件数
	 */
	private Integer lawCaseNum;

	/**
	 * 违法用地面积
	 */
	private Double lawCaseArea;

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public Integer getLawCaseNum() {
		return lawCaseNum;
	}

	public void setLawCaseNum(Integer lawCaseNum) {
		this.lawCaseNum = lawCaseNum;
	}

	public Double getLawCaseArea() {
		return lawCaseArea;
	}

	public void setLawCaseArea(Double lawCaseArea) {
		this.lawCaseArea = lawCaseArea;
	}
	
	
}
