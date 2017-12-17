package phalaenopsis.fgbz.entity;

import java.util.Date;

/**
 * Created by 13260 on 2017/12/18.
 */
public class Publishdep {

    /**
     * 主键ID
     */

    private String id;
    /**
     * 部门名称
     */

    private String pubdepname;
    /**
     * 备注
     */

    private String memo;
    /**
     * 录入人ID
     */

    private String inputuserid;
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
    /**
     * 修改人ID
     */

    private String modifyuserid;
    /**
     * 修改人时间
     */

    private Date modifydate;

    private int count;

    private boolean candelete;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPubdepname() {
        return pubdepname;
    }

    public void setPubdepname(String pubdepname) {
        this.pubdepname = pubdepname;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getInputuserid() {
        return inputuserid;
    }

    public void setInputuserid(String inputuserid) {
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

    public String getModifyuserid() {
        return modifyuserid;
    }

    public void setModifyuserid(String modifyuserid) {
        this.modifyuserid = modifyuserid;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public boolean isCandelete() {
        return candelete;
    }

    public void setCandelete(boolean candelete) {
        this.candelete = candelete;
    }
}
