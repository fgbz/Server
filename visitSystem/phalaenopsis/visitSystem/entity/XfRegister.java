package phalaenopsis.visitSystem.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * 
 * @author dongdongt
 *
 */
@TableName("XF_REGISTER")
public class XfRegister implements Serializable {
    private static final long serialVersionUID = 1L;
    

    /**
     * 登记ID
     */

	private Long registerid;
    /**
     * 信访人住址
     */

	private String homeaddress;
    /**
     * 信访人性别 0-女  1-男
     */

	private Integer gender;
    /**
     * 信访人年龄
     */

	private Integer age;
    /**
     * 信访人电话号码
     */
    
	private String phoneno;
    /**
     * 信访人身份证号码
     */

	private String identityno;
    /**
     * 是否 重要信访人 0-不是 ，1 -是
     */

	private Integer vipornot;
    /**
     * 信访人数
     */

	private Integer xfpeoplecount;
    /**
     * 信访编号
     */
	
	private String serialno;
    /**
     * 问题描述
     */

	private String issuedesc;
    /**
     * 信访方式
     */

	private String xftype;
    /**
     * 问题具体属地
     */

	private String issueaddress;
    /**
     * 经办人姓名
     */

	private String resposibleusername;
    /**
     * 经办人ID
     */

	private String responsibleuserid;
    /**
     * 3-省 ; 2-市  ;1-区县
     */

	private Integer arealevel;
    /**
     * 登记日期
     */

	private Date registerdate;
    /**
     * 0---保存状态；1--登记状态 ；2--待审核 ;  3 --办结 ；4--回告办结
     */

	private Integer status;
    /**
     * 信访人姓名
     */

	private String xfname;
    /**
     * 问题性质
     */

	private String issuenature;
	
    /**
     *  0-不诉讼 1-诉讼
     */

	private Integer litiornot;
    /**
     * 问题属地  省ID 默认“山东”
     */

	private Long provinceid;
    /**
     * 问题属地  市ID 
     */

	private Long cityid;
    /**
     * 问题属地  区县ID 
     */

	private Long countyid;
    /**
     * 登记级别 3-省 ; 2-市  ;1-区县
     */

	private Integer reglevel;
    /**
     * 办理级别 3-省 ; 2-市  ;1-区县
     */

	private Integer traslevel;
    /**
     * 办理方式   2--不予以受理 ； 3--不再受理；4--转办； 5--交办  ；6--办结; 
     */

	private Integer propattern;
    /**
     * 办理方式   2--不予以受理 ； 3--不再受理；4--转办； 5--交办  ；6--办结; 
     */

	private Integer citypattern;
    /**
     * 办理方式   2--不予以受理 ； 3--不再受理；4--转办； 5--交办  ；6--办结; 
     */

	private Integer countypattern;
    /**
     * 办理单位
     */

	private String transunit;
	/**
	 * 登记人区域ID
	 */
	private Long responsregionid;
	
	/**
	 * 省级状态 0---保存状态；1--登记状态 ；2--待审核 ;  3 --办结 ；4--回告办结；5--已交办
	 */
	private Integer prostatus;
	/**
	 * 市级状态 0---保存状态；1--登记状态 ；2--待审核 ;  3 --办结 ；4--回告办结；5--已交办
	 */
	private Integer citystatus;
	/**
	 * 上访次数
	 */
	private Integer accessnum;
	
	/**
	 * 信访方式名称
	 */
	private String xftypename;
	/**
	 * 截止时间(算预警状态用)
	 */
	private Date expirdate;
	private String issuenatureName;//问题性质名称



	/**
	 * 进度

	 */
	private String process;
	
	public String getIssuenatureName() {
		return issuenatureName;
	}
	public void setIssuenatureName(String issuenatureName) {
		this.issuenatureName = issuenatureName;
	}
	public Date getExpirdate() {
		return expirdate;
	}
	public void setExpirdate(Date expirdate) {
		this.expirdate = expirdate;
	}
	public String getXftypename() {
		return xftypename;
	}
	public void setXftypename(String xftypename) {
		this.xftypename = xftypename;
	}

	/***********辅助字段*************/
	private int count;//记录总条数
	/**
	 * 0.保存，1.提交
	 */
	private int isSave;
	/**
	 * 预警状态(辅助字段)(1.已超期，2.快超期)
	 */
	private Integer warnstaus;
	
