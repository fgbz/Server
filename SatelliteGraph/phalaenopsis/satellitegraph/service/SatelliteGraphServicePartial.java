package phalaenopsis.satellitegraph.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phalaenopsis.common.entity.Page;
import phalaenopsis.satellitegraph.dao.SatelliteGraphDaoPartial;
import phalaenopsis.satellitegraph.entity.MapSpot;
import phalaenopsis.satellitegraph.entity.MapSpot2016;
import phalaenopsis.satellitegraph.entity.SatelliteListResult;

@Service("satelliteGraphServicePartial")
public class SatelliteGraphServicePartial{

	@Autowired
	private SatelliteGraphDaoPartial daoPartial;

	public boolean getSpotStatus(long id) {
		return daoPartial.getSpotStatus(id);
	}

	public boolean restore(MapSpot2016 spot) {
		return daoPartial.restore(spot);
	}



//	public void test() {
//
//		daoPartial.test();
//	}



	@SuppressWarnings("unchecked")
	public SatelliteListResult<MapSpot> queryList(Page param) {
		return daoPartial.queryList(param);
	}

	

	public boolean merge(List<MapSpot2016> sourceSpots, MapSpot2016 newSpot) {
		return daoPartial.merge(sourceSpots, newSpot);
	}
	


	@SuppressWarnings("rawtypes")
	public Map satelliteReportQueryList(Page param) {
		return daoPartial.satelliteReportQueryList(param);
	}

	public boolean segment(MapSpot2016 source, List<MapSpot2016> spots) {
		return daoPartial.segment(source, spots);
	}
	

}
