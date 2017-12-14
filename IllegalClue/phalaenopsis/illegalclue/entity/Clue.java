package phalaenopsis.illegalclue.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;



public class Clue implements Serializable {

	
	private static final long serialVersionUID = 239032195223517926L;

	private String id;

	/**
	 * 线索的类型，默认值 0，表示 未确定的线索类型。
	 */
	private Integer type;

	/**
	 * 线索的来源
	 */
	private String source;
	//TODO 20170703添加线索状态字段
	/*
	 * 线索状态
	 */
	private Integer trialState;
	
	public Integer getTrialState() {
		return trialState;
	}
	public void setTrialState(Integer trialState) {
		this.trialState = trialState;
	}
	private String sourcePcode;
	/**
	 * 线索的来源名称
	 */
	private String sourceName;

	/**
	 * 线索的编号：yyyyMMdd + 4位顺序编号
	 */
	private String serialNumber;

	/**
	 * 线索描述内容的发生时间。
	 */
	private Long happendTime;

	/**
	 * 线索发生地点 行政区划
	 */
	private String regionId;
	private String city;
	/**
	 * 线索发生地点 行政区划名称
	 */
	private String regionIdName;

	/**
	 * 线索发生地点 详细地址
	 */
	private String address;

	/**
	 * 线索的主要内容。
	 */
	private String content;

	/**
	 * 举报人名称
	 */
	private String informerName;

	/**
	 * 举报人 联系地址 行政区划
	 */
	private String informerRegionId;
	private String informerCity;

	/**
	 * 举报人 联系地址 详细地址
	 */
	private String informerAddress;

	/**
	 * 举报人 联系电话
	 */
	private String informerPhoneNumber;

	/**
	 * 被告人（单位） 名称
	 */
	private String defendantName;

	/**
	 * 被告人 类型
	 */
	private Integer defendantType;

	/**
	 * 被告人类型是 个人 时，个人所属的单位名称。
	 */
	private String defendantOrganizationName;

	/**
	 * 被告人类型是 个人 时，个人所在单位担任的职务。
	 */
	private String defendantPost;

	/**
	 * 线索记录人
	 */
	private String recordStaffId;

	/**
	 * 线索记录机构
	 */
	private String recordOrganizationId;

	/**
	 * 线索记录时间
	 */
	private Long recordTime;

	/**
	 * 备注(登记时的备注)
	 */
	private String remarks;

	/**
	 * 此线索是否是重复（无效）线索
	 */
	private Integer isDuplicated;

	/**
	 * 是否是删除的线索。
	 */
	private Integer isRemoved;

	/**
	 * 是否暂存
	 */
	private Integer isTemporary;

	/**
	 * 举报人邮政编码
	 */
	private String informerPostCode;

	/**
	 * 举报方式为信件-挂号信时，填写凭证号。
	 */
	private String certificateNumberNor;

	/**
	 * 举报方式为信件-特别挂号信时，填写凭证号。
	 */
	private String certificateNumberSp;

	/**
	 * 举报方式为电子邮件时，填写电子邮件。
	 */
	private String email;

	/**
	 * 举报方式为领导批办时，填写主要领导。
	 */
	private String mainLeaders;

	/**
	 * 举报方式为下级上报时，填写下级单位。
	 */
	private String subOrganization;

	/**
	 * 举报方式为媒体反映时，填写主要媒体。
	 */
	private String mainMedia;

	/**
	 * 举报方式为其他-其他方式时，填写其他方式。
	 */
	private String otherWay;

	/**
	 * 举报方式为其他-传真时，填写传真号。
	 */
	private String faxNumber;

	/**
	 * 举报方式为其他-部委转办时，填写主要部委。
	 */
	private String mainMinistry;

	/**
	 * 登记人填写的记录人组织机构
	 */
	private String recordOrganization;

	/**
	 * 登记人填写的记录人
	 */
	private String recordStaff;

	/**
	 * 是否合并办理
	 */
	private Integer isMergeHandled;

	/**
	 * 涉及土地面积（亩）
	 */
	private String illegalArea;

	/**
	 * 其中耕地面积（亩）
	 */
	private String farmlandArea;

