/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.common.service.analysis;

import java.util.List;
import java.util.Map;

import phalaenopsis.common.entity.TwoTuple;
import phalaenopsis.common.entity.analysis.AnalysisRowItem;
import phalaenopsis.common.entity.analysis.Field;
import phalaenopsis.common.entity.analysis.IAnalysisBehavior;
import phalaenopsis.satellitegraph.entity.Polygon;

/**
 * @author yangw786
 *
 * 2017年5月5日下午3:20:51
 */
public abstract class BaseAnalysisBehavior implements IAnalysisBehavior {
	protected String error = "error";

	/**
	 * 是否合并
	 */
	public boolean isNeedUnion;

	public boolean isNeedUnion() {
		return isNeedUnion;
	}

	public void setNeedUnion(boolean isNeedUnion) {
		this.isNeedUnion = isNeedUnion;
	}

	/**
	 * 获取分析结果
	 * 
	 * @param fields
	 * @param url
	 * @param polygon
	 * @param statisticField
	 * @param geometryServer
	 * @return
	 */
	public abstract List<AnalysisRowItem> getAnalysisItems(List<Field> fields, String url, Polygon polygon,
			List<TwoTuple<String, Object>> statisticField, String geometryServer);

	/**
     * 初始化分析行为额外的参数
     * 根据不同的行为初始化不同的参数 
     */
	public abstract void initBehaviorParam(Map<String, Object> param);

}
