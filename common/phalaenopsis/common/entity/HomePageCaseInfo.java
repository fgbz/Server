package phalaenopsis.common.entity;

public class HomePageCaseInfo {
	/**
	 * 区域
	 */
	private String	region;
	/**
	 * 线索
	 */
	private Integer	caseSource = 44;
	/**
	 * 立案
	 */
	private Integer	filing = 55;
	/**
	 * 调查
	 */
	private Integer	investigation = 44;
	/**
	 * 审理
	 */
	private Integer	hear = 30;
	/**
	 * 处理决定
	 */
	private Integer	decision = 50;
	/**
	 * 处罚决定
	 */
	private Integer	punish = 20;
	/**
	 * 执行
	 */
	private Integer	implement = 60;
	/**
	 * 结案
	 */
	private Integer	closed;
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Integer getCaseSource() {
		return caseSource;
	}
	public void setCaseSource(Integer caseSource) {
		this.caseSource = caseSource;
	}
	public Integer getFiling() {
		return filing;
	}
	public void setFiling(Integer filing) {
		this.filing = filing;
	}
	public Integer getInvestigation() {
		return investigation;
	}
	public void setInvestigation(Integer investigation) {
		this.investigation = investigation;
	}
	public Integer getHear() {
		return hear;
	}
	public void setHear(Integer hear) {
		this.hear = hear;
	}
	public Integer getDecision() {
		return decision;
	}
	public void setDecision(Integer decision) {
		this.decision = decision;
	}
	public Integer getPunish() {
		return punish;
	}
	public void setPunish(Integer punish) {
		this.punish = punish;
	}
	public Integer getImplement() {
		return implement;
	}
	public void setImplement(Integer implement) {
		this.implement = implement;
	}
	public Integer getClosed() {
		return closed;
	}
	public void setClosed(Integer closed) {
		this.closed = closed;
	}
	
}
