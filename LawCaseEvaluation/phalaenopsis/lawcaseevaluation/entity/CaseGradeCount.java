package phalaenopsis.lawcaseevaluation.entity;

/**
 * 案件得分统计
 * @author chunl
 *
 */
public class CaseGradeCount {

	/**
	 * 优秀总数
	 */
	public int goodCount;

	/**
	 * 合格总数
	 */
	public int gradeCount;

	/**
	 * 不合格总数
	 */
	public int unGradeCount;

	/**
	 * 需要复核总数
	 */
	public int UnReviewCount;

	public CaseGradeCount() {
		goodCount = 0;
		gradeCount = 0;
		unGradeCount = 0;
		UnReviewCount = 0;
	}

	public int getGoodCount() {
		return goodCount;
	}

	public void setGoodCount(int goodCount) {
    this.goodCount = goodCount;
	}

	public int getGradeCount() {
		return gradeCount;
	}

	public void setGradeCount(int gradeCount) {
		this.gradeCount = gradeCount;
	}

	public int getUnGradeCount() {
		return unGradeCount;
	}

	public void setUnGradeCount(int unGradeCount) {
		this.unGradeCount = unGradeCount;
	}

	public int getUnReviewCount() {
		return UnReviewCount;
	}

	public void setUnReviewCount(int unReviewCount) {
		UnReviewCount = unReviewCount;
	}
}
