package phalaenopsis.common.entity.Attachment;

import java.io.Serializable;
import java.util.Date;

import javax.json.Json;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import phalaenopsis.common.entity.AppSettings;
import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.Json.DateJsonSerializer;
import phalaenopsis.common.util.BLHCoordinate;
import phalaenopsis.common.util.CoordinateHelper;
import phalaenopsis.common.util.Param7Config;
import phalaenopsis.common.util.XYZCoordinate;

public class Attachment implements Serializable   {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID（附件ID）
	 */
	@JsonProperty("ID")
	private String id;

	/**
	 * 附件名称
	 */
	@JsonProperty("FileName")
	private String fileName;

	/**
	 * 附件的扩展名，不带“.”（附件类型）
	 */
	@JsonProperty("FileExt")
	private String fileExt;

	/**
	 * 附件大小（字节）
	 */
	@JsonProperty("FileSize")
	private Long fileSize;

	/**
	 * 服务器存储的实际文件名
	 */
	@JsonProperty("ActualFile")
	private String actualFile;

	/**
	 * 服务器存储的缩略图文件名
	 */
	@JsonProperty("ThumbFile")
	private String thumbFile;

	/**
	 * 说明
	 */
	@JsonProperty("Explain")
	private String explain;

	/**
	 * 附件关联的业务数据ID
	 */
	@JsonProperty("BizID")
	private String bizID;

	/**
	 * 附件上传时间
	 */
	@JsonProperty("UploadTime")
	@JsonSerialize(using=DateJsonSerializer.class)
	private Date uploadTime;

	/**
	 * 附件所属模块
	 */
	@JsonProperty("Module")
	private String module;

	/**
	 * 附件来源（0：内业上传附件，1：外业巡查拍照）
	 */
	@JsonProperty("Source")
	private Integer source;

	/**
	 * 照片X坐标（经度）
	 */
	@JsonProperty("X")
	private Double x;

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	/**
	 * 照片Y坐标（纬度）
	 */
	@JsonProperty("Y")
	private Double y;

	/**
	 * 照片方位角
	 */
	@JsonProperty("Angle")
	private Double angle;

	/**
	 * 附件附加信息
	 */
	@JsonProperty("ExtraInfo")
	private String extraInfo;

	private Date lastUpdate;
	/**
	 * 是否有效
	 */
	private Integer isdelete;

	private String path;

	private String content;

	private Date modifydate;

	private String inputuserid;

	public String getInputuserid() {
		return inputuserid;
	}

	public void setInputuserid(String inputuserid) {
		this.inputuserid = inputuserid;
	}

	public Integer getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}



	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getActualFile() {
		return actualFile;
	}

	public void setActualFile(String actualFile) {
		this.actualFile = actualFile;
	}

	public String getThumbFile() {
		return thumbFile;
	}

	public void setThumbFile(String thumbFile) {
		this.thumbFile = thumbFile;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public String getBizID() {
		return bizID;
	}

	public void setBizID(String bizID) {
		this.bizID = bizID;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}



	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Double getAngle() {
		return angle;
	}

	public void setAngle(Double angle) {
		this.angle = angle;
	}


	public String getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}


	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	/**
	 * 获取附件存储文件夹 以文件名首字符创建文件夹
	 *
	 * @param actualFile
	 * @return
	 */
	public static String GetFileStorageFolder(String actualFile) {
		AppSettings appSettings = new AppSettings();

		//Resource resource = new ClassPathResource("");
		String webInfPath = null;
		Basis basis = new Basis();
		webInfPath = basis.getServerPath()  + appSettings.getUploadFolder();;

		String path = String.format("%s%s/%s/", webInfPath, actualFile.charAt(0), actualFile.charAt(1));
		return path;
	}

	public Attachment() {
		super();
	}



	public Attachment(String id, String fileName, String fileExt, Long fileSize, String actualFile, String explain,
					  String bizID, Date uploadTime, int source) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.fileExt = fileExt;
		this.fileSize = fileSize;
		this.actualFile = actualFile;
		this.explain = explain;
		this.bizID = bizID;
		this.uploadTime = uploadTime;
		this.source = source;
	}

	public Attachment(String id, String fileName, String fileExt, long fileSize, String actualFile, String bizID,
					  Date uploadTime, String module, Integer source) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.fileExt = fileExt;
		this.fileSize = fileSize;
		this.actualFile = actualFile;
		this.bizID = bizID;
		this.uploadTime = uploadTime;
		this.module = module;
		this.source = source;
	}

	public void WGS84ToXian80()
	{
		Param7Config param7 = new Param7Config();
		param7 = param7.GetConfig();

		if (this.source == AttachmentSource.Moblie && null != this.x && null != this.y) {
			BLHCoordinate blh_84 = new BLHCoordinate(this.x, this.y, 0d);
			XYZCoordinate xyz_84 = CoordinateHelper.BLHtoXYZ(blh_84, CoordinateHelper.WGS84Datum);
			XYZCoordinate xyz_80 = CoordinateHelper.Param7(xyz_84, param7.DeltaX, param7.DeltaY, param7.DeltaZ, param7.Rx, param7.Ry, param7.Rz, param7.K);
			BLHCoordinate blh_80 = CoordinateHelper.XYZtoBLH(xyz_80, CoordinateHelper.Xian80Datum);
			this.x = blh_80.getLongitude();// .Longitude;
			this.y = blh_80.getLatitude();//.Latitude;
		}
	}

}
