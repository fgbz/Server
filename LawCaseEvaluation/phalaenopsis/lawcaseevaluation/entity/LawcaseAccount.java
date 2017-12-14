/**
 * Description 案件台账实体类
 * Author lih
 * Date 2017-04-06
 */
package phalaenopsis.lawcaseevaluation.entity;

public class LawcaseAccount {
	/**
	 * 主键ID
	 */
	private long ID;
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
	 * 父级编码(市)
	 */
	private String parentRegionCode;
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
	/**
	 * 是否能够上报
	 */
	private String isCanReport;
	/**
	 * 评查标准 外键
	 */
	private long pcbzID;
	/**
	 * 年份
	 */
	private int belongedYear;
	/**
	 * 用户id
	 */
	private long userid;
	
	/**
	 * 复审状态
	 */
	public ReviewState reviewState;
	/**
	 * 评查人数
	 */
	private int pcCount;



	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

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

	public String getParentRegionCode() {
		return parentRegionCode;
	}

	public void setParentRegionCode(String parentRegionCode) {
		this.parentRegionCode = parentRegionCode;
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

	public String getIsCanReport() {
		return isCanReport;
	}

	public void setIsCanReport(String isCanReport) {
		this.isCanReport = isCanReport;
	}

	public long getPcbzID() {
		return pcbzID;
	}

	public void setPcbzID(long pcbzID) {
		this.pcbzID = pcbzID;
	}

	public int getBelongedYear() {
		return belongedYear;
	}

	public void setBelongedYear(int belongedYear) {
		this.belongedYear = belongedYear;
	}
	
	public ReviewState getReviewState() {
		return reviewState;
	}

	public void setReviewState(ReviewState reviewState) {
		this.reviewState = reviewState;
	}

	public int getPcCount() {
		return pcCount;
	}

	public void setPcCount(int pcCount) {
		this.pcCount = pcCount;
	}

}
