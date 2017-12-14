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
 * 2017年5月5日下午3:20:51
 */
public abstract class BaseAnalysisBehavior implements IAnalysisBehavior {
	 protected String error = "error";

     private boolean _isNeedUnion;

     /**
      * 获取分析结果 
      * @param fields
      * @param url
      * @param polygon
      * @param statisticField
      * @param geometryServer
      * @return
      */
     public abstract List<AnalysisRowItem> GetAnalysisItems(List<Field> fields, String url, Polygon polygon, List<TwoTuple<String, Object>> statisticField, String geometryServer);

     public abstract void initBehaviorParam(Map<String, Object> param);

	/**
	 * @return the _isNeedUnion
	 */
	public boolean is_isNeedUnion() {
		return _isNeedUnion;
	}

	/**
	 * @param _isNeedUnion the _isNeedUnion to set
	 */
	public void set_isNeedUnion(boolean _isNeedUnion) {
		this._isNeedUnion = _isNeedUnion;
	} 
}
