package phalaenopsis.fgbz.entity;

import java.util.Date;

/**
 * Created by 13260 on 2017/12/17.
 */
public class Adviceinfo {

    /**
     * 通知编号
     */

    private String id;
    /**
     * 通知标题
     */

    private String title;
    /**
     * 通知详细内容
     */

    private String details;
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
     * 记录状态
     */

    private Integer status;
    /**
     * 子系统ID
     */

    private String subsysid;

    /**
     * 辅助字段
     */
    private int count;

    /**
     * 录入人姓名
     */
    private String inputusername;

    /**
     * 组织名
     */
    private  String orgname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSubsysid() {
        return subsysid;
    }

    public void setSubsysid(String subsysid) {
        this.subsysid = subsysid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getInputusername() {
        return inputusername;
    }

    public void setInputusername(String inputusername) {
        this.inputusername = inputusername;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }
}
