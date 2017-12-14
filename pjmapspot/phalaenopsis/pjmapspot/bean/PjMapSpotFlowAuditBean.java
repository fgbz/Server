package phalaenopsis.pjmapspot.bean;


/**
 * 项目名称：Phalaenopsis
 * 创建日期：2017/10/31
 * 修改历史：
 * 1. [2017/10/31]创建文件
 *
 * @author weiz2902
 */
public class PjMapSpotFlowAuditBean {

	/**
	 * 区域ID
	 */
	private long id;
	/**
	 * 市级审核状态 1.未审核、2.审核通过、3.不通过
	 */
	private int cityState;
	/**
	 * 市级是否已审核  null：未审核  1：已审核
	 */
	private int cityAudit;
	/**
	 * 市级审核意见
	 */
	private String cityApproveRemark;
	/**
	 * 市级审核人
	 */
	private String cityApproveName;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getCityState() {
		return cityState;
	}
	public void setCityState(int cityState) {
		this.cityState = cityState;
	}
	public int getCityAudit() {
		return cityAudit;
	}
	public void setCityAudit(int cityAudit) {
		this.cityAudit = cityAudit;
	}
	public String getCityApproveRemark() {
		return cityApproveRemark;
	}
	public void setCityApproveRemark(String cityApproveRemark) {
		this.cityApproveRemark = cityApproveRemark;
	}
	public String getCityApproveName() {
		return cityApproveName;
	}
	public void setCityApproveName(String cityApproveName) {
		this.cityApproveName = cityApproveName;
	}

 
}