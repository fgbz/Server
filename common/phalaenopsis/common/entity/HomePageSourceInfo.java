package phalaenopsis.common.entity;

public class HomePageSourceInfo {
	/**
	 * 线索来源类型
	 */
	private String caseSource = "电话";
	
	/**
	 * 线索来源数量
	 */
	private Integer caseSourceNum = 35;

	public String getCaseSource() {
		return caseSource;
	}

	public void setCaseSource(String caseSource) {
		this.caseSource = caseSource;
	}

	public Integer getCaseSourceNum() {
		return caseSourceNum;
	}

	public void setCaseSourceNum(Integer caseSourceNum) {
		this.caseSourceNum = caseSourceNum;
	}
	
	
}
