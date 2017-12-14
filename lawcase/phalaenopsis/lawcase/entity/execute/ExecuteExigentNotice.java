package phalaenopsis.lawcase.entity.execute;

import java.util.Date;

import phalaenopsis.lawcase.entity.Receipt;

/**
 * 履行行政处罚决定催告函
 * 
 */
public class ExecuteExigentNotice {
	/**
	 * 编号
	 * 
	 */
	private String no;
	/**
	 * 当事人
	 * 
	 */
	private String litigant;
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
	 * 处罚依据
	 * 
	 */
	private String penaltyBasis;
	/**
	 * 处罚决定书下达时间
	 * 
	 */
	private Date penaltyDecisionTime;
	/**
	 * 处罚决定书文号
	 * 
	 */
	private String penaltyDecisionNo;
	/**
	 * 处罚决定书送达时间
	 * 
	 */
	private java.util.Date penaltyDecisionDeliveryTime;
	/**
	 * 行政处罚内容
	 * 
	 */
	private String penaltyContent;
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
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getLitigant() {
		return litigant;
	}
	public void setLitigant(String litigant) {
		this.litigant = litigant;
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
	public String getPenaltyBasis() {
		return penaltyBasis;
	}
	public void setPenaltyBasis(String penaltyBasis) {
		this.penaltyBasis = penaltyBasis;
	}
	public Date getPenaltyDecisionTime() {
		return penaltyDecisionTime;
	}
	public void setPenaltyDecisionTime(Date penaltyDecisionTime) {
		this.penaltyDecisionTime = penaltyDecisionTime;
	}
	public String getPenaltyDecisionNo() {
		return penaltyDecisionNo;
	}
	public void setPenaltyDecisionNo(String penaltyDecisionNo) {
		this.penaltyDecisionNo = penaltyDecisionNo;
	}
	public java.util.Date getPenaltyDecisionDeliveryTime() {
		return penaltyDecisionDeliveryTime;
	}
	public void setPenaltyDecisionDeliveryTime(java.util.Date penaltyDecisionDeliveryTime) {
		this.penaltyDecisionDeliveryTime = penaltyDecisionDeliveryTime;
	}
	public String getPenaltyContent() {
		return penaltyContent;
	}
	public void setPenaltyContent(String penaltyContent) {
		this.penaltyContent = penaltyContent;
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