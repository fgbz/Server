/**
 * Description 初评问卷实体类
 * Author lih
 * Date 2017-04-06
 */
package phalaenopsis.lawcaseevaluation.entity.caseDetail;

import java.util.List;

/**
 * 案件评审主表
 * @author chunl
 *
 */
public class CaseJudge {
	
	/**
	 * 编号-主键 
	 */
	private int id;
	
	/**
	 * 年份起 如 2016
	 */
	private int startYear;
	
	/**
	 * 年份止 如 2017
	 */
	private int endYear;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 备注
	 */
	private String remarks;
	
	/**
	 * 关联的评审项目
	 */
	private List<SurveyProject> surveyProjects;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getEndYear() {
		return endYear;
	}

	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<SurveyProject> getSurveyProjects() {
		return surveyProjects;
	}

	public void setSurveyProjects(List<SurveyProject> surveyProjects) {
		this.surveyProjects = surveyProjects;
	}
}
