package phalaenopsis.fgbz.entity;

import java.util.Date;

public class Favorite {

    /**
     * 主键id
     */

    private String id;
    /**
     * 搜藏夹名称
     */

    private String name;
    /**
     * 上级编号
     */

    private String parentid;
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
     * 记录状态
     */

    private Integer status;
    /**
     * 子系统ID
     */

    private Integer subsysid;

    /**
     * 辅助字段
     */
    private int count;

    /**
     * 关联表id
     */
    private String tableid;

    /**
     * 类别层级
     */

    private Integer itemlevel;
    /**
     * 层级代码
     */

    private Integer itemlevelcode;

    /**
     *  处理类型  新增同级 ‘addEqual’ 新增下级 'addDown' '上移' 'moveUp' '下移' 'moveDown'
     */
    private String handletype;

    /**
     * 点击按钮的对象，处理层级结构
     */
    private Favorite handleitem;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public Integer getItemlevel() {
        return itemlevel;
    }

    public void setItemlevel(Integer itemlevel) {
        this.itemlevel = itemlevel;
    }

    public Integer getItemlevelcode() {
        return itemlevelcode;
    }

    public void setItemlevelcode(Integer itemlevelcode) {
        this.itemlevelcode = itemlevelcode;
    }

    public String getHandletype() {
        return handletype;
    }

    public void setHandletype(String handletype) {
        this.handletype = handletype;
    }

    public Favorite getHandleitem() {
        return handleitem;
    }

    public void setHandleitem(Favorite handleitem) {
        this.handleitem = handleitem;
    }
}
