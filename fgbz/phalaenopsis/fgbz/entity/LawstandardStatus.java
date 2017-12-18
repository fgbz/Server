package phalaenopsis.fgbz.entity;

import java.util.Date;

public class LawstandardStatus {

    /**
     * 主键ID
     */

    private String id;
    /**
     * 状态名称
     */

    private String statusname;
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
     * 修改人ID
     */

    private String modifyuserid;
    /**
     * 修改时间
     */

    private Date modifydate;
    /**
     * 状态
     */

    private Integer status;
    /**
     * 子系统ID
     */

    private Integer subsysid;

    private int count;

    private boolean candelete;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatusname() {
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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

    public String getInputuserid() {
        return inputuserid;
    }

    public void setInputuserid(String inputuserid) {
        this.inputuserid = inputuserid;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isCandelete() {
        return candelete;
    }

    public void setCandelete(boolean candelete) {
        this.candelete = candelete;
    }
}
