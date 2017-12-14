/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.common.entity.analysis;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;


/**
 * @author yangw786
 *
 * 2017年5月4日下午4:14:48
 */
public class SpotAnalysisResult {
	/**
	 * 主键ID
	 */
	@JsonProperty("ID")
	public long id;
	
	/**
	 * 关联对象的ID
	 */
	@JsonProperty("BusinessID")
	public String businessID;

	/**
	* 分析结果
	*/
	@JsonProperty("Result")
	public String result;
	
	/**
	 * 分析来源
	 *//*
	public AnalysisSourceType Source;*/
	@JsonProperty("Source")
	public int source;
	/**
	 * 更新时间
	 */
	private Date timing;
	private Integer count;//辅助字段
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Date getTiming() {
		return timing;
	}

	public void setTiming(Date timing) {
		this.timing = timing;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBusinessID() {
		return businessID;
	}

	public void setBusinessID(String businessID) {
		this.businessID = businessID;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}
}
