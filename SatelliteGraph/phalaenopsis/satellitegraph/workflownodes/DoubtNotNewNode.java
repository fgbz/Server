package phalaenopsis.satellitegraph.workflownodes;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import phalaenopsis.common.method.ManualAssembly;
import phalaenopsis.satellitegraph.dao.SatelliteGraphDao;
import phalaenopsis.satellitegraph.entity.MapSpot2016;
import phalaenopsis.workflowEngine.core.NodeFlowContext;
import phalaenopsis.workflowEngine.core.WorkflowNode;

public class DoubtNotNewNode extends WorkflowNode{

	@Override
	protected void Next(NodeFlowContext flowContext) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		MapSpot2016 mapSpot2016 = (MapSpot2016) request.getAttribute("MapSpotData");
		if (mapSpot2016 != null) {
			SatelliteGraphDao satelliteGraphDao = ManualAssembly.getAssembly("satelliteGraphDao");
			satelliteGraphDao.saveMapSpot2016(mapSpot2016);
		}
	}

}
