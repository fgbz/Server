package phalaenopsis.fgbz.entity;

import java.util.List;

/**
 * Created by 13260 on 2017/12/15.
 */
public class FG_Menu {
    /**
     * 主键
     */
    private String id;
    /**
     * 权限菜单名称
     */
    private String menuname;

    private String tableid;

    private boolean ischeck =false ;
    private String parentid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public boolean isIscheck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }

    public String getTableid() {
        return tableid;
    }

    public void setTableid(String tableid) {
        this.tableid = tableid;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }
}
