package phalaenopsis.satellitegraph.workflowbranches;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import phalaenopsis.satellitegraph.entity.ApproveState;
import phalaenopsis.satellitegraph.entity.MapSpot2016;
import phalaenopsis.workflowEngine.core.IBranchSelector;
import phalaenopsis.workflowEngine.core.NodeDataContext;

public class ProofCitySelector implements IBranchSelector {

	@Override
	public String selectBranch(NodeDataContext dataContext, String currentNode, String currentBranch) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		MapSpot2016 spot = (MapSpot2016) request.getAttribute("MapSpotData");
		String op = (String) request.getAttribute("op");

		if (op.equals("Report")) {
			return "Report";
		} else if (op.equals("Back")) {
			switch (spot.getTheAuditSpot().getCityAuditIsPass()) {
			case ApproveState.Pass:
				return "Back-Pass";
			case ApproveState.NoPass:
				return "Back-NoPass";
			}
		}
		throw new IllegalArgumentException();
	}

}
