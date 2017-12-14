package phalaenopsis.statistics.entity;

/**
 *市结案情况以及比例 
 * @author chunl
 * 2017年8月7日下午3:19:38
 */
public class CountyLawCaseEnd {
	
	/**
	 * 地市
	 */
	private String countryName;

	/**
	 * 结案数
	 */
	private Integer endCaseSum;

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Integer getEndCaseSum() {
		return endCaseSum;
	}

	public void setEndCaseSum(Integer endCaseSum) {
		this.endCaseSum = endCaseSum;
	}
	
	
}
