package phalaenopsis.satellitegraph.entity;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import phalaenopsis.common.method.Json.NetDateJsonSerializer;

public class AuditSpot {

	/**
	 * 主键（自增长）
	 */
	@JsonProperty("id")
	private long id;

	/**
	 * 卫片图斑表外键
	 */
	@JsonProperty("mapSpotId")
	private long mapSpotId;

	/**
	 * 市级审核_审核结果
	 */
	@JsonProperty("cityAuditIsPass")
	private Integer cityAuditIsPass;

	/**
	 * 市级审核_备注
	 */
	@JsonProperty("cityAuditRemarks")
	private String cityAuditRemarks;

	/**
	 * 市级审核_审核日期
	 */
	@JsonProperty("cityAuditTime")
	@JsonSerialize(using=NetDateJsonSerializer.class)
	private Date cityAuditTime;

	/**
	 * 市级审核_审核人名称
	 */
	@JsonProperty("cityAuditPersonName")
	private String cityAuditPersonName;

	/**
	 * 市级审核_审核人ID
	 */
	@JsonProperty("cityAuditPersonID")
	private String cityAuditPersonID;

	/**
	 * 省级审核_审核结果
	 */
	@JsonProperty("provinceAuditIsPass")
	private Integer provinceAuditIsPass;

	/**
	 * 省级审核_备注
	 */
	@JsonProperty("provinceAuditRemarks")
	private String provinceAuditRemarks;

	/**
	 * 省级审核_审核日期
	 */
	@JsonProperty("provinceAuditTime")
	@JsonSerialize(using=NetDateJsonSerializer.class)
	private Date provinceAuditTime;

	/**
	 * 省级审核_审核人名称
	 */
	@JsonProperty("provinceAuditPersonName")
	private String provinceAuditPersonName;

	/**
	 * 省级审核_审核人ID
	 */
	@JsonProperty("provinceAuditPersonID")
	private String provinceAuditPersonID;

	/**
	 * 图斑审核状态
	 */
	@JsonProperty("auditStatus")
	private String auditStatus;

	/**
	 * 是否市级已审核
	 */
	@JsonProperty("isCityAudit")
	private boolean isCityAudit;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMapSpotId() {
		return mapSpotId;
	}

	public void setMapSpotId(long mapSpotId) {
		this.mapSpotId = mapSpotId;
	}

	public Integer getCityAuditIsPass() {
		return cityAuditIsPass;
	}

	public void setCityAuditIsPass(Integer cityAuditIsPass) {
		this.cityAuditIsPass = cityAuditIsPass;
	}

	public String getCityAuditRemarks() {
		return cityAuditRemarks;
	}

	public void setCityAuditRemarks(String cityAuditRemarks) {
		this.cityAuditRemarks = cityAuditRemarks;
	}

	public Date getCityAuditTime() {
		return cityAuditTime;
	}

	public void setCityAuditTime(Date cityAuditTime) {
		this.cityAuditTime = cityAuditTime;
	}

	public String getCityAuditPersonName() {
		return cityAuditPersonName;
	}

	public void setCityAuditPersonName(String cityAuditPersonName) {
		this.cityAuditPersonName = cityAuditPersonName;
	}

	public String getCityAuditPersonID() {
		return cityAuditPersonID;
	}

	public void setCityAuditPersonID(String cityAuditPersonID) {
		this.cityAuditPersonID = cityAuditPersonID;
	}

	public Integer getProvinceAuditIsPass() {
		return provinceAuditIsPass;
	}

	public void setProvinceAuditIsPass(Integer provinceAuditIsPass) {
		this.provinceAuditIsPass = provinceAuditIsPass;
	}

	public String getProvinceAuditRemarks() {
		return provinceAuditRemarks;
	}

	public void setProvinceAuditRemarks(String provinceAuditRemarks) {
		this.provinceAuditRemarks = provinceAuditRemarks;
	}

	public Date getProvinceAuditTime() {
		return provinceAuditTime;
	}

	public void setProvinceAuditTime(Date provinceAuditTime) {
		this.provinceAuditTime = provinceAuditTime;
	}

	public String getProvinceAuditPersonName() {
		return provinceAuditPersonName;
	}

	public void setProvinceAuditPersonName(String provinceAuditPersonName) {
		this.provinceAuditPersonName = provinceAuditPersonName;
	}

	public String getProvinceAuditPersonID() {
		return provinceAuditPersonID;
	}

	public void setProvinceAuditPersonID(String provinceAuditPersonID) {
		this.provinceAuditPersonID = provinceAuditPersonID;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public boolean isCityAudit() {
		return isCityAudit;
	}

	public void setCityAudit(boolean isCityAudit) {
		this.isCityAudit = isCityAudit;
	}


}
