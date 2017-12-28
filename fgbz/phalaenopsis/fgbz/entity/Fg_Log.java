package phalaenopsis.fgbz.entity;

import java.util.Date;

public class Fg_Log {

    private String id;
    /**
     * 操作名
     */
    private String operationname;
    /**
     * 人员id
     */
    private String userid;

    /**
     * 人员姓名
     */
    private String username;

    /**
     * 操作时间
     */
    private Date operationtime;

    /**
     * 组织名
     */
    private String organizationname;

    /**
     * 组织id
     */
    private String organizationid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperationname() {
        return operationname;
    }

    public void setOperationname(String operationname) {
        this.operationname = operationname;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getOperationtime() {
        return operationtime;
    }

    public void setOperationtime(Date operationtime) {
        this.operationtime = operationtime;
    }

    public String getOrganizationname() {
        return organizationname;
    }

    public void setOrganizationname(String organizationname) {
        this.organizationname = organizationname;
    }

    public String getOrganizationid() {
        return organizationid;
    }

    public void setOrganizationid(String organizationid) {
        this.organizationid = organizationid;
    }
}
