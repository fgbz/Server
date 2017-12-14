package phalaenopsis.fgbz.entity;

import java.util.Date;

public class Technical {
    /**
     * 主键ID
     */

    private String id;
    /**
     * 法规标准编号
     */

    private String code;
    /**
     * 中文名称
     */

    private String chinesename;
    /**
     * 英文名称
     */

    private String englishname;
    /**
     * 发布日期
     */

    private Date releasedate;
    /**
     * 关键字
     */

    private String keywords;
    /**
     * 摘要信息，最大2000个汉字
     */

    private String summaryinfo;
    /**
     * 是否经过批量上传
     */

    private Integer isbatch;
    /**
     * 录入人ID
     */

    private Integer inputuserid;
    /**
     * 录入时间
     */

    private Date inputdate;
    /**
     * 修改人ID
     */

    private Integer modifyuserid;
    /**
     * 修改人时间
     */

    private Date modifydate;
    /**
     * 备注
     */

    private String memo;
    /**
     * 子系统ID
     */

    private String subsysid;
    /**
     * 记录状态
     */

    private String status;

    private String count;

    private String typename;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getChinesename() {
        return chinesename;
    }

    public void setChinesename(String chinesename) {
        this.chinesename = chinesename;
    }

    public String getEnglishname() {
        return englishname;
    }

    public void setEnglishname(String englishname) {
        this.englishname = englishname;
    }

    public Date getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(Date releasedate) {
        this.releasedate = releasedate;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getSummaryinfo() {
        return summaryinfo;
    }

    public void setSummaryinfo(String summaryinfo) {
        this.summaryinfo = summaryinfo;
    }

    public Integer getIsbatch() {
        return isbatch;
    }

    public void setIsbatch(Integer isbatch) {
        this.isbatch = isbatch;
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

    public Integer getModifyuserid() {
        return modifyuserid;
    }

    public void setModifyuserid(Integer modifyuserid) {
        this.modifyuserid = modifyuserid;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getSubsysid() {
        return subsysid;
    }

    public void setSubsysid(String subsysid) {
        this.subsysid = subsysid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }
}