	public Integer getWarnstaus() {
		return warnstaus;
	}
	public void setWarnstaus(Integer warnstaus) {
		this.warnstaus = warnstaus;
	}
	public int getIsSave() {
		return isSave;
	}
	public void setIsSave(int isSave) {
		this.isSave = isSave;
	}

	public Integer getAccessnum() {
		return accessnum;
	}

	public void setAccessnum(Integer accessnum) {
		this.accessnum = accessnum;
	}

	public Integer getProstatus() {
		return prostatus;
	}

	public void setProstatus(Integer prostatus) {
		this.prostatus = prostatus;
	}

	public Integer getCitystatus() {
		return citystatus;
	}

	public void setCitystatus(Integer citystatus) {
		this.citystatus = citystatus;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Long getResponsregionid() {
		return responsregionid;
	}

	public void setResponsregionid(Long responsregionid) {
		this.responsregionid = responsregionid;
	}

	public Long getRegisterid() {
		return registerid;
	}

	public void setRegisterid(Long registerid) {
		this.registerid = registerid;
	}

	public String getHomeaddress() {
		return homeaddress;
	}

	public void setHomeaddress(String homeaddress) {
		this.homeaddress = homeaddress;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getIdentityno() {
		return identityno;
	}

	public void setIdentityno(String identityno) {
		this.identityno = identityno;
	}

	public Integer getVipornot() {
		return vipornot;
	}

	public void setVipornot(Integer vipornot) {
		this.vipornot = vipornot;
	}

	public Integer getXfpeoplecount() {
		return xfpeoplecount;
	}

	public void setXfpeoplecount(Integer xfpeoplecount) {
		this.xfpeoplecount = xfpeoplecount;
	}

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getIssuedesc() {
		return issuedesc;
	}

	public void setIssuedesc(String issuedesc) {
		this.issuedesc = issuedesc;
	}

	public String getXftype() {
		return xftype;
	}

	public void setXftype(String xftype) {
		this.xftype = xftype;
	}

	public String getIssueaddress() {
		return issueaddress;
	}

	public void setIssueaddress(String issueaddress) {
		this.issueaddress = issueaddress;
	}

	public String getResposibleusername() {
		return resposibleusername;
	}

	public void setResposibleusername(String resposibleusername) {
		this.resposibleusername = resposibleusername;
	}

	public String getResponsibleuserid() {
		return responsibleuserid;
	}

	public void setResponsibleuserid(String responsibleuserid) {
		this.responsibleuserid = responsibleuserid;
	}

	public Integer getArealevel() {
		return arealevel;
	}

	public void setArealevel(Integer arealevel) {
		this.arealevel = arealevel;
	}

	public Date getRegisterdate() {
		return registerdate;
	}

	public void setRegisterdate(Date registerdate) {
		this.registerdate = registerdate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getXfname() {
		return xfname;
	}

	public void setXfname(String xfname) {
		this.xfname = xfname;
	}

	public String getIssuenature() {
		return issuenature;
	}

	public void setIssuenature(String issuenature) {
		this.issuenature = issuenature;
	}

	public Integer getLitiornot() {
		return litiornot;
	}

	public void setLitiornot(Integer litiornot) {
		this.litiornot = litiornot;
	}

	public Long getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(Long provinceid) {
		this.provinceid = provinceid;
	}

	public Long getCityid() {
		return cityid;
	}

	public void setCityid(Long cityid) {
		this.cityid = cityid;
	}

	public Long getCountyid() {
		return countyid;
	}

	public void setCountyid(Long countyid) {
		this.countyid = countyid;
	}

	public Integer getReglevel() {
		return reglevel;
	}

	public void setReglevel(Integer reglevel) {
		this.reglevel = reglevel;
	}

	public Integer getTraslevel() {
		return traslevel;
	}

	public void setTraslevel(Integer traslevel) {
		this.traslevel = traslevel;
	}

	public Integer getPropattern() {
		return propattern;
	}

	public void setPropattern(Integer propattern) {
		this.propattern = propattern;
	}

	public Integer getCitypattern() {
		return citypattern;
	}

	public void setCitypattern(Integer citypattern) {
		this.citypattern = citypattern;
	}

	public Integer getCountypattern() {
		return countypattern;
	}

	public void setCountypattern(Integer countypattern) {
		this.countypattern = countypattern;
	}

	public String getTransunit() {
		return transunit;
	}

	public void setTransunit(String transunit) {
		this.transunit = transunit;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}
}