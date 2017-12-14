package phalaenopsis.patrolManagement.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import phalaenopsis.patrolManagement.entity.PatrolArea;

public interface PatrolAreaMapper {

	int getAreaCount(Map<String, Object> map);

	List<PatrolArea> getAreaList(Map<String, Object> map);

	int getAllAreaCount(Map<String, Object> map);

	List<PatrolArea> getAllAreaList(Map<String, Object> map);

	int deleteArea(@Param("id") String id);

	int saveArea(PatrolArea patrolarea);

}