	/**
	 * 违法类型
	 */
	private String illegalType;
	private String illegalTypePcode;

	/**
	 * 涉及矿种
	 */
	private Integer minerals;

	/**
	 * 是否审核
	 */
	private Integer isAudit;

	/**
	 * 是否移交
	 */
	private Integer isTransfer;

	/**
	 * 是否督办
	 */
	private Integer isSupervise;

	/**
	 * 其中基本农田面积（亩）
	 */
	private String basicFarmlandArea;

	/**
	 * 登记处理方式（0,1-本级办理，2-提请上级）
	 */
	private Integer handleType;

	/**
	 * 拟办意见
	 */
	private String proposedOpinion;

	/**
	 * 是否实名举报
	 */
	private Integer isRealName;

	/**
	 * 证件类型
	 */
	private Integer certificateType;

	/**
	 * 证件号码
	 */
	private String certificateNumber;

	/**
	 * 举报时间
	 */
	private Long informerInformTime;

	/**
	 * 举报人地址乡镇
	 */
	private String informerTownship;

	/**
	 * 问题发生地乡镇
	 */
	private String township;

	/**
	 * 是否重大线索
	 */
	private Integer isSignificant;

	/**
	 * 初判备注
	 */
	private String judgeRemark;

	/**
	 * 拟办告知举报人内容
	 */
	private String notifiedInformer;

	/**
	 * 初判人
	 */
	private String operatorName;

	/**
	 * 初判时间
	 */
	private Integer operate_date;

	/**
	 * 转交办单位
	 */
	private String dest_organization_id;

	/**
	 * 通知内容
	 */
	private String wechat_inform_content;

	/**
	 * 通知时间
	 */
	private Long wechat_inform_time;
	
	/**
	 * 是否实名举报
	 */
	private Integer realName;
	

	/**
	 * 初判对象
	 * @return
	 */
	private ClueJudge mClueJudge;
    /**
     * 初判审核实体对象
     */
	private ClueAudit cAudit;
	/**
     * 办结审核实体对象
     */
	private ClueAudit cendAudit;
	/**
	 * 审核列表
	 */
	private List<ClueAudit> clueAudits;
	private Long startTime;//开始时间 辅助字段
	private Long endTime;//截止时间 辅助字段
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public ClueAudit getCendAudit() {
		return cendAudit;
	}
	public void setCendAudit(ClueAudit cendAudit) {
		this.cendAudit = cendAudit;
	}
	/**
	 * 线索办结信息
	 */
	private ClueEnd cEnd;
	public List<ClueAudit> getClueAudits() {
		return clueAudits;
	}
	public void setClueAudits(List<ClueAudit> clueAudits) {
		this.clueAudits = clueAudits;
	}
	public ClueAudit getcAudit() {
		return cAudit;
	}
	public ClueEnd getcEnd() {
		return cEnd;
	}
	public void setcEnd(ClueEnd cEnd) {
		this.cEnd = cEnd;
	}
	/**
	 * 流程实例id
	 */
	private String instanceID;
	
	/**
	 * 当前流程节点
	 */
	private String node;
	public void setcAudit(ClueAudit cAudit) {
		this.cAudit = cAudit;
	}
	public String getInstanceID() {
		return instanceID;
	}

