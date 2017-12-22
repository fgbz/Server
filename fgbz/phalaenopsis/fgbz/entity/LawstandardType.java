package phalaenopsis.fgbz.entity;

import java.util.Date;
import java.util.List;

public class LawstandardType {

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
     * 类别层级
     */

    private Integer itemlevel;
    /**
     * 层级代码
     */

    private String itemlevelcode;
    /**
     * 下载次数
     */

    private Integer downloadcount;
    /**
     * 打印次数
     */

    private Integer printcount;
    /**
     * 0:法规,1:标准
     */

    private Integer lstype;
    /**
     * 记录状态
     */

    private Integer status;
    /**
     * 子系统ID
     */

    private Integer subsysid;

    /**
     * 是否可以删除
     */
    private boolean candelete;

    private List<LawstandardType> childLists;

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

    public Date getInputdate() {
        return inputdate;
    }

    public void setInputdate(Date inputdate) {
        this.inputdate = inputdate;
    }

    public Integer getItemlevel() {
        return itemlevel;
    }

    public void setItemlevel(Integer itemlevel) {
        this.itemlevel = itemlevel;
    }

    public String getItemlevelcode() {
        return itemlevelcode;
    }

    public void setItemlevelcode(String itemlevelcode) {
        this.itemlevelcode = itemlevelcode;
    }

    public Integer getDownloadcount() {
        return downloadcount;
    }

    public void setDownloadcount(Integer downloadcount) {
        this.downloadcount = downloadcount;
    }

    public Integer getPrintcount() {
        return printcount;
    }

    public void setPrintcount(Integer printcount) {
        this.printcount = printcount;
    }

    public Integer getLstype() {
        return lstype;
    }

    public void setLstype(Integer lstype) {
        this.lstype = lstype;
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


    /**
     * 辅助字段
     */
    private Integer count;
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public boolean isCandelete() {
        return candelete;
    }

    public void setCandelete(boolean candelete) {
        this.candelete = candelete;
    }

    public List<LawstandardType> getChildLists() {
        return childLists;
    }

    public void setChildLists(List<LawstandardType> childLists) {
        this.childLists = childLists;
    }
}
