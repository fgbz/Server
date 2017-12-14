package phalaenopsis.satellitegraph.entity;

import java.util.Date;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import phalaenopsis.common.method.Json.NetDateJsonSerializer;

/**
 * 上报、回退、违法不通过等操作记录
 * @author chunl
 *
 */
public class OperationLog {

	/**
	 * 主键
	 */
	public long ID;

	/**
	 * 操作描述/回退原因
	 */
	public String Description;

	/**
	 * 操作人ID
	 */
	public String Operater;

	/**
	 * 操作人名称
	 */
	public String OperaterName;

	/**
	 * 操作人组织机构ID
	 */
	public String Organization;

	/**
	 * 操作时间
	 */
	public Date OperationTime;

	/**
	 * 操作类型
	 */
	public String Type;

	/**
	 * 回退时间
	 */
	@JsonSerialize(using=NetDateJsonSerializer.class)
	public Date BackTime;

	/**
	 * 上报/回退区域
	 */
	public int Region;


	/**
	 * 年份
	 */
	public int Year;
	

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getOperater() {
		return Operater;
	}

	public void setOperater(String operater) {
		Operater = operater;
	}

	public String getOperaterName() {
		return OperaterName;
	}

	public void setOperaterName(String operaterName) {
		OperaterName = operaterName;
	}

	public String getOrganization() {
		return Organization;
	}

	public void setOrganization(String organization) {
		Organization = organization;
	}

	public Date getOperationTime() {
		return OperationTime;
	}

	public void setOperationTime(Date operationTime) {
		OperationTime = operationTime;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public Date getBackTime() {
		return BackTime;
	}

	public void setBackTime(Date backTime) {
		BackTime = backTime;
	}

	public int getRegion() {
		return Region;
	}

	public void setRegion(int region) {
		Region = region;
	}

	public int getYear() {
		return Year;
	}

	public void setYear(int year) {
		Year = year;
	}

	public OperationLog() {
		super();
	}
	
	public OperationLog(long iD, String operater, String operaterName, String organization) {
		super();
		ID = iD;
		Operater = operater;
		OperaterName = operaterName;
		Organization = organization;
	}
}
