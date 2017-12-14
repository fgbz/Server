package phalaenopsis.allWeather.entity;

import java.io.Serializable;
import java.util.Date;



import com.baomidou.mybatisplus.annotations.TableName;

import com.fasterxml.jackson.annotation.JsonIgnore;
import phalaenopsis.common.annotation.ExcelTitle;
import phalaenopsis.satellitegraph.entity.IMapSpot;
import phalaenopsis.satellitegraph.entity.Polygon;

/**
 * <p>
 * 监测图斑表
 * </p>
 *
 * @author dongdongt
 * @since 2017-07-13
 */
@TableName("SW_MAPSPOT")
public class SwMapspot extends SwAuditspot implements IMapSpot, Serializable {


    private static final long serialVersionUID = 1L;

    /**
     * 突破城市边界面积
     */

    private Double cityborderarea;
    /**
     * 疑似违法面积
     */

    private Double doubtillegalarea;
    /**
     * 面积等级
     */

    private String arealevel;
    /**
     * 监测面积
     */
    @ExcelTitle(value = "监测面积")
    private Double spotarea;
    /**
     * Y坐标
     */

    private Double coordinateY;
    /**
     * X坐标
     */

    private Double coordinateX;
    /**
     * 后时相
     */
    @ExcelTitle(value = "后时相")
    private String aftertime;
    /**
     * 前时相
     */
    @ExcelTitle(value = "前时相")
    private String beforetime;
    /**
     * 数据源
     */

    private String datasource;
    /**
     * TZ
     */

    private String tz;
    /**
     * 图斑类型
     */
    @ExcelTitle(value = "图斑类型")
    private String spottype;
    /**
     * 监测编号
     */
    @ExcelTitle(value = "监测编号")
    private String spotnumber;
    /**
     * 外业核查信息表外键id
     */

    private Long expertid;
    /**
     * 核查所在期数
     */

    private Integer checkperiod;
    /**
     * 原始下发所在期数
     */

    private Integer period;
    /**
     * 年份
     */

    private Integer year;
    /**
     * 最后更新时间
     */

    private Date lastupdate;
    /**
     * 非新增_备注
     */
    @ExcelTitle(value = "备注")
    private String notnewRemarks;
    /**
     * 非新增_图斑分类 NotNew
     */
    @ExcelTitle(value = "图斑分类")
    private Integer notnewSpotclassification;
    /**
     * 其他类型_批准机关名称
     */
    @ExcelTitle(value = "批准机关名称")
    private String otherApprovalname;
    /**
     * 其他类型_备注
     */
    @ExcelTitle(value = "备注")
    private String otherRemarks;
    /**
     * 其他类型_批准面积
     */
    @ExcelTitle(value = "批准面积")
    private Double otherApprovalarea;
    /**
     * 其他类型_批准时间
     */
    @ExcelTitle(value = "批准时间")
    private Date otherApprovaltime;
    /**
     * 其他类型_批准文号
     */
    @ExcelTitle(value = "批准文号")
    private String otherApprovalnumber;
    /**
     * 其他类型_批准机关级别
     */
    @ExcelTitle(value = "批准机关级别")
    private String otherApprovallevel;
    /**
     * 其他类型_项目名称
     */
    @ExcelTitle(value = "项目名称")
    private String otherProjectname;
    /**
     * 其他类型_图斑类型     Other
     */
    @ExcelTitle(value = "类别")
    private Double otherSpottype;
    /**
     * 增减挂钩_备注
     */
    @ExcelTitle(value = "备注")
    private String zjgRemarks;
    /**
     * 增减挂钩_建新地块面积
     */
    @ExcelTitle(value = "新建地面积")
    private Double zjgNewblockarea;
    /**
     * 增减挂钩_建新地块名称
     */
    @ExcelTitle(value = "新建地块名称")
    private String zjgNewblockname;
    /**
     * 增减挂钩_项目编号
     */
    @ExcelTitle(value = "项目编号")
    private String zjgProjectnumber;
    /**
     * 增减挂钩_项目名称
     */
    @ExcelTitle(value = "项目名称")
    private String zjgProjectname;
    /**
     * 增减挂钩_是否套合成功 ZJG
     */

