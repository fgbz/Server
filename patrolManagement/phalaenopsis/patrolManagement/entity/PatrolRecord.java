package phalaenopsis.patrolManagement.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import phalaenopsis.common.entity.Attachment.Attachment;

/**
 * 巡查记录详情
 * @author jund
 * 2017-9-18
 */
public class PatrolRecord {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;						//id
	
	private String patrolUserName;			//巡查人员姓名
	
	private String patrolUserId;			//巡查人员id
	
	private Date patrolStartDate;			//巡查开始时间
	
	private Date patrolEndDate;			//巡查结束时间
	
	private Date createDate;				//巡查日期
	
	private String createDateStr;				//巡查日期(字符串)
	
	private Double patrolDistance;			//巡查距离
	
	private String patrolContent;			//巡查内容、备注
	
	private Integer ImageCount;				//巡查照片数量
	
	private List<Attachment> attachments;	//图片内容
	
	private List<PatrolLog> patrolLogs;		//巡查记录
	
	private Map<String,String> attachmentsInfo;	//图片信息
	
	private String startDate;			//巡查开始时间
	
	private String endDate;			//巡查结束时间

	public String getPatrolUserName() {
		return patrolUserName;
	}

	public void setPatrolUserName(String patrolUserName) {
		this.patrolUserName = patrolUserName;
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

	public Integer getImageCount() {
		return ImageCount;
	}

	public void setImageCount(Integer imageCount) {
		ImageCount = imageCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public List<PatrolLog> getPatrolLogs() {
		return patrolLogs;
	}

	public void setPatrolLogs(List<PatrolLog> patrolLogs) {
		this.patrolLogs = patrolLogs;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPatrolContent() {
		return patrolContent;
	}

	public void setPatrolContent(String patrolContent) {
		this.patrolContent = patrolContent;
	}

	public Map<String, String> getAttachmentsInfo() {
		return attachmentsInfo;
	}

	public void setAttachmentsInfo(Map<String, String> attachmentsInfo) {
		this.attachmentsInfo = attachmentsInfo;
	}

	public String getCreateDateStr() {
		return createDateStr;
	}

	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}
	
}
