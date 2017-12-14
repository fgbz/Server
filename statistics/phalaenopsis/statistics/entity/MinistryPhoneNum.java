package phalaenopsis.statistics.entity;

/**
 *  部省12336受理违法举报电话线索量
 * @author chunl
 * 2017年8月7日下午2:26:54
 */
public class MinistryPhoneNum {
	
	/**
	 * 日期
	 */
	private String date;

	/**
	 * 部转违法举报（件）
	 */
	private Integer ministryNum;
	
	/**
	 * 省接违法举报（件）
	 */
	private Integer provinceNum;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getMinistryNum() {
		return ministryNum;
	}

	public void setMinistryNum(Integer ministryNum) {
		this.ministryNum = ministryNum;
	}

	public Integer getProvinceNum() {
		return provinceNum;
	}

	public void setProvinceNum(Integer provinceNum) {
		this.provinceNum = provinceNum;
	}
}