	public void setInstanceID(String instanceID) {
		this.instanceID = instanceID;
	}
	public ClueJudge getmClueJudge() {
		return mClueJudge;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public void setmClueJudge(ClueJudge mClueJudge) {
		this.mClueJudge = mClueJudge;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	public String getsourceName() {
		return sourceName;
	}

	public void setsourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	
	public String getsourcePcode() {
		return sourcePcode;
	}

	public void setsourcePcode(String sourcePcode) {
		this.sourcePcode = sourcePcode;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Long getHappendTime() {
		return happendTime;
	}

	public void setHappendTime(Long happendTime) {
		this.happendTime = happendTime;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getregionIdName() {
		return regionIdName;
	}

	public void setregionIdName(String regionIdName) {
		this.regionIdName = regionIdName;
	}
	
	
	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getInformerName() {
		return informerName;
	}

	public void setInformerName(String informerName) {
		this.informerName = informerName;
	}

	public String getInformerRegionId() {
		return informerRegionId;
	}

	public void setInformerRegionId(String informerRegionId) {
		this.informerRegionId = informerRegionId;
	}
	public String getInformerCity() {
		return informerCity;
	}
	public void setInformerCity(String informerCity){
		this.informerCity=informerCity;
	}

	public String getInformerAddress() {
		return informerAddress;
	}

	public void setInformerAddress(String informerAddress) {
		this.informerAddress = informerAddress;
	}

	public String getInformerPhoneNumber() {
		return informerPhoneNumber;
	}

	public void setInformerPhoneNumber(String informerPhoneNumber) {
		this.informerPhoneNumber = informerPhoneNumber;
	}

	public String getDefendantName() {
		return defendantName;
	}

	public void setDefendantName(String defendantName) {
		this.defendantName = defendantName;
	}

	public Integer getDefendantType() {
		return defendantType;
	}

	public void setDefendantType(Integer defendantType) {
		this.defendantType = defendantType;
	}

	public String getDefendantOrganizationName() {
		return defendantOrganizationName;
	}

	public void setDefendantOrganizationName(String defendantOrganizationName) {
		this.defendantOrganizationName = defendantOrganizationName;
	}

	public String getDefendantPost() {
		return defendantPost;
	}

	public void setDefendantPost(String defendantPost) {
		this.defendantPost = defendantPost;
	}

	public String getRecordStaffId() {
		return recordStaffId;
	}

	public void setRecordStaffId(String recordStaffId) {
		this.recordStaffId = recordStaffId;
	}

	public String getRecordOrganizationId() {
		return recordOrganizationId;
	}

	public void setRecordOrganizationId(String recordOrganizationId) {
		this.recordOrganizationId = recordOrganizationId;
	}

	public Long getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Long recordTime) {
		this.recordTime = recordTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getIsDuplicated() {
		return isDuplicated;
	}

	public void setIsDuplicated(Integer isDuplicated) {
		this.isDuplicated = isDuplicated;
	}

	public Integer getIsRemoved() {
		return isRemoved;
	}

	public void setIsRemoved(Integer isRemoved) {
		this.isRemoved = isRemoved;
	}

	public Integer getIsTemporary() {
		return isTemporary;
	}

	public void setIsTemporary(Integer isTemporary) {
		this.isTemporary = isTemporary;
	}

	public String getInformerPostCode() {
		return informerPostCode;
	}

	public void setInformerPostCode(String informerPostCode) {
		this.informerPostCode = informerPostCode;
	}

	public String getCertificateNumberNor() {
		return certificateNumberNor;
	}

	public void setCertificateNumberNor(String certificateNumberNor) {
		this.certificateNumberNor = certificateNumberNor;
	}

	public String getCertificateNumberSp() {
		return certificateNumberSp;
	}

	public void setCertificateNumberSp(String certificateNumberSp) {
		this.certificateNumberSp = certificateNumberSp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMainLeaders() {
		return mainLeaders;
	}

	public void setMainLeaders(String mainLeaders) {
		this.mainLeaders = mainLeaders;
	}

	public String getSubOrganization() {
		return subOrganization;
	}

	public void setSubOrganization(String subOrganization) {
		this.subOrganization = subOrganization;
	}

	public String getMainMedia() {
		return mainMedia;
	}

	public void setMainMedia(String mainMedia) {
		this.mainMedia = mainMedia;
	}

	public String getOtherWay() {
		return otherWay;
	}

	public void setOtherWay(String otherWay) {
		this.otherWay = otherWay;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getMainMinistry() {
		return mainMinistry;
	}

	public void setMainMinistry(String mainMinistry) {
		this.mainMinistry = mainMinistry;
	}

	public String getRecordOrganization() {
		return recordOrganization;
	}

	public void setRecordOrganization(String recordOrganization) {
		this.recordOrganization = recordOrganization;
	}

	public String getRecordStaff() {
		return recordStaff;
	}

	public void setRecordStaff(String recordStaff) {
		this.recordStaff = recordStaff;
	}

	public Integer getIsMergeHandled() {
		return isMergeHandled;
	}

	public void setIsMergeHandled(Integer isMergeHandled) {
		this.isMergeHandled = isMergeHandled;
	}

	public String getIllegalArea() {
		return illegalArea;
	}

	public void setIllegalArea(String illegalArea) {
		this.illegalArea = illegalArea;
	}

	public String getFarmlandArea() {
		return farmlandArea;
	}

	public void setFarmlandArea(String farmlandArea) {
		this.farmlandArea = farmlandArea;
	}

	public String getIllegalType() {
		return illegalType;
	}

	public void setIllegalType(String illegalType) {
		this.illegalType = illegalType;
	}
	public String getillegalTypePcode() {
		return illegalTypePcode;
	}

	public void setillegalTypePcode(String illegalTypePcode) {
		this.illegalTypePcode = illegalTypePcode;
	}

	public Integer getMinerals() {
		return minerals;
	}

	public void setMinerals(Integer minerals) {
		this.minerals = minerals;
	}

	public Integer getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(Integer isAudit) {
		this.isAudit = isAudit;
	}

	public Integer getIsTransfer() {
		return isTransfer;
	}

	public void setIsTransfer(Integer isTransfer) {
		this.isTransfer = isTransfer;
	}

	public Integer getIsSupervise() {
		return isSupervise;
	}

	public void setIsSupervise(Integer isSupervise) {
		this.isSupervise = isSupervise;
	}

	public String getBasicFarmlandArea() {
		return basicFarmlandArea;
	}

	public void setBasicFarmlandArea(String basicFarmlandArea) {
		this.basicFarmlandArea = basicFarmlandArea;
	}

	public Integer getHandleType() {
		return handleType;
	}

	public void setHandleType(Integer handleType) {
		this.handleType = handleType;
	}

	public String getProposedOpinion() {
		return proposedOpinion;
	}

	public void setProposedOpinion(String proposedOpinion) {
		this.proposedOpinion = proposedOpinion;
	}

	public Integer getIsRealName() {
		return isRealName;
	}

	public void setIsRealName(Integer isRealName) {
		this.isRealName = isRealName;
	}

	public Integer getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(Integer certificateType) {
		this.certificateType = certificateType;
	}

	public String getCertificateNumber() {
		return certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}

	public Long getInformerInformTime() {
		return informerInformTime;
	}

	public void setInformerInformTime(Long informerInformTime) {
		this.informerInformTime = informerInformTime;
	}

	public String getInformerTownship() {
		return informerTownship;
	}

	public void setInformerTownship(String informerTownship) {
		this.informerTownship = informerTownship;
	}

	public String getTownship() {
		return township;
	}

	public void setTownship(String township) {
		this.township = township;
	}

	public Integer getIsSignificant() {
		return isSignificant;
	}

	public void setIsSignificant(Integer isSignificant) {
		this.isSignificant = isSignificant;
	}

	public String getJudgeRemark() {
		return judgeRemark;
	}

	public void setJudgeRemark(String judgeRemark) {
		this.judgeRemark = judgeRemark;
	}

	public String getNotifiedInformer() {
		return notifiedInformer;
	}

	public void setNotifiedInformer(String notifiedInformer) {
		this.notifiedInformer = notifiedInformer;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public Integer getOperate_date() {
		return operate_date;
	}

	public void setOperate_date(Integer operate_date) {
		this.operate_date = operate_date;
	}

	public String getDest_organization_id() {
		return dest_organization_id;
	}

	public void setDest_organization_id(String dest_organization_id) {
		this.dest_organization_id = dest_organization_id;
	}

	public String getWechat_inform_content() {
		return wechat_inform_content;
	}

	public void setWechat_inform_content(String wechat_inform_content) {
		this.wechat_inform_content = wechat_inform_content;
	}

	public Long getWechat_inform_time() {
		return wechat_inform_time;
	}

	public void setWechat_inform_time(Long wechat_inform_time) {
		this.wechat_inform_time = wechat_inform_time;
	}
	
	public Integer getRealName() {
		return realName;
	}

	public void setRealName(Integer realName) {
		this.realName = realName;
	}

	
	/**
	 * 用于查询或更新，跟业务无关
	 */
	@JsonIgnore
	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
