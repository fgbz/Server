package phalaenopsis.lawcaseevaluation.entity;

public class EvaluationAreaUserMap {
	/**
	 * 主键id
	 */
	private long id;
	/**
	 * 区域id
	 */
	private long regionID;
	/**
	 * 人员id
	 */
	private long evaluationUserID;
	/**
	 * 人员姓名
	 */
	private String regionName;
	/**
	 * 区域名
	 */
	private String evaluationUserName;
	/**
	 * 分配的区域code
	 */
	private long allocationRegionCode;

	public long getAllocationRegionCode() {
		return allocationRegionCode;
	}

	public void setAllocationRegionCode(long allocationRegionCode) {
		this.allocationRegionCode = allocationRegionCode;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getEvaluationUserName() {
		return evaluationUserName;
	}

	public void setEvaluationUserName(String evaluationUserName) {
		this.evaluationUserName = evaluationUserName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRegionID() {
		return regionID;
	}

	public void setRegionID(long regionID) {
		this.regionID = regionID;
	}

	public long getEvaluationUserID() {
		return evaluationUserID;
	}

	public void setEvaluationUserID(long evaluationUserID) {
		this.evaluationUserID = evaluationUserID;
	}
}
