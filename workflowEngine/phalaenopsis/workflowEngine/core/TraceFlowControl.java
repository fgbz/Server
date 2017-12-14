package phalaenopsis.workflowEngine.core;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import phalaenopsis.common.method.ManualAssembly;
import phalaenopsis.common.method.Tools.Linq;
import phalaenopsis.workflowEngine.storage.IWorkflowStorage;
import phalaenopsis.workflowEngine.storage.WFHistory;
import phalaenopsis.workflowEngine.storage.WFInstance;

public class TraceFlowControl implements IFlowControl {

	@Autowired
	private IWorkflowStorage storage;

	@Override
	public String previous(IWorkflowInstance instance, NodeFlowContext flowContext) {
		// 如果当前节点已经为流转的第一个节点，则throw
		// 退回上一步 需要保留分支的信息
		// 如果还在分支内，则分支Index--
		// 如果退出了分支的第一个节点，则清除分支信息，认为离开了分支

		// 要重定向的历史节点距离当前节点的步骤数
		//int preStep = 0;

		storage = ManualAssembly.getAssembly("WFOracleDBStorage");
		List<WFHistory> histories = storage.getHistories(instance.getInstanceID());
		int lastSequence = histories.get(0).Sequence;
		for (WFHistory wfHistory : histories) {
			if (wfHistory.Sequence > lastSequence) {
				lastSequence = wfHistory.Sequence;
			}
		}
		
		//WFHistory preHistory = Linq.extEquals(histories, "Sequence", lastSequence);
		WFInstance obj = instance.getContext().getInstanceData();
		obj.SequencePtr++;
		
		//WFHistory preHistory = Linq.extEquals(histories, "Sequence", lastSequence);
		
		WFHistory currentHistory = Linq.extEquals(histories, "Sequence", lastSequence - 1);

		obj.NodeID = currentHistory.NodeID;
		
		// 本次流转历史
		WFHistory newHistory = new WFHistory();
		newHistory.setID(UUID.randomUUID().toString());
		newHistory.setInstanceID(obj.getInstanceID());
		newHistory.setNodeID(obj.getNodeID());
		newHistory.setSequence(lastSequence + 1);
		
		// 保存数据并删除最后一条历史
		storage.save(obj, newHistory);
		return obj.NodeID;

//		if (flowContext.State == FlowState.RedirectHistory) {
//			// 重定向新节点
//			List<NodeInfo> historyNodes = instance.getHistoryNodeSequences();
//
//			if (!Linq.extExists(historyNodes, "NodeID", flowContext.RedirectedNode)) {
//				// throw new CannotFlowException("流转历史中没有该节点。");
//				throw new Exception("流转历史中没有该节点。");
//			}
//			// 如果某个节点在历史记录中出现多次，则只找到距离当前节点最近的一次作为重定向的历史节点
//			// 从当前节点的前一步开始循环
//			for (int i = historyNodes.size() - 2, j = 1; i >= 0; i--, j++) {
//				// j：该节点距离当前节点的步骤数
//				if (historyNodes.get(i).getNodeID().equals(flowContext.RedirectedNode)) {
//					preStep = j;
//					break;
//				}
//			}
//			NodeInfo redirectedNode = instance.getMap().getNode(flowContext.RedirectedNode);
//		}
//
//		WFInstance obj = instance.getContext().getInstanceData();
//		// int lastSequence = getLastSequence(instance);
//		int currentSequence = lastSequence + 1;
//		// 获取本次要退回的节点
//		if (flowContext.State == FlowState.RedirectHistory) {
//			// 重定向
//			obj.setSequencePtr(obj.getSequencePtr() - preStep);
//			obj.setNodeID(flowContext.RedirectedNode);
//		} else {
//			// 没有重定向节点
//			obj.setSequencePtr(obj.getSequencePtr() - 1);
//			
//			
//			
//			//WFHistory preHistory = getHistory(instance, obj.getSequencePtr());
//			obj.setNodeID(preHistory.getNodeID());
//		}
//
//		// 本次流转历史
//		WFHistory newHistory = new WFHistory();
//		newHistory.setID(UUID.randomUUID().toString());
//		newHistory.setInstanceID(obj.getInstanceID());
//		newHistory.setNodeID(obj.getNodeID());
//		newHistory.setSequence(currentSequence);
//
//		// 保存数据
//		IWorkflowStorage storage = instance.getContext().getStorage();
//		storage.Save(obj, newHistory);
//		return obj.getNodeID();
	}

}
