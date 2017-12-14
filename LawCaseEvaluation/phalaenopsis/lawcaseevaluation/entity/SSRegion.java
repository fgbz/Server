package phalaenopsis.lawcaseevaluation.entity;

import java.util.List;

public class SSRegion {
	/**
	 * 行政区划代码
	 */
	private long regionCode;
	/**
	 * 行政区划名称
	 */
	private String regionName;
	/**
	 * 行政区下的人员
	 */
	private List<EvaluationPerson> peopleList;

	public List<EvaluationPerson> getPeopleList() {
		return peopleList;
	}

	public void setPeopleList(List<EvaluationPerson> peopleList) {
		this.peopleList = peopleList;
	}

	/**
	 * 是否展开
	 */
	private boolean isExtend = true;

	public boolean isExtend() {
		return isExtend;
	}

	public void setExtend(boolean isExtend) {
		this.isExtend = isExtend;
	}

	/**
	 * 是否选中
	 */
	private boolean isChecked = false;

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public long getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(long regionCode) {
		this.regionCode = regionCode;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
}
