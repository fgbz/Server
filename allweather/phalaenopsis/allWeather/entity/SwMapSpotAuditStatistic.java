package phalaenopsis.allWeather.entity;

import phalaenopsis.common.annotation.ExcelTitle;

public class SwMapSpotAuditStatistic {
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
	 * 行政区（区县）代码
	 */
	private String districtcounty;
	/**
	 * 行政区（区县）名称
	 */
	private String districtcountyName;
	/**
	 * 合计id
	 */
	private String sumId;
	/**
	 * 合计名称
	 */
	private String sumName;
	
	/**
	 * 图斑数量
	 */
	@ExcelTitle(value="图斑总数")
	private Integer mapSpotNum;
	
	/**
	 * 待审核数量
	 */
	@ExcelTitle(value="待审核数量")
	private Integer unAuditCountAll;
	
	/**
	 * 审核通过数量
	 */
	@ExcelTitle(value="审核通过数量")
	private Integer passAuditCountAll;
	
	/**
	 * 审核不通过数量
	 */
	@ExcelTitle(value="审核不通过数量")
	private Integer nopassAuditCountAll;
	
	/**
	 * 图斑总面积（监测面积）
	 */
	@ExcelTitle(value="图斑总面积")
	private Double mapSpotSumArea;
	
	/**
	 * 图斑总耕地面积
	 */
	@ExcelTitle(value="图斑总耕地面积")
	private Double mapSpotSumArableacreage;
	
	/**
	 * 图斑总可调整面积
	 */
	@ExcelTitle(value="图斑总可调整面积")
	private Double mapSpotSumAdjustablearea;

	/**
	 * 违法图斑数量
	 */
	@ExcelTitle(value = "图斑个数")
	private Integer illegalNum;
	
	/**
	 * 违法图斑总面积（监测面积）
	 */
	@ExcelTitle(value="总面积")
	private Double illegalSumArea;
	/**
	 * 违法图斑耕地面积
	 */
	@ExcelTitle(value="耕地面积")
	private Double illegalArableacreage;
	
	/**
	 * 违法图斑可调整面积
	 */
	@ExcelTitle(value="可调整面积")
	private Double illegalAdjustablearea;
	
	/**
	 * 疑似违法图斑数量
	 */
	@ExcelTitle(value = "图斑个数")
	private Integer suspectedIllegalNum;
	
	/**
	 * 疑似违法图斑总面积（监测面积）
	 */
	@ExcelTitle(value="总面积")
	private Double suspectedIllegalSumArea;
	/**
	 * 疑似违法图斑耕地面积
	 */
	@ExcelTitle(value="耕地面积")
	private Double suspectedIllegalArableacreage;
	
	/**
	 * 疑似违法图斑可调整面积
	 */
	@ExcelTitle(value="可调整面积")
	private Double suspectedIllegalAdjustablearea;
	
	/**
	 * 合法图斑数量
	 */
	@ExcelTitle(value = "图斑个数")
	private Integer legitimateNum;
	
	/**
	 * 合法图斑总面积（监测面积）
	 */
	@ExcelTitle(value="总面积")
	private Double legitimateSumArea;
	/**
	 * 合法图斑耕地面积
	 */
	@ExcelTitle(value="耕地面积")
	private Double legitimateArableacreage;
	
	/**
	 * 合法图斑可调整面积
	 */
	@ExcelTitle(value="可调整面积")
	private Double legitimateAdjustablearea;
	
	/**
	 * 非新增图斑数量
	 */
	@ExcelTitle(value = "图斑个数")
	private Integer notNewMapSpotNum;
	
	/**
	 * 非新增图斑总面积（监测面积）
	 */
	@ExcelTitle(value="总面积")
	private Double notNewMapSpotSumArea;
	/**
	 * 非新增图斑耕地面积
	 */
	@ExcelTitle(value="耕地面积")
	private Double notNewMapSpotArableacreage;
	
	/**
	 * 非新增图斑可调整面积
	 */
	@ExcelTitle(value="可调整面积")
	private Double notNewMapSpotAdjustablearea;

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

	public String getDistrictcounty() {
		return districtcounty;
	}

	public void setDistrictcounty(String districtcounty) {
		this.districtcounty = districtcounty;
	}

	public String getDistrictcountyName() {
		return districtcountyName;
	}

	public void setDistrictcountyName(String districtcountyName) {
		this.districtcountyName = districtcountyName;
	}

	public String getSumId() {
		return sumId;
	}

	public void setSumId(String sumId) {
		this.sumId = sumId;
	}

	public String getSumName() {
		return sumName;
	}

	public void setSumName(String sumName) {
		this.sumName = sumName;
	}

	public Integer getMapSpotNum() {
		return mapSpotNum;
	}

	public void setMapSpotNum(Integer mapSpotNum) {
		this.mapSpotNum = mapSpotNum;
	}

	public Integer getUnAuditCountAll() {
		return unAuditCountAll;
	}

	public void setUnAuditCountAll(Integer unAuditCountAll) {
		this.unAuditCountAll = unAuditCountAll;
	}

	public Integer getPassAuditCountAll() {
		return passAuditCountAll;
	}

