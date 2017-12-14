package phalaenopsis.lawcase.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


/**
 * 询问笔录信息
 * @author dongdongt
 *
 */
public class InterrogationRecord {


	@JsonProperty("CaseReason")
    public String caseReason;
	@JsonProperty("Year")
    public String year;

    /// <summary>
    /// 询问时间 月
    /// </summary>
	@JsonProperty("Month")
    public String month;

    /// <summary>
    /// 询问时间 日
    /// </summary>
	@JsonProperty("Day")
    public String day;

    /// <summary>
    /// 询问时间 起始小时数
    /// </summary>
	@JsonProperty("StartHour")
    public String startHour;

    /// <summary>
    /// 询问时间 截止小时数
    /// </summary>
	@JsonProperty("EndHour")
    public String endHour;

    /// <summary>
    /// 询问地址
    /// </summary>
	@JsonProperty("Address")
    public String address;

    /// <summary>
    /// 询问人
    /// </summary>
	@JsonProperty("Questioner")
    public String questioner;

    /// <summary>
    /// 询问人单位与职务
    /// </summary>
	@JsonProperty("QUnitAndPosition")
    public String qUnitAndPosition;

    /// <summary>
    /// 记录人
    /// </summary>
	@JsonProperty("Noter")
    public String noter;

    /// <summary>
    /// 记录人单位与职务
    /// </summary>
	@JsonProperty("NUnitAndPosition")
    public String nUnitAndPosition;

    /// <summary>
    /// 被询问人
    /// </summary>
	@JsonProperty("Interrogee")
    public String interrogee;

    /// <summary>
    /// 被询问人与本案关系
    /// </summary>
	@JsonProperty("Relationship")
    public String relationship;

    /// <summary>
    /// 被询问人身份证号码
    /// </summary>
	@JsonProperty("IdCarNo")
    public String idCardNo;

    /// <summary>
    /// 被询问人单位与职务
    /// </summary>
	@JsonProperty("IUnitAndPosition")
    public String iUnitAndPosition;

    /// <summary>
    /// 被询问人住址及电话
    /// </summary>
	@JsonProperty("AddressAndPhone")
    public String addressAndPhone;

    /// <summary>
    /// 询问人表明身份
    /// </summary>
	@JsonProperty("ShowIdentity")
    public String showIdentity;

    /// 告知询问人权利与义务
	@JsonProperty("RightsAndObligations")
    public String rightsAndObligations;

    /// <summary>
    /// 询问记录
    /// </summary>
	@JsonProperty("QuestionRecord")
    public List<QuestionAndAnswer> questionRecord;

    /// <summary>
    /// 被询问人（签名）
    /// </summary>
	@JsonProperty("InterrogeeSign")
    public String interrogeeSign;

    /// <summary>
    /// 询问人（签名）
    /// </summary>
	@JsonProperty("QuestionerSign")
    public String questionerSign;

    /// <summary>
    /// 记录人（签名）
    /// </summary>
	@JsonProperty("NoterSign")
    public String noterSign;
	@JsonProperty("CaseReason")
	public String getCaseReason() {
		return caseReason;
	}

	public void setCaseReason(String caseReason) {
		this.caseReason = caseReason;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getStartHour() {
		return startHour;
	}

	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}

	public String getEndHour() {
		return endHour;
	}

	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getQuestioner() {
		return questioner;
	}

	public void setQuestioner(String questioner) {
		this.questioner = questioner;
	}

	public String getqUnitAndPosition() {
		return qUnitAndPosition;
	}

	public void setqUnitAndPosition(String qUnitAndPosition) {
		this.qUnitAndPosition = qUnitAndPosition;
	}

	public String getNoter() {
		return noter;
	}

	public void setNoter(String noter) {
		this.noter = noter;
	}

	public String getnUnitAndPosition() {
		return nUnitAndPosition;
	}

	public void setnUnitAndPosition(String nUnitAndPosition) {
		this.nUnitAndPosition = nUnitAndPosition;
	}

	public String getInterrogee() {
		return interrogee;
	}

	public void setInterrogee(String interrogee) {
		this.interrogee = interrogee;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getiUnitAndPosition() {
		return iUnitAndPosition;
	}

	public void setiUnitAndPosition(String iUnitAndPosition) {
		this.iUnitAndPosition = iUnitAndPosition;
	}

	public String getAddressAndPhone() {
		return addressAndPhone;
	}

	public void setAddressAndPhone(String addressAndPhone) {
		this.addressAndPhone = addressAndPhone;
	}

	public String getShowIdentity() {
		return showIdentity;
	}

	public void setShowIdentity(String showIdentity) {
		this.showIdentity = showIdentity;
	}

	public String getRightsAndObligations() {
		return rightsAndObligations;
	}

	public void setRightsAndObligations(String rightsAndObligations) {
		this.rightsAndObligations = rightsAndObligations;
	}

	public List<QuestionAndAnswer> getQuestionRecord() {
		return questionRecord;
	}

	public void setQuestionRecord(List<QuestionAndAnswer> questionRecord) {
		this.questionRecord = questionRecord;
	}

	public String getInterrogeeSign() {
		return interrogeeSign;
	}

	public void setInterrogeeSign(String interrogeeSign) {
		this.interrogeeSign = interrogeeSign;
	}

	public String getQuestionerSign() {
		return questionerSign;
	}

	public void setQuestionerSign(String questionerSign) {
		this.questionerSign = questionerSign;
	}

	public String getNoterSign() {
		return noterSign;
	}

	public void setNoterSign(String noterSign) {
		this.noterSign = noterSign;
	}
    
}
