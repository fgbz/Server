package phalaenopsis.pjmapspot.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import phalaenopsis.common.annotation.ExcelTitle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 项目名称：Phalaenopsis
 * 创建日期：2017/10/24
 * 修改历史：
 * 1. [2017/10/24]创建文件
 *
 * @author chunl
 */
public class PjMapSpot {

    /**
     * 序号
     */
    @JsonIgnore
    @ExcelTitle("序 号")
    private Integer num;

    /**
     * 要素代码
     */
    private String elementScode;

    /**
     * 图斑预编号
     */
    private String mapspotpreNumber;

    /**
     * 地类编码
     */
    private String landtypeCode;

    /**
     * 地类名称
     */
    @ExcelTitle("地类名称")
    private String landtypeName;

    /**
     * 权属性质
     */
    private String ownershipNature;

    /**
     * 权属单位代码
     */
    private String ownershipDepartmentCode;

    /**
     * 权属单位名称
     */
    @ExcelTitle("权属单位名称")
    private String ownershipDepartmentName;

    /**
     * 坐落单位代码
     */
    private String locatedDepartmentCode;

    /**
     * 坐落单位名称
     */
    @ExcelTitle("坐落单位名称")
    private String locatedDepartmentName;

    /**
     * 耕地类型
     */
    private String arablelandType;

    /**
     * 耕地坡度级
     */
    private String arablelandGradient;

    /**
     * 扣除类型
     */
    private String deductionType;

    /**
     * 扣除地类编码
     */
    private String deductionLandCode;


    private String gxct;


    private Date gxsj;


    private String gxsm;

    private BigDecimal jsydthmj;

    /**
     * 备注
     */
    @ExcelTitle("备注")
    private String remark;

    /**
     * 地块面积
     */
    private Double acreage;

    /**
     * 地块面积_亩
     */
    @ExcelTitle("地块面积(亩)")
    private double acreageMu;


    private String idPj;

    /**
     * 导入年份
     */
    private BigDecimal year;

    /**
     * 主键
     */
    private BigDecimal id;

    /**
     * 区县级代码（管理区）
     */
    private Integer county;

    /**
     * 市级代码
     */
    private BigDecimal city;

    /**
     * 省级代码
     */
    private BigDecimal province;

    /**
     * 扣除地类系数
     */
    private BigDecimal deductionLandCoefficient;

    /**
     * 现状地物面积
     */
    private BigDecimal currentLandArea;

    /**
     * 零星地物面积
     */
    private BigDecimal sporadicLandArea;

    /**
     * 扣除地类面积
     */
    private BigDecimal deductionLandTypeArea;

    /**
     * 图斑地类面积
     */
    private BigDecimal mapspotLandTypeArea;

    /**
     * 地类备注
     */
    private String landTypeRemark;

    /**
     * 建设用地类型
     */
    private String constructionLandType;

    /**
     * 新增耕地来源
     */
    private String newArableLandSource;

    /**
     * 批准文号
     */
    private String approvalNumber;

    /**
     * 批准文件
     */
    @ExcelTitle("批准文件")
    private String approvalFile;

    /**
     * 新增耕地经费来源
     */
    private String newArableLandFundingSource;

    /**
     * 整治新增耕地面积
     */
    private BigDecimal remediationArableLandArea;

    /**
     * 坐标
     */
    private BigDecimal shapeLeng;

    /**
     * 耕地面积
     */
    private BigDecimal arableLandArea;

    /**
     * 可调整面积
     */
    private BigDecimal adjustableArea;

    /**
     * 标识码
     */
    @ExcelTitle("标识码")
    private Long identificationCode;

    /**
     * 区县名称（管理区）
     */
    @ExcelTitle("管理区名称")
    private String countyName;

    /**
     * 区县名称（行政区）
     */
    @ExcelTitle("行政区名称")
    private String districtCountyName;

    /**
     * 图斑号
     */
    @ExcelTitle("图斑号")
    private String spotNumber;

    /**
     * 填报状态
     */
    private Short fillState;

    /**
     * 耕地面积_调整后
     */
    private BigDecimal arableLandAreaAfter;

    /**
     * 耕地面积_调整后_亩
     */
    @ExcelTitle("其中耕地面积(亩)")
    private Double arableLandAreaAfterMu;

    /**
     * 可调整面积_调整后
     */
    private Double adjustableAreaAfter;

    /**
     * 可调整面积_调整后_亩
     */
    @ExcelTitle("可调整面积(亩)")
    private Double adjustableAreaAfterMu;


    /**
     * 区县级代码（行政区）
     */
    @ExcelTitle("行政区代码")
    private Integer districtCounty;

    /**
     * 市级审核状态，1.未审核、2.审核通过、3.不通过
     */
    private Short cityState;

