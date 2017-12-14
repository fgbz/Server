package phalaenopsis.lawcaseevaluation.entity.caseDetail;

import java.util.List;

/**
 * 评查内容
 * @author chunl
 *
 */
public class SurveyContent {
	
	/**
	 * 编号-主键
	 */
	private int id;
	
	/**
	 * 评分父项外键
	 */
	private int pcbzFxId;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 总分
	 */
	private int totalScore;
	
	/**
	 * 是否显示
	 */
	private int isShow;
	
	/**
	 * 评查标准
	 */
	private List<SurveryStandard> surveryStandards;

	public List<SurveryStandard> getSurveryStandards() {
		return surveryStandards;
	}

	public void setSurveryStandards(List<SurveryStandard> surveryStandards) {
		this.surveryStandards = surveryStandards;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPcbzFxId() {
		return pcbzFxId;
	}

	public void setPcbzFxId(int pcbzFxId) {
		this.pcbzFxId = pcbzFxId;
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

	public int getIsShow() {
		return isShow;
	}

	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}
}
