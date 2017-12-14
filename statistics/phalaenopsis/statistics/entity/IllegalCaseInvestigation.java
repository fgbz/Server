package phalaenopsis.statistics.entity;

import java.io.Serializable;

/**
 * 土地违法案件及查处情况统计表
 * @author dongdongt
 *
 */
public class IllegalCaseInvestigation implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 级别
	 * 1.顶级，2.中级，3.低级
	 */
	public Integer type;
	/**
	 * 类别名称
	 */
	public String  name;
	
	/**
	 * 违法案件及查处件数（合计）
	 */
	private Integer illegalCaseTotalCount;
	/**
	 * 违法案件及查处涉及土地面积（合计）
	 */
	private double illegalCaseTotalLandArea;
	/**
	 * 违法案件及查处涉及耕地面积（合计）
	 */
	private double illegalCaseTotalTillArea;
	
	/**
	 * 违法案件及查处件数（省级）
	 */
	private Integer illegalCaseProvinceCount;
	/**
	 * 违法案件及查处涉及土地面积（省级）
	 */
	private double illegalCaseProvinceLandArea;
	/**
	 * 违法案件及查处涉及耕地面积（省级）
	 */
	private double illegalCaseProvinceTillArea;

	/**
	 * 违法案件及查处件数（市级）
	 */
	private Integer illegalCaseCityCount;
	/**
	 * 违法案件及查处涉及土地面积（市级）
	 */
	private double illegalCaseCityLandArea;
	/**
	 * 违法案件及查处涉及耕地面积（市级）
	 */
	private double illegalCaseCityTillArea;

	/**
	 * 违法案件及查处件数（县级）
	 */
	private Integer illegalCaseCountyCount;
	/**
	 * 违法案件及查处涉及土地面积（县级）
	 */
	private double illegalCaseCountyLandArea;
	/**
	 * 违法案件及查处涉及耕地面积（县级）
	 */
	private double illegalCaseCountyTillArea;

	/**
	 * 违法案件及查处件数（乡级）
	 */
	private Integer illegalCaseCountryCount;
	/**
	 * 违法案件及查处涉及土地面积（乡级）
	 */
	private double illegalCaseCountryLandArea;
	/**
	 * 违法案件及查处涉及耕地面积（乡级）
	 */
	private double illegalCaseCountryTillArea;

	/**
	 * 违法案件及查处件数（村级）
	 */
	private Integer illegalCaseVillageCount;
	/**
	 * 违法案件及查处涉及土地面积（村级）
	 */
	private double illegalCaseVillageLandArea;
	/**
	 * 违法案件及查处涉及耕地面积（村级）
	 */
	private double illegalCaseVillageTillArea;

	/**
	 * 违法案件及查处件数（企业单位）
	 */
	private Integer illegalCaseCompanyCount;
	/**
	 * 违法案件及查处涉及土地面积（企业单位）
	 */
	private double illegalCaseCompanyLandArea;
	/**
	 * 违法案件及查处涉及耕地面积（企业单位）
	 */
	private double illegalCaseCompanyTillArea;

	/**
	 * 违法案件及查处件数（个人）
	 */
	private Integer illegalCasePersonalCount;
	/**
	 * 违法案件及查处涉及土地面积（个人）
	 */
	private double illegalCasePersonalLandArea;
	/**
	 * 违法案件及查处涉及耕地面积（个人）
	 */
	private double illegalCasePersonalTillArea;
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getIllegalCaseTotalCount() {
		return illegalCaseTotalCount;
	}
	public void setIllegalCaseTotalCount(Integer illegalCaseTotalCount) {
		this.illegalCaseTotalCount = illegalCaseTotalCount;
	}
	public double getIllegalCaseTotalLandArea() {
		return illegalCaseTotalLandArea;
	}
	public void setIllegalCaseTotalLandArea(double illegalCaseTotalLandArea) {
		this.illegalCaseTotalLandArea = illegalCaseTotalLandArea;
	}
	public double getIllegalCaseTotalTillArea() {
		return illegalCaseTotalTillArea;
	}
	public void setIllegalCaseTotalTillArea(double illegalCaseTotalTillArea) {
		this.illegalCaseTotalTillArea = illegalCaseTotalTillArea;
	}
	public Integer getIllegalCaseProvinceCount() {
		return illegalCaseProvinceCount;
	}
	public void setIllegalCaseProvinceCount(Integer illegalCaseProvinceCount) {
		this.illegalCaseProvinceCount = illegalCaseProvinceCount;
	}
	public double getIllegalCaseProvinceLandArea() {
		return illegalCaseProvinceLandArea;
	}
	public void setIllegalCaseProvinceLandArea(double illegalCaseProvinceLandArea) {
		this.illegalCaseProvinceLandArea = illegalCaseProvinceLandArea;
	}
	public double getIllegalCaseProvinceTillArea() {
		return illegalCaseProvinceTillArea;
	}
	public void setIllegalCaseProvinceTillArea(double illegalCaseProvinceTillArea) {
		this.illegalCaseProvinceTillArea = illegalCaseProvinceTillArea;
	}
	public Integer getIllegalCaseCityCount() {
		return illegalCaseCityCount;
	}
	public void setIllegalCaseCityCount(Integer illegalCaseCityCount) {
		this.illegalCaseCityCount = illegalCaseCityCount;
	}
	public double getIllegalCaseCityLandArea() {
		return illegalCaseCityLandArea;
	}
	public void setIllegalCaseCityLandArea(double illegalCaseCityLandArea) {
		this.illegalCaseCityLandArea = illegalCaseCityLandArea;
	}
	public double getIllegalCaseCityTillArea() {
		return illegalCaseCityTillArea;
	}
	public void setIllegalCaseCityTillArea(double illegalCaseCityTillArea) {
		this.illegalCaseCityTillArea = illegalCaseCityTillArea;
	}
	public Integer getIllegalCaseCountyCount() {
		return illegalCaseCountyCount;
	}
	public void setIllegalCaseCountyCount(Integer illegalCaseCountyCount) {
		this.illegalCaseCountyCount = illegalCaseCountyCount;
	}
	public double getIllegalCaseCountyLandArea() {
		return illegalCaseCountyLandArea;
	}
	public void setIllegalCaseCountyLandArea(double illegalCaseCountyLandArea) {
		this.illegalCaseCountyLandArea = illegalCaseCountyLandArea;
	}
	public double getIllegalCaseCountyTillArea() {
		return illegalCaseCountyTillArea;
	}
	public void setIllegalCaseCountyTillArea(double illegalCaseCountyTillArea) {
		this.illegalCaseCountyTillArea = illegalCaseCountyTillArea;
	}
	public Integer getIllegalCaseCountryCount() {
		return illegalCaseCountryCount;
	}
	public void setIllegalCaseCountryCount(Integer illegalCaseCountryCount) {
		this.illegalCaseCountryCount = illegalCaseCountryCount;
	}
	public double getIllegalCaseCountryLandArea() {
		return illegalCaseCountryLandArea;
	}
	public void setIllegalCaseCountryLandArea(double illegalCaseCountryLandArea) {
		this.illegalCaseCountryLandArea = illegalCaseCountryLandArea;
	}
	public double getIllegalCaseCountryTillArea() {
		return illegalCaseCountryTillArea;
	}
	public void setIllegalCaseCountryTillArea(double illegalCaseCountryTillArea) {
		this.illegalCaseCountryTillArea = illegalCaseCountryTillArea;
	}
	public Integer getIllegalCaseVillageCount() {
		return illegalCaseVillageCount;
	}
	public void setIllegalCaseVillageCount(Integer illegalCaseVillageCount) {
		this.illegalCaseVillageCount = illegalCaseVillageCount;
	}
	public double getIllegalCaseVillageLandArea() {
		return illegalCaseVillageLandArea;
	}
	public void setIllegalCaseVillageLandArea(double illegalCaseVillageLandArea) {
		this.illegalCaseVillageLandArea = illegalCaseVillageLandArea;
	}
	public double getIllegalCaseVillageTillArea() {
		return illegalCaseVillageTillArea;
	}
	public void setIllegalCaseVillageTillArea(double illegalCaseVillageTillArea) {
		this.illegalCaseVillageTillArea = illegalCaseVillageTillArea;
	}
	public Integer getIllegalCaseCompanyCount() {
		return illegalCaseCompanyCount;
	}
	public void setIllegalCaseCompanyCount(Integer illegalCaseCompanyCount) {
		this.illegalCaseCompanyCount = illegalCaseCompanyCount;
	}
	public double getIllegalCaseCompanyLandArea() {
		return illegalCaseCompanyLandArea;
	}
	public void setIllegalCaseCompanyLandArea(double illegalCaseCompanyLandArea) {
		this.illegalCaseCompanyLandArea = illegalCaseCompanyLandArea;
	}
	public double getIllegalCaseCompanyTillArea() {
		return illegalCaseCompanyTillArea;
	}
	public void setIllegalCaseCompanyTillArea(double illegalCaseCompanyTillArea) {
		this.illegalCaseCompanyTillArea = illegalCaseCompanyTillArea;
	}
	public Integer getIllegalCasePersonalCount() {
		return illegalCasePersonalCount;
	}
	public void setIllegalCasePersonalCount(Integer illegalCasePersonalCount) {
		this.illegalCasePersonalCount = illegalCasePersonalCount;
	}
	public double getIllegalCasePersonalLandArea() {
		return illegalCasePersonalLandArea;
	}
	public void setIllegalCasePersonalLandArea(double illegalCasePersonalLandArea) {
		this.illegalCasePersonalLandArea = illegalCasePersonalLandArea;
	}
	public double getIllegalCasePersonalTillArea() {
		return illegalCasePersonalTillArea;
	}
	public void setIllegalCasePersonalTillArea(double illegalCasePersonalTillArea) {
		this.illegalCasePersonalTillArea = illegalCasePersonalTillArea;
	}
}
