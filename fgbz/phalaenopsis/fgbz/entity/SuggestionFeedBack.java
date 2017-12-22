package phalaenopsis.fgbz.entity;

import java.util.Date;

public class SuggestionFeedBack {
    /**
     * 主键ID
     */

    private String id;
    /**
     * 意见单ID
     */

    private String sid;
    /**
     * 反馈标题
     */

    private String title;
    /**
     * 反馈内容
     */

    private String fdetails;
    /**
     * 反馈人ID
     */

    private String feedbackid;
    /**
     * 反馈时间
     */

    private Date feedbackdate;
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

    private String extend1;

    private String extend2;

    private String extend3;

    /**
     * 反馈人姓名
     */
    private String feedbackusername;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getFeedbackid() {
        return feedbackid;
    }

    public void setFeedbackid(String feedbackid) {
        this.feedbackid = feedbackid;
    }

    public String getModifyuserid() {
        return modifyuserid;
    }

    public void setModifyuserid(String modifyuserid) {
        this.modifyuserid = modifyuserid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFdetails() {
        return fdetails;
    }

    public void setFdetails(String fdetails) {
        this.fdetails = fdetails;
    }

    public Date getFeedbackdate() {
        return feedbackdate;
    }

    public void setFeedbackdate(Date feedbackdate) {
        this.feedbackdate = feedbackdate;
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

    public Integer getSubsysid() {
        return subsysid;
    }

    public void setSubsysid(Integer subsysid) {
        this.subsysid = subsysid;
    }

    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1;
    }

    public String getExtend2() {
        return extend2;
    }

    public void setExtend2(String extend2) {
        this.extend2 = extend2;
    }

    public String getExtend3() {
        return extend3;
    }

    public void setExtend3(String extend3) {
        this.extend3 = extend3;
    }

    public String getFeedbackusername() {
        return feedbackusername;
    }

    public void setFeedbackusername(String feedbackusername) {
        this.feedbackusername = feedbackusername;
    }
}
