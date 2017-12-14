package phalaenopsis.lawcase.entity;

import java.util.Date;

/** 
 行政处罚决定书
 
*/
public class PenaltyDecision
{
	/** 
	 *编号
	*/
	private String No;
	/** 
	 当事人
	*/
	private String Litigant;
	/** 
	 立案时间
	*/
	private String BuildTime;
	/** 
	 案件名称
	*/
	private String CaseName;
	/** 
	 违法行为
	 
	*/
	private String IllegalActs;
	/** 
	 违反的规定
	*/
	private String Regulations;
	/** 
	 证据
	*/
	private String Evidences;
	/** 
	 处罚/听证 告知时间
	*/
	private String NotifyTime;
	/** 
	 免责声明
	*/
	private String Disclaimer;
	/** 
	 处罚依据
	*/
	private String PenaltyBasis;
	/** 
	 行政处罚内容
	*/
	private String PenaltyContent;
	/** 
	 行政处罚履行方式和期限
	*/
	private String PenaltyPerformance;
	/** 
	 复议机构
	 
	*/
	private String ReconsiderationDepartment;
	/** 
	 复议限期
	*/
	private String ReconsiderationDeadline;
	/** 
	 联系人
	*/
	private String Contacts;
	/** 
	 联系电话
	*/
	private String Phone;
	/** 
	 地址
	*/
	private String Address;
	/** 
	 填写日期
	*/
	private String Date;
	/**
	 * 回证
	 */
	private Receipt receipt;
	public String getNo() {
		return No;
	}
	public void setNo(String no) {
		this.No = no;
	}
	public String getLitigant() {
		return Litigant;
	}
	public void setLitigant(String litigant) {
		this.Litigant = litigant;
	}
	public String getBuildTime() {
		return BuildTime;
	}
	public void setBuildTime(String buildTime) {
		this.BuildTime = buildTime;
	}
	public String getCaseName() {
		return CaseName;
	}
	public void setCaseName(String caseName) {
		this.CaseName = caseName;
	}
	public String getIllegalActs() {
		return IllegalActs;
	}
	public void setIllegalActs(String illegalActs) {
		this.IllegalActs = illegalActs;
	}
	public String getRegulations() {
		return Regulations;
	}
	public void setRegulations(String regulations) {
		this.Regulations = regulations;
	}
	public String getEvidences() {
		return Evidences;
	}
	public void setEvidences(String evidences) {
		this.Evidences = evidences;
	}
	public String getNotifyTime() {
		return NotifyTime;
	}
	public void setNotifyTime(String notifyTime) {
		this.NotifyTime = notifyTime;
	}
	public String getDisclaimer() {
		return Disclaimer;
	}
	public void setDisclaimer(String disclaimer) {
		this.Disclaimer = disclaimer;
	}
	public String getPenaltyBasis() {
		return PenaltyBasis;
	}
	public void setPenaltyBasis(String penaltyBasis) {
		this.PenaltyBasis = penaltyBasis;
	}
	public String getPenaltyContent() {
		return PenaltyContent;
	}
	public void setPenaltyContent(String penaltyContent) {
		this.PenaltyContent = penaltyContent;
	}
	public String getPenaltyPerformance() {
		return PenaltyPerformance;
	}
	public void setPenaltyPerformance(String penaltyPerformance) {
		this.PenaltyPerformance = penaltyPerformance;
	}
	public String getReconsiderationDepartment() {
		return ReconsiderationDepartment;
	}
	public void setReconsiderationDepartment(String reconsiderationDepartment) {
		this.ReconsiderationDepartment = reconsiderationDepartment;
	}
	public String getReconsiderationDeadline() {
		return ReconsiderationDeadline;
	}
	public void setReconsiderationDeadline(String reconsiderationDeadline) {
		this.ReconsiderationDeadline = reconsiderationDeadline;
	}
	public String getContacts() {
		return Contacts;
	}
	public void setContacts(String contacts) {
		this.Contacts = contacts;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		this.Phone = phone;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		this.Address = address;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		this.Date = date;
	}
	public Receipt getReceipt() {
		return receipt;
	}
	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}
	
}