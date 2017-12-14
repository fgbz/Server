/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.common.entity.analysis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangw786
 *
 * 2017年5月4日下午2:16:11
 * 分析返回结果
 */
public class AnalysisResult implements Serializable {
	
	
	private static final long serialVersionUID = -6654590166872781571L;

	/**
	 * 图层中的要素项
	 */ 
    public List<AnalysisRowItem> Items;

    /**
     * 列头的名称
     */ 
    public List<String> ColumnNames;

    /**
     * 图层名称 
     */
    public String LayerName; 

    /**
     * 服务返回单位为 平方米
     */
    public double SpotArea;

    /**
     * 最大展示列属性
     */
    public List<AnalysisColumnItem> MaxDisplayColumn;

    /**
     * 用于分析界面对应图层加载
     */
    public AttachedLayerAttributes LayerAttributes; 

    public AnalysisResult() {
        this.MaxDisplayColumn = new ArrayList<AnalysisColumnItem>();
        this.ColumnNames = new ArrayList<String>();
        this.LayerAttributes = new AttachedLayerAttributes();
    }
} 