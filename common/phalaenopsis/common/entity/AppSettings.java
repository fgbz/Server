package phalaenopsis.common.entity;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Repository;

@Repository("appSettings")
public class AppSettings {

	private final int ProvinceCode = 370000;

	private String ReportYear;

	private String GeometryService;

	private String ConstructionLandService;

	private String ExistingMapService;

	private String DLBM;

	private String path;

	private String backupsPath;

	private String isSyncMongodb;
	public String getDLBM() {
		return DLBM;
	}

	public void setDLBM(String dLBM) {
		DLBM = dLBM;
	}

	public String getExistingMapService() {
		return ExistingMapService;
	}

	public void setExistingMapService(String existingMapService) {
		ExistingMapService = existingMapService;
	}

	public String getConstructionLandService() {
		return ConstructionLandService;
	}

	public void setConstructionLandService(String constructionLandService) {
		ConstructionLandService = constructionLandService;
	}

	private String GeoWKID;

	private String ProjWKID;

	public String getProjWKID() {
		return ProjWKID;
	}

	public void setProjWKID(String projWKID) {
		ProjWKID = projWKID;
	}

	private String UploadFolder;

	private String Ratio;

	private String MapSpotLocationService;

	private String PJMapSpotLocationService;



	/**
	 * 全天候图斑定位服务
	 */
	private String allWeatherSpotLocationService;

	private String ExcelTemplates;

	private String PlanSpotService;

	private String machineID;

	private String AndroidVersion;

	private String OutsideService;

	private String IOSVersion;

	private String MinDistance;

	private String time;

	private String isStartCheck;

	private String dataname;

	private String swfPath;

	private String openoffice;

	private String solrpath;

	public String getMinDistance() {
		return MinDistance;
	}

	public void setMinDistance(String minDistance) {
		MinDistance = minDistance;
	}

	public String getMachineID() {
		return machineID;
	}

	public void setMachineID(String machineID) {
		this.machineID = machineID;
	}

	public int getProvinceCode() {
		return ProvinceCode;
	}

	public String getReportYear() {
		return ReportYear;
	}

	public String getUploadFolder() {
		return UploadFolder;
	}

	public String getRatio() {
		return Ratio;
	}

	public String getMapSpotLocationService() {
		return MapSpotLocationService;
	}

	public String getExcelTemplates() {
		return ExcelTemplates;
	}

	public String getPlanSpotService() {
		return PlanSpotService;
	}

	public String getAndroidVersion() {
		return AndroidVersion;
	}

	public String getOutsideService() {
		return OutsideService;
	}

	public String getIOSVersion() {
		return IOSVersion;
	}

	public String getSwfPath() {
		return swfPath;
	}

	public void setSwfPath(String swfPath) {
		this.swfPath = swfPath;
	}

	public String getOpenoffice() {
		return openoffice;
	}

	public void setOpenoffice(String openoffice) {
		this.openoffice = openoffice;
	}

	public String getSolrpath() {
		return solrpath;
	}

	public void setSolrpath(String solrpath) {
		this.solrpath = solrpath;
	}

