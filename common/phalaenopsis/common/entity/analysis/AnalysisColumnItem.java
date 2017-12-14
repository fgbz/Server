/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.common.entity.analysis;

/**
 * @author yangw786
 *
 * 2017年5月4日下午2:18:19
 * 分析返回结果项
 */
public class AnalysisColumnItem {
	/**
	 * 名 用于显示
	 */
	 public String Name;
	 /**
	  * 值
	  */
	 public Object Value;
	 
	
	 public AnalysisColumnItem(){
		 Value = "";
	 }
	 public AnalysisColumnItem(String name, Object value) {
		super();
		Name = name;
		Value = value;
	}
	 
}