    private Double zjgIsnestedsuccess;
    /**
     * 合法图斑举证类型
     */
    @ExcelTitle(value = "图斑分类")
    private Integer legalprooftype;
    /**
     * 图斑举证类型
     */

    private Integer node;
    /**
     * 流程ID
     */

    private String instanceid;

    private String prespotnumber;
    /**
     * 省级区划代码
     */

    private Integer province;
    /**
     * 地市级区划代码
     */

    private Integer city;
    /**
     * 区县名称（管理区）
     */
    @ExcelTitle(value = "管理区名称")
    private String countyname;
    /**
     * 区县级区划代码（管理区）
     */
    @ExcelTitle(value = "管理区代码")
    private Integer county;
    /**
     * 区县名称（行政区）
     */
    @ExcelTitle("行政区名称")
    private String districtcountyname;

    /**
     * 区县级代码（行政区）
     */
    @ExcelTitle("行政区代码")
    private Integer districtcounty;
    // /**
    // * 主键
    // */
    //
    // private Integer id;
    /**
     * 底图分辨率，数值1表示16米的，数字2表示2-5米的
     */
    @ExcelTitle(value = "底图分辨率")
    private Integer mapratio;

    private String bzX;
    /**
     * 图斑性质
     */

    private String spotnature;
    /**
     * 备注
     */
    @ExcelTitle(value = "备注")
    private String remark;

    /**
     * 占用保护区未通过用地审批面积
     */

    private Double protectfailarea;
    /**
     * 占用保护区且通过用地审批面积
     */

    private Double protectpassarea;
    /**
     * 保护区名称
     */

    private String protectname;
    /**
     * 占用新划定城市周边永久基本农田面积
     */

    private Double xhdjbntarea;
    /**
     * 占用基本农田总面积（占用新旧基本农田之和）
     */

    private Double jbntarea;

    /**
     * 举证实体
     */
    private SwExpert swExpert;

    /**
     * 耕地面积
     */
    @ExcelTitle(value = "占用耕地面积")
    private double arableAcreage;

    /**
     * 同步更新时间
     */
    private Date timing;
    /**
     * 设施农用地_用地协议备案编号
     */
    @ExcelTitle(value = "用地协议备案编号")
    private String ssnydBackupnumber;
    /**
     * 设施农用地_批准文号
     */
    @ExcelTitle(value = "批准文号")
    private String ssnydApprovalnumber;
    /**
     * 设施农用地实际用途
     */
    @ExcelTitle(value = "实际用途")
    private String ssnydUsetype;
    /**
     * 设施农用地批准用途
     */
    @ExcelTitle(value = "批准用途")
    private String ssnydApprovalusetype;
    /**
     * 设施农用地面积
     */
    @ExcelTitle(value = "批准面积")
    private Double ssnydApprovalarea;

    /**
     * 设施农用地项目名称
     */
    @ExcelTitle(value = "项目名称")
    private String ssnydProjectName;

    /**
     * 设施用地批准时间
     */
    @ExcelTitle(value = "批准时间")
    private Date ssnydApprovaltime;
    /**
     * 农村道路_硬化情况（是 否）
     */
    @ExcelTitle(value = "硬化情况")
    private Integer villageIsharden;


    /**
     * 农村道路_道路宽度
     */
    @ExcelTitle(value = "道路宽度（米）")
    private Double villageRoadwidth;
    /**
     * 油田用地_项目名称
     */
    @ExcelTitle(value = "项目名称")
    private String oilProjectname;
    /**
     * 油田用地_下达时间
     */
    @ExcelTitle(value = "下达时间")
    private Date oilIssuedtime;
    /**
     * 油田用地_下达文号   Oil
     */
    @ExcelTitle(value = "下达文号")
    private String oilIssuednumber;
    /**
     * 可调整面积
     */
    @ExcelTitle(value = "可调整面积")
    private Double adjustablearea;
    /**
     * 临时用地_批准文号
     */
    @ExcelTitle(value = "批准文号")
    private String tempApprovalnumber;
    /**
     * 临时用地_具体项目用途
     */

