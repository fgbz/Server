/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.common.entity.analysis;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangw786
 *
 * 2017年5月4日下午4:39:09
 * 用于分析的图层定义
 */
public class AnalysisLayer {
	public String Id;

    /**
     * 用于分析的图层的别名
     */ 
    public String Alias;

    /**
     * 分析需要返回的字段集合 
     */
    public List<Field> FieldCollection;

    /**
     * 是否范围所有的字段
     * 为返回所有字段，则忽略 FieldCollection 中的配置
     */  
    public String Star;

    /**分析的模式 
     * 目前只有一种，采用ArcGIS服务,预留字段 
     */ 
    public short Mode;

    /**
     * 分析的图层名 
     */
    public String Name;

    /**
     * 分析的图层服务地址
     * ArcGIS地址
     */   
    public String Url;

    /**
     * 分析的图层服务地址 映射地址（山东项目用于区县访问）
     * ArcGIS地址
     */ 
    public String MappingUrl;

    /**
     * 分析的图层类型
     */ 
    public String Type;

    /**
     * 所属终端业务分析图层组 
     */
    public AnalysisLayerGroup AnalysisLayerGroup;

    /**
     * 被分析的图层在服务中的序号 
     */
    public int LayerIndex;

    /**
     * 是否将图层中的字段进行合并
     * 如果为true 则需要合并，那么在 FieldCollection 集合中 Field设置的合并相关属性有效，反之 无效
     * 如果为false 不需要考虑字段合并的问题
     */ 
    public boolean IsNeedUnion;

    /**
     * 排序字段
     */ 
    public int SortOrder;

    public AnalysisLayer() {
        FieldCollection = new ArrayList<Field>();
    }

    public void AddFields(Field[] fields) {
    	for (Field field : fields) {
    		field.AnalysisLayer = this;
            this.FieldCollection.add(field);
		} 
    }

	/**
	 * @return the id
	 */
	public String getId() {
		return Id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		Id = id;
	}

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return Alias;
	}

	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		Alias = alias;
	}

	/**
	 * @return the fieldCollection
	 */
	public List<Field> getFieldCollection() {
		return FieldCollection;
	}

	/**
	 * @param fieldCollection the fieldCollection to set
	 */
	public void setFieldCollection(List<Field> fieldCollection) {
		FieldCollection = fieldCollection;
	}

	/**
	 * @return the star
	 */
	public String getStar() {
		return Star;
	}

	/**
	 * @param star the star to set
	 */
	public void setStar(String star) {
		Star = star;
	}

	/**
	 * @return the mode
	 */
	public short getMode() {
		return Mode;
	}

	/**
	 * @param mode the mode to set
	 */
	public void setMode(Short mode) {
		Mode = mode;
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
	 * @return the url
	 */
	public String getUrl() {
		return Url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		Url = url;
	}

	/**
	 * @return the mappingUrl
	 */
	public String getMappingUrl() {
		return MappingUrl;
	}

	/**
	 * @param mappingUrl the mappingUrl to set
	 */
	public void setMappingUrl(String mappingUrl) {
		MappingUrl = mappingUrl;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return Type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		Type = type;
	}

	/**
	 * @return the analysisLayerGroup
	 */
	public AnalysisLayerGroup getAnalysisLayerGroup() {
		return AnalysisLayerGroup;
	}

	/**
	 * @param analysisLayerGroup the analysisLayerGroup to set
	 */
	public void setAnalysisLayerGroup(AnalysisLayerGroup analysisLayerGroup) {
		AnalysisLayerGroup = analysisLayerGroup;
	}

	/**
	 * @return the layerIndex
	 */
	public int getLayerIndex() {
		return LayerIndex;
	}

	/**
	 * @param layerIndex the layerIndex to set
	 */
	public void setLayerIndex(int layerIndex) {
		LayerIndex = layerIndex;
	}

	/**
	 * @return the isNeedUnion
	 */
	public boolean isIsNeedUnion() {
		return IsNeedUnion;
	}

	/**
	 * @param isNeedUnion the isNeedUnion to set
	 */
	public void setIsNeedUnion(boolean isNeedUnion) {
		IsNeedUnion = isNeedUnion;
	}

	/**
	 * @return the sortOrder
	 */
	public int getSortOrder() {
		return SortOrder;
	}

	/**
	 * @param sortOrder the sortOrder to set
	 */
	public void setSortOrder(int sortOrder) {
		SortOrder = sortOrder;
	} 
}
