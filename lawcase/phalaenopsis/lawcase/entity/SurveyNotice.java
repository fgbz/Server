package phalaenopsis.lawcase.entity;

import java.util.Date;

/**
 * 接受调查通知书信息
 * 
 */
public class SurveyNotice {
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
	 * 截止日期
	 * 
	 */
	private java.util.Date deadline;
	/**
	 * 接受调查地址
	 * 
	 */
	private String receiveSurveyAddress;
	/**
	 * 携带的材料
	 * 
	 */
	private String material;
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
	public java.util.Date getDeadline() {
		return deadline;
	}
	public void setDeadline(java.util.Date deadline) {
		this.deadline = deadline;
	}
	public String getReceiveSurveyAddress() {
		return receiveSurveyAddress;
	}
	public void setReceiveSurveyAddress(String receiveSurveyAddress) {
		this.receiveSurveyAddress = receiveSurveyAddress;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
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