	public void setPassAuditCountAll(Integer passAuditCountAll) {
		this.passAuditCountAll = passAuditCountAll;
	}

	public Integer getNopassAuditCountAll() {
		return nopassAuditCountAll;
	}

	public void setNopassAuditCountAll(Integer nopassAuditCountAll) {
		this.nopassAuditCountAll = nopassAuditCountAll;
	}

	public Double getMapSpotSumArea() {
		return mapSpotSumArea;
	}

	public void setMapSpotSumArea(Double mapSpotSumArea) {
		this.mapSpotSumArea = mapSpotSumArea;
	}

	public Double getMapSpotSumArableacreage() {
		return mapSpotSumArableacreage;
	}

	public void setMapSpotSumArableacreage(Double mapSpotSumArableacreage) {
		this.mapSpotSumArableacreage = mapSpotSumArableacreage;
	}

	public Double getMapSpotSumAdjustablearea() {
		return mapSpotSumAdjustablearea;
	}

	public void setMapSpotSumAdjustablearea(Double mapSpotSumAdjustablearea) {
		this.mapSpotSumAdjustablearea = mapSpotSumAdjustablearea;
	}

	public Integer getIllegalNum() {
		return illegalNum;
	}

	public void setIllegalNum(Integer illegalNum) {
		this.illegalNum = illegalNum;
	}

	public Double getIllegalSumArea() {
		return illegalSumArea;
	}

	public void setIllegalSumArea(Double illegalSumArea) {
		this.illegalSumArea = illegalSumArea;
	}

	public Double getIllegalArableacreage() {
		return illegalArableacreage;
	}

	public void setIllegalArableacreage(Double illegalArableacreage) {
		this.illegalArableacreage = illegalArableacreage;
	}

	public Double getIllegalAdjustablearea() {
		return illegalAdjustablearea;
	}

	public void setIllegalAdjustablearea(Double illegalAdjustablearea) {
		this.illegalAdjustablearea = illegalAdjustablearea;
	}

	public Integer getSuspectedIllegalNum() {
		return suspectedIllegalNum;
	}

	public void setSuspectedIllegalNum(Integer suspectedIllegalNum) {
		this.suspectedIllegalNum = suspectedIllegalNum;
	}

	public Double getSuspectedIllegalSumArea() {
		return suspectedIllegalSumArea;
	}

	public void setSuspectedIllegalSumArea(Double suspectedIllegalSumArea) {
		this.suspectedIllegalSumArea = suspectedIllegalSumArea;
	}

	public Double getSuspectedIllegalArableacreage() {
		return suspectedIllegalArableacreage;
	}

	public void setSuspectedIllegalArableacreage(Double suspectedIllegalArableacreage) {
		this.suspectedIllegalArableacreage = suspectedIllegalArableacreage;
	}

	public Double getSuspectedIllegalAdjustablearea() {
		return suspectedIllegalAdjustablearea;
	}

	public void setSuspectedIllegalAdjustablearea(Double suspectedIllegalAdjustablearea) {
		this.suspectedIllegalAdjustablearea = suspectedIllegalAdjustablearea;
	}

	public Integer getLegitimateNum() {
		return legitimateNum;
	}

	public void setLegitimateNum(Integer legitimateNum) {
		this.legitimateNum = legitimateNum;
	}

	public Double getLegitimateSumArea() {
		return legitimateSumArea;
	}

	public void setLegitimateSumArea(Double legitimateSumArea) {
		this.legitimateSumArea = legitimateSumArea;
	}

	public Double getLegitimateArableacreage() {
		return legitimateArableacreage;
	}

	public void setLegitimateArableacreage(Double legitimateArableacreage) {
		this.legitimateArableacreage = legitimateArableacreage;
	}

	public Double getLegitimateAdjustablearea() {
		return legitimateAdjustablearea;
	}

	public void setLegitimateAdjustablearea(Double legitimateAdjustablearea) {
		this.legitimateAdjustablearea = legitimateAdjustablearea;
	}

	public Integer getNotNewMapSpotNum() {
		return notNewMapSpotNum;
	}

	public void setNotNewMapSpotNum(Integer notNewMapSpotNum) {
		this.notNewMapSpotNum = notNewMapSpotNum;
	}

	public Double getNotNewMapSpotSumArea() {
		return notNewMapSpotSumArea;
	}

	public void setNotNewMapSpotSumArea(Double notNewMapSpotSumArea) {
		this.notNewMapSpotSumArea = notNewMapSpotSumArea;
	}

	public Double getNotNewMapSpotArableacreage() {
		return notNewMapSpotArableacreage;
	}

	public void setNotNewMapSpotArableacreage(Double notNewMapSpotArableacreage) {
		this.notNewMapSpotArableacreage = notNewMapSpotArableacreage;
	}

	public Double getNotNewMapSpotAdjustablearea() {
		return notNewMapSpotAdjustablearea;
	}

	public void setNotNewMapSpotAdjustablearea(Double notNewMapSpotAdjustablearea) {
		this.notNewMapSpotAdjustablearea = notNewMapSpotAdjustablearea;
	}
}
