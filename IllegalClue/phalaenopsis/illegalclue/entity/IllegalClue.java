package phalaenopsis.illegalclue.entity;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import phalaenopsis.common.method.Json.DateJsonDeserializer;
import phalaenopsis.common.method.Json.DateJsonSerializer;
import phalaenopsis.common.method.Json.DateJsonSimpleSerializer;

/**
 * 违法线索
 * @author chunl
 *
 */
public class IllegalClue {
	
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 举报线索号
	 */
	private String num;
	
	/**
	 * 线索来源
	 */
	private Integer clueSource;
	
	/**
	 * 邮政编码
	 */
	private String clueSourceDetail;
	
	/**
	 * 举报人	
	 */
	private String reporter;
	
	/**
	 * 是否实名举报
	 */
	private Integer realName;
	
	/**
	 * 证件类型
	 */
	private Integer cardType;
	
	/**
	 * 证件号
	 */
	private String cardNum;
	
	/**
	 * 举报人联系地址-省
	 */
	private Integer contactProvince;
	
	/**
	 * 举报人联系地址-市
	 */
	private Integer contactCity;
	
	/**
	 * 举报人联系地址-区
	 */
	private Integer contactCounty;
	
	/**
	 *举报人联系地址-乡镇
	 */
	private String contactTown;
	
	/**
	 * 举报人联系地址-详细地址
	 */
	private String contactDetail;
	
	/**
	 * 举报人联系电话
	 */
	private String contactPhone;
	
	/**
	 * 举报时间
	 */
	@JsonSerialize(using=DateJsonSimpleSerializer.class)
	@JsonDeserialize(using=DateJsonDeserializer.class)
	private Date reportTime;
	
	/**
	 * 被举报人
	 */
	@NotNull(message="请填写举报人")
	private String informer;
	
	/**
	 * 举报问题发生地-省
	 */
	private Integer problemProvince;
	
	/**
	 * 举报问题发生地-市
	 */
	private Integer problemCity;
	
	/**
	 * 举报问题发生地-区
	 */
	private Integer problemCounty;
	
	/**
	 * 举报问题发生地-乡镇
	 */

	private String  problemTown;

	/**
	 * 举报问题发生地-详细地址
	 */
	private String problemDetail;
	
	/**
	 * 举报问题发生时间
	 */
	@JsonSerialize(using=DateJsonSimpleSerializer.class)
	@JsonDeserialize(using=DateJsonDeserializer.class)
	private Date problemTime;
	
	/**
	 * 违法类型
	 */
	private Integer illegalType;
	
	/**
	 * 涉及土地面积（亩）
	 */
	private float landArea;
	
	/**
	 * 涉及耕地面积（亩）
	 */
	private float farmlandArea;
	
	/**
	 * 涉及基本农田面积（亩）
	 */
	private float basicfarmlandArea;
	
	/**
	 * 举报问题描述
	 */
	private String problemDescribe;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 登记单位
	 */
	private String registUnit;
	
	/**
	 * 登记人
	 */
	@NotNull(message="请填写登记人")
	private String register;
	
	/**
	 * 登记时间
	 */
	@JsonSerialize(using=DateJsonSimpleSerializer.class)
	@JsonDeserialize(using=DateJsonDeserializer.class)
	private Date registTime;
	
	/**
	 * 举报类型。0表示登记，1表示来电。根据此字段判断是登记还是来电
	 */
	private Integer reportType;
	
	/**
	 * 来电电话
	 */
	private String callPhone;
	
	/**
	 * 来电时间
	 */
	@JsonSerialize(using=DateJsonSerializer.class)
	@JsonDeserialize(using=DateJsonDeserializer.class)
	private Date callTime;
	
	/**
	 * 来电情况
	 */
	private Integer callSituation;
	
	/**
	 * 来电类别
	 */
	private Integer callType;
	
	/**
	 * 流程实例Id
	 */
	private String instanceId;
	
	/**
	 * 流程节点Id
	 */
	private String node;
	
	/**
	 * 系统创建登记表单人员Id
	 */
	private String createUserId;
	
	/**
	 * 矿种
	 */
	private Integer mineType;
	
	/**
	 * 被举报人身份是否不详
	 */
	private Integer isDetailed;

	/**
	 * 线索来源-下级
	 */
	private Integer clueSourceSub;
	
	/**
	 * 违法类型下级
	 */
	private Integer illegalTypeSub;   
	
	
	/**
	 * 线索来源的value
	 */
	private String clueSourceName;
	
	/**
	 * 问题发生地的value
	 */
	private String problemDetailName;
	
	/**
	 * 初判表单
	 */
	private ClueJudge clueJudge;
	
	/**
	 * 审核表单集合
	 */
	private List<ClueAudit> clueAudits;
	
	/**
	 * 办结表单
	 */
	private ClueEnd clueEnd;




	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public Integer getClueSource() {
		return clueSource;
	}

	public void setClueSource(Integer clueSource) {
		this.clueSource = clueSource;
	}

	public String getClueSourceDetail() {
		return clueSourceDetail;
	}

