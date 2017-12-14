/**
 * Description 初评案件实体类
 * Author lih
 * Date 2017-04-06
 */
package phalaenopsis.lawcaseevaluation.entity;

import javax.persistence.EnumType;

import phalaenopsis.lawcaseevaluation.entity.caseDetail.CaseJudge;

public class FirstTrialLawcase extends LawcaseAccount {
	
	/**
	 * 编号-主键
	 */
	private long id;


	/**
	 * 案件ID，案件台账表外键
	 */
	private long   ceLawCaseAccountId;
	
	/**
	 * 初评用户ID，用户表外键
	 */
	private long  evaluationUserId;


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
	 * 案件评审
	 */
	private CaseJudge caseJudge;
	
	/**
	 * 分数等级
	 */
	private ScoreGrade grade;

	public ScoreGrade getGrade() {
		return ScoreGrade.getGrade(firstTotalScore);
	}

	public void setGrade(ScoreGrade grade) {
		this.grade = grade;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCeLawCaseAccountId() {
		return ceLawCaseAccountId;
	}

	public void setCeLawCaseAccountId(long ceLawCaseAccountId) {
		this.ceLawCaseAccountId = ceLawCaseAccountId;
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

	public CaseJudge getCaseJudge() {
		return caseJudge;
	}

	public void setCaseJudge(CaseJudge caseJudge) {
		this.caseJudge = caseJudge;
	}

}
