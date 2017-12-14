package phalaenopsis.allWeather.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import phalaenopsis.allWeather.entity.HandleProgress;
import phalaenopsis.allWeather.entity.SwAuditspot;
import phalaenopsis.allWeather.entity.SwLog;
import phalaenopsis.allWeather.entity.SwMapSpotAuditStatistic;
import phalaenopsis.allWeather.entity.SwMapSpotStatistic;
import phalaenopsis.allWeather.entity.SwMapspot;
import phalaenopsis.allWeather.entity.YearAndPeriod;

/**
 * 
 * @author dongdongt
 *
 */
public interface IallWeatherCitydao {
	/**
	 * 根据组织机构ID查询上报信息
	 * @param id
	 * @return
	 */
	List<SwLog> getLogs(Map<String, Object> map);
	/**
	 * 获取历史核查期数列表
	 * @return
	 */
	List<YearAndPeriod> getHistoryPeroid();
	
	/**
	 * 获取历史下发期数列表
	 * @return
	 */
	List<YearAndPeriod> getHistoryxfPeroid();
	/**
	 * 查询当前用户上报情况
	 * @param regionId
	 * @return
	 */
	SwLog getLog(Map<String, Object> map);
	/**
	 * 根据组织机构ID查询图斑信息
	 * @param id
	 * @return
	 */
	List<SwMapspot> getAllMapSpot(Map<String, Object> map);
	/**
	 * 根据组织机构ID获取图斑信息
	 * @param map
	 * @return
	 */
	List<HandleProgress> getAllMapSpotByRegion(Map<String, Object> map);
	/**
	 * 根据组织机构ID查询审核未通过的图斑信息
	 * @param id
	 * @return
	 */
	List<SwMapspot> getMapSpotByNoPass(Map<String, Object> map);
	
	/**
	 * 判断级别
	 * @param id
	 * @return
	 */
	List<SwMapspot> getMapSpotLevel(Map<String, Object> map);
	
	/**
	 * 根据组织机构ID查询审核信息
	 * @param id
	 * @return
	 */
	List<SwAuditspot> getAudits(Integer id);
	/**
	 * 查询单个图斑信息
	 * @param id
	 * @return
	 */
	SwMapspot getSwMapspot(@Param("id") Long id);
	/**
	 * 上报或回退
	 * @param auditspot
	 */
	boolean saveOrUpdateSwLog(SwLog swLog);
	/**
	 * 根据ID删除
	 * @param id
	 */
	void deleteById(@Param("id") Long id);
	/**
	 * 更新批量审核状态
	 * @param swLog
	 */
	void updateLogState(SwLog swLog);
	/**
	 * 查询图斑条数
	 * @param map
	 * @return
	 */
    int getMapspotCount(Map<String, Object> map);
    /**
     * 分页查询图斑信息
     * @param map
     * @return
     */
    List<SwMapspot> getSwMapspots(Map<String, Object> map);
    /**
	 * 查询图斑统计条数
	 * @param map
	 * @return
	 */
    int getStatisticCount(Map<String, Object> map);
    
    /**
	 * 查询图斑举证情况统计条数
	 * @param map
	 * @return
	 */
    int getLegalproofStatisticCount(Map<String, Object> map);
    /**
	 * 查询图斑审核情况统计条数
	 * @param map
	 * @return
	 */
    int getAuditStatisticCount(Map<String, Object> map);
    /**
     * 图斑统计列表查询
     * @param map
     * @return
     */
    List<SwMapspot> getStatisticSwMapspots(Map<String, Object> map);
    
    /**
     * 图斑举证情况统计列表查询
     * @param map
     * @return
     */
    List<SwMapSpotStatistic> getLegalproofStatistic(Map<String, Object> map);
    
    /**
     * 图斑审核情况统计列表查询
     * @param map
     * @return
     */
    List<SwMapSpotAuditStatistic> getAuditStatistic(Map<String, Object> map);
    /**
     * 图斑审核情况统计合计
     * @param map
     * @return
     */
    SwMapSpotAuditStatistic getSumAuditStatistic(Map<String,Object> map);
    /**
     * 图斑举证情况统计合计
     * @param map
     * @return
     */
    SwMapSpotStatistic getSumSpotStatistic(Map<String,Object> map);
    /**
     * 保存审核信息
     * @param auditspot
     * @return
     */
    int saveOrUpdateSwAudit(SwAuditspot auditspot);
    /**
     * 批量审核
     * @param map
     * @return
     */
     int saveAudits(Map<String, Object> map);
    /**
     * 批量修改图斑信息
     * @param ids
     * @return
     */
     
    void batchUpdate(@Param("ids") List<Long> ids,@Param("organizationGrade") int organizationGrade);  
    /**
    * 批量修改图斑信息level
    * @param ids
    * @return
    */
    void batchUpdateLevel(@Param("ids") List<Long> ids,@Param("organizationGrade") int organizationGrade);  
    /**
     * 修改上报图斑的状态
     * @param mapspot
     */
    void batchUpdateAppear(SwMapspot mapspot);
    
    /**
     * 修改上报图斑level值
     * @param mapspot
     */
    void batchUpdateAppearLevel(SwMapspot mapspot);
    /**
     * 获取最新期数
     * @return
     */
    int getNewPeroid();
}
