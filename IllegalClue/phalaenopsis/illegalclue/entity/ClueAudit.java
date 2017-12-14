package phalaenopsis.illegalclue.entity;

import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonIgnore;
import phalaenopsis.common.method.Json.DateJsonSimpleSerializer;
import phalaenopsis.common.method.Json.NetDateJsonSerializer;

/**
 * 审核实体
 * @author chunl
 *
 */
public class ClueAudit implements Serializable {
	
	
	private static final long serialVersionUID = 1516153428774441570L;

	/**
	 * 主键Id
	 */
	private String id;
	
	/**
	 * 外键关联(CL_Clue)表Id
	 */
	private String clueId;
	
	/**
	 * 审核类型
	 */
	private Integer type;
	/**
	 * 处理建议
	 */
	private String  handleSuggestion;
	/**
	 * 复核意见
	 */
	private String reviewOpinion;
	/**
	 * 复核人
	 */
	private String reviewer;
	/**
	 * 复核时间
	 */
	private Integer reviewTime;
	/**
	 * 审核意见
	 */
	private String auditOpinion;
	/**
	 * 审核人
	 */
	private String  auditor;
	/**
	 * 审核时间
	 */
//	@JsonSerialize(using=NetDateJsonSerializer.class)
	private Long auditorTime; 
	
	/**
	 * 转交办id（审核类型为3-转交办时）
	 */
	private String assignId;
	
	/**
	 * 本级办理id（审核类型为2-本级办理时）
	 */
	private String processId;
	/**
	 * 上报id（审核类型为4-申请上级介入时）
	 */
	private String reportId;
	/**
	 * 操作人
	 */
	private String operator;
	/**
	 * 操作时间
	 */
	private Long operatorTime;
	
	/**
	 * 审核状态(0,1-审核通过,2-退回)
	 */
	private Integer status;
	
	/**
	 * 移送id（审核类型为5-移送审核时）
	 */
	private String transferId;
	/**
	 * 退回理由
	 */
	private String returnReason;
	
	/**
	 * 初判对象
	 * @return
	 */
	private ClueJudge mClueJudge;
	private Integer judgeType;//初判类型辅助
	private Integer handleType;//初判建议处理方式
	/**
	 * 1.初判审核，2.办结审核
	 */
	private Integer auditType;
	public Integer getAuditType() {
		return auditType;
	}

	public void setAuditType(Integer auditType) {
		this.auditType = auditType;
	}

	public ClueJudge getmClueJudge() {
		return mClueJudge;
	}

	public void setmClueJudge(ClueJudge mClueJudge) {
		this.mClueJudge = mClueJudge;
	}

	public Integer getJudgeType() {
		return judgeType;
	}

	public void setJudgeType(Integer judgeType) {
		this.judgeType = judgeType;
	}

	public Integer getHandleType() {
		return handleType;
	}

	public void setHandleType(Integer handleType) {
		this.handleType = handleType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClueId() {
		return clueId;
	}

	public void setClueId(String clueId) {
		this.clueId = clueId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getHandleSuggestion() {
		return handleSuggestion;
	}

	public void setHandleSuggestion(String handleSuggestion) {
		this.handleSuggestion = handleSuggestion;
	}

	public String getReviewOpinion() {
		return reviewOpinion;
	}

	public void setReviewOpinion(String reviewOpinion) {
		this.reviewOpinion = reviewOpinion;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	

	public String getAuditOpinion() {
		return auditOpinion;
	}

	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public String getAssignId() {
		return assignId;
	}

	public void setAssignId(String assignId) {
		this.assignId = assignId;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTransferId() {
		return transferId;
	}

	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}

	public String getReturnReason() {
		return returnReason;
	}

	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	
	public Integer getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(Integer reviewTime) {
		this.reviewTime = reviewTime;
	}


	public Long getAuditorTime() {
		return auditorTime;
	}

	public void setAuditorTime(Long auditorTime) {
		this.auditorTime = auditorTime;
	}

	public Long getOperatorTime() {
		return operatorTime;
	}

	public void setOperatorTime(Long operatorTime) {
		this.operatorTime = operatorTime;
	}





	/**
	 * 用于保存或更新的字段。跟实际业务无关
	 */
	@JsonIgnore
	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
