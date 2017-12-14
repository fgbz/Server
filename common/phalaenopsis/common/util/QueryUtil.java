package phalaenopsis.common.util;

import java.util.Date;
import java.util.List;

import phalaenopsis.common.entity.ExportCondition;

/**
 * 查询辅助类
 * @author dongdongt
 *
 */
public class QueryUtil {
	private Integer year;//年
	private Date  month;//月
	private String quarter;//季度
	private List<ExportCondition> conditions;
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Date getMonth() {
		return month;
	}
	public void setMonth(Date month) {
		this.month = month;
	}
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	public List<ExportCondition> getConditions() {
		return conditions;
	}
	public void setConditions(List<ExportCondition> conditions) {
		this.conditions = conditions;
	}
}
