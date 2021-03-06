package phalaenopsis.fgbz.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by 13260 on 2017/12/15.
 */
public class FG_Role {
    /**
     * 角色主键ID
     */

    private String id;
    /**
     * 角色名称
     */

    private String rolename;
    /**
     * 录入人ID
     */

    private String inputuserid;
    /**
     * 录入时间
     */

    private Date inputdate;

    /**
     * 修改人id
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

    private Integer subsysid;

    /**
     * 角色对应的菜单
     */
    private List<FG_Menu> menus;
    /**
     * 辅助字段
     */
    private int count;

    private String tableid;
    /**
     * 是否能删除
     */
    private boolean candelete;

    private boolean ischeck=false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
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

    public List<FG_Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<FG_Menu> menus) {
        this.menus = menus;
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

    public boolean isIscheck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
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

    public String getTableid() {
        return tableid;
    }

    public void setTableid(String tableid) {
        this.tableid = tableid;
    }
}
