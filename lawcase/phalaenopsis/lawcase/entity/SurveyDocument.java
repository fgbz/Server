package phalaenopsis.lawcase.entity;



import com.fasterxml.jackson.annotation.JsonProperty;
import phalaenopsis.lawcase.entity.document.Document;

/**
 * 调查节点表单信息
 * 
 */
public class SurveyDocument {
	/**
	 * 询问笔录表单信息
	 * 
	 */
	@JsonProperty("InterrogationRecord")
	private Document interrogationRecord;
	
	/**
	 * 调查报告
	 * 
	 */
	@JsonProperty("SurveyReport")
	private Document surveyReport;
	
	public Document getInterrogationRecord() {
		return interrogationRecord;
	}
	public void setInterrogationRecord(Document interrogationRecord) {
		this.interrogationRecord = interrogationRecord;
	}
	public Document getSurveyReport() {
		return surveyReport;
	}
	public void setSurveyReport(Document surveyReport) {
		this.surveyReport = surveyReport;
	}
	
}