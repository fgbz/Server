package phalaenopsis.allWeather.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import phalaenopsis.allWeather.entity.SwMapspot;

public interface IallWeatherSpotdao {
	
	/**
	 * 通过id、year、checkPeriod删除
	 * @param id
	 * @param year
	 * @param checkPeriod
	 */
	public Integer deleteSwMapSpot(@Param("list") List<Long> ids, @Param("year") int year, @Param("checkPeriod") int checkPeriod);
	
	/**
	 *  添加全天候图斑
	 * @param swMapspot
	 * @return
	 */
	public int insertSwMapSpot(SwMapspot swMapspot);
}
