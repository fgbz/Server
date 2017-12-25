package phalaenopsis.fgbz.entity;

import java.util.Date;
import java.util.List;

public class TechnicalType {
    /**
     * 主键id
     */

    private String id;
    /**
     * 类别名称
     */

    private String typename;
    /**
     * 上级类别编号
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
     * 修改人时间
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
     * 类别层级
     */

    private Integer itemlevel;
    /**
     * 层级代码
     */

    private Integer itemlevelcode;

    /**
     * 技术文档id
     */
    private String tectype;

    /**
     *  处理类型  新增同级 ‘addEqual’ 新增下级 'addDown' '上移' 'moveUp' '下移' 'moveDown'
     */
    private String handletype;
    /**
     * 是否可以删除
     */
    private boolean candelete;

    private int count;

    private List<TechnicalType> childLists;

    /**
     * 点击按钮的对象，处理层级结构
     */
    private TechnicalType handleitem;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
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

    public String getTectype() {
        return tectype;
    }

    public void setTectype(String tectype) {
        this.tectype = tectype;
    }

    public boolean isCandelete() {
        return candelete;
    }

    public void setCandelete(boolean candelete) {
        this.candelete = candelete;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getInputuserid() {
        return inputuserid;
    }

    public void setInputuserid(String inputuserid) {
        this.inputuserid = inputuserid;
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

    public List<TechnicalType> getChildLists() {
        return childLists;
    }

    public void setChildLists(List<TechnicalType> childLists) {
        this.childLists = childLists;
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

    public TechnicalType getHandleitem() {
        return handleitem;
    }

    public void setHandleitem(TechnicalType handleitem) {
        this.handleitem = handleitem;
    }
}
