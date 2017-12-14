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
 * 2017年5月4日下午2:57:39
 * 字段组合的模式 分析过程如果需要针对字段进行合并
 * 那么除了合并关键字外的字段 根据设定的模式进行组合
 */
public class CombinMode {
	/**
	 * 组合，值为字符串类型是，将相同合并关键字外的字段通过逗号，组合为一个字段显示
	 */
	 public static final short Combination = 0;
	 
	 /**
	  * 相加值为数字类型才可以
	  */
	 public static final short Add = 1;
	 
	 /**
	  * 平均值 值为数字类型才可以
	  */
	 public static final short Avg = 2;
}