    private String tempProjectusetype;
    /**
     * 临时用地_实际用途
     */
    @ExcelTitle(value = "实际用途")
    private String tempUsetype;
    /**
     * 临时用地_面积
     */
    @ExcelTitle(value = "批准面积")
    private Double tempArea;

    /**
     * 临时用地_批准用途
     */
    @ExcelTitle(value = "批准用途")
    private String tempApprovalusetype;

    /**
     * 临时用地项目名称
     */
    @ExcelTitle(value = "项目名称")
    private String tempProjectName;

    /**
     * 上传的shp、txt相对于服务端的路径
     */
    private String[] fileServerPaths;


    public String getTempApprovalusetype() {
        return tempApprovalusetype;
    }

    public void setTempApprovalusetype(String tempApprovalusetype) {
        this.tempApprovalusetype = tempApprovalusetype;
    }

    /**
     * 临时用地批准时间
     */
    @ExcelTitle(value = "批准时间")
    private Date tempApprovaltime;
    /**
     * 查询辅助字段 判断登录用户是区县 还是省市
     */
    private Integer organizationGrade;

    @ExcelTitle(value = "市级名称")
    private String cityName;//市级名称

    /**
     * 标记。部级关注 1001，省级关注 1002，自然保护区 1003， 其他 1004
     */
    private String mark;
    
    /**
     * 图斑所在级别1市2省
     */
    private String mapspotLevel;

//    /**
//     * mark的别名
//     */
//    private String markAlias;

    /**
     * 标注类型别名
     */
    @ExcelTitle("图斑标记")
    private String tempRemark;

    /**
     * 不符合规范面积
     */
    @ExcelTitle("不符合规划面积")
    private double unPlaningArea;



    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getSsnydUsetype() {
        return ssnydUsetype;
    }

    public void setSsnydUsetype(String ssnydUsetype) {
        this.ssnydUsetype = ssnydUsetype;
    }

    public String getSsnydApprovalusetype() {
        return ssnydApprovalusetype;
    }

    public void setSsnydApprovalusetype(String ssnydApprovalusetype) {
        this.ssnydApprovalusetype = ssnydApprovalusetype;
    }

    public Double getSsnydApprovalarea() {
        return ssnydApprovalarea;
    }

    public void setSsnydApprovalarea(Double ssnydApprovalarea) {
        this.ssnydApprovalarea = ssnydApprovalarea;
    }

    public Date getTempApprovaltime() {
        return tempApprovaltime;
    }

    public void setTempApprovaltime(Date tempApprovaltime) {
        this.tempApprovaltime = tempApprovaltime;
    }

    public Date getSsnydApprovaltime() {
        return ssnydApprovaltime;
    }

    public void setSsnydApprovaltime(Date ssnydApprovaltime) {
        this.ssnydApprovaltime = ssnydApprovaltime;
    }

    public String getSsnydBackupnumber() {
        return ssnydBackupnumber;
    }

    public void setSsnydBackupnumber(String ssnydBackupnumber) {
        this.ssnydBackupnumber = ssnydBackupnumber;
    }

    public String getSsnydApprovalnumber() {
        return ssnydApprovalnumber;
    }

    public void setSsnydApprovalnumber(String ssnydApprovalnumber) {
        this.ssnydApprovalnumber = ssnydApprovalnumber;
    }

    public Integer getVillageIsharden() {
        return villageIsharden;
    }

    public void setVillageIsharden(Integer villageIsharden) {
        this.villageIsharden = villageIsharden;
    }

    public Double getVillageRoadwidth() {
        return villageRoadwidth;
    }

    public void setVillageRoadwidth(Double villageRoadwidth) {
        this.villageRoadwidth = villageRoadwidth;
    }

    public String getOilProjectname() {
        return oilProjectname;
    }

    public void setOilProjectname(String oilProjectname) {
        this.oilProjectname = oilProjectname;
    }