	public void setClueSourceDetail(String postCode) {
		this.clueSourceDetail = postCode;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	public Integer getRealName() {
		return realName;
	}

	public void setRealName(Integer realName) {
		this.realName = realName;
	}

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public Integer getContactProvince() {
		return contactProvince;
	}

	public void setContactProvince(Integer contactProvince) {
		this.contactProvince = contactProvince;
	}

	public Integer getContactCity() {
		return contactCity;
	}

	public void setContactCity(Integer contactCity) {
		this.contactCity = contactCity;
	}

	public Integer getContactCounty() {
		return contactCounty;
	}

	public void setContactCounty(Integer contactCounty) {
		this.contactCounty = contactCounty;
	}

	public String getContactTown() {
		return contactTown;
	}

	public void setContactTown(String contactTown) {
		this.contactTown = contactTown;
	}

	public String getContactDetail() {
		return contactDetail;
	}

	public void setContactDetail(String contactDetail) {
		this.contactDetail = contactDetail;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public String getInformer() {
		return informer;
	}

	public void setInformer(String informer) {
		this.informer = informer;
	}

	public Integer getProblemProvince() {
		return problemProvince;
	}

	public void setProblemProvince(Integer problemProvince) {
		this.problemProvince = problemProvince;
	}

	public Integer getProblemCity() {
		return problemCity;
	}

	public void setProblemCity(Integer problemCity) {
		this.problemCity = problemCity;
	}

	public Integer getProblemCounty() {
		return problemCounty;
	}

	public void setProblemCounty(Integer problemCounty) {
		this.problemCounty = problemCounty;
	}


	public String getProblemTown() {
		return problemTown;
	}

	public void setProblemTown(String problemTown) {
		this.problemTown = problemTown;
	}

	public String getProblemDetail() {
		return problemDetail;
	}

	public void setProblemDetail(String problemDetail) {
		this.problemDetail = problemDetail;
	}

	public Date getProblemTime() {
		return problemTime;
	}

	public void setProblemTime(Date problemTime) {
		this.problemTime = problemTime;
	}

	public Integer getIllegalType() {
		return illegalType;
	}

	public void setIllegalType(Integer illegalType) {
		this.illegalType = illegalType;
	}

	public float getLandArea() {
		return landArea;
	}

	public void setLandArea(float landArea) {
		this.landArea = landArea;
	}

	public float getFarmlandArea() {
		return farmlandArea;
	}

	public void setFarmlandArea(float farmlandArea) {
		this.farmlandArea = farmlandArea;
	}

	public float getBasicfarmlandArea() {
		return basicfarmlandArea;
	}

	public void setBasicfarmlandArea(float basicfarmlandArea) {
		this.basicfarmlandArea = basicfarmlandArea;
	}

	public String getProblemDescribe() {
		return problemDescribe;
	}

	public void setProblemDescribe(String problemDescribe) {
		this.problemDescribe = problemDescribe;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRegistUnit() {
		return registUnit;
	}

	public void setRegistUnit(String registUnit) {
		this.registUnit = registUnit;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	public Integer getReportType() {
		return reportType;
	}

	public void setReportType(Integer reportType) {
		this.reportType = reportType;
	}

	public String getCallPhone() {
		return callPhone;
	}

	public void setCallPhone(String callPhone) {
		this.callPhone = callPhone;
	}

	public Date getCallTime() {
		return callTime;
	}

	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}

	public Integer getCallSituation() {
		return callSituation;
	}

	public void setCallSituation(Integer callSituation) {
		this.callSituation = callSituation;
	}

	public Integer getCallType() {
		return callType;
	}

	public void setCallType(Integer callType) {
		this.callType = callType;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	
	public Integer getMineType() {
		return mineType;
	}

	public void setMineType(Integer mineType) {
		this.mineType = mineType;
	}

	public Integer getIsDetailed() {
		return isDetailed;
	}

	public void setIsDetailed(Integer isDetailed) {
		this.isDetailed = isDetailed;
	}
	

	public Integer getClueSourceSub() {
		return clueSourceSub;
	}

	public void setClueSourceSub(Integer clueSourceSub) {
		this.clueSourceSub = clueSourceSub;
	}

	public Integer getIllegalTypeSub() {
		return illegalTypeSub;
	}

	public void setIllegalTypeSub(Integer illegalTypeSub) {
		this.illegalTypeSub = illegalTypeSub;
	}
	
	public String getClueSourceName() {
		return clueSourceName;
	}

	public void setClueSourceName(String clueSourceName) {
		this.clueSourceName = clueSourceName;
	}

	public String getProblemDetailName() {
		return problemDetailName;
	}

	public void setProblemDetailName(String problemDetailName) {
		this.problemDetailName = problemDetailName;
	}
	
	public ClueJudge getClueJudge() {
		return clueJudge;
	}

	public void setClueJudge(ClueJudge clueJudge) {
		this.clueJudge = clueJudge;
	}

	public List<ClueAudit> getClueAudits() {
		return clueAudits;
	}

	public void setClueAudits(List<ClueAudit> clueAudits) {
		this.clueAudits = clueAudits;
	}

	public ClueEnd getClueEnd() {
		return clueEnd;
	}

	public void setClueEnd(ClueEnd clueEnd) {
		this.clueEnd = clueEnd;
	}
	
	/**
	 * 初判实体
	 */
	public ClueJudge ClueJudge ;
	
}
