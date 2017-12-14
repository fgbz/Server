package phalaenopsis.lawcaseevaluation.entity;

import java.util.List;

public class ExportScheduleTotal {
	/**
	 * 案件总数
	 */
	private int caseCountTotal;
	/**
	 * 优秀案卷数
	 */
	private int goodCaseCountTotal;
	/**
	 * 合格案卷数
	 */
	private int gradeCaseCountTotal;
	/**
	 * 不合格案卷数
	 */
	private int unGradeCaseCountTotal;
	/**
	 * 不符合评查条件
	 */
	private int unMatchedCaseCountTotal;

	/**
	 * 各市一览情况集合
	 */
	private List<ExportSchedule> exportScheduleList;

	public int getCaseCountTotal() {
		return caseCountTotal;
	}

	public void setCaseCountTotal(int caseCountTotal) {
		this.caseCountTotal = caseCountTotal;
	}

	public int getGoodCaseCountTotal() {
		return goodCaseCountTotal;
	}

	public void setGoodCaseCountTotal(int goodCaseCountTotal) {
		this.goodCaseCountTotal = goodCaseCountTotal;
	}

	public int getGradeCaseCountTotal() {
		return gradeCaseCountTotal;
	}

	public void setGradeCaseCountTotal(int gradeCaseCountTotal) {
		this.gradeCaseCountTotal = gradeCaseCountTotal;
	}

	public int getUnGradeCaseCountTotal() {
		return unGradeCaseCountTotal;
	}

	public void setUnGradeCaseCountTotal(int unGradeCaseCountTotal) {
		this.unGradeCaseCountTotal = unGradeCaseCountTotal;
	}

	public int getUnMatchedCaseCountTotal() {
		return unMatchedCaseCountTotal;
	}

	public void setUnMatchedCaseCountTotal(int unMatchedCaseCountTotal) {
		this.unMatchedCaseCountTotal = unMatchedCaseCountTotal;
	}

	public List<ExportSchedule> getExportScheduleList() {
		return exportScheduleList;
	}

	public void setExportScheduleList(List<ExportSchedule> exportScheduleList) {

		if (exportScheduleList != null && exportScheduleList.size() > 0) {
			for (ExportSchedule exportSchedule : exportScheduleList) {
				caseCountTotal += exportSchedule.getCaseCount();
				goodCaseCountTotal += exportSchedule.getGoodCaseCount();
				gradeCaseCountTotal += exportSchedule.getGradeCaseCount();
				unGradeCaseCountTotal += exportSchedule.getUnGradeCaseCount();
				unMatchedCaseCountTotal += exportSchedule.getUnMatchedCaseCount();
			}
		}
		this.exportScheduleList = exportScheduleList;
	}
}
