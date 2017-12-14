package phalaenopsis.lawcase.entity.execute;

import java.util.Date;

import phalaenopsis.lawcase.entity.Receipt;

/**
 * 强制执行申请书
 * 
 */
public class ExecuteEnforceForm {
	/**
	 * 编号
	 */
	private String no;
	/**
	 * 法院名称
	 */
	private String court;
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
	 * 处罚决定书送达时间
	 * 
	 */
	private Date penaltyDecisionDeliveryTime;
	/**
	 * 处罚决定书文号
	 * 
	 */
	private String penaltyDecisionNo;
	/**
	 * 催告函送达时间
	 * 
	 */
	private Date exigentNoticeDeliveryTime;
	/**
	 * 行政处罚内容
	 * 
	 */
	private String penaltyContent;
	/**
	 * 强制执行依据
	 * 
	 */
	private String enforceBasis;
	/**
	 * 强制执行内容
	 * 
	 */
	private String enforceContent;
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
	 * 签发人
	 * 
	 */
	private String issuer;
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
	public String getCourt() {
		return court;
	}
	public void setCourt(String court) {
		this.court = court;
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
	public Date getPenaltyDecisionDeliveryTime() {
		return penaltyDecisionDeliveryTime;
	}
	public void setPenaltyDecisionDeliveryTime(Date penaltyDecisionDeliveryTime) {
		this.penaltyDecisionDeliveryTime = penaltyDecisionDeliveryTime;
	}
	public String getPenaltyDecisionNo() {
		return penaltyDecisionNo;
	}
	public void setPenaltyDecisionNo(String penaltyDecisionNo) {
		this.penaltyDecisionNo = penaltyDecisionNo;
	}
	public Date getExigentNoticeDeliveryTime() {
		return exigentNoticeDeliveryTime;
	}
	public void setExigentNoticeDeliveryTime(Date exigentNoticeDeliveryTime) {
		this.exigentNoticeDeliveryTime = exigentNoticeDeliveryTime;
	}
	public String getPenaltyContent() {
		return penaltyContent;
	}
	public void setPenaltyContent(String penaltyContent) {
		this.penaltyContent = penaltyContent;
	}
	public String getEnforceBasis() {
		return enforceBasis;
	}
	public void setEnforceBasis(String enforceBasis) {
		this.enforceBasis = enforceBasis;
	}
	public String getEnforceContent() {
		return enforceContent;
	}
	public void setEnforceContent(String enforceContent) {
		this.enforceContent = enforceContent;
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
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
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