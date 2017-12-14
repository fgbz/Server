package phalaenopsis.lawcase.entity;

/**
 * 保存坐标信息相关参数
 * 
 */
public class CoordinateInfoParam {
	/**
	 * 案件ID
	 * 
	 */
	private String caseID;
	/**
	 * 案件版本号
	 * 
	 */
	private int version;
	/**
	 * 坐标类型
	 * 
	 */
	private int locationType;
	/**
	 * 定位坐标（以","连接各坐标）
	 * 
	 */
	private String shape;
	/**
	 * 实测坐标（以","连接各坐标）
	 * 
	 */
	private String realShape;
	/**
	 * 七参数原始坐标系ID
	 * 
	 */
	private String oldsrid;
	public String getCaseID() {
		return caseID;
	}
	public void setCaseID(String caseID) {
		this.caseID = caseID;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public int getLocationType() {
		return locationType;
	}
	public void setLocationType(int locationType) {
		this.locationType = locationType;
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public String getRealShape() {
		return realShape;
	}
	public void setRealShape(String realShape) {
		this.realShape = realShape;
	}
	public String getOldsrid() {
		return oldsrid;
	}
	public void setOldsrid(String oldsrid) {
		this.oldsrid = oldsrid;
	}
	
}