    /**
     * 市级是否已审核  null：未审核  1：已审核
     */
    private Short cityisAudit;

    /**
     * 市级审核人
     */
    private String cityApproveName;

    /**
     * 市级审核时间
     */
    private Date cityApproveTime;

    /**
     * 实际审核意见
     */
    private String cityApproveRemark;

    public String getElementScode() {
        return elementScode;
    }

    public void setElementScode(String elementScode) {
        this.elementScode = elementScode;
    }

    public String getMapspotpreNumber() {
        return mapspotpreNumber;
    }

    public void setMapspotpreNumber(String mapspotpreNumber) {
        this.mapspotpreNumber = mapspotpreNumber;
    }

    public String getLandtypeCode() {
        return landtypeCode;
    }

    public void setLandtypeCode(String landtypeCode) {
        this.landtypeCode = landtypeCode;
    }

    public String getLandtypeName() {
        return landtypeName;
    }

    public void setLandtypeName(String landtypeName) {
        this.landtypeName = landtypeName;
    }

    public String getOwnershipNature() {
        return ownershipNature;
    }

    public void setOwnershipNature(String ownershipNature) {
        this.ownershipNature = ownershipNature;
    }

    public String getOwnershipDepartmentCode() {
        return ownershipDepartmentCode;
    }

    public void setOwnershipDepartmentCode(String ownershipDepartmentCode) {
        this.ownershipDepartmentCode = ownershipDepartmentCode;
    }

    public String getOwnershipDepartmentName() {
        return ownershipDepartmentName;
    }

    public void setOwnershipDepartmentName(String ownershipDepartmentName) {
        this.ownershipDepartmentName = ownershipDepartmentName;
    }

    public String getLocatedDepartmentCode() {
        return locatedDepartmentCode;
    }

    public void setLocatedDepartmentCode(String locatedDepartmentCode) {
        this.locatedDepartmentCode = locatedDepartmentCode;
    }

    public String getLocatedDepartmentName() {
        return locatedDepartmentName;
    }

    public void setLocatedDepartmentName(String locatedDepartmentName) {
        this.locatedDepartmentName = locatedDepartmentName;
    }

    public String getArablelandGradient() {
        return arablelandGradient;
    }

    public void setArablelandGradient(String arablelandGradient) {
        this.arablelandGradient = arablelandGradient;
    }

    public String getDeductionType() {
        return deductionType;
    }

    public void setDeductionType(String deductionType) {
        this.deductionType = deductionType;
    }

    public String getDeductionLandCode() {
        return deductionLandCode;
    }

    public void setDeductionLandCode(String deductionLandCode) {
        this.deductionLandCode = deductionLandCode;
    }

    public String getGxct() {
        return gxct;
    }

    public void setGxct(String gxct) {
        this.gxct = gxct;
    }

    public Date getGxsj() {
        return gxsj;
    }

    public void setGxsj(Date gxsj) {
        this.gxsj = gxsj;
    }

    public String getGxsm() {
        return gxsm;
    }

    public void setGxsm(String gxsm) {
        this.gxsm = gxsm;
    }

    public BigDecimal getJsydthmj() {
        return jsydthmj;
    }

