package phalaenopsis.fgbz.entity;

import java.util.Date;
import java.util.List;

public class FG_Organization {

    /**
     * 组织主键
     */

    private String id;
    /**
     * 组织名称
     */

    private String orgname;
    /**
     * 上级组织ID
     */

    private String parentid;

    private String inputuserid;

    private Date inputdate;
    /**
     * 修改人ID
     */

    private String modifyuserid;
    /**
     * 修改人时间
     */

    private Date modifydate;

    private Integer status;
    /**
     * 备注
     */

    private String memo;

    private Integer subsysid;

    /**
     * 辅助字段
     */
    private int count;

    private boolean candelete;

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

    private List<FG_Organization> childsorg;
    /**
     * 点击按钮的对象，处理层级结构
     */
    private FG_Organization handleitem;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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

    public boolean isCandelete() {
        return candelete;
    }

    public void setCandelete(boolean candelete) {
        this.candelete = candelete;
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

    public FG_Organization getHandleitem() {
        return handleitem;
    }

    public void setHandleitem(FG_Organization handleitem) {
        this.handleitem = handleitem;
    }

    public List<FG_Organization> getChildsorg() {
        return childsorg;
    }

    public void setChildsorg(List<FG_Organization> childsorg) {
        this.childsorg = childsorg;
    }
}
