package phalaenopsis.workflowEngine.core;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;

import phalaenopsis.common.method.ManualAssembly;
import phalaenopsis.common.method.Tools.Linq;
import phalaenopsis.workflowEngine.storage.IWorkflowStorage;
import phalaenopsis.workflowEngine.storage.WFHistory;
import phalaenopsis.workflowEngine.storage.WFInstance;

/**
 * 
 * @author chunhongl
 *
 * 2017年4月6日下午4:23:35
 * 
 * 不包含节点流转过程记录的流转控制器
 */
class NoTraceFlowControl implements IFlowControl {
	
	@Autowired
	private IWorkflowStorage storage;
	
	@Override
	public String previous(IWorkflowInstance instance, NodeFlowContext flowContext) {

		if (flowContext.State == FlowState.RedirectHistory) {
			// 重定向新节点
			throw new NotImplementedException();
		}
		
		storage = ManualAssembly.getAssembly("WFOracleDBStorage");
		List<WFHistory> histories = storage.getHistories(instance.getInstanceID());
		int lastSequence = histories.get(0).Sequence;
		for (WFHistory wfHistory : histories) {
			if (wfHistory.Sequence > lastSequence) {
				lastSequence = wfHistory.Sequence;
			}
		}
		WFHistory preHistory = Linq.extEquals(histories, "Sequence", lastSequence);
		WFInstance obj = instance.getContext().getInstanceData();
		obj.SequencePtr--;
		WFHistory currentHistory = Linq.extEquals(histories, "Sequence", lastSequence - 1);

		if (currentHistory == null)
			return null;

		obj.NodeID = currentHistory.NodeID;
		// 保存数据并删除最后一条历史
		storage.saveAndRemoveLastHistory(obj, preHistory);
		return obj.NodeID;
	}
}

