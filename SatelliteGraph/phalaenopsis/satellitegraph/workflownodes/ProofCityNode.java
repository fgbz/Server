package phalaenopsis.satellitegraph.workflownodes;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import phalaenopsis.common.method.ManualAssembly;
import phalaenopsis.satellitegraph.dao.SatelliteGraphDao;
import phalaenopsis.satellitegraph.entity.ApproveState;
import phalaenopsis.satellitegraph.entity.MapSpot2016;
import phalaenopsis.workflowEngine.core.NodeFlowContext;
import phalaenopsis.workflowEngine.core.WorkflowNode;

public class ProofCityNode extends WorkflowNode {
	protected void Next(NodeFlowContext flowContext) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		MapSpot2016 mapSpot = (MapSpot2016) request.getAttribute("MapSpotData");

		String op = (String) request.getAttribute("op");

		if (mapSpot.getTheAuditSpot() == null) {
			return;
		}
		if (op.equals("Report")) {
			return;
		} else if (op.equals("Back")) {
			int isPass = mapSpot.getTheAuditSpot().getCityAuditIsPass();
			if (isPass == 0 || isPass == ApproveState.NoPass) {
				mapSpot.getTheAuditSpot().setCityAudit(false);
			} else {
				mapSpot.getTheAuditSpot().setCityAudit(true);
			}
		}
		SatelliteGraphDao satelliteGraphDao = ManualAssembly.getAssembly("satelliteGraphDao");
		satelliteGraphDao.saveAuditSpot(mapSpot.getTheAuditSpot());

	}
}
