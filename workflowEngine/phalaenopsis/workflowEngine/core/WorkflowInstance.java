/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.workflowEngine.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import static org.springframework.util.Assert.notNull;

import phalaenopsis.common.method.ManualAssembly;
import phalaenopsis.common.service.WFOracleDBStorage;
import phalaenopsis.workflowEngine.storage.WFHistory;
import phalaenopsis.workflowEngine.storage.WFInstance;
import phalaenopsis.workflowEngine.storage.WFInstanceState;

/**
 * @author yangw786
 *
 *         2017年4月6日下午3:55:55 流程实例
 */
public class WorkflowInstance implements IWorkflowInstance {
	private IWorkflowMap map = null;
	private InstanceRuntimeContext context;
	private String current;// 当前节点
	
	@Autowired
	private WFOracleDBStorage storage;

	public WorkflowInstance(IWorkflowMap map) {
		this.map = map;
	}

	@Autowired
	private FlowManager flowManager;

	// 使用流程实例数据填充流程实例
	@Override
	public void initialize(InstanceRuntimeContext context) {
		WFInstance obj = context.getInstanceData();
		notNull(obj);
		if (!obj.MapID.equals(map.getMapID()))
		{
			throw new IllegalArgumentException("InstanceDataHasError");
		}
		
		if ((obj.getState() == WFInstanceState.Created || obj.getState() == WFInstanceState.Running)
				&& map.containsNode(obj.getNodeID())) {
			// 流程正在进行
			current = obj.NodeID;
		} else if (obj.State == WFInstanceState.Finished && obj.NodeID.equals(map.getFinishedNode())) {
			// 流程已经结束
			// 查询流程的最后一个历史节点
			// ReadOnlyCollection<NodeInfo> histories =
			// this.GetHistoryNodeSequences();
			// current = histories[histories.Count - 1].NodeID;
			// 放到Instance外面做
			current = obj.NodeID;
		} else {
			throw new IllegalArgumentException("InstanceDataHasError");
		}
		// this.DataContext = new NodeDataContext(context.InstanceData);
		this.context = context;
	}

	public String instanceID() {
		return context.getInstanceData().InstanceID;
	}

	public IWorkflowMap getMap() {
		return map;
	}

	public String getCurrent() {
		return current;
	}

	public List<NodeInfo> getHistoryNodeSequences() {
		//IWorkflowStorage storage = context.getStorage();
		List<NodeInfo> list = new ArrayList<NodeInfo>();
		storage = ManualAssembly.getAssembly("WFOracleDBStorage");
		List<WFHistory> histories = storage.getHistories(context.getInstanceData().getInstanceID());
		if (histories.size() > 0) {
			String last = histories.get(histories.size() - 1).NodeID;
			if (last.equals(map.getFinishedNode())) {
				histories.remove(histories.size() - 1);
			}
			for (WFHistory wfHistory : histories) {
				list.add(map.getNode(wfHistory.getNodeID()));
			}
		}
		return list;
	}

	public boolean isFinished() {
		return context.getInstanceData().NodeID == map.getFinishedNode();
	}

	public NodeDataContext DataContext;

	/**
	 * @return the dataContext
	 */
	public NodeDataContext getDataContext() {
		return DataContext;
	}

	/**
	 * @param dataContext
	 *            the dataContext to set
	 */
	public void setDataContext(NodeDataContext dataContext) {
		this.DataContext = dataContext;
	}

	public String next() {
		try {
			flowManager = ManualAssembly.getAssembly("FlowManager");

			return flowManager.next(this);
		} catch (CannotFlowException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String previous() {
		try {
			flowManager = ManualAssembly.getAssembly("FlowManager");
			return flowManager.previous(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public InstanceRuntimeContext Context;

	/**
	 * @return the context
	 */
	public InstanceRuntimeContext getContext() {
		return Context;
	}

	/**
	 * @param context
	 *            the context to set
	 */
	public void setContext(InstanceRuntimeContext context) {
		Context = context;
	}

	@Override
	public String getInstanceID() {
		return context.getInstanceData().getInstanceID();
	}

	@Override
	public boolean getFinished() {
		return context.getInstanceData().getNodeID() == map.getFinishedNode();
	}

}
