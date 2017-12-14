package phalaenopsis.statistics.entity;

import java.io.Serializable;

/**
 * 矿产违法案件及查处情况
 * @author chunhongl
 * 2017年8月7日下午3:16:48
 */
public class MineralCaseEvaluation implements Serializable {

	private static final long serialVersionUID = 1L;
    /**
     * 级别
     */
	private int type;
	/**
	 * 级别名称
	 */
	private String typeName;
	// 合计
	private int count;
	/**
	 * 国家机关
	 */
	private int stateOrgans;
	// 省级
	private int province;
	// 地市级
	private int city;
	// 县级
	private int county;
	/**
	 * 企事业单位
	 */
	private int company;
	// 外商
	private int foreignCompany;
	/**
	 * 集体
	 */
	private int group;
	// 乡村
	private int village;

	/**
	 * 个人
	 */
	private int personal;

	
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getStateOrgans() {
		return stateOrgans;
	}

	public void setStateOrgans(int stateOrgans) {
		this.stateOrgans = stateOrgans;
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

	public int getCompany() {
		return company;
	}

	public void setCompany(int company) {
		this.company = company;
	}

	public int getForeignCompany() {
		return foreignCompany;
	}

	public void setForeignCompany(int foreignCompany) {
		this.foreignCompany = foreignCompany;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public int getVillage() {
		return village;
	}

	public void setVillage(int village) {
		this.village = village;
	}

	public int getPersonal() {
		return personal;
	}

	public void setPersonal(int personal) {
		this.personal = personal;
	}

}
