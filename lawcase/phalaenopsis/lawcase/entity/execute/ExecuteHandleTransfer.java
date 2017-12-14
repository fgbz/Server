package phalaenopsis.lawcase.entity.execute;

import java.util.Date;

import phalaenopsis.lawcase.entity.Receipt;

/** 
 土地违法违规处理移送书
 
*/
public class ExecuteHandleTransfer
{
	/** 
	 编号
	*/
	private String no;
	/** 
	 移送机构
	*/
	private String transferDepartment;
	/** 
	 违法事实
	*/
	private String illegalTruth;
	/** 
	 当事人
	*/
	private String litigant;
	/** 
	 填写日期
	*/
	private Date date;
	/** 
	 回证
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
	public String getIllegalTruth() {
		return illegalTruth;
	}
	public void setIllegalTruth(String illegalTruth) {
		this.illegalTruth = illegalTruth;
	}
	public String getLitigant() {
		return litigant;
	}
	public void setLitigant(String litigant) {
		this.litigant = litigant;
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