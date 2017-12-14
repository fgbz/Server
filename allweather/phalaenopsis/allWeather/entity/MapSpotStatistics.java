package phalaenopsis.allWeather.entity;

import phalaenopsis.common.annotation.ExcelTitle;

public class MapSpotStatistics {
	
	private String provinceName;
	/**
	 * 城市名称
	 */
	@ExcelTitle(value="市")
	private String cityName;
	/**
	 * 城市id
	 */
	private String cityId;
	/**
	 * 区县名称
	 */
	@ExcelTitle(value="管理区名称")
	private String countyName;
	/**
	 * 区县id
	 */
	@ExcelTitle(value="管理区代码")
	private String countyId;
	/**
	 * 图斑数量
	 */
	@ExcelTitle(value="图斑宗数")
	private Integer mapSpotNum;
	/**
	 * 未举证图斑数量
	 */
	@ExcelTitle(value = "待举证")
	private Integer unLegalproofNum;
	/**
	 * 违法图斑数量
	 */
	@ExcelTitle(value = "违法图斑")
	private Integer illegalNum;
	/**
	 * 合法图斑数量
	 */
	@ExcelTitle(value = "举证合法")
	private Integer legitimateNum;
	/**
	 * 非新增图斑数量
	 */
	@ExcelTitle(value = "图斑宗数")
	private Integer notNewMapSpotNum;
	/**
	 * 非新增且举证中图斑数量
	 */
	@ExcelTitle(value = "举证中")
	private Integer notNewLegalproofingNum;
	/**
	 * 非新增且已举证图标数量
	 */
	@ExcelTitle(value = "已举证")
	private Integer notNewLegalproofedNum;
	/**
	 * 上报到市级
	 */
	@ExcelTitle(value = "上报到市级")
	private String toCity;
	/**
	 * 上报到省级
	 */
	@ExcelTitle(value = "上报到省级")
	private String toProvince;

	private int period;
	
	private int year;
	/**
	 * 标注类型
	 */
	private String mark;
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	public String getCountyId() {
		return countyId;
	}
	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}
	public Integer getMapSpotNum() {
		return mapSpotNum;
	}
	public void setMapSpotNum(Integer mapSpotNum) {
		this.mapSpotNum = mapSpotNum;
	}
	public Integer getUnLegalproofNum() {
		return unLegalproofNum;
	}
	public void setUnLegalproofNum(Integer unLegalproofNum) {
		this.unLegalproofNum = unLegalproofNum;
	}
	public Integer getIllegalNum() {
		return illegalNum;
	}
	public void setIllegalNum(Integer illegalNum) {
		this.illegalNum = illegalNum;
	}
	public Integer getLegitimateNum() {
		return legitimateNum;
	}
	public void setLegitimateNum(Integer legitimateNum) {
		this.legitimateNum = legitimateNum;
	}
	public Integer getNotNewMapSpotNum() {
		return notNewMapSpotNum;
	}
	public void setNotNewMapSpotNum(Integer notNewMapSpotNum) {
		this.notNewMapSpotNum = notNewMapSpotNum;
	}
	public Integer getNotNewLegalproofingNum() {
		return notNewLegalproofingNum;
	}
	public void setNotNewLegalproofingNum(Integer notNewLegalproofingNum) {
		this.notNewLegalproofingNum = notNewLegalproofingNum;
	}
	public Integer getNotNewLegalproofedNum() {
		return notNewLegalproofedNum;
	}
	public void setNotNewLegalproofedNum(Integer notNewLegalproofedNum) {
		this.notNewLegalproofedNum = notNewLegalproofedNum;
	}
	public String getToProvince() {
		return toProvince;
	}
	public void setToProvince(String toProvince) {
		this.toProvince = toProvince;
	}
	public String getToCity() {
		return toCity;
	}
	public void setToCity(String toCity) {
		this.toCity = toCity;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
}
