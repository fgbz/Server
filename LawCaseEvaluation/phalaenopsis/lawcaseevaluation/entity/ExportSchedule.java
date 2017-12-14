package phalaenopsis.lawcaseevaluation.entity;

/**
 * 导出各市各案卷一览表实体
 * 
 * @author yuhangc
 *
 */
public class ExportSchedule {
	/**
	 * 市名
	 */
	private String regionName;
	/**
	 * 案件总数
	 */
	private int caseCount;
	/**
	 * 优秀案卷数
	 */
	private int goodCaseCount;
	/**
	 * 合格案卷数
	 */
	private int gradeCaseCount;
	/**
	 * 不合格案卷数
	 */
	private int unGradeCaseCount;
	/**
	 * 不符合评查条件
	 */
	private int unMatchedCaseCount;

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public int getCaseCount() {
		return caseCount;
	}

	public void setCaseCount(int caseCount) {
		this.caseCount = caseCount;
	}

	public int getGoodCaseCount() {
		return goodCaseCount;
	}

	public void setGoodCaseCount(int goodCaseCount) {
		this.goodCaseCount = goodCaseCount;
	}

	public int getGradeCaseCount() {
		return gradeCaseCount;
	}

	public void setGradeCaseCount(int gradeCaseCount) {
		this.gradeCaseCount = gradeCaseCount;
	}

	public int getUnGradeCaseCount() {
		return unGradeCaseCount;
	}

	public void setUnGradeCaseCount(int unGradeCaseCount) {
		this.unGradeCaseCount = unGradeCaseCount;
	}

	public int getUnMatchedCaseCount() {
		return unMatchedCaseCount;
	}

	public void setUnMatchedCaseCount(int unMatchedCaseCount) {
		this.unMatchedCaseCount = unMatchedCaseCount;
	}
}
