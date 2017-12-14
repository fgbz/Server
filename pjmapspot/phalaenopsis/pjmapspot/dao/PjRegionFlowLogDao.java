package phalaenopsis.pjmapspot.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import phalaenopsis.pjmapspot.entity.PjRegionflow;

/**
 * 项目名称：Phalaenopsis
 * 创建日期：2017/10/24
 * 修改历史：
 * 1. [2017/10/24]创建文件
 *
 * @author weiz2902
 */
public interface PjRegionFlowLogDao {
	
	
	/**
	 * @param listPjMapSpotBean
	 * @return int
	 */
    Integer saveOrUpdate(@Param(value="listPjMapSpotBean")List<PjRegionflow> listPjMapSpotBean);
    
    
}
