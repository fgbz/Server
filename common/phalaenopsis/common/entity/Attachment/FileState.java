package phalaenopsis.common.entity.Attachment;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import phalaenopsis.common.method.Json.NetDateJsonSerializer;

public class FileState {
	
	/**
	 * 文件名称
	 */
	@JsonProperty("FileName")
	private String fileName;
	
	@JsonProperty("Success")
	private boolean success;
	
	@JsonProperty("FileID")
	private String fileID;

	@JsonSerialize(using=NetDateJsonSerializer.class)
	@JsonProperty("UploadTime")
	private Date uploadTime;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getFileID() {
		return fileID;
	}

	public void setFileID(String fileID) {
		this.fileID = fileID;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	
	
	public FileState() {
		super();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public FileState(boolean success, String fileID, Date uploadTime, String fileName) {
		super();
		this.success = success;
		this.fileID = fileID;
		this.uploadTime = uploadTime;
		this.fileName = fileName;
	}
	
	
}
