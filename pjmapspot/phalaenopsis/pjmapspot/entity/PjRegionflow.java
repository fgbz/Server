package phalaenopsis.pjmapspot.entity;

import java.util.Date;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import phalaenopsis.common.method.Json.DateJsonSerializer;

/**
 * 项目名称：Phalaenopsis
 * 创建日期：2017/10/24
 * 修改历史：
 * 1. [2017/10/24]创建文件
 *
 * @author chunl
 */
public class PjRegionflow {


    /**
     * 主键
     */
    private long id;

    /**
     * 区域ID
     */
    private long regionId;

    /**
     * 上报状态（1 区级已上报市级，2 市级已上报省级，3 市级退回区级，4 省级退回市级，5 省级退回区级）
     */
    private Short state;

    /**
     * 审核信息/退回原因
     */
    private String approveInfo;

    /**
     * 审核人
     */
    private String approveName;

    /**
     * 审核时间
     */
    @JsonSerialize(using=DateJsonSerializer.class)
    private Date approveTime;

    /**
     * 年份
     */
    private Short year;




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getRegionId() {
		return regionId;
	}

	public void setRegionId(long regionId) {
		this.regionId = regionId;
	}

	public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
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

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public Short getYear() {
        return year;
    }

    public void setYear(Short year) {
        this.year = year;
    }
}