package phalaenopsis.satellitegraph.entity;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import phalaenopsis.common.method.Json.NetDateJsonSerializer;

public class MapSpot2016 extends MapSpot implements Cloneable{
	
	/**
	 * 该函数只为克隆该类对象
	 */
	@Override
	public Object clone(){
		MapSpot2016 mapSpot2016 = null;
		try {
			return mapSpot2016 = (MapSpot2016)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return mapSpot2016;
	}
	

	/**
	 * 合法图斑举证类型
	 * 
	 */
//	public int LegalProofType;
	@JsonProperty("legalProofType")
	public Integer legalProofType;

	/**
	 * 历史批文_是否套合成功 History
	 * 
	 */
//	public boolean His_IsNestedSuccess;
	@JsonProperty("hisIsNestedSuccess")
	public Boolean hisIsNestedSuccess;

	/**
	 * 历史批文_是否符合规划
	 * 
	 */
//	public boolean His_IsConfirmPlanning;
	@JsonProperty("hisIsConfirmPlanning")
	public Boolean hisIsConfirmPlanning;

	/**
	 * 
	 * 历史批文_批准机关级别
	 */
	@JsonProperty("hisApprovalLevel")
	public String hisApprovalLevel;

	/**
	 * 历史批文_批准机关名称
	 * 
	 */
	@JsonProperty("hisApprovalName")
	public String hisApprovalName;

	/**
	 * 历史批文_批准文号
	 * 
	 */
	@JsonProperty("hisApprovalNumber")
	public String hisApprovalNumber;

	/**
	 * 历史批文_批准时间
	 * 
	 */
	@JsonProperty("hisApprovalTime")
	@JsonSerialize(using=NetDateJsonSerializer.class)
	public Date hisApprovalTime;
	
	
	/**
	 * 历史批文_批准面积
	 * 
	 */

//	public double His_ApprovalArea;

	@JsonProperty("hisApprovalArea")
	public Double hisApprovalArea;


	/**
	 * 历史批文_省厅审核确认
	 * 
	 */

//	public boolean His_ProvincialAudit;

	@JsonProperty("hisProvincialAudit")
	public Boolean hisProvincialAudit;


	/**
	 * 历史批文_备注
	 * 
	 */
	@JsonProperty("hisRemarks")
	public String hisRemarks;

	/**
	 * 历史批文_符合规划面积
	 * 
	 */

//	public double His_ConformArea;

	@JsonProperty("hisConformArea")
	public Double hisConformArea;


	/**
	 * 历史批文_不符合规划面积
	 * 
	 */

//	public double His_NotConformArea;

	@JsonProperty("hisNotConformArea")
	public Double hisNotConformArea;


	/**
	 * 重点工程_项目名称 Major
	 * 
	 */
	@JsonProperty("majProjectName")
	public String majProjectName;

	/**
	 * 重点工程_批准机关级别
	 * 
	 */
	@JsonProperty("majApprovalLevel")
	public String majApprovalLevel;

	/**
	 * 重点工程_批准机关名称
	 * 
	 */
	@JsonProperty("majApprovalName")
	public String majApprovalName;

	/**
	 * 重点工程_批准文号
	 * 
	 */
	@JsonProperty("majApprovalNumber")
	public String majApprovalNumber;

	/**
	 * 重点工程_批准时间
	 * 
	 */
	@JsonProperty("majApprovalTime")
	@JsonSerialize(using=NetDateJsonSerializer.class)
	public Date majApprovalTime;

	/**
	 * 
	 * 重点工程_批准面积
	 */

//	public double Maj_ApprovalArea;

	@JsonProperty("majApprovalArea")
	public Double majApprovalArea;


	/**
	 * 重点工程_备注
	 * 
	 */
	@JsonProperty("majRemarks")
	public String majRemarks;

	/**
	 * 油田用地_下达文号 Oil
	 * 
	 */
	@JsonProperty("oilIssuedNumber")
	public String oilIssuedNumber;

	/**
	 * 油田用地_下达时间
	 * 
	 */
	@JsonProperty("oilIssuedTime")
	@JsonSerialize(using=NetDateJsonSerializer.class)
	public Date oilIssuedTime;

	/**
	 * 油田用地_项目名称
	 * 
	 */
	@JsonProperty("oilProjectName")
	public String oilProjectName;

	/**
	 * 油田用地_备注
	 * 
	 */
	@JsonProperty("oilRemarks")
	public String oilRemarks;

	/**
	 * 增减挂钩_项目名称 ZJG
	 * 
	 */
	@JsonProperty("zJGProjectName")
	public String zJGProjectName;

	/**
	 * 增减挂钩_项目编号
	 * 
	 */
	@JsonProperty("zJGProjectNumber")
	public String zJGProjectNumber;

	/**
	 * 增减挂钩_建新地块名称
	 * 
	 */
	@JsonProperty("zJGNewBlockName")
	public String zJGNewBlockName;

	/**
	 * 增减挂钩_建新地块面积
	 * 
	 */

//	public double ZJG_NewBlockArea;

	@JsonProperty("zJGNewBlockArea")
	public Double zJGNewBlockArea;


	/**
	 * 增减挂钩_备注
	 * 
	 */
	@JsonProperty("zJGRemarks")
	public String zJGRemarks;

	/**
	 * 其他类型_图斑类型 Other
	 * 
	 */

//	public int Other_SpotType;

	@JsonProperty("otherSpotType")
	public Integer otherSpotType;


	/**
	 * 其他类型_项目名称
	 * 
	 */
	@JsonProperty("otherProjectName")
	public String otherProjectName;

	/**
	 * 
	 * 其他类型_批准机关级别
	 */
	@JsonProperty("otherApprovalLevel")
	public String otherApprovalLevel;

	/**
	 * 其他类型_批准机关名称
	 * 
	 */
	@JsonProperty("otherApprovalName")
	public String otherApprovalName;

	/**
	 * 其他类型_批准文号
	 * 
	 */
	@JsonProperty("otherApprovalNumber")
	public String otherApprovalNumber;

	/**
	 * 其他类型_批准时间
	 * 
	 */
	@JsonProperty("otherApprovalTime")
	@JsonSerialize(using=NetDateJsonSerializer.class)
	public Date otherApprovalTime;

	/**
	 * 其他类型_批准面积
	 * 
	 */

//	public double Other_ApprovalArea;

	@JsonProperty("otherApprovalArea")
	public Double otherApprovalArea;


	/**
	 * 其他类型_备注
	 * 
	 */
	@JsonProperty("otherRemarks")
	public String otherRemarks;

	/**
	 * 非新增_图斑分类 NotNew
	 * 
	 */

//	public int NotNew_SpotClassification;

	@JsonProperty("notNewSpotClassification")
	public Integer notNewSpotClassification;


	/**
	 * 非新增_备注
	 * 
	 */
	@JsonProperty("notNewRemarks")
	public String notNewRemarks;

	/**
	 * 疑似违法图斑处理状态
	 * 
	 */
	@JsonProperty("spotHandlingStatus")
	public String spotHandlingStatus;

	/**
	 * 审核信息
	 * 
	 */
	@JsonProperty("theAuditSpot")
	public AuditSpot theAuditSpot;

	/**
	 * 是否已填写卫片登记卡
	 * 
	 */
	@JsonProperty("isFillReport")
	public boolean isFillReport;

	

	public Integer getLegalProofType() {
		return legalProofType;
	}

	public void setLegalProofType(Integer legalProofType) {
		this.legalProofType = legalProofType;
	}

	
	public Boolean getHisIsNestedSuccess() {
		return hisIsNestedSuccess;
	}

	public void setHisIsNestedSuccess(Boolean hisIsNestedSuccess) {
		this.hisIsNestedSuccess = hisIsNestedSuccess;
	}

	public Boolean getHisIsConfirmPlanning() {
		return hisIsConfirmPlanning;
	}

	public void setHisIsConfirmPlanning(Boolean hisIsConfirmPlanning) {
		this.hisIsConfirmPlanning = hisIsConfirmPlanning;
	}

	public String getHisApprovalLevel() {
		return hisApprovalLevel;
	}

	public void setHisApprovalLevel(String hisApprovalLevel) {
		this.hisApprovalLevel = hisApprovalLevel;
	}

	public String getHisApprovalName() {
		return hisApprovalName;
	}

	public void setHisApprovalName(String hisApprovalName) {
		this.hisApprovalName = hisApprovalName;
	}

	public String getHisApprovalNumber() {
		return hisApprovalNumber;
	}

	public void setHisApprovalNumber(String hisApprovalNumber) {
		this.hisApprovalNumber = hisApprovalNumber;
	}

	public Date getHisApprovalTime() {
		return hisApprovalTime;
	}

	public void setHisApprovalTime(Date hisApprovalTime) {
		this.hisApprovalTime = hisApprovalTime;
	}

	public Double getHisApprovalArea() {
		return hisApprovalArea;
	}

	public void setHisApprovalArea(Double hisApprovalArea) {
		this.hisApprovalArea = hisApprovalArea;
	}

	public Boolean getHisProvincialAudit() {
		return hisProvincialAudit;
	}

	public void setHisProvincialAudit(Boolean hisProvincialAudit) {
		this.hisProvincialAudit = hisProvincialAudit;
	}

	public String getHisRemarks() {
		return hisRemarks;
	}

	public void setHisRemarks(String hisRemarks) {
		this.hisRemarks = hisRemarks;
	}

	public Double getHisConformArea() {
		return hisConformArea;
	}

	public void setHisConformArea(Double hisConformArea) {
		this.hisConformArea = hisConformArea;
	}

	public Double getHisNotConformArea() {
		return hisNotConformArea;
	}

	public void setHisNotConformArea(Double hisNotConformArea) {
		this.hisNotConformArea = hisNotConformArea;
	}

	public String getMajProjectName() {
		return majProjectName;
	}

	public void setMajProjectName(String majProjectName) {
		this.majProjectName = majProjectName;
	}

	public String getMajApprovalLevel() {
		return majApprovalLevel;
	}

	public void setMajApprovalLevel(String majApprovalLevel) {
		this.majApprovalLevel = majApprovalLevel;
	}

	public String getMajApprovalName() {
		return majApprovalName;
	}

	public void setMajApprovalName(String majApprovalName) {
		this.majApprovalName = majApprovalName;
	}

	public String getMajApprovalNumber() {
		return majApprovalNumber;
	}

	public void setMajApprovalNumber(String majApprovalNumber) {
		this.majApprovalNumber = majApprovalNumber;
	}

	public Date getMajApprovalTime() {
		return majApprovalTime;
	}

	public void setMajApprovalTime(Date majApprovalTime) {
		this.majApprovalTime = majApprovalTime;
	}

	public Double getMajApprovalArea() {
		return majApprovalArea;
	}

	public void setMajApprovalArea(Double majApprovalArea) {
		this.majApprovalArea = majApprovalArea;
	}

	public String getMajRemarks() {
		return majRemarks;
	}

	public void setMajRemarks(String majRemarks) {
		this.majRemarks = majRemarks;
	}

	public String getOilIssuedNumber() {
		return oilIssuedNumber;
	}

	public void setOilIssuedNumber(String oilIssuedNumber) {
		this.oilIssuedNumber = oilIssuedNumber;
	}

	public Date getOilIssuedTime() {
		return oilIssuedTime;
	}

	public void setOilIssuedTime(Date oilIssuedTime) {
		this.oilIssuedTime = oilIssuedTime;
	}

	public String getOilProjectName() {
		return oilProjectName;
	}

	public void setOilProjectName(String oilProjectName) {
		this.oilProjectName = oilProjectName;
	}

	public String getOilRemarks() {
		return oilRemarks;
	}

	public void setOilRemarks(String oilRemarks) {
		this.oilRemarks = oilRemarks;
	}

	public String getzJGProjectName() {
		return zJGProjectName;
	}

	public void setzJGProjectName(String zJGProjectName) {
		this.zJGProjectName = zJGProjectName;
	}

	public String getzJGProjectNumber() {
		return zJGProjectNumber;
	}

	public void setzJGProjectNumber(String zJGProjectNumber) {
		this.zJGProjectNumber = zJGProjectNumber;
	}

	public String getzJGNewBlockName() {
		return zJGNewBlockName;
	}

	public void setzJGNewBlockName(String zJGNewBlockName) {
		this.zJGNewBlockName = zJGNewBlockName;
	}

	public Double getzJGNewBlockArea() {
		return zJGNewBlockArea;
	}

	public void setzJGNewBlockArea(Double zJGNewBlockArea) {
		this.zJGNewBlockArea = zJGNewBlockArea;
	}

	public String getzJGRemarks() {
		return zJGRemarks;
	}

	public void setzJGRemarks(String zJGRemarks) {
		this.zJGRemarks = zJGRemarks;
	}

	public Integer getOtherSpotType() {
		return otherSpotType;
	}

	public void setOtherSpotType(Integer otherSpotType) {
		this.otherSpotType = otherSpotType;
	}

	public String getOtherProjectName() {
		return otherProjectName;
	}

	public void setOtherProjectName(String otherProjectName) {
		this.otherProjectName = otherProjectName;
	}

	public String getOtherApprovalLevel() {
		return otherApprovalLevel;
	}

	public void setOtherApprovalLevel(String otherApprovalLevel) {
		this.otherApprovalLevel = otherApprovalLevel;
	}

	public String getOtherApprovalName() {
		return otherApprovalName;
	}

	public void setOtherApprovalName(String otherApprovalName) {
		this.otherApprovalName = otherApprovalName;
	}

	public String getOtherApprovalNumber() {
		return otherApprovalNumber;
	}

	public void setOtherApprovalNumber(String otherApprovalNumber) {
		this.otherApprovalNumber = otherApprovalNumber;
	}

	public Date getOtherApprovalTime() {
		return otherApprovalTime;
	}

	public void setOtherApprovalTime(Date otherApprovalTime) {
		this.otherApprovalTime = otherApprovalTime;
	}

	public Double getOtherApprovalArea() {
		return otherApprovalArea;
	}

	public void setOtherApprovalArea(Double otherApprovalArea) {
		this.otherApprovalArea = otherApprovalArea;
	}

	public String getOtherRemarks() {
		return otherRemarks;
	}

	public void setOtherRemarks(String otherRemarks) {
		this.otherRemarks = otherRemarks;
	}

	public Integer getNotNewSpotClassification() {
		return notNewSpotClassification;
	}

	public void setNotNewSpotClassification(Integer notNewSpotClassification) {
		this.notNewSpotClassification = notNewSpotClassification;
	}

	public String getNotNewRemarks() {
		return notNewRemarks;
	}

	public void setNotNewRemarks(String notNewRemarks) {
		this.notNewRemarks = notNewRemarks;
	}

	public static String getPolygonkey() {
		return PolygonKey;
	}

	public String getSpotHandlingStatus() {
		return spotHandlingStatus;
	}

	public void setSpotHandlingStatus(String spotHandlingStatus) {
		this.spotHandlingStatus = spotHandlingStatus;
	}

	public AuditSpot getTheAuditSpot() {
		return theAuditSpot;
	}

	public void setTheAuditSpot(AuditSpot theAuditSpot) {
		this.theAuditSpot = theAuditSpot;
	}

	public boolean isFillReport() {
		return isFillReport;
	}

	public void setFillReport(boolean isFillReport) {
		this.isFillReport = isFillReport;
	}

	public Date getLastUpdate() {
		return LastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		LastUpdate = lastUpdate;
	}

	public String getPolygonKey() {
		return PolygonKey;
	}


//	public boolean ShouldSynchronize(EntityAction action) {
//		if (action == EntityAction.Delete)
//			return true;
//
//		switch (this.Node) {
//		case NodeStates.ProofingLegal:
//		case NodeStates.ProofingMaybeNew:
//		case NodeStates.ProofingNotNew:
//		case NodeStates.ReallyIllegal:
//			return true;
//		case NodeStates.ProofMaybeNew:
//		case NodeStates.ProofNotNew:
//			return AppSettings.OutsideService;
//		case NodeStates.ProofLegal:
//			if (this.LegalProofType == Phalaenopsis.SatelliteGraph.Entities.LegalProofType.OilfieldLand)
//				return true;
//			else
//				return false;
//		default:
//			return false;
//		}
//	}

	public Date LastUpdate;

	public final static String PolygonKey = "Phalaenopsis$MapSpot$Polygon@17!";

//	Stream IAttachmentSynchronization.Read(EntityAction action)
//    {
//        if (AppSettings.OutsideService)
//            return null;
//        String  serviceUrl = AppSettings.MapSpotLocationService;
//        if (String .IsNullOrEmpty(serviceUrl))
//            return null;
//
//        Polygon polygon = SpotArcGISHelper.GetSpotShape(serviceUrl, this.ID.ToString());
//        if (polygon == null)
//            return null;
//        Xian80ToWGS84(polygon);
//        return WcfSerializer.Json.SerializeStream(polygon);
//    }

//	void IAttachmentSynchronization.Insert(Stream attachment)
//    {
//        String  shape = GetEncryptedShape(attachment);
//        SpotSDE sde = new SpotSDE()
//        {
//            MID = this.ID,
//            Shape = shape
//        };
//        using (ISession session = DBOperator.Instance.OpenSession()) {
//            session.Save(sde);
//            session.Flush();
//        }
//        IUnityContainer container = Nefarian.Core.WebServiceSite.GetModuleContainer<SatelliteGraphModule>();
//        PolygonRegion pr = container.Resolve<PolygonRegion>();
//        pr.LoadPolygon(sde);
//    }

//	void IAttachmentSynchronization.Update(Stream attachment)
//    {
//        String  shape = GetEncryptedShape(attachment);
//        SpotSDE sde = new SpotSDE()
//        {
//            MID = this.ID,
//            Shape = shape
//        };
//        using (ISession session = DBOperator.Instance.OpenSession()) {
//            session.SaveOrUpdate(sde);
//            session.Flush();
//        }
//        IUnityContainer container = Nefarian.Core.WebServiceSite.GetModuleContainer<SatelliteGraphModule>();
//        PolygonRegion pr = container.Resolve<PolygonRegion>();
//        pr.LoadPolygon(sde);
//    }

//	public String  GetEncryptedShape(Stream attachment) {
//		using(MemoryStream mem=new MemoryStream()){attachment.CopyTo(mem);String  encrypted=CryptoHelper.ToRijndael(PolygonKey,mem.ToArray());return encrypted;}
//	}

//	void IAttachmentSynchronization.Delete()
//    {
//        using (ISession session = DBOperator.Instance.OpenSession()) {
//            SpotSDE sde = new SpotSDE()
//            {
//                MID = this.ID
//            };
//            session.Delete(sde);
//            session.Flush();
//        }
//    }

	public static Polygon getDecryptShape(String shape) {
		//byte[] src = CryptoHelper.fromRijndael(PolygonKey, shape);
		//return (Polygon) SerializationUtils.deserialize(src);
		return null;
	}

//	public void Xian80ToWGS84(Polygon polygon) {
//		Param7Config param7=Param7Config.GetConfig();foreach(var ring in polygon.Rings){foreach(var point in ring){BLHCoordinate blh_80=new BLHCoordinate(point[0],point[1],0d);XYZCoordinate xyz_80=CoordinateHelper.BLHtoXYZ(blh_80,CoordinateHelper.Xian80Datum);XYZCoordinate xyz_84=CoordinateHelper.Param7(xyz_80,param7.DeltaX,param7.DeltaY,param7.DeltaZ,param7.Rx,param7.Ry,param7.Rz,param7.K);BLHCoordinate blh_84=CoordinateHelper.XYZtoBLH(xyz_84,CoordinateHelper.WGS84Datum);point[0]=blh_84.Longitude;point[1]=blh_84.Latitude;}}
//	}
}
