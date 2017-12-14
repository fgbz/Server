package phalaenopsis.common.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import phalaenopsis.common.entity.MapLayer;

@Repository("mapServiceDao")
public class MapServiceDao {
	
	@Resource
	private SqlSession sqlSession;
	
	public List<MapLayer> getMapLayerCollection(int type) {
		return sqlSession.selectList("mapService.getMapLayerCollection", type);
	}

	public List<MapLayer> getIdentifyFields(Map<String, Object> map) {
		return sqlSession.selectList("mapService.getIdentifyFields", map);
	}

	/**
	 * 得到经纬度坐标点与平面坐标点的偏移量
	 * @param map 传递的经纬度坐标点
	 * @return 返回偏移量
	 */
	public Map<String, Object> getPointShift(Map<String, Object> map){
		return sqlSession.selectOne("mapService.getPointShift", map);
	}
}
