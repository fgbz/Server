package phalaenopsis.satellitegraph.entity;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import phalaenopsis.common.entity.Attachment.Attachment;
import phalaenopsis.workflowEngine.controller.WFObject;

public class MapSpot implements IMapSpot {
	
	/**
	 * 主键
	 */
	@JsonProperty("id")
	private long id;

	/**
	 * 省级区划代码
	 */
	@JsonProperty("province")
	private int province;
	
	/**
	 * 地市级区划代码
	 */
	@JsonProperty("city")
	private int city;

	/**
	 * 区县级区划代码（管理区）
	 */
	@JsonProperty("county")
	private int county;

	/**
	 * 区县名称（管理区）
	 */
	@JsonProperty("countyName")
	private String countyName;

	/**
	 * 区县级代码（行政区）
	 */
	@JsonProperty("districtCounty")
	private int districtCounty;

	/**
	 * 区县名称（行政区）
	 */
	@JsonProperty("districtCountyName")
	private String districtCountyName;

	/**
	 * 图斑类型
	 */
	@JsonProperty("spotType")
	private String spotType;

	/**
	 * TZ
	 */
	@JsonProperty("tZ")
	private String tZ;

	/**
	 * 前时相
	 */
	@JsonProperty("beforeTime")
	private String beforeTime;

	/**
	 * 后时相
	 */
	@JsonProperty("afterTime")
	private String afterTime;

	/**
	 * X坐标
	 */
	@JsonProperty("coordinateX")
	private double coordinateX;

	/**
	 * Y坐标
	 */
	@JsonProperty("coordinateY")
	private double coordinateY;

	/**
	 * 监测面积
	 */
	@JsonProperty("spotArea")
	private double spotArea;
	/**
	 * 监测面积亩
	 */
	@JsonProperty("spotAreaMu")
	private double spotAreaMu;

