package phalaenopsis.pjmapspot.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import phalaenopsis.pjmapspot.bean.PjMapSpotBean;
import phalaenopsis.pjmapspot.entity.PjRegionflow;

/**
 * 项目名称：Phalaenopsis
 * 创建日期：2017/10/24
 * 修改历史：
 * 1. [2017/10/24]创建文件
 *
 * @author chunl
 */
public interface PjRegionFlowDao {
	
	
	/**
	 * @param pjMapSpotBean
	 * @param listPjMapSpotBean
	 * @return int
	 */
    Integer saveOrUpdate(@Param(value="pjMapSpotBean")PjMapSpotBean pjMapSpotBean,@Param(value="listPjMapSpotBean")List<PjRegionflow> listPjMapSpotBean);
    
   /**
    * long
    * @return
    */
    Long querySeq();
    
	/**
	 * 
	 * @param pjMapSpotBean
	 * @param listPjMapSpotBean
	 * @return list
	 */
    List<PjRegionflow> getList(PjRegionflow pjRegionflow);

	Integer updatePJMapSpotInfoState(@Param(value="regionId")List<Long> regionId);
    
}
