package phalaenopsis.lawcaseevaluation.entity.caseDetail;

import java.util.List;

/**
 * 评查项目
 * 
 * @author chunl
 *
 */
public class SurveyProject {

	/**
	 * 编号-主键
	 */
	private int id;

	/**
	 * 评查标准主表外键
	 */
	private int pcbzId;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 总分
	 */
	private int totalScore;
	
	/**
	 * 实际得分
	 */
	private float actualScore;
	/**
	 * 复核实际得分
	 */
	private float reviewActualScore;
	
	public float getReviewActualScore() {
		return reviewActualScore;
	}

	public void setReviewActualScore(float reviewActualScore) {
		this.reviewActualScore = reviewActualScore;
	}

	public float getActualScore() {
		return actualScore;
	}

	public void setActualScore(float actualScore) {
		this.actualScore = actualScore;
	}

	private boolean checked =false;
	
	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	/**
	 * 评审内容
	 */
	private List<SurveyContent> surveyContents;
	

	public List<SurveyContent> getSurveyContents() {
		return surveyContents;
	}

	public void setSurveyContents(List<SurveyContent> surveyContents) {
		this.surveyContents = surveyContents;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPcbzId() {
		return pcbzId;
	}

	public void setPcbzId(int pcbzId) {
		this.pcbzId = pcbzId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

}
