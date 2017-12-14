package phalaenopsis.lawcase.entity.execute;

import java.util.Date;

import phalaenopsis.lawcase.entity.Receipt;

/**
 * 非法财物移交书
 * 
 */
public class ExecuteIllegalityTransfer {
	/**
	 * 编号
	 * 
	 */
	private String no;
	/**
	 * 移交部门
	 * 
	 */
	private String transferDepartment;
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
	public String getTransferDepartment() {
		return transferDepartment;
	}
	public void setTransferDepartment(String transferDepartment) {
		this.transferDepartment = transferDepartment;
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