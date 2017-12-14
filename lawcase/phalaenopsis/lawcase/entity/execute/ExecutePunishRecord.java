package phalaenopsis.lawcase.entity.execute;

import java.util.Date;

/** 
 行政处罚决定执行记录
 
*/
public class ExecutePunishRecord
{
	/** 
	 案件名称及编号
	*/
	private String caseNameAndNo;
	/** 
	 当事人
	*/
	private String litigant;
	/** 
	 行政处罚内容
	*/
	private String penaltyContent;
	/** 
	 执行记录
	*/
	private String executeRecord;
	/**
	 * 填写日期
	 * 
	 */
	private Date date;
	public String getCaseNameAndNo() {
		return caseNameAndNo;
	}
	public void setCaseNameAndNo(String caseNameAndNo) {
		this.caseNameAndNo = caseNameAndNo;
	}
	public String getLitigant() {
		return litigant;
	}
	public void setLitigant(String litigant) {
		this.litigant = litigant;
	}
	public String getPenaltyContent() {
		return penaltyContent;
	}
	public void setPenaltyContent(String penaltyContent) {
		this.penaltyContent = penaltyContent;
	}
	public String getExecuteRecord() {
		return executeRecord;
	}
	public void setExecuteRecord(String executeRecord) {
		this.executeRecord = executeRecord;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}