package phalaenopsis.allWeather.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.io.Serializable;


/**
 * <p>
 * 外业核查信息表
 * </p>
 *
 * @author dongdongt
 * @since 2017-07-13
 */
@TableName("SW_EXPERT")
public class SwExpert implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 批准面积
     */
	private Double approvalarea;
    /**
     * 批准时间
     */

	private Date approvaltime;
    /**
     * 是否突破城市边界
     */

	private Integer iscityborder;
    /**
     * 是否占用基本农田
     */

	private Integer isjbnt;
    /**
     * 是否未批即供
     */

	private Integer iswpjg;
    /**
     * 是否已供图斑
     */

	private Integer issupply;
    /**
     * 是否违法
     */

	private Integer isillegal;
    /**
     * 是否批准
     */

	private Integer isapprove;
    /**
     * 是否新增建设用地
     */

	private Integer isnewbuild;
    /**
     * 用地性质
     */

	private Integer landuseage;
    /**
     * 建设情况
     */

	private Integer buildstatus;
    /**
     * 占地面积
     */

	private Double landarea;
    /**
     * 用地时间
     */

	private Date landusetime;
    /**
     * 用地单位
     */

	private String landuser;
    /**
     * 项目名称
     */

	private String projectname;
    /**
     * 地块位置
     */

	private String location;

	private Long id;
    /**
     * 现场情况
     */

	private String spotsituation;
    /**
     * 突破城市边界面积
     */

	private Double cityborderarea;
    /**
     * 占用基本农田面积
     */

	private Double jbntarea;
    /**
     * 未批即供面积
     */

	private Double wpjgarea;
    /**
     * 已供面积
     */

	private Double supplyarea;
    /**
     * 批准文号
     */

	private String approvalnumber;
	
	private int count;
	/**
	 * 违法类型
	 */
	private Integer illegaltype;
	/**
	 * 违法面积
	 */
	private Double illegalarea;
	
	private Date timing;
	
	public Integer getIllegaltype() {
		return illegaltype;
	}

	public void setIllegaltype(Integer illegaltype) {
		this.illegaltype = illegaltype;
	}

	public Double getIllegalarea() {
		return illegalarea;
	}

	public void setIllegalarea(Double illegalarea) {
		this.illegalarea = illegalarea;
	}

	public Date getTiming() {
		return timing;
	}

	public void setTiming(Date timing) {
		this.timing = timing;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Double getApprovalarea() {
		return approvalarea;
	}

	public void setApprovalarea(Double approvalarea) {
		this.approvalarea = approvalarea;
	}

	public Date getApprovaltime() {
		return approvaltime;
	}

	public void setApprovaltime(Date approvaltime) {
		this.approvaltime = approvaltime;
	}

	public Integer getIscityborder() {
		return iscityborder;
	}

	public void setIscityborder(Integer iscityborder) {
		this.iscityborder = iscityborder;
	}

	public Integer getIsjbnt() {
		return isjbnt;
	}

	public void setIsjbnt(Integer isjbnt) {
		this.isjbnt = isjbnt;
	}

	public Integer getIswpjg() {
		return iswpjg;
	}

	public void setIswpjg(Integer iswpjg) {
		this.iswpjg = iswpjg;
	}

	public Integer getIssupply() {
		return issupply;
	}

	public void setIssupply(Integer issupply) {
		this.issupply = issupply;
	}

	public Integer getIsillegal() {
		return isillegal;
	}

	public void setIsillegal(Integer isillegal) {
		this.isillegal = isillegal;
	}

	public Integer getIsapprove() {
		return isapprove;
	}

	public void setIsapprove(Integer isapprove) {
		this.isapprove = isapprove;
	}

	public Integer getIsnewbuild() {
		return isnewbuild;
	}

	public void setIsnewbuild(Integer isnewbuild) {
		this.isnewbuild = isnewbuild;
	}

	public Integer getLanduseage() {
		return landuseage;
	}

	public void setLanduseage(Integer landuseage) {
		this.landuseage = landuseage;
	}

	public Integer getBuildstatus() {
		return buildstatus;
	}

	public void setBuildstatus(Integer buildstatus) {
		this.buildstatus = buildstatus;
	}

	public Double getLandarea() {
		return landarea;
	}

	public void setLandarea(Double landarea) {
		this.landarea = landarea;
	}

	public Date getLandusetime() {
		return landusetime;
	}

	public void setLandusetime(Date landusetime) {
		this.landusetime = landusetime;
	}

	public String getLanduser() {
		return landuser;
	}

	public void setLanduser(String landuser) {
		this.landuser = landuser;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSpotsituation() {
		return spotsituation;
	}

	public void setSpotsituation(String spotsituation) {
		this.spotsituation = spotsituation;
	}

	public Double getCityborderarea() {
		return cityborderarea;
	}

	public void setCityborderarea(Double cityborderarea) {
		this.cityborderarea = cityborderarea;
	}

	public Double getJbntarea() {
		return jbntarea;
	}

	public void setJbntarea(Double jbntarea) {
		this.jbntarea = jbntarea;
	}

	public Double getWpjgarea() {
		return wpjgarea;
	}

	public void setWpjgarea(Double wpjgarea) {
		this.wpjgarea = wpjgarea;
	}

	public Double getSupplyarea() {
		return supplyarea;
	}

	public void setSupplyarea(Double supplyarea) {
		this.supplyarea = supplyarea;
	}

	public String getApprovalnumber() {
		return approvalnumber;
	}

	public void setApprovalnumber(String approvalnumber) {
		this.approvalnumber = approvalnumber;
	}

}