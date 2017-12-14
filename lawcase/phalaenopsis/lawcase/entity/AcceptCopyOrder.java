package phalaenopsis.lawcase.entity;

import java.util.Date;

/**
 * 抄告单
 * @author dongdongt
 *
 */
public class AcceptCopyOrder {
	/**
	 * ID 用以区分各条数据
	 */
	private String id;
	/**
	 * 编号
	 */
	private String no;
	/**
	 * 抄告单位名称
	 * 
	 */
	private String copyOrderUnit;
	/**
	 * 违法事实
	 */
	private String illegalTruth;
	/**
	 * 违反的规定
	 */
	private String regulations;
	/**
	 * 责令停止违法行为通知时间
	 * 
	 */
	private Date orderStopNoticeDeliveryTime;
	/**
	 * 合作依据
	 * 
	 */
	private String cooperationBasis;
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
	public String getCopyOrderUnit() {
		return copyOrderUnit;
	}
	public void setCopyOrderUnit(String copyOrderUnit) {
		this.copyOrderUnit = copyOrderUnit;
	}
	public String getIllegalTruth() {
		return illegalTruth;
	}
	public void setIllegalTruth(String illegalTruth) {
		this.illegalTruth = illegalTruth;
	}
	public String getRegulations() {
		return regulations;
	}
	public void setRegulations(String regulations) {
		this.regulations = regulations;
	}
	public Date getOrderStopNoticeDeliveryTime() {
		return orderStopNoticeDeliveryTime;
	}
	public void setOrderStopNoticeDeliveryTime(Date orderStopNoticeDeliveryTime) {
		this.orderStopNoticeDeliveryTime = orderStopNoticeDeliveryTime;
	}
	public String getCooperationBasis() {
		return cooperationBasis;
	}
	public void setCooperationBasis(String cooperationBasis) {
		this.cooperationBasis = cooperationBasis;
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
	
}