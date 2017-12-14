package phalaenopsis.satellitegraph.workflowbranches;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import phalaenopsis.workflowEngine.core.IBranchSelector;
import phalaenopsis.workflowEngine.core.NodeDataContext;

public class DoubtIllegalSelector implements IBranchSelector {

	@Override
	public String selectBranch(NodeDataContext dataContext, String currentNode, String currentBranch) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		String  tag = (String) request.getAttribute("Tag");
		List<String> nextBranches = new ArrayList<String>() ;
		nextBranches.add("ProofForLegal");
		nextBranches.add("ProofForLegal-Oil");
		nextBranches.add("MakeSureIllegal");
		nextBranches.add("ProofForNotNew");
		for (String nextBranche: nextBranches ) {
			if (nextBranche.toUpperCase().equals(tag.toUpperCase())) {
				return nextBranche;
			}
		}
		return tag;
	}

}
