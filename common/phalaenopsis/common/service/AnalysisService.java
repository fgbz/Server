/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.common.service;

import java.util.List;

import phalaenopsis.common.entity.analysis.AnalysisResult;
import phalaenopsis.common.entity.analysis.AnalysisSourceType;
import phalaenopsis.common.entity.analysis.BusinessType;
import phalaenopsis.common.entity.analysis.DisplayType;
import phalaenopsis.common.entity.analysis.SpotAnalysisResult;
import phalaenopsis.common.entity.analysis.TerminalType;
import phalaenopsis.satellitegraph.entity.Polygon;

/**
 * @author yangw786
 *
 * 2017年5月4日下午1:21:41
 * 图斑分析服务
 */
public interface AnalysisService {
	/**
	 * 根据不同的设备与不同的业务类型，提供不同的分析结果
	 * @param businessType 业务类型
	 * @param terminalType 终端类型
	 * @param displayType 
	 * @param polygon 被分析的坐标对象 arcgis 对象
	 * @return
	 */
	List<AnalysisResult> analysis(BusinessType businessType, TerminalType terminalType, DisplayType displayType, Polygon polygon);

	/**
	 * 根据行政区划名称获取其位置数据
	 * 调用Arcgis服务获取
	 * @param regionName 行政区划名称
	 * @return
	 * 根据行政区划名称获取其位置数据,客户Arcgis服务中采用的是名称匹配
	 */
	Polygon getRegeionShape(String regionName);
	
	/**
	 * 获取已经保存的分析结果
	 * @param id
	 * @param source
	 * @return
	 */ 
    SpotAnalysisResult getAnalysisResult(String id, AnalysisSourceType source);

    /**
     * 保存分析的结果,以免下次分析时间过长
     * @param anresult
     * @return
     */
    long saveAnalysisResult(SpotAnalysisResult anresult);
    
    /**
     * 删除分析结果
     * @param BusinessID
     * @return
     */
    boolean deleteAnalysisResult(String BusinessID);
}
