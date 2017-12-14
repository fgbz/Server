package phalaenopsis.satellitegraph.workflowbranches;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import phalaenopsis.satellitegraph.entity.ApproveState;
import phalaenopsis.satellitegraph.entity.MapSpot2016;
import phalaenopsis.workflowEngine.core.IBranchSelector;
import phalaenopsis.workflowEngine.core.NodeDataContext;

public class ProofMaybeNewProvinceSelector implements IBranchSelector{

	@Override
	public String selectBranch(NodeDataContext dataContext, String currentNode, String currentBranch) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		
		MapSpot2016 spot = (MapSpot2016) request.getAttribute("MapSpotData");

         switch (spot.getTheAuditSpot().getProvinceAuditIsPass())
         {
             case ApproveState.IegalPass:
                 return "Pass";
             case ApproveState.IllegalNotCase:
                 return "NoPass";
             case ApproveState.IllegalNotPass:
                 return "NoPass";
             case ApproveState.IllegalPass:
                 return "NoPass";
             case ApproveState.NotNewSpotNotPass:
                 return "NoPass-NotNew";
         }

         throw new IllegalArgumentException();
	}

}
