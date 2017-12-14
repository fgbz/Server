package phalaenopsis.lawcaseevaluation.entity;

/**
 * 案件台账与Excel映射实体类
 * 
 * @author yuhangc
 *
 */
public class LawcaseAccountExcel {
	/**
	 * 序号
	 */
	private String XH;
	/**
	 * 执法单位市本级 /县（市、区）
	 */
	private String regionName;
	/**
	 * 执法单位编码(编码到区县)
	 */
	private String regionCode;
	/**
	 * 案卷编码
	 */
	private String caseCode;
	/**
	 * 案卷名字
	 */
	private String caseName;
	/**
	 * 主要违法事实
	 */
	private String mainTruth;
	/**
	 * 是否结案
	 */
	private String isClosed;
	/**
	 * 是否是重点案件
	 */
	private String isImportant;
	/**
	 * 是否本级自查
	 */
	private String isChecked;
	/**
	 * 是否参加市级评查
	 */
	private String isCityChecked;

	public String getXH() {
		return XH;
	}

	public void setXH(String xH) {
		XH = xH;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getMainTruth() {
		return mainTruth;
	}

	public void setMainTruth(String mainTruth) {
		this.mainTruth = mainTruth;
	}

	public String getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(String isClosed) {
		this.isClosed = isClosed;
	}

	public String getIsImportant() {
		return isImportant;
	}

	public void setIsImportant(String isImportant) {
		this.isImportant = isImportant;
	}

	public String getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}

	public String getIsCityChecked() {
		return isCityChecked;
	}

	public void setIsCityChecked(String isCityChecked) {
		this.isCityChecked = isCityChecked;
	}
}
