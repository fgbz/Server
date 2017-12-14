package phalaenopsis.statistics.entity;

public class ProvinceLawCaseEnd {
	
	/**
	 * 地市
	 */
	private String countyName;

	/**
	 * 立案案数
	 */
	private Integer buildCount;
	
	/**
	 * 结案数
	 */
	private Integer endCount;
	
	/**
	 * 立案面积
	 */
	private Double buildArea;
	
	/**
	 * 结案面积
	 */
	private Double endArea;

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public Integer getBuildCount() {
		return buildCount;
	}

	public void setBuildCount(Integer buildCount) {
		this.buildCount = buildCount;
	}

	public Integer getEndCount() {
		return endCount;
	}

	public void setEndCount(Integer endCount) {
		this.endCount = endCount;
	}

	public Double getBuildArea() {
		return buildArea;
	}

	public void setBuildArea(Double buildArea) {
		this.buildArea = buildArea;
	}

	public Double getEndArea() {
		return endArea;
	}

	public void setEndArea(Double endArea) {
		this.endArea = endArea;
	}
	
	
	
}
