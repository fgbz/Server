package phalaenopsis.fgbz.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by 13260 on 2017/12/15.
 */
public class FG_User {

    /**
     * 主键id
     */

    private String id;
    /**
     * 用户名
     */

    private String username;
    /**
     * 密码,md5加密
     */

    private String password;
    /**
     * 真实姓名
     */

    private String userrealname;
    /**
     * 邮箱
     */

    private String email;
    /**
     * 电话
     */

    private String mobile;

    private String orgid;
    /**
     * 组织名字
     */
    private String orgname;
    /**
     * 录入人ID
     */

    private Integer inputuserid;
    /**
     * 录入时间
     */

    private Date inputdate;
    /**
     * 状态
     */

    private Integer status;
    /**
     * 最后一次登录ID
     */

    private String lastloginip;
    /**
     * 最后一次登录时间
     */

    private Date lastlogintime;
    /**
     * 修改人ID
     */

    private String lastmodifyuserid;
    /**
     * 修改时间
     */

    private Date mpdifydate;
    /**
     * 子系统ID
     */

    private Integer subsysid;

    /**
     * 收藏夹根节点id
     */
    private String favoriteid;

    /**
     * 辅助字段
     */
    private int count;

    /**
     * 用户角色
     */
    private List<FG_Role> roles;

    /**
     * 用户权限
     */
    private List<FG_Menu> menus;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserrealname() {
        return userrealname;
    }

    public void setUserrealname(String userrealname) {
        this.userrealname = userrealname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
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

    public String getLastloginip() {
        return lastloginip;
    }

    public void setLastloginip(String lastloginip) {
        this.lastloginip = lastloginip;
    }

    public Date getLastlogintime() {
        return lastlogintime;
    }

    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public String getLastmodifyuserid() {
        return lastmodifyuserid;
    }

    public void setLastmodifyuserid(String lastmodifyuserid) {
        this.lastmodifyuserid = lastmodifyuserid;
    }

    public Date getMpdifydate() {
        return mpdifydate;
    }

    public void setMpdifydate(Date mpdifydate) {
        this.mpdifydate = mpdifydate;
    }

    public Integer getSubsysid() {
        return subsysid;
    }

    public void setSubsysid(Integer subsysid) {
        this.subsysid = subsysid;
    }

    public String getFavoriteid() {
        return favoriteid;
    }

    public void setFavoriteid(String favoriteid) {
        this.favoriteid = favoriteid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<FG_Role> getRoles() {
        return roles;
    }

    public void setRoles(List<FG_Role> roles) {
        this.roles = roles;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public List<FG_Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<FG_Menu> menus) {
        this.menus = menus;
    }
}