	public AppSettings() {
		super();

		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("conf/config.properties");

		java.util.Properties properties = new java.util.Properties();

		try{
			properties.load(inputStream);
			//拍照限制距离
			this.MinDistance = properties.getProperty("MinDistance");
			this.ReportYear = properties.getProperty("ReportYear");
			//地图工具服务
			this.GeometryService = properties.getProperty("GeometryService");
			//新增建设用地服务
			this.ConstructionLandService = properties.getProperty("ConstructionLandService");
			//现状图层服务
			this.ExistingMapService = properties.getProperty("ExistingMapService");
			//现状图层中的耕地类型(DLBM)的开头，用于求取对应图形所占耕地面积
			this.DLBM = properties.getProperty("DLBM");
			this.GeoWKID = properties.getProperty("GeoWKID");
			this.ProjWKID = properties.getProperty("ProjWKID");
			this.UploadFolder = properties.getProperty("UploadFolder");
			this.Ratio = properties.getProperty("Ratio");
			this.MapSpotLocationService = properties.getProperty("MapSpotLocationService");
			//全天候图斑定位服务
			this.allWeatherSpotLocationService = properties.getProperty("AllWeatherSpotLocationService");
			this.ExcelTemplates = properties.getProperty("ExcelTemplates");
			this.PlanSpotService = properties.getProperty("PlanSpotService");
			this.machineID = properties.getProperty("machineID");
			this.AndroidVersion = properties.getProperty("AndroidVersion");
			this.OutsideService = properties.getProperty("OutsideService");
			this.IOSVersion = properties.getProperty("IOSVersion");

			this.DeltaX = properties.getProperty("DeltaX");
			this.DeltaY = properties.getProperty("DeltaY");
			this.DeltaZ = properties.getProperty("DeltaZ");
			this.Rx = properties.getProperty("Rx");
			this.Ry = properties.getProperty("Ry");
			this.Rz = properties.getProperty("Rz");
			this.K = properties.getProperty("K");
			this.backupsPath =  properties.getProperty("backupsPath");
			this.path =  properties.getProperty("path");
			this.time = properties.getProperty("time");
			this.isStartCheck = properties.getProperty("isStartCheck");
			this.isSyncMongodb = properties.getProperty("isSyncMongodb");
			this.dataname = properties.getProperty("dataname");
			this.PJMapSpotLocationService = properties.getProperty("PJMapSpotLocationService");
			this.swfPath = properties.getProperty("swfPath");
			this.openoffice = properties.getProperty("openoffice");
			this.solrpath = properties.getProperty("solrpath");
		}
		catch(IOException e1) {
			e1.printStackTrace();
		}
	}

	public String getGeoWKID() {
		return GeoWKID;
	}

	public void setGeoWKID(String geoWKID) {
		GeoWKID = geoWKID;
	}

	public String getGeometryService() {
		return GeometryService;
	}

	public void setGeometryService(String geometryService) {
		GeometryService = geometryService;
	}

	public String getAllWeatherSpotLocationService() {
		return allWeatherSpotLocationService;
	}

	public void setAllWeatherSpotLocationService(String allWeatherSpotLocationService) {
		this.allWeatherSpotLocationService = allWeatherSpotLocationService;
	}

	private String DeltaX;

	private String DeltaY;

	private String DeltaZ;

	private String Rx;

	private String Ry;

	private String Rz;

	private String K;

	public String getDeltaX() {
		return DeltaX;
	}

	public void setDeltaX(String deltaX) {
		DeltaX = deltaX;
	}

	public String getDeltaY() {
		return DeltaY;
	}

	public void setDeltaY(String deltaY) {
		DeltaY = deltaY;
	}

	public String getDeltaZ() {
		return DeltaZ;
	}

	public void setDeltaZ(String deltaZ) {
		DeltaZ = deltaZ;
	}

	public String getRx() {
		return Rx;
	}

	public void setRx(String rx) {
		Rx = rx;
	}

	public String getRy() {
		return Ry;
	}

	public void setRy(String ry) {
		Ry = ry;
	}

	public String getRz() {
		return Rz;
	}

	public void setRz(String rz) {
		Rz = rz;
	}

	public String getK() {
		return K;
	}

	public void setK(String k) {
		K = k;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getBackupsPath() {
		return backupsPath;
	}

	public void setBackupsPath(String backupsPath) {
		this.backupsPath = backupsPath;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getIsStartCheck() {
		return isStartCheck;
	}

	public void setIsStartCheck(String isStartCheck) {
		this.isStartCheck = isStartCheck;
	}

	public String getIsSyncMongodb() {
		return isSyncMongodb;
	}

	public void setIsSyncMongodb(String isSyncMongodb) {
		this.isSyncMongodb = isSyncMongodb;
	}

	public String getDataname() {
		return dataname;
	}

	public void setDataname(String dataname) {
		this.dataname = dataname;
	}

	public String getPJMapSpotLocationService() {
		return PJMapSpotLocationService;
	}

	public void setPJMapSpotLocationService(String PJMapSpotLocationService) {
		this.PJMapSpotLocationService = PJMapSpotLocationService;
	}
}
