/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.common.service.analysis;

import phalaenopsis.common.entity.analysis.AnalysisMode;
import phalaenopsis.common.entity.analysis.IAnalysisBehavior;

/**
 * @author yangw786
 *
 * 2017年5月5日下午3:05:18
 */
public class AnalysisBehaviorFactory {
	/**
	 * 根据图层不同的模式，返回不同的套合分析行为
	 * @param mode
	 * @return
	 */
	public static IAnalysisBehavior getBehavior(short analysisMode) {
		IAnalysisBehavior behavior = null;

		switch (analysisMode) {
		case AnalysisMode.ArcGIS_WFS:
		case AnalysisMode.ArcGIS_Query:
		case AnalysisMode.Default:
		default:
			behavior = new QueryConvertBehavior();
			break;
		}

		return behavior;
	}
}
