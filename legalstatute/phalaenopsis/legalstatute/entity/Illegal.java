package phalaenopsis.legalstatute.entity;

public class Illegal {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 违法行为类别id
	 */
	private long illegalId;
	
	/**
	 * 违法行为类别
	 */
	private String illegalType;
	
	/**
	 * 违法行为标题
	 */
	private String illegalTitle;
	
	/**
	 * 法律依据
	 */
	private String lawName;
	
	/**
	 * 法律内容
	 */
	private String lawContent;
	
	/**
	 * 说明
	 */
	private String remark;

	public long getIllegalId() {
		return illegalId;
	}

	public void setIllegalId(long illegalId) {
		this.illegalId = illegalId;
	}

	public String getIllegalType() {
		return illegalType;
	}

	public void setIllegalType(String illegalType) {
		this.illegalType = illegalType;
	}

	public String getIllegalTitle() {
		return illegalTitle;
	}

	public void setIllegalTitle(String illegalTitle) {
		this.illegalTitle = illegalTitle;
	}

	public String getLawName() {
		return lawName;
	}

	public void setLawName(String lawName) {
		this.lawName = lawName;
	}

	public String getLawContent() {
		return lawContent;
	}

	public void setLawContent(String lawContent) {
		this.lawContent = lawContent;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
