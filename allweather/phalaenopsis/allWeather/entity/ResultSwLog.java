package phalaenopsis.allWeather.entity;

import java.util.List;

public class ResultSwLog {
	private String mark;
	/**
	 * 上报和未上报的list
	 */
	private List<SwLog> swLogs;
	/**
	 * 总审核通过数量
	 */
	private Integer allAuditCount;
	/**
	 * 根据当前用户查询对应上报信息
	 */
	private SwLog swLog;
	public SwLog getSwLog() {
		return swLog;
	}
	public void setSwLog(SwLog swLog) {
		this.swLog = swLog;
	}
	public List<SwLog> getSwLogs() {
		return swLogs;
	}
	public void setSwLogs(List<SwLog> swLogs) {
		this.swLogs = swLogs;
	}
	public Integer getAllAuditCount() {
		return allAuditCount;
	}
	public void setAllAuditCount(Integer allAuditCount) {
		this.allAuditCount = allAuditCount;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	
}
