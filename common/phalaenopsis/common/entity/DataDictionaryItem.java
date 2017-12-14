package phalaenopsis.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;



public class DataDictionaryItem implements Serializable {
	
	/**
	 * 序列化ID号
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("ID")
	public int id;
	
	@JsonProperty("Module")
	public String module;
	
	@JsonProperty("Type")
	public String type;
	
	@JsonProperty("Value")
	public String value;
	
	@JsonProperty("Text")
	public String text;
	
	@JsonProperty("Parent")
	public String parent;
	

	@JsonProperty("Rank")
	public int rank;
	
	@JsonProperty("Remarks")
	public String remarks;

	/**
	 * 字典别名
	 */
	private String alias;

	/**
	 * 模块别名
	 */
	private String moduleAlias;

	/**
	 * 是否为系统默认字典。1表示是
	 */
	private boolean isSystem;

	@JsonIgnore
	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getModuleAlias() {
		return moduleAlias;
	}

	public void setModuleAlias(String moduleAlias) {
		this.moduleAlias = moduleAlias;
	}

	public boolean isSystem() {
		return isSystem;
	}

	public void setSystem(boolean system) {
		isSystem = system;
	}
}
