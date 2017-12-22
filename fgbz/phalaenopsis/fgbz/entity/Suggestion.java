package phalaenopsis.fgbz.entity;

import java.util.Date;
import java.util.List;

/**
 * 留言
 */
public class Suggestion {

    /**
     * 主键ID
     */

    private String id;
    /**
     * 标题
     */

    private String title;
    /**
     * 详细内容
     */

    private String details;
    /**
     * 是否已读
     */

    private Integer isread;
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
     * 状态
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
     * 组织部门名称
     */
    private String orgname;

    /**
     * 发布人
     */
    private String inputname;

    /**
     * 反馈列表
     */
    private List<SuggestionFeedBack> feedBackLists;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getIsread() {
        return isread;
    }

    public void setIsread(Integer isread) {
        this.isread = isread;
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

    public Date getInputdate() {
        return inputdate;
    }

    public void setInputdate(Date inputdate) {
        this.inputdate = inputdate;
    }



    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
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

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getInputname() {
        return inputname;
    }

    public void setInputname(String inputname) {
        this.inputname = inputname;
    }

    public List<SuggestionFeedBack> getFeedBackLists() {
        return feedBackLists;
    }

    public void setFeedBackLists(List<SuggestionFeedBack> feedBackLists) {
        this.feedBackLists = feedBackLists;
    }
}
