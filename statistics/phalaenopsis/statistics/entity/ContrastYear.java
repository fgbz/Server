package phalaenopsis.statistics.entity;

/**
 * 市分年度数据比对实体类
 * @author dongdongt
 *
 */
public class ContrastYear {
	/**
	 * 区域名称
	 */
	private String regionName;
	/**
	 * 最大年份的发现违法面积
	 */
	private Integer maxYearArea;
	/**
	 * 最小年份的发现违法面积
	 */
	private Integer minYearArea;
	/**
	 * 最大年份的发现违法案件数量
	 */
	private Integer maxYearCount;
	/**
	 * 最小年份的发现违法案件数量
	 */
	private Integer minYearCount;
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public Integer getMaxYearArea() {
		return maxYearArea;
	}
	public void setMaxYearArea(Integer maxYearArea) {
		this.maxYearArea = maxYearArea;
	}
	public Integer getMinYearArea() {
		return minYearArea;
	}
	public void setMinYearArea(Integer minYearArea) {
		this.minYearArea = minYearArea;
	}
	public Integer getMaxYearCount() {
		return maxYearCount;
	}
	public void setMaxYearCount(Integer maxYearCount) {
		this.maxYearCount = maxYearCount;
	}
	public Integer getMinYearCount() {
		return minYearCount;
	}
	public void setMinYearCount(Integer minYearCount) {
		this.minYearCount = minYearCount;
	}
}
