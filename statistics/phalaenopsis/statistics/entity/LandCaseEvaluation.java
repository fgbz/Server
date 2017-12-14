package phalaenopsis.statistics.entity;

import java.io.Serializable;

/**
 * 土地违法案件及查处情况
 * @author chunhongl
 * 2017年8月7日下午3:16:01
 */
public class LandCaseEvaluation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 类型名称
	 */
	private String typeName;
	/**
	 * 合计
	 */
	// 件数
	private int allCount;
	// 涉及土地面积
	private double allLandArea;
	// 耕地面积
	private double allArableArea;
	/**
	 * 省级
	 */
	// 件数
	private int provinceCount;
	// 涉及土地面积
	private double provinceLandArea;
	// 耕地面积
	private double provinceArableArea;
	/**
	 * 地级
	 */
	// 件数
	private int cityCount;
	// 涉及土地面积
	private double cityLandArea;
	// 耕地面积
	private double cityArableArea;
	/**
	 * 县 级
	 */
	// 件数
	private int countyCount;
	// 涉及土地面积
	private double countyLandArea;
	// 耕地面积
	private double countyArableArea;
	/**
	 * 乡级
	 */
	// 件数
	private int villageCount;
	// 涉及土地面积
	private double villageLandArea;
	// 耕地面积
	private double villageArableArea;
	/**
	 * 村、组集体
	 */
	// 件数
	private int groupCount;
	// 涉及土地面积
	private double groupLandArea;
	// 耕地面积
	private double groupArableArea;
	/**
	 * 企事业单位
	 */
	// 件数
	private int companyCount;
	// 涉及土地面积
	private double companyLandArea;
	// 耕地面积
	private double companyArableArea;
	/**
	 * 个人
	 */
	// 件数
	private int personalCount;
	// 涉及土地面积
	private double personalLandArea;
	// 耕地面积
	private double personalArableArea;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getAllCount() {
		return allCount;
	}

	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}

	public double getAllLandArea() {
		return allLandArea;
	}

	public void setAllLandArea(double allLandArea) {
		this.allLandArea = allLandArea;
	}

	public double getAllArableArea() {
		return allArableArea;
	}

	public void setAllArableArea(double allArableArea) {
		this.allArableArea = allArableArea;
	}

	public int getProvinceCount() {
		return provinceCount;
	}

	public void setProvinceCount(int provinceCount) {
		this.provinceCount = provinceCount;
	}

	public double getProvinceLandArea() {
		return provinceLandArea;
	}

	public void setProvinceLandArea(double provinceLandArea) {
		this.provinceLandArea = provinceLandArea;
	}

	public double getProvinceArableArea() {
		return provinceArableArea;
	}

	public void setProvinceArableArea(double provinceArableArea) {
		this.provinceArableArea = provinceArableArea;
	}

	public int getCityCount() {
		return cityCount;
	}

	public void setCityCount(int cityCount) {
		this.cityCount = cityCount;
	}

	public double getCityLandArea() {
		return cityLandArea;
	}

	public void setCityLandArea(double cityLandArea) {
		this.cityLandArea = cityLandArea;
	}

	public double getCityArableArea() {
		return cityArableArea;
	}

	public void setCityArableArea(double cityArableArea) {
		this.cityArableArea = cityArableArea;
	}

	public int getCountyCount() {
		return countyCount;
	}

	public void setCountyCount(int countyCount) {
		this.countyCount = countyCount;
	}

	public double getCountyLandArea() {
		return countyLandArea;
	}

	public void setCountyLandArea(double countyLandArea) {
		this.countyLandArea = countyLandArea;
	}

	public double getCountyArableArea() {
		return countyArableArea;
	}

	public void setCountyArableArea(double countyArableArea) {
		this.countyArableArea = countyArableArea;
	}

	public int getVillageCount() {
		return villageCount;
	}

	public void setVillageCount(int villageCount) {
		this.villageCount = villageCount;
	}

	public double getVillageLandArea() {
		return villageLandArea;
	}

	public void setVillageLandArea(double villageLandArea) {
		this.villageLandArea = villageLandArea;
	}

	public double getVillageArableArea() {
		return villageArableArea;
	}

	public void setVillageArableArea(double villageArableArea) {
		this.villageArableArea = villageArableArea;
	}

	public int getGroupCount() {
		return groupCount;
	}

	public void setGroupCount(int groupCount) {
		this.groupCount = groupCount;
	}

	public double getGroupLandArea() {
		return groupLandArea;
	}

	public void setGroupLandArea(double groupLandArea) {
		this.groupLandArea = groupLandArea;
	}

	public double getGroupArableArea() {
		return groupArableArea;
	}

	public void setGroupArableArea(double groupArableArea) {
		this.groupArableArea = groupArableArea;
	}

	public int getCompanyCount() {
		return companyCount;
	}

	public void setCompanyCount(int companyCount) {
		this.companyCount = companyCount;
	}

	public double getCompanyLandArea() {
		return companyLandArea;
	}

	public void setCompanyLandArea(double companyLandArea) {
		this.companyLandArea = companyLandArea;
	}

	public double getCompanyArableArea() {
		return companyArableArea;
	}

	public void setCompanyArableArea(double companyArableArea) {
		this.companyArableArea = companyArableArea;
	}

	public int getPersonalCount() {
		return personalCount;
	}

	public void setPersonalCount(int personalCount) {
		this.personalCount = personalCount;
	}

	public double getPersonalLandArea() {
		return personalLandArea;
	}

	public void setPersonalLandArea(double personalLandArea) {
		this.personalLandArea = personalLandArea;
	}

	public double getPersonalArableArea() {
		return personalArableArea;
	}

	public void setPersonalArableArea(double personalArableArea) {
		this.personalArableArea = personalArableArea;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
