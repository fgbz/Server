package phalaenopsis.visitSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;



/**
 * 记录审核详细信息实体
 * @author chunl
 * 2017年8月31日上午9:37:43
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class XfDeals {
	
    /**
     * 处理ID
     */
    private Long dealId;

    /**
     * 本次处理人意见
     */
    private String dealAdvice;

    /**
     * 本次处理人ID
     */
    private String dealUserId;

    /**
     * 本次处理的登记ID
     */
    private Long registerId;

    /**
     * 本次处理的日期时间
     */
    private Date createTime;

    /**
     * 2--不予以受理 ； 3--不再受理；4--转办； 5--交办  ；6--办结; 
     */
    private Short dealStatus;

    /**
     * 交办或转办  的组织ID
     */
    private String organizationId;

    /**
     * 交办或转办   的截止日期
     */
    private Date expirDate;


    /**
     * 0--审核不通过，1--审核通过 
     */
    private Short agree;

    /**
     * 审核类型：1--经办人办理，2--领导审核
     */
    //@NotNull
    @Pattern(regexp="S{6,30}")
    private Short  auditType;

    /**
     *0-保存 ； 1--提交
     */
    @NotNull
    private Short commit;
    
    /**
     * 本次处理组织机构Id
     */
    private String orgId;
    
    private Short orgLevel;

	private String issueOrgName;

	//private String orgName;

	public Long getDealId() {
		return dealId;
	}



	public void setDealId(Long dealId) {
		this.dealId = dealId;
	}

	public String getDealAdvice() {
		return dealAdvice;
	}

	public void setDealAdvice(String dealAdvice) {
		this.dealAdvice = dealAdvice;
	}

	public String getDealUserId() {
		return dealUserId;
	}

	public void setDealUserId(String dealUserId) {
		this.dealUserId = dealUserId;
	}

	public Long getRegisterId() {
		return registerId;
	}

	public void setRegisterId(Long registerId) {
		this.registerId = registerId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Short getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(Short dealStatus) {
		this.dealStatus = dealStatus;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public Date getExpirDate() {
		return expirDate;
	}

	public void setExpirDate(Date expirDate) {
		this.expirDate = expirDate;
	}

	public Short getAgree() {
		return agree;
	}

	public void setAgree(Short agree) {
		this.agree = agree;
	}

	public Short getAuditType() {
		return auditType;
	}

	public void setAuditType(Short auditType) {
		this.auditType = auditType;
	}

	public Short getCommit() {
		return commit;
	}

	public void setCommit(Short commit) {
		this.commit = commit;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	public Short getOrgLevel() {
		return orgLevel;
	}

	public void setOrgLevel(Short orgLevel) {
		this.orgLevel = orgLevel;
	}


	public String getIssueOrgName() {
		return issueOrgName;
	}

	public void setIssueOrgName(String issueOrgName) {
		this.issueOrgName = issueOrgName;
	}

	/**
	 * 查询字段，不参与实际业务
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