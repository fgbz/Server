package phalaenopsis.fgbz.entity;

import java.util.Date;

public class LawstandardApprove {
    /**
     * 主键ID
     */

    private String id;
    /**
     * 法规标准ID
     */
    private String lawstandardID;
    /**
     * 审核内容
     */
    private String content;
    /**
     * 审核人ID
     */
    private String approveUserID;
    /**
     * 审核日期
     */
    private Date approveDate;
    /**
     * 是否通过0不通过 1通过
     */
    private Integer status;

    /**
     * 获取审核人姓名
     */
    private String username;

    private String inputorgname;

    public String getInputorgname() {
        return inputorgname;
    }

    public void setInputorgname(String inputorgname) {
        this.inputorgname = inputorgname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLawstandardID() {
        return lawstandardID;
    }

    public void setLawstandardID(String lawstandardID) {
        this.lawstandardID = lawstandardID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getApproveUserID() {
        return approveUserID;
    }

    public void setApproveUserID(String approveUserID) {
        this.approveUserID = approveUserID;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
