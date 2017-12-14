package phalaenopsis.lawcaseevaluation.entity.caseDetail;

import java.util.List;

/**
 * 案件和评查人员关系表
 * @author chunl
 *
 */
public class LawCaseUserMap {
	
	/**
	 * 编号-主键
	 */
	private long id;
	
	/**
	 * 案件ID，案件台账表外键
	 */
	private long ceLawCaseAccounId; 

	/**
	 * 初评用户ID，用户表外键
	 */
	private long  evaluationUserId;
	
	/**
	 * 初评用户名称
	 */
	private String evaluationUserName;

	/**
	 * 初评得分
	 */
	private float firstTotalScore;
	
	/**
	 * 初评得分是否提交
	 */
	private boolean firstSubmitted;
	
	/**
	 * 复审评分
	 */
	private float reviewTotalScore;
	
	/**
	 * 复审是否提交
	 */
	private boolean reviewSubmitted;
	
	/**
	 * 复核录入人员ID
	 */
	private String  reviewUserAccount;
	
	/**
	 * 复核录入人员姓名
	 */
	private String reviewUserName;
	/**
	 * 评查人提出的意见
	 */
	private String reasons;
	/**
	 * 统计时的备注
	 */
	private String statisticRemark;


	public String getStatisticRemark() {
		return statisticRemark;
	}

	public void setStatisticRemark(String statisticRemark) {
		this.statisticRemark = statisticRemark;
	}

	public String getReasons() {
		return reasons;
	}

	public void setReasons(String reasons) {
		this.reasons = reasons;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCeLawCaseAccounId() {
		return ceLawCaseAccounId;
	}

	public void setCeLawCaseAccounId(long ceLawCaseAccounId) {
		this.ceLawCaseAccounId = ceLawCaseAccounId;
	}

	public long getEvaluationUserId() {
		return evaluationUserId;
	}

	public void setEvaluationUserId(long evaluationUserId) {
		this.evaluationUserId = evaluationUserId;
	}

	public float getFirstTotalScore() {
		return firstTotalScore;
	}

	public void setFirstTotalScore(float firstTotalScore) {
		this.firstTotalScore = firstTotalScore;
	}

	public boolean isFirstSubmitted() {
		return firstSubmitted;
	}

	public void setFirstSubmitted(boolean firstSubmitted) {
		this.firstSubmitted = firstSubmitted;
	}

	public float getReviewTotalScore() {
		return reviewTotalScore;
	}

	public void setReviewTotalScore(float reviewTotalScore) {
		this.reviewTotalScore = reviewTotalScore;
	}

	public boolean isReviewSubmitted() {
		return reviewSubmitted;
	}

	public void setReviewSubmitted(boolean reviewSubmitted) {
		this.reviewSubmitted = reviewSubmitted;
	}
	
	public String getReviewUserAccount() {
		return reviewUserAccount;
	}

	public void setReviewUserAccount(String reviewUserAccount) {
		this.reviewUserAccount = reviewUserAccount;
	}

	public String getEvaluationUserName() {
		return evaluationUserName;
	}

	public void setEvaluationUserName(String evaluationUserName) {
		this.evaluationUserName = evaluationUserName;
	}

	public String getReviewUserName() {
		return reviewUserName;
	}

	public void setReviewUserName(String reviewUserName) {
		this.reviewUserName = reviewUserName;
	}
	
}
