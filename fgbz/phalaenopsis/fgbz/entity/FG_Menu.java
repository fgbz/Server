package phalaenopsis.fgbz.entity;

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

    private boolean ischeck =false ;

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
}