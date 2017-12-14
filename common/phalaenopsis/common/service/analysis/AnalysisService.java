/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.common.service.analysis;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phalaenopsis.common.dao.AnalysisDao;
import phalaenopsis.common.entity.analysis.AnalysisResult;
import phalaenopsis.common.entity.analysis.SpotAnalysisResult;
import phalaenopsis.satellitegraph.entity.Polygon;

/**
 * @author yangw786
 *
 * 2017年5月4日下午2:12:34
 * 图斑分析服务实现类
 */
@Service("analysisService")
public class AnalysisService {
	@Autowired
	private AnalysisDao analysisDao;
	
	/**
	 * 根据不同的设备与不同的业务类型，提供不同的分析结果
	 * @param businessType 业务类型
	 * @param terminalType 终端类型
	 * @param displayType 
	 * @param polygon 被分析的坐标对象 arcgis 对象
	 * @return
	 */
	public List<AnalysisResult> analysis(Map<String,Object> map) {
		return analysisDao.analysis(map);
	}

	/**
	 * 根据行政区划名称获取其位置数据
	 * 调用Arcgis服务获取
	 * @param regionName 行政区划名称
	 * @return
	 * 根据行政区划名称获取其位置数据,客户Arcgis服务中采用的是名称匹配
	 */
	public Polygon getRegeionShape(String regionName) {
		return analysisDao.getRegeionShape(regionName);
	}

	/**
	 * 获取已经保存的分析结果
	 * @param id
	 * @param source
	 * @return
	 */ 
	public SpotAnalysisResult getAnalysisResult(String id, int source) {
		return analysisDao.getAnalysisResult(id, source);
	}

	/**
     * 保存分析的结果,以免下次分析时间过长
     * @param anresult
     * @return
     */
	public long saveAnalysisResult(SpotAnalysisResult anresult) {
		return analysisDao.saveAnalysisResult(anresult);
	}

	/**
     * 删除分析结果
     * @param BusinessID
     * @return
     */
	public boolean deleteAnalysisResult(String BusinessID) {
		return analysisDao.deleteAnalysisResult(Long.parseLong(BusinessID));
	}
}
