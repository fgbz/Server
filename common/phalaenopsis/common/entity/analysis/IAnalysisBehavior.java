/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.common.entity.analysis;

import java.util.List;
import java.util.Map;

import phalaenopsis.common.entity.TwoTuple;
import phalaenopsis.satellitegraph.entity.Polygon;

/**
 * @author yangw786
 *
 * 2017年5月5日下午3:06:06
 */
public interface IAnalysisBehavior { 
    /**
     * 初始化分析行为额外的参数
     * 根据不同的行为初始化不同的参数 
     */
    void initBehaviorParam(Map<String, Object> param);

	/**
	 * 获取分析结果
	 * 
	 * @param fields
	 *            图层分析字段集合
	 * @param url
	 *            图层地址
	 * @param polygon
	 * @param statisticField
	 * @param geometryServer
	 * @return 返回的分析结果
	 */
	List<AnalysisRowItem> getAnalysisItems(List<Field> fields, String url, Polygon polygon,
			List<TwoTuple<String, Object>> statisticField, String geometryServer);
	
	
}
