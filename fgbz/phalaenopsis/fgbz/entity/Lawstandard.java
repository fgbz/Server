package phalaenopsis.fgbz.entity;

import java.util.Date;
import java.util.List;

public class Lawstandard {
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
     * 0:受控，1:不受控
     */

    private Integer iscontrol;
    /**
     * 发布日期
     */

    private Date releasedate;
    /**
     * 实施日期
     */

    private Date impdate;
    /**
     * 关键字
     */

    private String keywords;
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
     * 点击次数
     */

    private Integer clickcount;
    /**
     * 下载次数
     */

    private Integer downloadcount;
    /**
     * 打印次数
     */

    private Integer printcount;
    /**
     * 0:固顶标识,1:热点标识,2:推荐标识
     */

    private Integer inforeleasename;
    /**
     * 备注
     */

    private String memo;
    /**
     * 摘要信息，最大2000个汉字
     */

    private String summaryinfo;
    /**
     * 子系统ID
     */

    private Integer subsysid;
    /**
     * 状态：具体参加状态表
     */

    private String statusname;
    /**
     * 记录状态
     */

    private String status;

    private String lawtype;
    /**
     * 批准状态：
     1、草稿
     2、已提交待审核
     3、已发布
     */

    private Integer approvestatus;

    private String extend1;

    private String extend2;

    private String extend3;

    private String lsstatusname;
    private Integer sortno;

    private String typename;
    private Integer istop;

    private String organization;

    private String pubdepname;

    private List<String> fileids;

    public String getPubdepname() {
        return pubdepname;
    }

    public void setPubdepname(String pubdepname) {
        this.pubdepname = pubdepname;
    }

    private List<Lawstandard> refence;

    public List<Lawstandard> getRefence() {
        return refence;
    }

    public void setRefence(List<Lawstandard> refence) {
        this.refence = refence;
    }

    public List<Lawstandard> getReplace() {
        return replace;
    }

    public void setReplace(List<Lawstandard> replace) {
        this.replace = replace;
    }

    private List<Lawstandard> replace;

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public Integer getIstop() {
        return istop;
    }

    public void setIstop(Integer istop) {
        this.istop = istop;
    }

    private int count;

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

    public Integer getIscontrol() {
        return iscontrol;
    }

    public void setIscontrol(Integer iscontrol) {
        this.iscontrol = iscontrol;
    }

    public Date getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(Date releasedate) {
        this.releasedate = releasedate;
    }

    public Date getImpdate() {
        return impdate;
    }

    public void setImpdate(Date impdate) {
        this.impdate = impdate;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
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

    public Integer getClickcount() {
        return clickcount;
    }

    public void setClickcount(Integer clickcount) {
        this.clickcount = clickcount;
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

    public Integer getInforeleasename() {
        return inforeleasename;
    }

    public void setInforeleasename(Integer inforeleasename) {
        this.inforeleasename = inforeleasename;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getSummaryinfo() {
        return summaryinfo;
    }

    public void setSummaryinfo(String summaryinfo) {
        this.summaryinfo = summaryinfo;
    }

    public Integer getSubsysid() {
        return subsysid;
    }

    public void setSubsysid(Integer subsysid) {
        this.subsysid = subsysid;
    }

    public String getStatusname() {
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getApprovestatus() {
        return approvestatus;
    }

    public void setApprovestatus(Integer approvestatus) {
        this.approvestatus = approvestatus;
    }

    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1;
    }

    public String getExtend2() {
        return extend2;
    }

    public void setExtend2(String extend2) {
        this.extend2 = extend2;
    }

    public String getExtend3() {
        return extend3;
    }

    public void setExtend3(String extend3) {
        this.extend3 = extend3;
    }

    public String getLsstatusname() {
        return lsstatusname;
    }

    public void setLsstatusname(String lsstatusname) {
        this.lsstatusname = lsstatusname;
    }

    public Integer getSortno() {
        return sortno;
    }

    public void setSortno(Integer sortno) {
        this.sortno = sortno;
    }

    public String getLawtype() {
        return lawtype;
    }

    public void setLawtype(String lawtype) {
        this.lawtype = lawtype;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public List<String> getFileids() {
        return fileids;
    }

    public void setFileids(List<String> fileids) {
        this.fileids = fileids;
    }
}