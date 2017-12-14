package phalaenopsis.statistics.entity;

/**
 * 全省发现土地违法案件数及面积
 * @author chunl
 * 2017年8月7日下午2:28:57
 */
public class ProvinceLandLawCase {

	/**
	 * 日期
	 */
	private String date;

	/**
	 * 本年发现违法件数
	 */
	private Integer illegalCount;

	/**
	 * 本年发现违法面积（亩）
	 */
	private Integer illegalArea;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getIllegalCount() {
		return illegalCount;
	}

	public void setIllegalCount(Integer illegalCount) {
		this.illegalCount = illegalCount;
	}

	public Integer getIllegalArea() {
		return illegalArea;
	}

	public void setIllegalArea(Integer illegalArea) {
		this.illegalArea = illegalArea;
	}
}
