package phalaenopsis.illegalclue.workflowbranches;

import org.springframework.beans.factory.annotation.Autowired;

import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.cache.WFCache;
import phalaenopsis.illegalclue.dao.IIllegalClueDaoNew;
import phalaenopsis.illegalclue.entity.Clue;
import phalaenopsis.illegalclue.entity.ClueAudit;
import phalaenopsis.illegalclue.entity.ClueJudge;
import phalaenopsis.illegalclue.entity.IllegalClue;
import phalaenopsis.illegalclue.enums.ClueAuditType;
import phalaenopsis.illegalclue.service.IllegalClueServiceNew;
import phalaenopsis.workflowEngine.core.IBranchSelector;
import phalaenopsis.workflowEngine.core.NodeDataContext;

public class AuditSelector extends Basis  implements IBranchSelector {
	@Autowired
	private IIllegalClueDaoNew dao;
	@Override
	public String selectBranch(NodeDataContext dataContext, String currentNode, String currentBranch) {
		
		Clue clue=(Clue) WFCache.getData(getAuthId()+ currentNode);
		ClueAudit clueAudit=clue.getcAudit();
		ClueJudge clueJudge=clue.getmClueJudge();
		if(clueAudit.getStatus()==0){
			//不同意，回退到登记节点
			//TODO
			return "back";
		}else{
				//审核同意
				if (clueJudge.getJudgeType() == 3) {
					// 属于处理范围，判断建议处理方式
					if (clueJudge.getHandleType() == 1) {
						// 本级办理,进入办结节点
						return "end";
					} else {
						// 转办或者交办,结束
						return "subHandle";
					}
				} else {
					// 不属于处理范围,结束
					return "unHandle";
			}
		}
	}
}
