package phalaenopsis.patrolManagement.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import phalaenopsis.patrolManagement.bean.PatrolTrackViewBean;
import phalaenopsis.common.method.Json.DateJsonSerializer;
import phalaenopsis.common.util.PageEntity;

/**
 * 单条巡查记录
 * @author jund
 * 2017-9-15
 */
public class PatrolLog extends PageEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;						//主键id
	
	private String patrolUserId;			//巡查人员id
	
	private Date patrolStartDate;			//巡查开始时间
	
	private Date patrolEndDate;			//巡查结束时间
	
	private String patrolStartDateStr;		//巡查开始时间(String)
	
	private String patrolEndDateStr;		//巡查结束时间(String)
	
	@JsonSerialize(using=DateJsonSerializer.class)
	private Date createDate;				//巡查记录创建日期
	
	private Double patrolDistance;			//巡查距离
	
	private String patrolContent;			//巡查内容、备注
	
	private Integer status;					//巡查删除状态
	
	private Integer imageCount;				//图片数量
	
	private PatrolTrackViewBean patrolTrackViewBean;		//坐标点集合
	
	@JsonSerialize(using=DateJsonSerializer.class)
	private Date startDate;
	@JsonSerialize(using=DateJsonSerializer.class)
	private Date endDate;
	private String orgId;					//组织Id
	
	/*
	 * 临时变量接受开始时间与结束时间
	 */
	private String tempStartDate;
	private String tempEndDate;
	private String tempId;
	private String tempName;
	private List<String> listPatrolUserId = new ArrayList<String>();
	private List<String> listPatrolOrgId = new ArrayList<String>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPatrolUserId() {
		return patrolUserId;
	}

	public void setPatrolUserId(String patrolUserId) {
		this.patrolUserId = patrolUserId;
	}

	public Date getPatrolStartDate() {
		return patrolStartDate;
	}

	public void setPatrolStartDate(Date patrolStartDate) {
		this.patrolStartDate = patrolStartDate;
	}

	public Date getPatrolEndDate() {
		return patrolEndDate;
	}

	public void setPatrolEndDate(Date patrolEndDate) {
		this.patrolEndDate = patrolEndDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Double getPatrolDistance() {
		return patrolDistance;
	}

	public void setPatrolDistance(Double patrolDistance) {
		this.patrolDistance = patrolDistance;
	}

	public String getPatrolContent() {
		return patrolContent;
	}

	public void setPatrolContent(String patrolContent) {
		this.patrolContent = patrolContent;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getImageCount() {
		return imageCount;
	}

	public void setImageCount(Integer imageCount) {
		this.imageCount = imageCount;
	}

	public PatrolTrackViewBean getPatrolTrackViewBean() {
		return patrolTrackViewBean;
	}

	public void setPatrolTrackViewBean(PatrolTrackViewBean patrolTrackViewBean) {
		this.patrolTrackViewBean = patrolTrackViewBean;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getTempStartDate() {
		return tempStartDate;
	}

	public void setTempStartDate(String tempStartDate) {
		this.tempStartDate = tempStartDate;
	}

	public String getTempEndDate() {
		return tempEndDate;
	}

	public void setTempEndDate(String tempEndDate) {
		this.tempEndDate = tempEndDate;
	}

	public String getPatrolStartDateStr() {
		return patrolStartDateStr;
	}

	public void setPatrolStartDateStr(String patrolStartDateStr) {
		this.patrolStartDateStr = patrolStartDateStr;
	}

	public String getPatrolEndDateStr() {
		return patrolEndDateStr;
	}

	public void setPatrolEndDateStr(String patrolEndDateStr) {
		this.patrolEndDateStr = patrolEndDateStr;
	}

	public String getTempId() {
		return tempId;
	}

	public void setTempId(String tempId) {
		this.tempId = tempId;
	}

	public String getTempName() {
		return tempName;
	}

	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	public List<String> getListPatrolUserId() {
		return listPatrolUserId;
	}

	public void setListPatrolUserId(List<String> listPatrolUserId) {
		this.listPatrolUserId = listPatrolUserId;
	}

	public List<String> getListPatrolOrgId() {
		return listPatrolOrgId;
	}

	public void setListPatrolOrgId(List<String> listPatrolOrgId) {
		this.listPatrolOrgId = listPatrolOrgId;
	}
	
}