    public Date getOilIssuedtime() {
        return oilIssuedtime;
    }

    public void setOilIssuedtime(Date oilIssuedtime) {
        this.oilIssuedtime = oilIssuedtime;
    }

    public String getOilIssuednumber() {
        return oilIssuednumber;
    }

    public void setOilIssuednumber(String oilIssuednumber) {
        this.oilIssuednumber = oilIssuednumber;
    }

    public Double getAdjustablearea() {
        return adjustablearea;
    }

    public void setAdjustablearea(Double adjustablearea) {
        this.adjustablearea = adjustablearea;
    }

    public String getTempApprovalnumber() {
        return tempApprovalnumber;
    }

    public void setTempApprovalnumber(String tempApprovalnumber) {
        this.tempApprovalnumber = tempApprovalnumber;
    }

    public String getTempProjectusetype() {
        return tempProjectusetype;
    }

    public void setTempProjectusetype(String tempProjectusetype) {
        this.tempProjectusetype = tempProjectusetype;
    }

    public String getTempUsetype() {
        return tempUsetype;
    }

    public void setTempUsetype(String tempUsetype) {
        this.tempUsetype = tempUsetype;
    }

    public Double getTempArea() {
        return tempArea;
    }

    public void setTempArea(Double tempArea) {
        this.tempArea = tempArea;
    }

    public Date getTiming() {
        return timing;
    }

    public void setTiming(Date timing) {
        this.timing = timing;
    }

    public Integer getOrganizationGrade() {
        return organizationGrade;
    }

    public void setOrganizationGrade(Integer organizationGrade) {
        this.organizationGrade = organizationGrade;
    }

    public double getArableAcreage() {
        return arableAcreage;
    }

    public void setArableAcreage(double arableAcreage) {
        this.arableAcreage = arableAcreage;
    }

    public SwExpert getSwExpert() {
        return swExpert;
    }

    public void setSwExpert(SwExpert swExpert) {
        this.swExpert = swExpert;
    }

    public Double getCityborderarea() {
        return cityborderarea;
    }

    public void setCityborderarea(Double cityborderarea) {
        this.cityborderarea = cityborderarea;
    }

    public Double getDoubtillegalarea() {
        return doubtillegalarea;
    }

    public void setDoubtillegalarea(Double doubtillegalarea) {
        this.doubtillegalarea = doubtillegalarea;
    }

    public String getArealevel() {
        return arealevel;
    }

    public void setArealevel(String arealevel) {
        this.arealevel = arealevel;
    }

    public Double getSpotarea() {
        return spotarea;
    }

    public void setSpotarea(Double spotarea) {
        this.spotarea = spotarea;
    }

    public Double getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(Double coordinateY) {
        this.coordinateY = coordinateY;
    }

