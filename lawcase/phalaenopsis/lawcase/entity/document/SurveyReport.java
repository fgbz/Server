package phalaenopsis.lawcase.entity.document;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 调查报告
 * 
 */
public class SurveyReport implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 案由
	 * 
	 */
	@JsonProperty("CaseReason")
	private String caseReason;
	
	/**
	 * 调查机构
	 * 
	 */
	@JsonProperty("SurveyOrgan")
	private String surveyOrgan;
	
	/**
	 * 承办人
	 * 
	 */
	@JsonProperty("Undertaker")
	private String undertaker;
	
	/**
	 * 调查起始时间
	 * 
	 */
	@JsonProperty("SurveyStartTime")
	private String surveyStartTime;
	
	/**
	 * 调查结束时间
	 * 
	 */
	@JsonProperty("SurveyEndTime")
	private String surveyEndTime;
	
	/**
	 * 当事人情况
	 * 
	 */
	@JsonProperty("LitigantSituation")
	private String litigantSituation;
	
	/**
	 * 住址
	 * 
	 */
	@JsonProperty("Address")
	private String address;
	
	/**
	 * 主要违法事实
	 * 
	 */
	@JsonProperty("IllegalTruth")
	private String illegalTruth;
	
	/**
	 * 处理意见
	 * 
	 */
	@JsonProperty("HandleSuggestion")
	private String handleSuggestion;
	
	/**
	 * 填写日期
	 * 
	 */
	@JsonProperty("Date")
	private String date;
	
	/**
	 * 选择的项：1：选项一，2：选项二，3：选项三
	 * 
	 */
	@JsonProperty("CheckedNo")
	private int checkedNo;
	
	/**
	 * 违法性质（同步更新casebaseinfo表对应字段，用于统计）
	 * 
	 */
	@JsonProperty("LawlessNature")
	private String lawlessNature;
	
	/**
	 * 违法性质 二级类（同步更新casebaseinfo表对应字段，用于统计）
	 * 
	 */
	@JsonProperty("LawlessNatureDetail")
	private String lawlessNatureDetail;
	
	public String getCaseReason() {
		return caseReason;
	}
	public void setCaseReason(String caseReason) {
		this.caseReason = caseReason;
	}
	public String getSurveyOrgan() {
		return surveyOrgan;
	}
	public void setSurveyOrgan(String surveyOrgan) {
		this.surveyOrgan = surveyOrgan;
	}
	public String getUndertaker() {
		return undertaker;
	}
	public void setUndertaker(String undertaker) {
		this.undertaker = undertaker;
	}
	public String getLitigantSituation() {
		return litigantSituation;
	}
	public void setLitigantSituation(String litigantSituation) {
		this.litigantSituation = litigantSituation;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIllegalTruth() {
		return illegalTruth;
	}
	public void setIllegalTruth(String illegalTruth) {
		this.illegalTruth = illegalTruth;
	}
	public String getHandleSuggestion() {
		return handleSuggestion;
	}
	public void setHandleSuggestion(String handleSuggestion) {
		this.handleSuggestion = handleSuggestion;
	}
	public String getSurveyStartTime() {
		return surveyStartTime;
	}
	public void setSurveyStartTime(String surveyStartTime) {
		this.surveyStartTime = surveyStartTime;
	}
	public String getSurveyEndTime() {
		return surveyEndTime;
	}
	public void setSurveyEndTime(String surveyEndTime) {
		this.surveyEndTime = surveyEndTime;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getCheckedNo() {
		return checkedNo;
	}
	public void setCheckedNo(int checkedNo) {
		this.checkedNo = checkedNo;
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
	
}