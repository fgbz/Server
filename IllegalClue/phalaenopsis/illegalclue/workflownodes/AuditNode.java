package phalaenopsis.illegalclue.workflownodes;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.ManualAssembly;
import phalaenopsis.common.method.cache.WFCache;
import phalaenopsis.illegalclue.dao.IIllegalClueDaoNew;
import phalaenopsis.illegalclue.entity.Clue;
import phalaenopsis.illegalclue.entity.ClueAudit;
import phalaenopsis.illegalclue.service.IllegalClueServiceNew;
import phalaenopsis.workflowEngine.core.NodeFlowContext;
import phalaenopsis.workflowEngine.core.WorkflowNode;

/**
 * 办理审核、办结审核
 * @author chunl
 *
 */
public class AuditNode extends WorkflowNode{

	@Override
	protected void Next(NodeFlowContext flowContext) {
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//				.getRequest();
//		ClueAudit clueAudit = (ClueAudit) request.getAttribute("ServerData");
		
//		ClueAudit clueAudit = (ClueAudit)WFCache.getData(Basis.getAuthId()+ "JudgeAuditNode");
		Clue illegalClue = (Clue)WFCache.getData(Basis.getAuthId()+ "JudgeAuditNode");
		ClueAudit clueAudit=illegalClue.getcAudit();
		clueAudit.setClueId(illegalClue.getId());
		IllegalClueServiceNew service = ManualAssembly.getAssembly("illegalClueServiceNew");
		service.saveOrUpdateClueAudit(clueAudit);
	}

}
