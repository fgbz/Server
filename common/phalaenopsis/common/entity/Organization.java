/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.common.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;

//import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author yangw786
 *
 * 2017年3月7日下午2:55:30
 */
public class Organization {
	/// <summary>
    /// 组织/部门ID
    /// </summary>
	@JsonProperty("ID")
    public String ID;

    /// <summary>
    /// 组织/部门名称
    /// </summary>
	@JsonProperty("Name")
    public String Name; 

    /// <summary>
    /// 级别（省级/市级/县级）
    /// </summary>
	@JsonProperty("Grade")
    public int Grade;

    /// <summary>
    /// 类型（组织/部门）
    /// </summary>
	@JsonProperty("Type")
    public int Type;

    /// <summary>
    /// 父级组织/部门ID
    /// </summary>
	@JsonProperty("ParentID")
    public String ParentID;

    /// <summary>
    /// 在同一层级内的排序顺序
    /// </summary>
	@JsonProperty("Rank")
    public int Rank;
    
    @JsonProperty("MapLayerGrade")
    public Integer MapLayerGrade;


	/// <summary>
    /// 关联的区域的ID列表
    /// </summary>
    @JsonProperty("Regions")
    public List<Integer> reg;
    

    public Integer getMapLayerGrade() {
		return MapLayerGrade;
	}

	public void setMapLayerGrade(Integer mapLayerGrade) {
		MapLayerGrade = mapLayerGrade;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(String iD) {
		ID = iD;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}
    
	public int getGrade() {
		return Grade;
	}
	
	public void setGrade(int grade){ 
		Grade = grade;
	}
	
	public int getType() {
		return Type;
	}
	
	public void setType(int type){ 
		Type = type;
	}
	
	public String getParentID() {
		return ParentID;
	}
	
	public void setParentID(String parentID){ 
		ParentID = parentID;
	}
	
	public int getRank() {
		return Rank;
	}
	
	public void setRank(int rank){ 
		Rank = rank;
	}

	/**
	 * @return the reg
	 */
	public List<Integer> getReg() {
		return reg;
	}

	/**
	 * @param reg the reg to set
	 */
	public void setReg(Integer[] reg) {
		if (reg != null && !"".equals(reg)) {
			this.reg = Arrays.asList(reg);
		}
	}

	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}
	
    
}