    public Double getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(Double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public String getAftertime() {
        return aftertime;
    }

    public void setAftertime(String aftertime) {
        this.aftertime = aftertime;
    }

    public String getBeforetime() {
        return beforetime;
    }

    public void setBeforetime(String beforetime) {
        this.beforetime = beforetime;
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }

    public String getSpottype() {
        return spottype;
    }

    public void setSpottype(String spottype) {
        this.spottype = spottype;
    }

    public String getSpotnumber() {
        return spotnumber;
    }

    public void setSpotnumber(String spotnumber) {
        this.spotnumber = spotnumber;
    }

    public Long getExpertid() {
        return expertid;
    }

    public void setExpertid(Long expertid) {
        this.expertid = expertid;
    }

    public Integer getCheckperiod() {
        return checkperiod;
    }

    public void setCheckperiod(Integer checkperiod) {
        this.checkperiod = checkperiod;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getNotnewRemarks() {
        return notnewRemarks;
    }

    public void setNotnewRemarks(String notnewRemarks) {
        this.notnewRemarks = notnewRemarks;
    }

    public String getOtherApprovalname() {
        return otherApprovalname;
    }

    public void setOtherApprovalname(String otherApprovalname) {
        this.otherApprovalname = otherApprovalname;
    }

    public String getOtherRemarks() {
        return otherRemarks;
    }

    public void setOtherRemarks(String otherRemarks) {
        this.otherRemarks = otherRemarks;
    }

    public Double getOtherApprovalarea() {
        return otherApprovalarea;
    }

    public void setOtherApprovalarea(Double otherApprovalarea) {
        this.otherApprovalarea = otherApprovalarea;
    }

    public Date getOtherApprovaltime() {
        return otherApprovaltime;
    }

    public void setOtherApprovaltime(Date otherApprovaltime) {
        this.otherApprovaltime = otherApprovaltime;
    }

    public String getOtherApprovalnumber() {
        return otherApprovalnumber;
    }

    public void setOtherApprovalnumber(String otherApprovalnumber) {
        this.otherApprovalnumber = otherApprovalnumber;
    }

    public String getOtherApprovallevel() {
        return otherApprovallevel;
    }

    public void setOtherApprovallevel(String otherApprovallevel) {
        this.otherApprovallevel = otherApprovallevel;
    }

    public String getOtherProjectname() {
        return otherProjectname;
    }

    public void setOtherProjectname(String otherProjectname) {
        this.otherProjectname = otherProjectname;
    }

    public Double getOtherSpottype() {
        return otherSpottype;
    }

    public void setOtherSpottype(Double otherSpottype) {
        this.otherSpottype = otherSpottype;
    }

    public String getZjgRemarks() {
        return zjgRemarks;
    }

    public void setZjgRemarks(String zjgRemarks) {
        this.zjgRemarks = zjgRemarks;
    }

    public Double getZjgNewblockarea() {
        return zjgNewblockarea;
    }

    public void setZjgNewblockarea(Double zjgNewblockarea) {
        this.zjgNewblockarea = zjgNewblockarea;
    }

    public String getZjgNewblockname() {
        return zjgNewblockname;
    }

    public void setZjgNewblockname(String zjgNewblockname) {
        this.zjgNewblockname = zjgNewblockname;
    }

    public String getZjgProjectnumber() {
        return zjgProjectnumber;
    }

    public void setZjgProjectnumber(String zjgProjectnumber) {
        this.zjgProjectnumber = zjgProjectnumber;
    }

    public String getZjgProjectname() {
        return zjgProjectname;
    }

    public void setZjgProjectname(String zjgProjectname) {
        this.zjgProjectname = zjgProjectname;
    }

    public Double getZjgIsnestedsuccess() {
        return zjgIsnestedsuccess;
    }

    public void setZjgIsnestedsuccess(Double zjgIsnestedsuccess) {
        this.zjgIsnestedsuccess = zjgIsnestedsuccess;
    }

    public Integer getNotnewSpotclassification() {
        return notnewSpotclassification;
    }

    public void setNotnewSpotclassification(Integer notnewSpotclassification) {
        this.notnewSpotclassification = notnewSpotclassification;
    }

    public Integer getLegalprooftype() {
        return legalprooftype;
    }

    public void setLegalprooftype(Integer legalprooftype) {
        this.legalprooftype = legalprooftype;
    }

    public Integer getNode() {
        return node;
    }

    public void setNode(Integer node) {
        this.node = node;
    }

    public String getInstanceid() {
        return instanceid;
    }

    public void setInstanceid(String instanceid) {
        this.instanceid = instanceid;
    }

    public String getPrespotnumber() {
        return prespotnumber;
    }

    public void setPrespotnumber(String prespotnumber) {
        this.prespotnumber = prespotnumber;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public String getCountyname() {
        return countyname;
    }

    public void setCountyname(String countyname) {
        this.countyname = countyname;
    }

    public Integer getCounty() {
        return county;
    }

    public void setCounty(Integer county) {
        this.county = county;
    }

    public String getDistrictcountyname() {
        return districtcountyname;
    }

    public void setDistrictcountyname(String districtcountyname) {
        this.districtcountyname = districtcountyname;
    }

    public Integer getDistrictcounty() {
        return districtcounty;
    }

    public void setDistrictcounty(Integer districtcounty) {
        this.districtcounty = districtcounty;
    }

    public Integer getMapratio() {
        return mapratio;
    }

    public void setMapratio(Integer mapratio) {
        this.mapratio = mapratio;
    }

    public String getBzX() {
        return bzX;
    }

    public void setBzX(String bzX) {
        this.bzX = bzX;
    }

    public String getSpotnature() {
        return spotnature;
    }

    public void setSpotnature(String spotnature) {
        this.spotnature = spotnature;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getProtectfailarea() {
        return protectfailarea;
    }

    public void setProtectfailarea(Double protectfailarea) {
        this.protectfailarea = protectfailarea;
    }

    public Double getProtectpassarea() {
        return protectpassarea;
    }

    public void setProtectpassarea(Double protectpassarea) {
        this.protectpassarea = protectpassarea;
    }

    public String getProtectname() {
        return protectname;
    }

    public void setProtectname(String protectname) {
        this.protectname = protectname;
    }

    public Double getXhdjbntarea() {
        return xhdjbntarea;
    }

    public void setXhdjbntarea(Double xhdjbntarea) {
        this.xhdjbntarea = xhdjbntarea;
    }

    public Double getJbntarea() {
        return jbntarea;
    }

    public void setJbntarea(Double jbntarea) {
        this.jbntarea = jbntarea;
    }

    /**
     * 举证人id
     */
    private String proveuserId;

    /**
     * 举证人姓名
     */
    private String proveuserName;

    /**
     * 举证人账号
     */
    private String proveuserAccount;

    /**
     * 坐标文件文件存储路径
     */
    @JsonIgnore
    private String coordinateStorage;

    public void setCoordinateStorage(String coordinateStorage) {
        this.coordinateStorage = coordinateStorage;
    }

    public String getProveuserId() {
        return proveuserId;
    }

    public void setProveuserId(String proveuserId) {
        this.proveuserId = proveuserId;
    }

    public String getProveuserName() {
        return proveuserName;
    }

    public void setProveuserName(String proveuserName) {
        this.proveuserName = proveuserName;
    }

    public String getProveuserAccount() {
        return proveuserAccount;
    }

    public void setProveuserAccount(String proveuserAccount) {
        this.proveuserAccount = proveuserAccount;
    }

    public String getSsnydProjectName() {
        return ssnydProjectName;
    }

    public void setSsnydProjectName(String ssnydProjectName) {
        this.ssnydProjectName = ssnydProjectName;
    }

    public String getTempProjectName() {
        return tempProjectName;
    }

    public void setTempProjectName(String tempProjectName) {
        this.tempProjectName = tempProjectName;
    }

    private Long id;

    private Polygon shape;

    private String bizType;

    @Override
    public long getID() {
        return id;
    }

    @Override
    public void setID(long id) {
        this.id = id;
    }

    @Override
    public String getBizType() {
        return "allWeather";
    }

    @Override
    public void setBizType(String s) {
        this.bizType = s;
    }

    @Override
    public Polygon getShape() {
        return shape;
    }

    @Override
    public void setShape(Polygon p) {
        this.shape = p;
    }

    public String[] getFileServerPaths() {
        return fileServerPaths;
    }

    public void setFileServerPaths(String[] fileServerPaths) {
        this.fileServerPaths = fileServerPaths;
    }

    public String getCoordinateStorage() {
        return coordinateStorage;
    }


    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    /**
     * 与业务无关字段
     */
    @JsonIgnore
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 序号
     */
    @JsonIgnore
    @ExcelTitle(value = "序号")
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTempRemark() {
        return tempRemark;
    }

    public void setTempRemark(String tempRemark) {
        this.tempRemark = tempRemark;
    }

	public String getMapspotLevel() {
		return mapspotLevel;
	}

	public void setMapspotLevel(String mapspotLevel) {
		this.mapspotLevel = mapspotLevel;
	}

    public double getUnPlaningArea() {
        return unPlaningArea;
    }

    public void setUnPlaningArea(double unPlaningArea) {
        this.unPlaningArea = unPlaningArea;
    }

}