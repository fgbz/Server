package phalaenopsis.pjmapspot.dao;

import phalaenopsis.common.entity.Region;
import phalaenopsis.pjmapspot.bean.PjMapSpotFlowAuditBean;
import phalaenopsis.pjmapspot.entity.PjMapSpot;
import phalaenopsis.pjmapspot.entity.PjMapSpotReport;

import java.util.List;
import java.util.Map;

/**
 * 项目名称：Phalaenopsis
 * 创建日期：2017/10/24
 * 修改历史：
 * 1. [2017/10/24]创建文件
 *
 * @author chunl
 */
public interface PjMapSpotDao {

    /**
     * 根据条件查询PJ图斑集合
     * @param map 查询条件
     * @return 返回查询PJ图斑集合
     */
    List<PjMapSpot> listPjMapSpot(Map<String,Object> map);

    /**
     * 根据条件查询PJ图斑个数
     * @param map 查询条件
     * @return 返回查询PJ图斑个数
     */
    Integer countPjMapSpot(Map<String, Object> map);

//    /**
//     * 更新数据
//     * @param map 更新的数据
//     * @return 返回更新结果
//     */
//    Integer updatePjMapSpot(Map<String, Object> map);

    /**
     * 查询单个Pj图斑
     * @param id pj图斑id
     * @return 返回查询结果
     */
    PjMapSpot getPjMapSpot(long id);

    /**
     * PJ图斑统计
     * 2017年10月25日
     * @author jund
     */
	List<PjMapSpot> listMapSpot(Map<String, Object> map);

	int listMapSpotCount(Map<String, Object> map);

	/**
     * PJ图斑详情展示
     * 2017年10月26日
     * @author jund
     */
	PjMapSpot getPJMapSpotInfo(long id);

	PjMapSpot getPJMapSpotFlowInfo(long id);

	/**
     * 区县图斑填报
     * 2017年10月27日
     * @author jund
     */
	int updatePJMapSpotInfo(PjMapSpot spot);
	
	int updatePJMapSpotInfoed(PjMapSpot spot);

	List<PjMapSpotReport> getPJMapSpotReport(Map<String, String> map);

	List<PjMapSpotReport> getPJMapSpotProReport(String year);


	/**
	 * @param pjMapSpotFlowAuditBean
	 * @return int
	 */
    Integer flowAudit(PjMapSpotFlowAuditBean pjMapSpotFlowAuditBean);

	List<PjMapSpotReport> getPJMapSpotCountyReport(Map<String, String> map);

	PjMapSpotReport getPJMapSpotIsReport(Map<String, String> map);

	List<PjMapSpotReport> getUnPJMapSpotReport(Map<String, String> map);

	List<Region> getRegionTree(String parentID);

	List<String> getPjMapSpotYear();

	
}
