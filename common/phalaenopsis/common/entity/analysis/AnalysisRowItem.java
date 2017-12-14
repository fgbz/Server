/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.common.entity.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import phalaenopsis.satellitegraph.entity.Polygon;

/**
 * @author yangw786
 *
 * 2017年5月4日下午2:44:33
 * 分析返回结果项
 */
public class AnalysisRowItem {
	
	/**
	 * 字段属性值
	 */
	public List<AnalysisColumnItem> Columns;
	
	/**
	 * 坐标串json格式返回
	 */
	public Polygon Coordinates;
	
	/**
	 * 当前行的面积
	 */
	public double Area;
	
	/**
	 * Tag标记，用于绑定部分必要的额外信息
	 */
	public HashMap<String, Object> Tag;
	  
	public List<AnalysisColumnItem> getColumns() {
		return Columns;
	}
 
	public void setColumns(List<AnalysisColumnItem> columns) {
		Columns = columns;
	}
 
	public Polygon getCoordinates() {
		return Coordinates;
	}
 
	public void setCoordinates(Polygon coordinates) {
		Coordinates = coordinates;
	}
 
	public double getArea() {
		return Area;
	}
 
	public void setArea(double area) {
		Area = area;
	}
 
	public HashMap<String, Object> getTag() {
		return Tag;
	}




	public void setTag(HashMap<String, Object> tag) {
		Tag = tag;
	}




	public AnalysisRowItem() {
		this.Columns = new ArrayList<AnalysisColumnItem>();
	}
}
