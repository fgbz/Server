package phalaenopsis.fgbz.entity;

import java.util.Date;

/**
 * Created by 13260 on 2018/1/24.
 */
public class Slor {
    private String id;
    private String solrtext;

    private String chinesename;
    private Date releasedate;
    private String keywords;
    private String summaryinfo ;
    private String clickcount;
    private String lawtype;
    private Date modifydate;

    private String code;
    private String englishname;
    private String lawstatus;

    public String getChinesename() {
        return chinesename;
    }

    public void setChinesename(String chinesename) {
        this.chinesename = chinesename;
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

    public String getClickcount() {
        return clickcount;
    }

    public void setClickcount(String clickcount) {
        this.clickcount = clickcount;
    }

    public String getLawtype() {
        return lawtype;
    }

    public void setLawtype(String lawtype) {
        this.lawtype = lawtype;
    }

    public String getId() {
        return id;
    }

    public Date getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(Date releasedate) {
        this.releasedate = releasedate;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSolrtext() {
        return solrtext;
    }

    public void setSolrtext(String solrtext) {
        this.solrtext = solrtext;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEnglishname() {
        return englishname;
    }

    public void setEnglishname(String englishname) {
        this.englishname = englishname;
    }

    public String getLawstatus() {
        return lawstatus;
    }

    public void setLawstatus(String lawstatus) {
        this.lawstatus = lawstatus;
    }
}
