package phalaenopsis.allWeather.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableName;

import phalaenopsis.allWeather.enums.ReportEnum;

/**
 * <p>
 * 上报操作记录表
 * </p>
 *
 * @author dongdongt
 * @since 2017-07-13
 */
@TableName("SW_LOG")
public class SwLog implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */

	private Long id;
	/**
	 * 操作描述/回退原因
	 */

	private String description;
	/**
	 * 年份
	 */

	private Integer year;
	/**
	 * 被回退(上报)的区域ID
	 */

	private Integer region;
	/**
	 * 回退时间
	 */

	private Date backtime;
	/**
	 * 操作类型
	 */

	private Integer type;

	/**
	 * 操作时间
	 */

	private Date operationtime;
	/**
	 * 操作人组织机构ID
	 */

	private String organization;
	/**
	 * 上报或者回退父级ID
	 */
	private String regionParent;
	/**
	 * 操作人名称
	 */
	private String operatername;
	/**
	 * 操作人ID
	 */
	private String operater;

	/**
	 * 期数
	 */
	private int period;

	// 辅助字段
	/**
	 * 分子
	 */
	private Integer member;
	/**
	 * 分母
	 */
	private Integer denominator;

	/**
	 * 审核通过数量
	 */
	private Integer auditCount;
	
	/**
	 * 上报区域ID对应的名称
	 */
	private String regionName;
	/**
	 * 辅助(上报还是回退区分)字段
	 */
	private Integer count;
	/**
	 * 审核通过还是不通过，辅助字段
	 */
	private Integer passOrNo;
	/**
	 * 审核界面控制(处理多次批量审核的情况)
	 * 1.通过，2.不通过
	 */
	private Integer  historyState;
	/**
	 * 省级审核不通过数量
	 */
	private Integer proUnAuditCount;

	/**
	 * 标记。部级关注 1001，省级关注 1002，自然保护区 1003， 其他 1004
	 */
	private String mark;


	private int organizationGrade;
	public Integer getProUnAuditCount() {
		return proUnAuditCount;
	}

	public void setProUnAuditCount(Integer proUnAuditCount) {
		this.proUnAuditCount = proUnAuditCount;
	}

	public Integer getHistoryState() {
		return historyState;
	}

	public void setHistoryState(Integer historyState) {
		this.historyState = historyState;
	}
	public Integer getPassOrNo() {
		return passOrNo;
	}

	public void setPassOrNo(Integer passOrNo) {
		this.passOrNo = passOrNo;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Integer getAuditCount() {
		return auditCount;
	}

	public void setAuditCount(Integer auditCount) {
		this.auditCount = auditCount;
	}

	public String getRegionParent() {
		return regionParent;
	}

	public void setRegionParent(String regionParent) {
		this.regionParent = regionParent;
	}

	public Integer getMember() {
		return member;
	}

	public void setMember(Integer member) {
		this.member = member;
	}

	public Integer getDenominator() {
		return denominator;
	}

	public void setDenominator(Integer denominator) {
		this.denominator = denominator;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getRegion() {
		return region;
	}

	public void setRegion(Integer region) {
		this.region = region;
	}

	public Date getBacktime() {
		return backtime;
	}

	public void setBacktime(Date backtime) {
		this.backtime = backtime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getOperationtime() {
		return operationtime;
	}

	public void setOperationtime(Date operationtime) {
		this.operationtime = operationtime;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getOperatername() {
		return operatername;
	}

	public void setOperatername(String operatername) {
		this.operatername = operatername;
	}

	public String getOperater() {
		return operater;
	}

	public void setOperater(String operater) {
		this.operater = operater;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public SwLog() {
		super();
	}

	public SwLog(Long id, Integer year,Integer period, Integer region, Integer type, Date operationtime, String organization,String regionParent,
			String operatername, String operater) {
		super();
		this.id = id;
		this.year = year;
		this.period = period;
		this.region = region;
		this.type = type;
		this.operationtime = operationtime;
		this.organization = organization;
		this.regionParent=regionParent;
		this.operatername = operatername;
		this.operater = operater;
	}


	public SwLog(Long id, Integer year,Integer period, Integer region, Integer type, Date operationtime, String organization,String regionParent,
				 String operatername, String operater, String mark) {
		super();
		this.id = id;
		this.year = year;
		this.period = period;
		this.region = region;
		this.type = type;
		this.operationtime = operationtime;
		this.organization = organization;
		this.regionParent=regionParent;
		this.operatername = operatername;
		this.operater = operater;
		this.mark = mark;
	}

	public int getOrganizationGrade() {
		return organizationGrade;
	}

	public void setOrganizationGrade(int organizationGrade) {
		this.organizationGrade = organizationGrade;
	}

}