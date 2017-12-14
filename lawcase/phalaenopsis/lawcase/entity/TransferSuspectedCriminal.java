package phalaenopsis.lawcase.entity;

import java.util.Date;

/**
 * 涉嫌犯罪案件移送书
 * 
 */
public class TransferSuspectedCriminal {
	/**
	 * ID 用以区分各条数据
	 * 
	 */
	private String id;
	/**
	 * 编号
	 * 
	 */
	private String no;
	/**
	 * 移送单位
	 * 
	 */
	private String transferUnit;
	/**
	 * 移送单位类型
	 * 
	 */
	private String unitType;
	/**
	 * 案件调查时间
	 * 
	 */
	private Date surveyTime;
	/**
	 * 案件简介
	 * 
	 */
	private String caseDescription;
	/**
	 * 违法行为
	 * 
	 */
	private String illegalActs;
	/**
	 * 违反的规定
	 * 
	 */
	private String regulations;
	/**
	 * 移送依据
	 * 
	 */
	private String transferBasis;
	/**
	 * 附件
	 * 
	 */
	private String attachment;
	/**
	 * 联系人
	 * 
	 */
	private String contacts;
	/**
	 * 联系电话
	 * 
	 */
	private String phone;
	/**
	 * 地址
	 * 
	 */
	private String address;
	/**
	 * 填写日期
	 * 
	 */
	private Date date;
	/**
	 * 回证
	 * 
	 */
	private Receipt receipt;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getTransferUnit() {
		return transferUnit;
	}
	public void setTransferUnit(String transferUnit) {
		this.transferUnit = transferUnit;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public Date getSurveyTime() {
		return surveyTime;
	}
	public void setSurveyTime(Date surveyTime) {
		this.surveyTime = surveyTime;
	}
	public String getCaseDescription() {
		return caseDescription;
	}
	public void setCaseDescription(String caseDescription) {
		this.caseDescription = caseDescription;
	}
	public String getIllegalActs() {
		return illegalActs;
	}
	public void setIllegalActs(String illegalActs) {
		this.illegalActs = illegalActs;
	}
	public String getRegulations() {
		return regulations;
	}
	public void setRegulations(String regulations) {
		this.regulations = regulations;
	}
	public String getTransferBasis() {
		return transferBasis;
	}
	public void setTransferBasis(String transferBasis) {
		this.transferBasis = transferBasis;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Receipt getReceipt() {
		return receipt;
	}
	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}
}