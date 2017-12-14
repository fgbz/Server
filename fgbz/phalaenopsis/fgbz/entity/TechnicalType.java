package phalaenopsis.fgbz.entity;

import java.util.Date;

public class TechnicalType {
    /**
     * 主键id
     */

    private String id;
    /**
     * 类别名称
     */

    private String typename;
    /**
     * 上级类别编号
     */

    private String parentid;
    /**
     * 备注
     */

    private String memo;
    /**
     * 录入人ID
     */

    private Integer inputuserid;
    /**
     * 录入时间
     */

    private Date inputdate;
    /**
     * 记录状态
     */

    private Integer status;
    /**
     * 子系统ID
     */

    private Integer subsysid;

    private String tectype;

    /**
     * 是否可以删除 'candelete' 可以删除 ‘unabledelete’不能删除
     */
    private String iscandelete;

    private int count;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getInputuserid() {
        return inputuserid;
    }

    public void setInputuserid(Integer inputuserid) {
        this.inputuserid = inputuserid;
    }

    public Date getInputdate() {
        return inputdate;
    }

    public void setInputdate(Date inputdate) {
        this.inputdate = inputdate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSubsysid() {
        return subsysid;
    }

    public void setSubsysid(Integer subsysid) {
        this.subsysid = subsysid;
    }

    public String getTectype() {
        return tectype;
    }

    public void setTectype(String tectype) {
        this.tectype = tectype;
    }

    public String getIscandelete() {
        return iscandelete;
    }

    public void setIscandelete(String iscandelete) {
        this.iscandelete = iscandelete;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
