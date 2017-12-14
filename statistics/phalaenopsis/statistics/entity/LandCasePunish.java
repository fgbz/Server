package phalaenopsis.statistics.entity;

import java.io.Serializable;

/**
 * 土地违法案件处分情况
 * @author chunhongl
 * 2017年8月7日下午3:16:29
 */
public class LandCasePunish implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 级别类型
	 */
	private int type;
	/**
	 * 名称
	 */
	private String typeName;
	// 合计
	private int count;
	/**
	 * 各级领导人员
	 */
	// 省级
	private int province;
	// 地市级
	private int city;
	// 县级
	private int county;
	// 乡镇级
	private int village;
	/**
	 * 国土资源主管部门人员
	 */
	// 厅级
	private int leadOffice;
	// 处级
	private int leadPart;
	// 科级及以下
	private int leadDivision;
	/**
	 * 其他部门人员
	 */
	// 厅级
	private int otherOffice;
	// 处级
	private int otherPart;
	// 科级及以下
	private int otherDivision;

	/**
	 * 企事业单位公职人员
	 */
	private int company;

	/**
	 * 村、组干部
	 */
	private int villageCadre;

	/**
	 * 其他人员
	 */
	private int otherPerson;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getProvince() {
		return province;
	}

	public void setProvince(int province) {
		this.province = province;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public int getCounty() {
		return county;
	}

	public void setCounty(int county) {
		this.county = county;
	}

	public int getVillage() {
		return village;
	}

	public void setVillage(int village) {
		this.village = village;
	}

	public int getLeadOffice() {
		return leadOffice;
	}

	public void setLeadOffice(int leadOffice) {
		this.leadOffice = leadOffice;
	}

	public int getLeadPart() {
		return leadPart;
	}

	public void setLeadPart(int leadPart) {
		this.leadPart = leadPart;
	}

	public int getLeadDivision() {
		return leadDivision;
	}

	public void setLeadDivision(int leadDivision) {
		this.leadDivision = leadDivision;
	}

	public int getOtherOffice() {
		return otherOffice;
	}

	public void setOtherOffice(int otherOffice) {
		this.otherOffice = otherOffice;
	}

	public int getOtherPart() {
		return otherPart;
	}

	public void setOtherPart(int otherPart) {
		this.otherPart = otherPart;
	}

	public int getOtherDivision() {
		return otherDivision;
	}

	public void setOtherDivision(int otherDivision) {
		this.otherDivision = otherDivision;
	}

	public int getCompany() {
		return company;
	}

	public void setCompany(int company) {
		this.company = company;
	}

	public int getVillageCadre() {
		return villageCadre;
	}

	public void setVillageCadre(int villageCadre) {
		this.villageCadre = villageCadre;
	}

	public int getOtherPerson() {
		return otherPerson;
	}

	public void setOtherPerson(int otherPerson) {
		this.otherPerson = otherPerson;
	}

}
