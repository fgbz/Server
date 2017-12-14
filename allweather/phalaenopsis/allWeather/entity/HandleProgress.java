package phalaenopsis.allWeather.entity;


import phalaenopsis.allWeather.enums.ReportEnum;
import phalaenopsis.common.annotation.Comment;


public class HandleProgress {
		
	/**
	 * 上报状态。
	 */
	@Comment("上报状态")
	private String state;
	
	/**
	 * 区县名称
	 */
	@Comment("区县名称")
	private String regionName;
	
	/**
	 * 区县Id
	 */
	@Comment("区县Id")
	private String regionId;
	
	/**
	 * 分子
	 */
	@Comment("分子")
	private int numerator;
	
	/**
	 * 分母
	 */
	@Comment("分母")
	private int denominator;

	/**
	 * 标注类型
	 */
	@Comment("标注类型")
	private String mark;
    
	/**
	 * 审核数量
	 */
	@Comment("审核数量")
	private Integer auditCount;
	/**
	 * 省级审核不通过数量
	 */
	@Comment("省级审核不通过数量")
	private Integer proUnAuditCount;

	public HandleProgress() {
	}

	public HandleProgress(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}

	public Integer getProUnAuditCount() {
		return proUnAuditCount;
	}

	public void setProUnAuditCount(Integer proUnAuditCount) {
		this.proUnAuditCount = proUnAuditCount;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getAuditCount() {
		return auditCount;
	}

	public void setAuditCount(Integer auditCount) {
		this.auditCount = auditCount;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public int getNumerator() {
		return numerator;
	}

	public void setNumerator(int numerator) {
		this.numerator = numerator;
	}

	public int getDenominator() {
		return denominator;
	}

	public void setDenominator(int denominator) {
		this.denominator = denominator;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}
}
