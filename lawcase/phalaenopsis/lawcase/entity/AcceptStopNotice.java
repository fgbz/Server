package phalaenopsis.lawcase.entity;

import java.util.Date;

/**
 * 责令停止违法行为通知书（简易停建书）
 * 
 */
public class AcceptStopNotice {
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
	 * 当事人
	 * 
	 */
	private String privateLitigant;
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
	 * 责令停止依据
	 * 
	 */
	private String orderStopBasis;
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
	public String getPrivateLitigant() {
		return privateLitigant;
	}
	public void setPrivateLitigant(String privateLitigant) {
		this.privateLitigant = privateLitigant;
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
	public String getOrderStopBasis() {
		return orderStopBasis;
	}
	public void setOrderStopBasis(String orderStopBasis) {
		this.orderStopBasis = orderStopBasis;
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