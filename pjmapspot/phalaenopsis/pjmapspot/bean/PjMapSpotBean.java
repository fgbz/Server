package phalaenopsis.pjmapspot.bean;

import java.util.List;

/**
 * 项目名称：Phalaenopsis
 * 创建日期：2017/10/30
 * 修改历史：
 * 1. [2017/10/30]创建文件
 *
 * @author weiz2902
 */
public class PjMapSpotBean {

    /**
     * 区域ID
     */
    private List<Long> regionId;

    /**
     * 类型（退回/上报） 1上报2退回
     */
    private String type;

    /**
     * 用户组织级别 1区县 2市级 3省级
     */
    private int grade;

    /**
     * 审核信息/退回原因
     */
    private String approveInfo;

    /**
     * 上报/审核人
     */
    private String approveName;

    /**
     * 上报年份
     */
    private Short year;

    /**
     * 上报状态（1 区级已上报市级，2 市级已上报省级，3 市级退回区级，4 省级退回市级，5 省级退回区级）
     */
    private Short state;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getApproveInfo() {
		return approveInfo;
	}

	public void setApproveInfo(String approveInfo) {
		this.approveInfo = approveInfo;
	}

	public String getApproveName() {
		return approveName;
	}

	public void setApproveName(String approveName) {
		this.approveName = approveName;
	}


	public Short getYear() {
		return year;
	}

	public void setYear(Short year) {
		this.year = year;
	}

	public List<Long> getRegionId() {
		return regionId;
	}

	public void setRegionId(List<Long> regionId) {
		this.regionId = regionId;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

 
}