	/**
	 * 变更地类
	 */
	@JsonProperty("changedLandType")
	private String changedLandType;
	
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}

	public String getInstanceID() {
		return instanceID;
	}

	public void setInstanceID(String instanceID) {
		this.instanceID = instanceID;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public int getSourceType() {
		return sourceType;
	}

	public void setSourceType(int sourceType) {
		this.sourceType = sourceType;
	}

	public Integer getMaybeNewSource() {
		return maybeNewSource;
	}

	public void setMaybeNewSource(Integer maybeNewSource) {
		this.maybeNewSource = maybeNewSource;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	/**
	 * 变更范围
	 */
	@JsonProperty("changedRange")
	private String changedRange;
	
	/**
	 * 未变更地类
	 */
	@JsonProperty("notChangedLandType")
	private String notChangedLandType;
	
	/**
	 * 备注
	 */
	@JsonProperty("remarks")
	private String remarks;
	
	/**
	 * 建设用地面积
	 */
	@JsonProperty("constructionAcreage")
	private double constructionAcreage;
	/**
	 * 建设用地面积(亩)
	 */
	@JsonProperty("constructionAcreageMu")
	private double constructionAcreageMu;
	
	/**
	 * 耕地面积
	 */
	@JsonProperty("arableAcreage")
	private double arableAcreage;
	/**
	 * 耕地面积(亩)
	 */
	@JsonProperty("arableAcreageMu")
	private double arableAcreageMu;
	
	/**
	 * 监测编号
	 */
	@JsonProperty("spotNumber")
	private String spotNumber;

	/**
	 * 年份
	 */
	@JsonProperty("year")
	private int year;
	
	
	/**
	 * 流程ID
	 */
	@JsonProperty("instanceID")
	private String instanceID;

	/**
	 * 所处节点
	 */
	@JsonProperty("node")
	private String node;

	/**
	 * 图斑导入时的原始大类
	 */
	@JsonProperty("sourceType")
	private int sourceType;

	/**
	 * 图斑是否经过了切割（分宗并宗）
	 */
	@JsonProperty("hasCut")
	private boolean hasCut;

	/**
	 * 疑似非新增图斑的来源
	 */
	@JsonProperty("maybeNewSource")
	private Integer maybeNewSource;

	/**
	 * 导入时的图斑分类（TBCLLX）
	 */
	@JsonProperty("spotKind")
	public String spotKind;

	/**
	 * 图斑流程还原的次数
	 */
	@JsonProperty("version")
	public Integer version;

	/**
	 * 附件
	 */
	@JsonProperty("attachments")
	private List<Attachment> attachments;

	@JsonProperty("bizType")
	private String bizType;

	@JsonProperty("shape")
	private Polygon shape;

	@Override
	public long getID() {
		return id;
	}

	@Override
	public void setID(long id) {
		this.id = id;
	}

	public int getProvince() {
		return province;
	}

	public void setProvince(int province) {
		this.province = province;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public int getCounty() {
		return county;
	}

	public void setCounty(int county) {
		this.county = county;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public int getDistrictCounty() {
		return districtCounty;
	}

	public void setDistrictCounty(int districtCounty) {
		this.districtCounty = districtCounty;
	}

	public String getDistrictCountyName() {
		return districtCountyName;
	}

	public void setDistrictCountyName(String districtCountyName) {
		this.districtCountyName = districtCountyName;
	}

	public String getSpotType() {
		return spotType;
	}

	public void setSpotType(String spotType) {
		this.spotType = spotType;
	}

	public String gettZ() {
		return tZ;
	}

	public void settZ(String tZ) {
		this.tZ = tZ;
	}

	public String getBeforeTime() {
		return beforeTime;
	}

	public void setBeforeTime(String beforeTime) {
		this.beforeTime = beforeTime;
	}

	public String getAfterTime() {
		return afterTime;
	}

	public void setAfterTime(String afterTime) {
		this.afterTime = afterTime;
	}


	public double getCoordinateX() {
		return coordinateX;
	}

	public void setCoordinateX(double coordinateX) {
		this.coordinateX = coordinateX;
	}

	public double getCoordinateY() {
		return coordinateY;
	}

	public void setCoordinateY(double coordinateY) {
		this.coordinateY = coordinateY;
	}

	public double getSpotArea() {
		return spotArea;
	}

	public void setSpotArea(double spotArea) {
		this.spotArea = spotArea;
	}

	public String getChangedLandType() {
		return changedLandType;
	}

	public void setChangedLandType(String changedLandType) {
		this.changedLandType = changedLandType;
	}

	public String getChangedRange() {
		return changedRange;
	}

	public void setChangedRange(String changedRange) {
		this.changedRange = changedRange;
	}

	public String getNotChangedLandType() {
		return notChangedLandType;
	}

	public void setNotChangedLandType(String notChangedLandType) {
		this.notChangedLandType = notChangedLandType;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public double getConstructionAcreage() {
		return constructionAcreage;
	}

	public void setConstructionAcreage(double constructionAcreage) {
		this.constructionAcreage = constructionAcreage;
	}

	public double getArableAcreage() {
		return arableAcreage;
	}

	public void setArableAcreage(double arableAcreage) {
		this.arableAcreage = arableAcreage;
	}

	public String getSpotNumber() {
		return spotNumber;
	}

	public void setSpotNumber(String spotNumber) {
		this.spotNumber = spotNumber;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public boolean isHasCut() {
		return hasCut;
	}

	public void setHasCut(boolean hasCut) {
		this.hasCut = hasCut;
	}



	// public override bool TryGetMember(GetMemberBinder binder, out object
	// result)
	// {
	// bool hasProperty = HasProperty(binder.Name);
	// if (hasProperty)
	// return base.TryGetMember(binder, out result);
	// else
	// {
	// result = null;
	// return true;
	// }
	// }
	//
	// public override bool TrySetMember(SetMemberBinder binder, object value)
	// {
	// bool hasProperty = HasProperty(binder.Name);
	// if (hasProperty)
	// return base.TrySetMember(binder, value);
	// else
	// return true;
	// }
	//
	// private bool HasProperty(string name)
	// {
	// Type t = this.GetType();
	// return t.GetProperty(name) != null;
	// }

	private double ConvertToMu(double acreage) {
		BigDecimal b = new BigDecimal(acreage * 0.0015);
		double df = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return df;
	}

	@Override
	public String getBizType() {
		return "YearSatellite";
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


	/**
	 * 流程信息
	 */
	public WFObject WFObject;

	public WFObject getWFObject() {
		return WFObject;
	}

	public void setWFObject(WFObject wFObject) {
		WFObject = wFObject;
	}

	public double getSpotAreaMu() {
		return ConvertToMu(spotArea);
	}

	public double getConstructionAcreageMu() {
		return ConvertToMu(constructionAcreage);
	}

	public double getArableAcreageMu() {
		return ConvertToMu(arableAcreage);
	}

	public String getSpotKind() {
		return spotKind;
	}

	public void setSpotKind(String spotKind) {
		this.spotKind = spotKind;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
}
