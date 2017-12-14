package phalaenopsis.pjmapspot.entity;
/**
 * 获取上报情况列表
 * @ 2017年10月30日
 * @author jund
 */
public class PjMapSpotReport {
	
	/**
	 * 行政区id
	 */
	private int regionId;
	
	/**
	 * 行政区名称
	 */
	private String name;
	
	/**
	 * 未处理图斑数量
	 */
	private int amount;
	
	/**
	 * 已审核通过图斑数量
	 */
	private int passCount;
	
	/**
	 * 已审核不通过图斑数量
	 */
	private int unPassCount;
	
	/**
	 * 图斑总数
	 */
	private int total;
	
	/**
	 * 图斑可调总数
	 */
	private int adjustmentTotal;
	
	/**
	 * 已处理百分比
	 */
	private int percent;
	
	/**
	 * 是否已经上报
	 */
	private boolean isReport;
	
	/**
	 * 是否可以上报
	 */
	private boolean canReport;
	
	/**
	 * 状态
	 */
	private int regionState;
	
	private int isReportNum;

	public int getRegionId() {
		return regionId;
	}

	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPassCount() {
		return passCount;
	}

	public void setPassCount(int passCount) {
		this.passCount = passCount;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}


	public int getAdjustmentTotal() {
		return adjustmentTotal;
	}

	public void setAdjustmentTotal(int adjustmentTotal) {
		this.adjustmentTotal = adjustmentTotal;
	}

	public int getIsReportNum() {
		return isReportNum;
	}

	public void setIsReportNum(int isReportNum) {
		this.isReportNum = isReportNum;
	}

	public boolean isIsReport() {
		return isReport;
	}

	public void setIsReport(boolean isReport) {
		this.isReport = isReport;
	}

	public boolean isCanReport() {
		return canReport;
	}

	public void setCanReport(boolean canReport) {
		this.canReport = canReport;
	}

	public int getRegionState() {
		return regionState;
	}

	public void setRegionState(int regionState) {
		this.regionState = regionState;
	}

	public int getUnPassCount() {
		return unPassCount;
	}

	public void setUnPassCount(int unPassCount) {
		this.unPassCount = unPassCount;
	}
	
	
}
