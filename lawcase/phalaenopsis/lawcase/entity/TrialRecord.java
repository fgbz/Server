package phalaenopsis.lawcase.entity;

import java.util.Date;

/**
 * 违法案件审理记录
 * 
 */
public class TrialRecord {
	/**
	 * 案件名称及编号
	 * 
	 */
	private String caseNameAndNo;
	/**
	 * 时间
	 * 
	 */
	private Date trialTime;
	/**
	 * 地点
	 * 
	 */
	private String trialAddress;
	/**
	 * 主持人
	 * 
	 */
	private String host;
	/**
	 * 记录人
	 * 
	 */
	private String recorder;
	/**
	 * 审理（会审）人员
	 * 
	 */
	private String trialPerson;
	/**
	 * 列席人员
	 * 
	 */
	private String attendStaff;
	/**
	 * 案件承办人员
	 * 
	 */
	private String undertaker;
	/**
	 * 审理记录
	 * 
	 */
	private String record;
	/**
	 * 审理意见
	 * 
	 */
	private String trialSuggestion;
	/**
	 * 参与会议人员签名
	 * 
	 */
	private String participantsSign;
	public String getCaseNameAndNo() {
		return caseNameAndNo;
	}
	public void setCaseNameAndNo(String caseNameAndNo) {
		this.caseNameAndNo = caseNameAndNo;
	}
	public Date getTrialTime() {
		return trialTime;
	}
	public void setTrialTime(Date trialTime) {
		this.trialTime = trialTime;
	}
	public String getTrialAddress() {
		return trialAddress;
	}
	public void setTrialAddress(String trialAddress) {
		this.trialAddress = trialAddress;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getRecorder() {
		return recorder;
	}
	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}
	public String getTrialPerson() {
		return trialPerson;
	}
	public void setTrialPerson(String trialPerson) {
		this.trialPerson = trialPerson;
	}
	public String getAttendStaff() {
		return attendStaff;
	}
	public void setAttendStaff(String attendStaff) {
		this.attendStaff = attendStaff;
	}
	public String getUndertaker() {
		return undertaker;
	}
	public void setUndertaker(String undertaker) {
		this.undertaker = undertaker;
	}
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	public String getTrialSuggestion() {
		return trialSuggestion;
	}
	public void setTrialSuggestion(String trialSuggestion) {
		this.trialSuggestion = trialSuggestion;
	}
	public String getParticipantsSign() {
		return participantsSign;
	}
	public void setParticipantsSign(String participantsSign) {
		this.participantsSign = participantsSign;
	}
	
}