    public void setJsydthmj(BigDecimal jsydthmj) {
        this.jsydthmj = jsydthmj;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getAcreage() {
        return acreage;
    }

    public void setAcreage(Double acreage) {
        this.acreage = acreage;
        this.acreageMu = this.acreage.doubleValue() * 0.0015;
    }

    public String getIdPj() {
        return idPj;
    }

    public void setIdPj(String idPj) {
        this.idPj = idPj;
    }

    public BigDecimal getYear() {
        return year;
    }

    public void setYear(BigDecimal year) {
        this.year = year;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Integer getCounty() {
        return county;
    }

    public void setCounty(Integer county) {
        this.county = county;
    }

    public BigDecimal getCity() {
        return city;
    }

    public void setCity(BigDecimal city) {
        this.city = city;
    }

    public BigDecimal getProvince() {
        return province;
    }

    public void setProvince(BigDecimal province) {
        this.province = province;
    }

    public BigDecimal getDeductionLandCoefficient() {
        return deductionLandCoefficient;
    }

    public void setDeductionLandCoefficient(BigDecimal deductionLandCoefficient) {
        this.deductionLandCoefficient = deductionLandCoefficient;
    }

    public BigDecimal getCurrentLandArea() {
        return currentLandArea;
    }

    public void setCurrentLandArea(BigDecimal currentLandArea) {
        this.currentLandArea = currentLandArea;
    }

    public BigDecimal getSporadicLandArea() {
        return sporadicLandArea;
    }

    public void setSporadicLandArea(BigDecimal sporadicLandArea) {
        this.sporadicLandArea = sporadicLandArea;
    }

    public BigDecimal getDeductionLandTypeArea() {
        return deductionLandTypeArea;
    }

    public void setDeductionLandTypeArea(BigDecimal deductionLandTypeArea) {
        this.deductionLandTypeArea = deductionLandTypeArea;
    }

    public BigDecimal getMapspotLandTypeArea() {
        return mapspotLandTypeArea;
    }

    public void setMapspotLandTypeArea(BigDecimal mapspotLandTypeArea) {
        this.mapspotLandTypeArea = mapspotLandTypeArea;
    }

    public String getLandTypeRemark() {
        return landTypeRemark;
    }

    public void setLandTypeRemark(String landTypeRemark) {
        this.landTypeRemark = landTypeRemark;
    }

    public String getConstructionLandType() {
        return constructionLandType;
    }

    public void setConstructionLandType(String constructionLandType) {
        this.constructionLandType = constructionLandType;
    }

    public String getNewArableLandSource() {
        return newArableLandSource;
    }

    public void setNewArableLandSource(String newArableLandSource) {
        this.newArableLandSource = newArableLandSource;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getApprovalFile() {
        return approvalFile;
    }

    public void setApprovalFile(String approvalFile) {
        this.approvalFile = approvalFile;
    }

    public String getNewArableLandFundingSource() {
        return newArableLandFundingSource;
    }

    public void setNewArableLandFundingSource(String newArableLandFundingSource) {
        this.newArableLandFundingSource = newArableLandFundingSource;
    }

    public BigDecimal getRemediationArableLandArea() {
        return remediationArableLandArea;
    }

    public void setRemediationArableLandArea(BigDecimal remediationArableLandArea) {
        this.remediationArableLandArea = remediationArableLandArea;
    }

    public BigDecimal getShapeLeng() {
        return shapeLeng;
    }

    public void setShapeLeng(BigDecimal shapeLeng) {
        this.shapeLeng = shapeLeng;
    }

    public BigDecimal getArableLandArea() {
        return arableLandArea;
    }

    public void setArableLandArea(BigDecimal arableLandArea) {
        this.arableLandArea = arableLandArea;
    }

    public BigDecimal getAdjustableArea() {
        return adjustableArea;
    }

    public void setAdjustableArea(BigDecimal adjustableArea) {
        this.adjustableArea = adjustableArea;
    }

    public Long getIdentificationCode() {
        return identificationCode;
    }

    public void setIdentificationCode(Long identificationCode) {
        this.identificationCode = identificationCode;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getDistrictCountyName() {
        return districtCountyName;
    }

    public void setDistrictCountyName(String districtCountyName) {
        this.districtCountyName = districtCountyName;
    }

    public String getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(String spotNumber) {
        this.spotNumber = spotNumber;
    }

    public Short getFillState() {
        return fillState;
    }

    public void setFillState(Short fillState) {
        this.fillState = fillState;
    }

    public BigDecimal getArableLandAreaAfter() {
        return arableLandAreaAfter;
    }

    public void setArableLandAreaAfter(BigDecimal arableLandAreaAfter) {
        this.arableLandAreaAfter = arableLandAreaAfter;
        this.arableLandAreaAfterMu = this.arableLandAreaAfter.doubleValue() * 0.0015;
    }

    public Double getAdjustableAreaAfter() {
        return adjustableAreaAfter;
    }

    public void setAdjustableAreaAfter(Double adjustableAreaAfter) {

        this.adjustableAreaAfter = adjustableAreaAfter;
        this.adjustableAreaAfterMu = this.adjustableAreaAfter.doubleValue() * 0.0015;
    }

    public Integer getDistrictCounty() {
        return districtCounty;
    }

    public void setDistrictCounty(Integer districtCounty) {
        this.districtCounty = districtCounty;
    }

    public Short getCityState() {
        return cityState;
    }

    public void setCityState(Short cityState) {
        this.cityState = cityState;
    }

    public Short getCityisAudit() {
        return cityisAudit;
    }

    public void setCityisAudit(Short cityisAudit) {
        this.cityisAudit = cityisAudit;
    }

    public String getCityApproveName() {
        return cityApproveName;
    }

    public void setCityApproveName(String cityApproveName) {
        this.cityApproveName = cityApproveName;
    }

    public Date getCityApproveTime() {
        return cityApproveTime;
    }

    public void setCityApproveTime(Date cityApproveTime) {
        this.cityApproveTime = cityApproveTime;
    }

    public String getCityApproveRemark() {
        return cityApproveRemark;
    }

    public void setCityApproveRemark(String cityApproveRemark) {
        this.cityApproveRemark = cityApproveRemark;
    }

    public String getArablelandType() {
        return arablelandType;
    }

    public void setArablelandType(String arablelandType) {
        this.arablelandType = arablelandType;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }


    public PjMapSpot() {
    }
}