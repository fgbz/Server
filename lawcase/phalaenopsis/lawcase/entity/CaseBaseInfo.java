package phalaenopsis.lawcase.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import phalaenopsis.common.entity.Attachment.Attachment;
import phalaenopsis.common.method.Json.DateJsonDeserializer;
import phalaenopsis.common.method.Json.DateJsonSimpleSerializer;
import phalaenopsis.common.method.Json.NetDateJsonSerializer;

/**
 * 违法案件实体类
 * 
 * @author yuhangc
 *
 */
public class CaseBaseInfo {

	/**
	 * 主键ID
	 */
	@JsonProperty("ID")
	private String id;
	/**
	 * 案件ID
	 */	
	@JsonProperty("CaseID")
	private String caseID;
	/**
	 * 案件关联的流程实例ID
	 */
	@JsonProperty("InstanceID")
	private String instanceID;
	/**
	 * 案件编号
	 */
	@JsonProperty("RegisterCaseNo")
	private String registerCaseNo;
	/**
	 * 案由
	 */
	@JsonProperty("CaseReason")
	private String caseReason;
	/**
	 * 受理时间
	 */
	@JsonProperty("AcceptTime")
	private Date acceptTime;
	/**
	 * 违法发生时间
	 */
	@JsonProperty("HappenedTime")
	@JsonSerialize(using=NetDateJsonSerializer.class)
	private Date happenedTime;
	/**
	 * 行政机关违法发现时间
	 */
	@JsonProperty("DiscoverTime")
	private Date discoverTime;
	/**
	 * 违法发生区域
	 */
	@JsonProperty("IllegalRegion")
	private String illegalRegion;
	/**
	 * 案发地点区县
	 */
	@JsonProperty("District")
	private int district;
	/**
	 * 案发地点详细地址
	 */
	@JsonProperty("DetailAddress")
	private String detailAddress;
	/**
	 * 所属国土所
	 */
	@JsonProperty("BelongDepartment")
	private String belongDepartment;
	/**
	 * 线索来源
	 */
	@JsonProperty("CaseSource")
	private String caseSource;
	/**
	 * 违法用途
	 */
	@JsonProperty("IllegalPurpose")
	private String illegalPurpose;
	/**
	 * 违法主体
	 */
	@JsonProperty("IllegalBody")
	private String illegalBody;
	/**
	 * 案件类型
	 */
	@JsonProperty("CaseType")
	private String caseType;
	/**
	 * 案件性质
	 */
	@JsonProperty("CaseNature")
	private String caseNature;
	/**
	 * 违法性质
	 */
	@JsonProperty("IllegalNature")
	private String illegalNature;
	/**
	 * 紧急程度
	 */
	@JsonProperty("UrgencyDegree")
	private String urgencyDegree;
	/**
	 * 是否卫片
	 */
	@JsonProperty("IsSatellite")
	private int isSatellite;
	/**
	 * 是否土地变更调查
	 */
	@JsonProperty("IsLandChange")
	private int isLandChange;
	/**
	 * 填表单位
	 */
	@JsonProperty("FillFormDepartment")
	private String fillFormDepartment;
	/**
	 * 填表人
	 */
	@JsonProperty("FillFormPerson")
	private String fillFormPerson;
	/**
	 * 填表时间
	 */
	@JsonProperty("FillFormTime")
	private Date fillFormTime;
	/**
	 * 违法事实
	 */
	@JsonProperty("IllegalTruth")
	private String illegalTruth;
	/**
	 * 报案方式
	 */
	@JsonProperty("ReportCaseType")
	private String reportCaseType;
	/**
	 * 报案电话
	 */
	@JsonProperty("ReportCasePhone")
	private String reportCasePhone;
	/**
	 * 报案人身份证号码
	 */
	@JsonProperty("ReporterIDCard")
	private String reporterIDCard;
	/**
	 * 报案人姓名
	 */
	@JsonProperty("ReporterName")
	private String reporterName;
	/**
	 * 报案人性别
	 */
	@JsonProperty("ReporterSex")
	private String reporterSex;
	/**
	 * 报案人年龄
	 */
	@JsonProperty("ReporterAge")
	private int reporterAge;
	/**
	 * 报案人户籍地址
	 */
	@JsonProperty("ReporterHouseHold")
	private String reporterHouseHold;
	/**
	 * 报案人现住址
	 */
	@JsonProperty("ReporterAddress")
	private String reporterAddress;
	/**
	 * 报案人联系电话
	 */
	@JsonProperty("ReporterPhone")
	private String reporterPhone;
	/**
	 * 举报内容
	 */
	@JsonProperty("ReportContent")
	private String reportContent;
	/**
	 * 媒体名称
	 */
	@JsonProperty("MediaName")
	private String mediaName;
	/**
	 * 媒体披露时间
	 */
	@JsonProperty("DisclosureTime")
	private Date disclosureTime;
	/**
	 * 交办单位
	 */
	@JsonProperty("AssignDepartment")
	private String assignDepartment;
	/**
	 * 交办方式
	 */
	@JsonProperty("AssignWay")
	private String assignWay;
	/**
	 * 交办时间
	 */
	@JsonProperty("AssignTime")
	private Date assignTime;
	/**
	 * 交办要求
	 */
	@JsonProperty("AssignRequirements")
	private String assignRequirements;
	/**
	 * 巡查人员
	 */
	@JsonProperty("PatrolPeople")
	private String patrolPeople;
	/**
	 * 巡查时间
	 */
	@JsonProperty("PatrolTime")
	private Date patrolTime;
	/**
	 * 巡查路线
	 */
	@JsonProperty("PatrolRoute")
	private String patrolRoute;
	/**
	 * 卫片图斑号
	 */
	@JsonProperty("SatelliteImagesNo")
	private String satelliteImagesNo;
	/**
	 * 卫片图斑号年度
	 */
	@JsonProperty("SatelliteImagesYear")
	private String satelliteImagesYear;
	/**
	 * 土地变更图斑号
	 */
	@JsonProperty("LandChangeMapSpotNo")
	private String landChangeMapSpotNo;
	/**
	 * 土地变更图斑号年度
	 */
	@JsonProperty("LandChangeMapSpotYear")
	private String landChangeMapSpotYear;
	/**
	 * 发现途径
	 */
	@JsonProperty("FindWay")
	private String findWay;
	/**
	 * 土地变更调查号
	 */
	@JsonProperty("LandChangeSurveyNo")
	private String landChangeSurveyNo;
	/**
	 * 土地变更调查号年度
	 */
	@JsonProperty("LandChangeSurveyYear")
	private String landChangeSurveyYear;
	/**
	 * 是否可定位
	 */
	@JsonProperty("IsLocation")
	private String isLocation;
	/**
	 * 版本号
	 */
	@JsonProperty("Version")
	private int version;
	/**
	 * 立案时间
	 */
	@JsonProperty("BuildTime")
	@JsonSerialize(using=NetDateJsonSerializer.class)
	private Date buildTime;
	/**
	 * 记录创建人ID
	 */
	@JsonProperty("CreateUser")
	private String createUser;
	/**
	 * 面积
	 */
	@JsonProperty("Area")
	private double area;
	/**
	 * 耕地面积
	 */
	@JsonProperty("ArableLandArea")
	private double arableLandArea;
	/**
	 * 基本农田面积
	 */
	@JsonProperty("BasicFarmLandArea")
	private double basicFarmLandArea;
	/**
	 * 处理情况
	 */
	@JsonProperty("DealSituation")
	private String dealSituation;
	/**
	 * 案件简要情况
	 */
	@JsonProperty("CaseBrief")
	private String caseBrief;
	/**
	 * 行政处罚或行政处理内容
	 */
	@JsonProperty("PunishmentContent")
	private String punishmentContent;
	/**
	 * 执行情况
	 */
	@JsonProperty("ExecutiveCondition")
	private String executiveCondition;
	/**
	 * 案件处理重要程度描述 一般 or 重大疑难
	 */
	@JsonProperty("Importance")
	private String importance;
	/**
	 * 决定处理下一步确定的后续多个阶段的节点
	 */
	@JsonProperty("NextNodes")
	private String nextNodes;
	/**
	 * 决定处理阶段主要违法事实及性质
	 */
	@JsonProperty("DealIllegalTruth")
	private String dealIllegalTruth;
	/**
	 * 申请延迟处罚理由
	 */
	@JsonProperty("DelayReason")
	private String delayReason;
	/**
	 * 申请延迟状态（0未申请，1申请中，2申请成功）
	 */
	@JsonProperty("DelayStatus")
	private int delayStatus;
	/**
	 * 违法性质
	 */
	@JsonProperty("LawlessNature")
	private String lawlessNature;
	/**
	 * 违法性质 小类
	 */
	@JsonProperty("LawlessNatureDetail")
	private String lawlessNatureDetail;
	/**
	 * 案件的当前流程节点
	 */
	@JsonProperty("NodeID")
	private String nodeID;
	/**
	 * 案件创建人的部门名称
	 */
	@JsonProperty("OrgName")
	private String orgName;
	/**
	 * 案发地点完整地址
	 */
	@JsonProperty("CasePlace")
	private String casePlace;
	/**
	 * 当事人列表
	 */
	@JsonProperty("LitigantInfos")
	private List<LitigantInfo> litigantInfos;
	/**
	 * 受理人列表
	 */
	@JsonProperty("AcceptUsers")
	private List<CaseAcceptUser> acceptUsers;
	/**
	 * 定位坐标
	 */
	@JsonProperty("Shape")
	private String shape;
	/**
	 * 实测坐标
	 */
	@JsonProperty("RealShape")
	private String realShape;
	/**
	 * 保存坐标信息的参数
	 */
	@JsonProperty("CoordinateInfoParam")
	private CoordinateInfoParam coordinateInfoParam;
	/**
	 * 案件所有签字信息列表
	 */
	@JsonProperty("SignatureList")
	private List<Signature> signatureList;
	/**
	 * 要保存的签字数据
	 */
	@JsonProperty("Signs")
	private List<Signature> signs;
	/**
	 * 附件列表
	 */
	@JsonIgnore
	private List<Attachment> attachments;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCaseID() {
		return caseID;
	}
	public void setCaseID(String caseID) {
		this.caseID = caseID;
	}
	public String getInstanceID() {
		return instanceID;
	}
	public void setInstanceID(String instanceID) {
		this.instanceID = instanceID;
	}
	public String getRegisterCaseNo() {
		return registerCaseNo;
	}
	public void setRegisterCaseNo(String registerCaseNo) {
		this.registerCaseNo = registerCaseNo;
	}
	public String getCaseReason() {
		return caseReason;
	}
	public void setCaseReason(String caseReason) {
		this.caseReason = caseReason;
	}
	public Date getAcceptTime() {
		return acceptTime;
	}
	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}
	public Date getHappenedTime() {
		return happenedTime;
	}
	public void setHappenedTime(Date happenedTime) {
		this.happenedTime = happenedTime;
	}
	public Date getDiscoverTime() {
		return discoverTime;
	}
	public void setDiscoverTime(Date discoverTime) {
		this.discoverTime = discoverTime;
	}
	public String getIllegalRegion() {
		return illegalRegion;
	}
	public void setIllegalRegion(String illegalRegion) {
		this.illegalRegion = illegalRegion;
	}
	public int getDistrict() {
		return district;
	}
	public void setDistrict(int district) {
		this.district = district;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public String getBelongDepartment() {
		return belongDepartment;
	}
	public void setBelongDepartment(String belongDepartment) {
		this.belongDepartment = belongDepartment;
	}
	public String getCaseSource() {
		return caseSource;
	}
	public void setCaseSource(String caseSource) {
		this.caseSource = caseSource;
	}
	public String getIllegalPurpose() {
		return illegalPurpose;
	}
	public void setIllegalPurpose(String illegalPurpose) {
		this.illegalPurpose = illegalPurpose;
	}
	public String getIllegalBody() {
		return illegalBody;
	}
	public void setIllegalBody(String illegalBody) {
		this.illegalBody = illegalBody;
	}
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	public String getCaseNature() {
		return caseNature;
	}
	public void setCaseNature(String caseNature) {
		this.caseNature = caseNature;
	}
	public String getIllegalNature() {
		return illegalNature;
	}
	public void setIllegalNature(String illegalNature) {
		this.illegalNature = illegalNature;
	}
	public String getUrgencyDegree() {
		return urgencyDegree;
	}
	public void setUrgencyDegree(String urgencyDegree) {
		this.urgencyDegree = urgencyDegree;
	}
	public int getIsSatellite() {
		return isSatellite;
	}
	public void setIsSatellite(int isSatellite) {
		this.isSatellite = isSatellite;
	}
	public int getIsLandChange() {
		return isLandChange;
	}
	public void setIsLandChange(int isLandChange) {
		this.isLandChange = isLandChange;
	}
	public String getFillFormDepartment() {
		return fillFormDepartment;
	}
	public void setFillFormDepartment(String fillFormDepartment) {
		this.fillFormDepartment = fillFormDepartment;
	}
	public String getFillFormPerson() {
		return fillFormPerson;
	}
	public void setFillFormPerson(String fillFormPerson) {
		this.fillFormPerson = fillFormPerson;
	}
	public Date getFillFormTime() {
		return fillFormTime;
	}
	public void setFillFormTime(Date fillFormTime) {
		this.fillFormTime = fillFormTime;
	}
	public String getIllegalTruth() {
		return illegalTruth;
	}
	public void setIllegalTruth(String illegalTruth) {
		this.illegalTruth = illegalTruth;
	}
	public String getReportCaseType() {
		return reportCaseType;
	}
	public void setReportCaseType(String reportCaseType) {
		this.reportCaseType = reportCaseType;
	}
	public String getReportCasePhone() {
		return reportCasePhone;
	}
	public void setReportCasePhone(String reportCasePhone) {
		this.reportCasePhone = reportCasePhone;
	}
	public String getReporterIDCard() {
		return reporterIDCard;
	}
	public void setReporterIDCard(String reporterIDCard) {
		this.reporterIDCard = reporterIDCard;
	}
	public String getReporterName() {
		return reporterName;
	}
	public void setReporterName(String reporterName) {
		this.reporterName = reporterName;
	}
	public String getReporterSex() {
		return reporterSex;
	}
	public void setReporterSex(String reporterSex) {
		this.reporterSex = reporterSex;
	}
	public int getReporterAge() {
		return reporterAge;
	}
	public void setReporterAge(int reporterAge) {
		this.reporterAge = reporterAge;
	}
	public String getReporterHouseHold() {
		return reporterHouseHold;
	}
	public void setReporterHouseHold(String reporterHouseHold) {
		this.reporterHouseHold = reporterHouseHold;
	}
	public String getReporterAddress() {
		return reporterAddress;
	}
	public void setReporterAddress(String reporterAddress) {
		this.reporterAddress = reporterAddress;
	}
	public String getReporterPhone() {
		return reporterPhone;
	}
	public void setReporterPhone(String reporterPhone) {
		this.reporterPhone = reporterPhone;
	}
	public String getReportContent() {
		return reportContent;
	}
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	public String getMediaName() {
		return mediaName;
	}
	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}
	public Date getDisclosureTime() {
		return disclosureTime;
	}
	public void setDisclosureTime(Date disclosureTime) {
		this.disclosureTime = disclosureTime;
	}
	public String getAssignDepartment() {
		return assignDepartment;
	}
	public void setAssignDepartment(String assignDepartment) {
		this.assignDepartment = assignDepartment;
	}
	public String getAssignWay() {
		return assignWay;
	}
	public void setAssignWay(String assignWay) {
		this.assignWay = assignWay;
	}
	public Date getAssignTime() {
		return assignTime;
	}
	public void setAssignTime(Date assignTime) {
		this.assignTime = assignTime;
	}
	public String getAssignRequirements() {
		return assignRequirements;
	}
	public void setAssignRequirements(String assignRequirements) {
		this.assignRequirements = assignRequirements;
	}
	public String getPatrolPeople() {
		return patrolPeople;
	}
	public void setPatrolPeople(String patrolPeople) {
		this.patrolPeople = patrolPeople;
	}
	public Date getPatrolTime() {
		return patrolTime;
	}
	public void setPatrolTime(Date patrolTime) {
		this.patrolTime = patrolTime;
	}
	public String getPatrolRoute() {
		return patrolRoute;
	}
	public void setPatrolRoute(String patrolRoute) {
		this.patrolRoute = patrolRoute;
	}
	public String getSatelliteImagesNo() {
		return satelliteImagesNo;
	}
	public void setSatelliteImagesNo(String satelliteImagesNo) {
		this.satelliteImagesNo = satelliteImagesNo;
	}
	public String getSatelliteImagesYear() {
		return satelliteImagesYear;
	}
	public void setSatelliteImagesYear(String satelliteImagesYear) {
		this.satelliteImagesYear = satelliteImagesYear;
	}
	public String getLandChangeMapSpotNo() {
		return landChangeMapSpotNo;
	}
	public void setLandChangeMapSpotNo(String landChangeMapSpotNo) {
		this.landChangeMapSpotNo = landChangeMapSpotNo;
	}
	public String getLandChangeMapSpotYear() {
		return landChangeMapSpotYear;
	}
	public void setLandChangeMapSpotYear(String landChangeMapSpotYear) {
		this.landChangeMapSpotYear = landChangeMapSpotYear;
	}
	public String getFindWay() {
		return findWay;
	}
	public void setFindWay(String findWay) {
		this.findWay = findWay;
	}
	public String getLandChangeSurveyNo() {
		return landChangeSurveyNo;
	}
	public void setLandChangeSurveyNo(String landChangeSurveyNo) {
		this.landChangeSurveyNo = landChangeSurveyNo;
	}
	public String getLandChangeSurveyYear() {
		return landChangeSurveyYear;
	}
	public void setLandChangeSurveyYear(String landChangeSurveyYear) {
		this.landChangeSurveyYear = landChangeSurveyYear;
	}
	public String getIsLocation() {
		return isLocation;
	}
	public void setIsLocation(String isLocation) {
		this.isLocation = isLocation;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public Date getBuildTime() {
		return buildTime;
	}
	public void setBuildTime(Date buildTime) {
		this.buildTime = buildTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public double getArableLandArea() {
		return arableLandArea;
	}
	public void setArableLandArea(double arableLandArea) {
		this.arableLandArea = arableLandArea;
	}
	public double getBasicFarmLandArea() {
		return basicFarmLandArea;
	}
	public void setBasicFarmLandArea(double basicFarmLandArea) {
		this.basicFarmLandArea = basicFarmLandArea;
	}
	public String getDealSituation() {
		return dealSituation;
	}
	public void setDealSituation(String dealSituation) {
		this.dealSituation = dealSituation;
	}
	public String getCaseBrief() {
		return caseBrief;
	}
	public void setCaseBrief(String caseBrief) {
		this.caseBrief = caseBrief;
	}
	public String getPunishmentContent() {
		return punishmentContent;
	}
	public void setPunishmentContent(String punishmentContent) {
		this.punishmentContent = punishmentContent;
	}
	public String getExecutiveCondition() {
		return executiveCondition;
	}
	public void setExecutiveCondition(String executiveCondition) {
		this.executiveCondition = executiveCondition;
	}
	public String getImportance() {
		return importance;
	}
	public void setImportance(String importance) {
		this.importance = importance;
	}
	public String getNextNodes() {
		return nextNodes;
	}
	public void setNextNodes(String nextNodes) {
		this.nextNodes = nextNodes;
	}
	public String getDealIllegalTruth() {
		return dealIllegalTruth;
	}
	public void setDealIllegalTruth(String dealIllegalTruth) {
		this.dealIllegalTruth = dealIllegalTruth;
	}
	public String getDelayReason() {
		return delayReason;
	}
	public void setDelayReason(String delayReason) {
		this.delayReason = delayReason;
	}
	public int getDelayStatus() {
		return delayStatus;
	}
	public void setDelayStatus(int delayStatus) {
		this.delayStatus = delayStatus;
	}
	public String getLawlessNature() {
		return lawlessNature;
	}
	public void setLawlessNature(String lawlessNature) {
		this.lawlessNature = lawlessNature;
	}
	public String getLawlessNatureDetail() {
		return lawlessNatureDetail;
	}
	public void setLawlessNatureDetail(String lawlessNatureDetail) {
		this.lawlessNatureDetail = lawlessNatureDetail;
	}
	public String getNodeID() {
		return nodeID;
	}
	public void setNodeID(String nodeID) {
		this.nodeID = nodeID;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getCasePlace() {
		return casePlace;
	}
	public void setCasePlace(String casePlace) {
		this.casePlace = casePlace;
	}
	public List<LitigantInfo> getLitigantInfos() {
		return litigantInfos;
	}
	public void setLitigantInfos(List<LitigantInfo> litigantInfos) {
		this.litigantInfos = litigantInfos;
	}
	public List<CaseAcceptUser> getAcceptUsers() {
		return acceptUsers;
	}
	public void setAcceptUsers(List<CaseAcceptUser> acceptUsers) {
		this.acceptUsers = acceptUsers;
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public String getRealShape() {
		return realShape;
	}
	public void setRealShape(String realShape) {
		this.realShape = realShape;
	}
	public CoordinateInfoParam getCoordinateInfoParam() {
		return coordinateInfoParam;
	}
	public void setCoordinateInfoParam(CoordinateInfoParam coordinateInfoParam) {
		this.coordinateInfoParam = coordinateInfoParam;
	}
	public List<Signature> getSignatureList() {
		return signatureList;
	}
	public void setSignatureList(List<Signature> signatureList) {
		this.signatureList = signatureList;
	}
	public List<Signature> getSigns() {
		return signs;
	}
	public void setSigns(List<Signature> signs) {
		this.signs = signs;
	}
	public List<Attachment> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}
	
	@JsonIgnore
	private Integer count;

	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
}
