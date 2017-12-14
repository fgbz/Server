package phalaenopsis.lawcase.entity;

import java.util.Date;

/**
 * 回证
 * 
 */
public class Receipt {
	/**
	 * 编号
	 * 
	 */
	private String no;
	/**
	 * 法律文书名称
	 * 
	 */
	private String documentName;
	/**
	 * 法律文书文号
	 * 
	 */
	private String documentNo;
	/**
	 * 受送达人
	 * 
	 */
	private String litigant;
	/**
	 * 送达人
	 * 
	 */
	private String sender;
	/**
	 * 送达地点
	 * 
	 */
	private String address;
	/**
	 * 受送达人签名或者盖章
	 * 
	 */
	private String litigantSigned;
	/**
	 * 受送达人签名或者盖章日期
	 * 
	 */
	private Date litigantSignedDate;
	/**
	 * 代收人签名或者盖章
	 * 
	 */
	private String receiverSigned;
	/**
	 * 代收人签名或者盖章日期
	 * 
	 */
	private Date receiverSignedDate;
	/**
	 * 送达方式
	 * 
	 */
	private String deliveryMode;
	/**
	 * 送达日期
	 * 
	 */
	private Date deliveryDate;
	/**
	 * 送达人签名
	 * 
	 */
	private String senderSigned;
	/**
	 * 送达人签名日期
	 * 
	 */
	private Date senderSignedDate;
	/**
	 * 备注
	 * 
	 */
	private String remarks;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getDocumentNo() {
		return documentNo;
	}
	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
	public String getLitigant() {
		return litigant;
	}
	public void setLitigant(String litigant) {
		this.litigant = litigant;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLitigantSigned() {
		return litigantSigned;
	}
	public void setLitigantSigned(String litigantSigned) {
		this.litigantSigned = litigantSigned;
	}
	public Date getLitigantSignedDate() {
		return litigantSignedDate;
	}
	public void setLitigantSignedDate(Date litigantSignedDate) {
		this.litigantSignedDate = litigantSignedDate;
	}
	public String getReceiverSigned() {
		return receiverSigned;
	}
	public void setReceiverSigned(String receiverSigned) {
		this.receiverSigned = receiverSigned;
	}
	public Date getReceiverSignedDate() {
		return receiverSignedDate;
	}
	public void setReceiverSignedDate(Date receiverSignedDate) {
		this.receiverSignedDate = receiverSignedDate;
	}
	public String getDeliveryMode() {
		return deliveryMode;
	}
	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getSenderSigned() {
		return senderSigned;
	}
	public void setSenderSigned(String senderSigned) {
		this.senderSigned = senderSigned;
	}
	public Date getSenderSignedDate() {
		return senderSignedDate;
	}
	public void setSenderSignedDate(Date senderSignedDate) {
		this.senderSignedDate = senderSignedDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}