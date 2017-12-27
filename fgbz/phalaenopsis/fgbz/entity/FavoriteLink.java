package phalaenopsis.fgbz.entity;

import java.util.Date;

public class FavoriteLink {

    /**
     * 法规或技术文件id
     */
    private String id;

    /**
     * 收藏夹id
     */
    private String favid;

    /**
     * 收藏夹名称
     */
    private  String favname;
    /**
     * 标题
     */
    private String title;
    /**
     * 类型
     */
    private  int type;

    /**
     * 收藏时间
     */
    private Date favoritetime;

    /**
     * 点击次数
     */
    private int clickcount;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFavname() {
        return favname;
    }

    public void setFavname(String favname) {
        this.favname = favname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getFavoritetime() {
        return favoritetime;
    }

    public void setFavoritetime(Date favoritetime) {
        this.favoritetime = favoritetime;
    }

    public String getFavid() {
        return favid;
    }

    public void setFavid(String favid) {
        this.favid = favid;
    }

    public int getClickcount() {
        return clickcount;
    }

    public void setClickcount(int clickcount) {
        this.clickcount = clickcount;
    }
}
