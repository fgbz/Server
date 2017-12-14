package phalaenopsis.allWeather.entity;

public class YearAndPeriod {
	/**
	 * 年份
	 */
	private Integer year;
	/**
	 * 核查期数
	 */
	private Integer checkperiod;
	/**
	 * 下发期数
	 */
	private Integer period;
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getCheckperiod() {
		return checkperiod;
	}
	public void setCheckperiod(Integer checkperiod) {
		this.checkperiod = checkperiod;
	}
}
