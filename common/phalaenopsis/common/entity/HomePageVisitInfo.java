package phalaenopsis.common.entity;

public class HomePageVisitInfo {
	/**
	 * 线索总数
	 */
	private Integer allCaseSource = 500;
	/**
	 * 线索未办理总数
	 */
	private Integer allUnCaseSource = 35;
	/**
	 * 信访总数
	 */
	private Integer allVisit = 800;
	/**
	 * 信访未办理总数
	 */
	private Integer allUnVisit = 66;
	
	public Integer getAllCaseSource() {
		return allCaseSource;
	}
	public void setAllCaseSource(Integer allCaseSource) {
		this.allCaseSource = allCaseSource;
	}
	public Integer getAllUnCaseSource() {
		return allUnCaseSource;
	}
	public void setAllUnCaseSource(Integer allUnCaseSource) {
		this.allUnCaseSource = allUnCaseSource;
	}
	public Integer getAllVisit() {
		return allVisit;
	}
	public void setAllVisit(Integer allVisit) {
		this.allVisit = allVisit;
	}
	public Integer getAllUnVisit() {
		return allUnVisit;
	}
	public void setAllUnVisit(Integer allUnVisit) {
		this.allUnVisit = allUnVisit;
	}
	
	
}
