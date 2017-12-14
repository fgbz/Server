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
 * 2017年5月4日下午4:41:29
 * 图层中的字段
 */
public class Field {
	/**
	 * 静态字段，表示所有字段
	 */
    public static String Star = "*";

    public String Id;

    /**
     * 字段名称，图层中字段名 
     */
    public String Name;

    /**
     * 界面显示的字段名称 
     */
    public String Alias;

    /**
     * 是否属于合并字段
     * 叠加分析中 用于标识 需要合并的字段
     * 当Layer中设置Union时候，Layer中的已经配置的字段不是作为合并的关键字，就是被用于组合 或者 相加
     * 合并关键字字段   值为 true
     * 非合并关键字字段 值为 false
     */ 
    public boolean IsUnionKey;

    /**
     * 定义非合并关键字字段其值组合方式 IsUnionKey 为false 有效
     */ 
    public short CombinMode;

    /**
     * 是否为最大展示列属性 
     */
    public boolean IsMaxDisplayColumn;

    /**
     * 所属分析的图层 
     */
    public AnalysisLayer AnalysisLayer;

    public String ToString() {
        return this.Name;
    }
}
