package phalaenopsis.allWeather.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import phalaenopsis.allWeather.entity.HandleProgress;
import phalaenopsis.allWeather.entity.MapSpotStatistics;
import phalaenopsis.allWeather.entity.SwExpert;
import phalaenopsis.allWeather.entity.SwLog;
import phalaenopsis.allWeather.entity.SwMapspot;
import phalaenopsis.allWeather.entity.SwSde;
import phalaenopsis.common.entity.Condition;

public interface IallWeatherdao {
	
	/**
	 * 获取区县处理进度
	 * @param regionId
	 * @return
	 */
	HandleProgress getCountyProgress(Map<String, Object> map);

	void updateSwMapSpot(SwMapspot mapSpot);

	/**
	 * 保存或更新上报信息
	 * @param swLog
	 * @return
	 */
	int saveOrUpdateSwLog(SwLog swLog);


	/**
	 * 获取图斑集合
	 * @param map
	 * @return
	 */
	int getMapspotCount(Map<String, Object> map);
	
	/**
	 * 查询集合
	 * @param map
	 * @return
	 */
	List<SwMapspot> getMapspotList(Map<String, Object> map);
	
	/**
	 * 根据图斑号模糊匹配
	 * @param map
	 * @return
	 */
	List<SwMapspot> getMapspotListBlur(Map<String, Object> map);

	/**
	 * 保存或更新
	 * @param swExpert
	 */
	void saveOrUpdateSwExpert(SwExpert swExpert);
	
	/**
	 * 获取mapSpot实体
	 * @param id
	 * @return
	 */
	SwMapspot getSwMapspot(@Param("id") Long id);
	
	/**
	 * 获取核查信息
	 * @param id
	 * @return
	 */
	SwExpert getSwExpert(@Param("id") Long id);
	
	/**
	 * 移动端获取图斑坐标
	 * @param county
	 * @return
	 */
	List<SwSde> getSwSde(@Param("county") Integer county, @Param("spotNumber") String spotNumber, @Param("year") String year, @Param("period") String period);

	/**
	 * 移动端定位
	 * @param id
	 * @return
	 */
	String getSwSdeById(@Param("id") Long id);

	/**
	 * 获取回退信息
	 * @param regionID
	 * @return
	 */
	SwLog geBackInfo(Map<String, Object> map);

	/**
	 * 更新图斑状态未市级状态
	 * @param regionID
	 * @return
	 */
	int updateNodeCityState(Map<String, Object> map);
	
	/**
	 * 获取当前最大期数
	 * @return
	 */
	List<String> getCurrentPeriod();
	
	/**
	 * 获取最大年份
	 * @return
	 */
	int getCurrentYear();
	
	/**
	 * 获取图斑的状态
	 * @param id
	 * @return
	 */
	Integer getSpotNode(@Param("id") Long id);

	int restore(Map<String, Object> ids);
	
	int updateSpotNode(SwMapspot mapspot);
	
	/**
	 * 删除外业核查信息
	 * @param id
	 * @return
	 */
	int deleteSpotExpert(@Param("id") Long id);

	List<MapSpotStatistics> getCityList(Map<String, Object> map);

	MapSpotStatistics searchMapSpotNum(MapSpotStatistics mapSpotStatistics);

	MapSpotStatistics searchMapSpotSum(Map<String, Object> map);

	int getCityListCount(Map<String, Object> map);

	String getCityNameById(String string);

	int updateMarkMapSpot(@Param("mark") String mark, @Param("id") Long id);

	List<SwMapspot> listMapSpotMark(Map<String, Object> map);

	int countMapSpotMark(Map<String, Object> map);

	int deleteMapSpot(String[] ids);

    int updateMapSpotLevel(Map<String, Object> map);


    void markMapSpotList(Map<String, Object> map);
}
