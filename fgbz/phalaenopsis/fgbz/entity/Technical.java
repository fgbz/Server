package phalaenopsis.fgbz.entity;

import phalaenopsis.common.annotation.ExcelTitle;

import java.util.Date;
import java.util.List;

public class Technical {
    /**
     * 主键ID
     */

    private String id;
    /**
     * 技术文档编号
     */

    @ExcelTitle(value = "技术文档编号")
    private String code;
    /**
     * 中文名称
     */
    @ExcelTitle(value = "中文名称")
    private String chinesename;
    /**
     * 英文名称
     */
    @ExcelTitle(value = "英文名称")
    private String englishname;
    /**
     * 发布日期
     */
    @ExcelTitle(value = "发布日期")
    private Date releasedate;
    /**
     * 关键字
     */
    @ExcelTitle(value = "关键字")
    private String keywords;
    /**
     * 摘要信息，最大2000个汉字
     */
    @ExcelTitle(value = "摘要")
    private String summaryinfo;
    /**
     * 是否经过批量上传
     */

    private Integer isbatch;
    /**
     * 录入人ID
     */

    private String inputuserid;
    /**
     * 录入时间
     */

    private Date inputdate;

    private  String inputorgname;
    /**
     * 录入人姓名
     */
    private  String inputusername;
    /**
     * 修改人ID
     */

    private String modifyuserid;
    /**
     * 修改人时间
     */

    private Date modifydate;
    /**
     * 备注
     */
    @ExcelTitle(value = "备注")
    private String memo;
    /**
     * 子系统ID
     */

    private String subsysid;
    /**
     * 记录状态
     */

    private String status;

    private int count;

    @ExcelTitle(value = "类别")
    private String typename;

    private String tectype;

    private int approvestatus;

    private List<String> fileids;

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

    public String getTectype() {
        return tectype;
    }

    public void setTectype(String tectype) {
        this.tectype = tectype;
    }

    public List<String> getFileids() {
        return fileids;
    }

    public void setFileids(List<String> fileids) {
        this.fileids = fileids;
    }

    public int getApprovestatus() {
        return approvestatus;
    }

    public void setApprovestatus(int approvestatus) {
        this.approvestatus = approvestatus;
    }

    public String getInputuserid() {
        return inputuserid;
    }

    public void setInputuserid(String inputuserid) {
        this.inputuserid = inputuserid;
    }

    public String getInputorgname() {
        return inputorgname;
    }

    public void setInputorgname(String inputorgname) {
        this.inputorgname = inputorgname;
    }

    public String getInputusername() {
        return inputusername;
    }

    public void setInputusername(String inputusername) {
        this.inputusername = inputusername;
    }

    public String getModifyuserid() {
        return modifyuserid;
    }

    public void setModifyuserid(String modifyuserid) {
        this.modifyuserid = modifyuserid;
    }
}
