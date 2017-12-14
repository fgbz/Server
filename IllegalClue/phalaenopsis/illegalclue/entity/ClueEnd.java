package phalaenopsis.illegalclue.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;



/**
 * 线索办结实体
 * @author chunl
 *
 */
public class ClueEnd {
	
	/**
	 * 主键Id
	 */
	private String id;
	
	/**
	 * 线索处理类型
	 */
	private Integer handleType;
	
	/**
	 * 是否结案
	 */
	private Integer endcase;
	
	/**
	 * 反应调查情况
	 */
	private Integer responseOpinion;
	
	/**
	 * 办结备注
	 */
	private String remark;
	
	/**
	 * 拟告知举报人内容
	 */
	private String intendContent;
	
	/**
	 * 登记人
	 */
	private String register;
	
	/**
	 * 登记时间
	 */
	private Long registeTime;
	
	/**
	 * 外键关联(CL_CLUE)表Id
	 */
	private String clueId;
	private String operator;
	private String operatorTime;
	
	private String auditor;
	private Long auditorTime;
	/**
	 * 审核要求
	 */
	private String auditOpinion;
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
	public Long getAuditorTime() {
		return auditorTime;
	}
	public void setAuditorTime(Long auditorTime) {
		this.auditorTime = auditorTime;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getOperatorTime() {
		return operatorTime;
	}
	public void setOperatorTime(String operatorTime) {
		this.operatorTime = operatorTime;
	}


	private String operate_organizattion_Id;//组织结构Id
	/**
	 * 审核状态
	 */
	private Integer status;
	/**
	 * 是否审核（0 -未审核,1-已审核）
	 */
	private Integer is_Audit;
	/**
	 * 是否告知办结
	 */
	private Integer is_Notify;
	public Integer getIs_Notify() {
		return is_Notify;
	}
	public void setIs_Notify(Integer is_Notify) {
		this.is_Notify = is_Notify;
	}
	public Integer getIs_Audit() {
		return is_Audit;
	}
	public void setIs_Audit(Integer is_Audit) {
		this.is_Audit = is_Audit;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getHandleType() {
		return handleType;
	}

	public void setHandleType(Integer handleType) {
		this.handleType = handleType;
	}

	public Integer getEndcase() {
		return endcase;
	}

	public void setEndcase(Integer endcase) {
		this.endcase = endcase;
	}

	public Integer getResponseOpinion() {
		return responseOpinion;
	}

	public void setResponseOpinion(Integer responseOpinion) {
		this.responseOpinion = responseOpinion;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIntendContent() {
		return intendContent;
	}

	public void setIntendContent(String intendContent) {
		this.intendContent = intendContent;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}


	public Long getRegisteTime() {
		return registeTime;
	}
	public void setRegisteTime(Long registeTime) {
		this.registeTime = registeTime;
	}
	public String getClueId() {
		return clueId;
	}
	public void setClueId(String clueId) {
		this.clueId = clueId;
	}
	
	
	public String getOperate_organizattion_Id() {
		return operate_organizattion_Id;
	}
	public void setOperate_organizattion_Id(String operate_organizattion_Id) {
		this.operate_organizattion_Id = operate_organizattion_Id;
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
