package phalaenopsis.allWeather.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableName;

import phalaenopsis.allWeather.enums.AuditEnum;
import phalaenopsis.common.annotation.ExcelTitle;

import java.io.Serializable;


/**
 * <p>
 * 全天候图斑审核表
 * </p>
 *
 * @author dongdongt
 * @since 2017-07-13
 */
@TableName("SW_AUDITSPOT")
public class SwAuditspot implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 市级审核_审核结果 1:通过 2:不通过
     */
    @ExcelTitle(value = "市级审核状态")
    private Integer cityauditispass;
    /**
     * 主键
     */
    private Long id;
    /**
     * 省级审核_审核人ID
     */

    private String provinceauditpersonid;
    /**
     * 市级审核_审核人ID
     */

    private String cityauditpersonid;
    /**
     * 是否市级已审核  0:未审核  1:已审核  （解决多次上报回退问题）
     */

    private Integer iscityaudit;
    /**
     * 卫片图斑表ID
     */

    private Long mapspotid;
    /**
     * 省级审核_审核人名称
     */
    @ExcelTitle("省级审核人")
    private String provinceauditpersonname;
    /**
     * 省级审核_审核日期
     */

    private Date provinceaudittime;
    /**
     * 省级审核_备注
     */
    @ExcelTitle(value = "省级审核备注")
    private String provinceauditremarks;
    /**
     * 省级审核_审核结果 1:通过 2:不通过
     */
    @ExcelTitle(value = "省级审核状态")
    private Integer provinceauditispass;
    /**
     * 市级审核_审核人名称
     */
    @ExcelTitle("市级审核人")
    private String cityauditpersonname;
    /**
     * 市级审核_审核日期
     */

    private Date cityaudittime;
    /**
     * 市级审核_备注
     */
    @ExcelTitle(value = "市级审核备注")
    private String cityauditremarks;
    /**
     * 图斑当前状态
     */
    private Integer nowNode;

    private Integer organizationType;//辅助字段

    public Integer getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(Integer organizationType) {
        this.organizationType = organizationType;
    }

    public Integer getNowNode() {
        return nowNode;
    }

    public void setNowNode(Integer nowNode) {
        this.nowNode = nowNode;
    }

    public Integer getCityauditispass() {
        return cityauditispass;
    }

    public void setCityauditispass(Integer cityauditispass) {
        this.cityauditispass = cityauditispass;
    }
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}

    public String getProvinceauditpersonid() {
        return provinceauditpersonid;
    }

    public void setProvinceauditpersonid(String provinceauditpersonid) {
        this.provinceauditpersonid = provinceauditpersonid;
    }

    public String getCityauditpersonid() {
        return cityauditpersonid;
    }

    public void setCityauditpersonid(String cityauditpersonid) {
        this.cityauditpersonid = cityauditpersonid;
    }

    public Integer getIscityaudit() {
        return iscityaudit;
    }

    public void setIscityaudit(Integer iscityaudit) {
        this.iscityaudit = iscityaudit;
    }

    public Long getMapspotid() {
        return mapspotid;
    }

    public void setMapspotid(Long mapspotid) {
        this.mapspotid = mapspotid;
    }

    public String getProvinceauditpersonname() {
        return provinceauditpersonname;
    }

    public void setProvinceauditpersonname(String provinceauditpersonname) {
        this.provinceauditpersonname = provinceauditpersonname;
    }

    public Date getProvinceaudittime() {
        return provinceaudittime;
    }

    public void setProvinceaudittime(Date provinceaudittime) {
        this.provinceaudittime = provinceaudittime;
    }

    public String getProvinceauditremarks() {
        return provinceauditremarks;
    }

    public void setProvinceauditremarks(String provinceauditremarks) {
        this.provinceauditremarks = provinceauditremarks;
    }

    public Integer getProvinceauditispass() {
        return provinceauditispass;
    }

    public void setProvinceauditispass(Integer provinceauditispass) {
        this.provinceauditispass = provinceauditispass;
    }

    public String getCityauditpersonname() {
        return cityauditpersonname;
    }

    public void setCityauditpersonname(String cityauditpersonname) {
        this.cityauditpersonname = cityauditpersonname;
    }

    public Date getCityaudittime() {
        return cityaudittime;
    }

    public void setCityaudittime(Date cityaudittime) {
        this.cityaudittime = cityaudittime;
    }

    public String getCityauditremarks() {
        return cityauditremarks;
    }

    public void setCityauditremarks(String cityauditremarks) {
        this.cityauditremarks = cityauditremarks;
    }

}