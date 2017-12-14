/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.common.entity;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author yangw786
 *
 * 2017年3月9日上午10:21:59
 */
public class TreeNode {
	/*节点业务对象Id*/ 
	@JsonProperty("ObjectId")
    public String ObjectID;

    /*节点名称*/  
	@JsonProperty("name")
    public String Name;

    /*父节点ID*/
	@JsonProperty("parentOId")
    public String ParentID;
	
	/*在线人数*/
	@JsonProperty("onlineNum")
    public int onlineNum;
	/*总人数*/
	@JsonProperty("lineNum")
	public int lineNum;
	
	/*当前级在线人数*/
	@JsonProperty("currentOnlineNum")
	public int currentOnlineNum;
	/*当前级总人数*/
	@JsonProperty("currentLineNum")
    public int currentLineNum;
	
    /*节点业务对象类型：组织机构/部门 等*/
    public int ObjectType;

    /*组织级别：区、市、省*/
    public int Grade;

	/**
	 * @return the objectID
	 */
	public String getObjectID() {
		return ObjectID;
	}

	/**
	 * @param objectID the objectID to set
	 */
	public void setObjectID(String objectID) {
		ObjectID = objectID;
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

	/**
	 * @return the parentID
	 */
	public String getParentID() {
		return ParentID;
	}

	/**
	 * @param parentID the parentID to set
	 */
	public void setParentID(String parentID) {
		ParentID = parentID;
	}

	/**
	 * @return the objectType
	 */
	public int getObjectType() {
		return ObjectType;
	}

	/**
	 * @param objectType the objectType to set
	 */
	public void setObjectType(int objectType) {
		ObjectType = objectType;
	}

	/**
	 * @return the grade
	 */
	public int getGrade() {
		return Grade;
	}

	/**
	 * @param grade the grade to set
	 */
	public void setGrade(int grade) {
		Grade = grade;
	}

	public int getOnlineNum() {
		return onlineNum;
	}

	public void setOnlineNum(int onlineNum) {
		this.onlineNum = onlineNum;
	}

	public int getLineNum() {
		return lineNum;
	}

	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}

	public int getCurrentOnlineNum() {
		return currentOnlineNum;
	}

	public void setCurrentOnlineNum(int currentOnlineNum) {
		this.currentOnlineNum = currentOnlineNum;
	}

	public int getCurrentLineNum() {
		return currentLineNum;
	}

	public void setCurrentLineNum(int currentLineNum) {
		this.currentLineNum = currentLineNum;
	}
     
}
