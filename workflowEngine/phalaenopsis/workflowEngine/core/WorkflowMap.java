/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.workflowEngine.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.util.Assert;

import phalaenopsis.common.method.ManualAssembly;
import phalaenopsis.common.method.Tools.Linq;
import phalaenopsis.workflowEngine.core.NetGraph.NGraphNode;
import phalaenopsis.workflowEngine.storage.WFInstance;

/**
 * @author yangw786
 *
 *         2017年4月6日下午3:56:09 流程图
 */
public class WorkflowMap implements IWorkflowMap {
	private String mapID;
	private boolean keep;
	// 流程图定义
	private NetGraph graph;
	private Map<String, SortedSet<String>> branches;// 节点-分支列表
	private String finishedNode;

	/**
	 * 默认的流程已经结束的标记节点ID
	 */
	private final String DefaultFinishedNodeID = "Workflow_FinishedNode";



	public WorkflowMap(String mapID, boolean keep) {
		Assert.hasText(mapID);
		this.mapID = mapID;
		this.keep = keep;
		graph = new NetGraph();
		branches = new HashMap<String, SortedSet<String>>();
	}

	/// <summary>
	/// 添加流程节点
	/// </summary>
	/// <typeparam name="TNode">流程节点对应的类</typeparam>
	/// <param name="nodeID">节点ID</param>
	/// <param name="nodeType">流程节点类型</param>
	@Override
	public <TNode extends IWorkflowNode> void addNode(String nodeID, int nodeType) {
		Assert.hasText(nodeID);
		addNodeCore(nodeID, IWorkflowNode.class, nodeType);
	}

	/// <summary>
	/// 添加流程节点
	/// </summary>
	/// <param name="nodeID">节点ID</param>
	/// <param name="classType">流程节点对应的类的类型</param>
	/// <param name="nodeType">流程节点类型</param>
	@Override
	public void addNode(String nodeID, Class<?> classType, int nodeType) {
		Assert.hasText(nodeID);
		Assert.notNull(classType);
	    Assert.isTrue(!classType.isPrimitive());
		Assert.isTrue(IWorkflowNode.class.isAssignableFrom(classType));
		addNodeCore(nodeID, classType, nodeType);
	}

	private void addNodeCore(String nodeID, Class<?> classType, int nodeType) {
		NodeInfo node = new NodeInfo(nodeID, classType, nodeType);
		if (nodeID.equals(this.finishedNode))
			throw new IllegalArgumentException();
		graph.addNode(nodeID, node);
	}

	/// <summary>
	/// 设置某个节点的下一步节点
	/// </summary>
	/// <param name="nodeID"></param>
	/// <param name="next"></param>
	public void setNext(String nodeID, String next) {
		Assert.hasText(nodeID);
		graph.setOutArcs(nodeID, next);
	}

	public NodeInfo getNode(String nodeID) {
		Assert.hasText(nodeID);
		return graph.getNode(nodeID);
	}
	
	public boolean getKeep()
	{
		return this.keep;
	}

	public List<NodeInfo> getNodes() {
		List<NodeInfo> nodeInfos = new ArrayList<NodeInfo>();
		List<NGraphNode> list = graph.getAllNodes();
		for (NGraphNode nGraphNode : list) {
			nodeInfos.add(nGraphNode.getValue());
		}
		return Collections.unmodifiableList(nodeInfos);
	}

	/// <summary>
	/// 获取该节点的后继节点（下一步节点）
	/// </summary>
	/// <param name="nodeID"></param>
	/// <returns></returns>
	@Override
	public NodeInfo getNextNode(String nodeID) {
		Assert.hasText(nodeID);
		List<NGraphNode> nextNodes = graph.getOutNodes(nodeID);
		// 下一步可能有很多命名的分支节点流经，只需要获取唯一的一个命名为null的节点就是定义的nextNode
		for (NGraphNode node : nextNodes) {
			Set<String> sets = graph.getArc(nodeID, node.getKey());
			if (sets == null || sets.size() == 0)
				return node.getValue();
		}
		// 没有找到下一步节点
		return null;
	}

	/// <summary>
	/// 判断流程图中是否包含指定ID的节点
	/// </summary>
	/// <param name="nodeID"></param>
	/// <returns></returns>
	@Override
	public boolean containsNode(String nodeID) {
		if (nodeID == null || nodeID.equals("")) {
			return false;
		}
		return graph.Contains(nodeID);
	}

	/**
	 * @return the finishedNode
	 */
	public String getFinishedNode() {
		if (finishedNode == null)
			return DefaultFinishedNodeID;
		return finishedNode;
	}

	/**
	 * @param finishedNode
	 *            the finishedNode to set
	 */
	@Override
	public void setFinishedNode(String finishedNode) {
		Assert.hasText(finishedNode);
		if (graph.Contains(finishedNode)) {
			throw new IllegalArgumentException("MapContainsNodeID");
		} else {
			this.finishedNode = finishedNode;
		}
	}
	


	
	@Override
	public IWorkflowInstance createInstance( IRuntimeEnvironment environment) {
		// 创建流程实例对象
		InstanceRuntimeContext context = environment.createContext();

		IWorkflowInstance instance = this.CreateInstanceObject();
		instance.setContext(context);

		// 创建流程实例数据
		FlowManager flowManager = ManualAssembly.getAssembly("FlowManager");
		
		WFInstance instanceData = flowManager.start(this, context);
		if (instanceData == null)
			return null;
		context.setInstanceData(instanceData);
		instance.initialize(context);
		return instance;
	}


	public IWorkflowInstance getInstance(WFInstance instanceData, IRuntimeEnvironment environment) {
		Assert.notNull(instanceData);
		InstanceRuntimeContext context = environment.createContext();

		context.setInstanceData(instanceData);
		IWorkflowInstance instance = this.CreateInstanceObject();
		instance.initialize(context);

		instance.setContext(context);
		return instance;
	}

	/// <summary>
	/// 创建IWorkflowInstance对象实例
	/// </summary>
	/// <param name="context"></param>
	/// <returns></returns>
	private IWorkflowInstance CreateInstanceObject() {
		return (IWorkflowInstance) new WorkflowInstance(this);
	}

	@Override
	public void addBranch(String nodeID, Class<?> selectorType, String branchName, String... nextNodes) {
		Assert.isTrue(IBranchSelector.class.isAssignableFrom(selectorType));
		
		NodeInfo node = graph.getNode(nodeID);
		node.IsBranch = true;
		node.BranchSelector = selectorType;
		// 按照顺序，依次将分支添加到节点
		String current = nodeID;
		for (int i = 0; i < nextNodes.length; i++) {
			graph.addOutArcs(current, nextNodes[i]);
			// 给边添加权值
			Set<String> sets = graph.getArc(current, nextNodes[i]);
			String newName = getBranchName(nodeID, branchName);// 分支内所有节点的权值应以分支节点名+分支名
																// 全部一致
			sets.add(newName);

			current = nextNodes[i];

			// node的出边添加了权值，意味着next的这条入边也应该加权值
		}
		// 添加分支 到分支列表
		SortedSet<String> list = branches.get(nodeID);
		if (list == null) {
			list = new TreeSet<String>();
			branches.put(nodeID, list);
		}
		list.add(branchName);
	}

	private String getBranchName(String nodeID, String branchName) {
		return nodeID + ":" + branchName;
	}

	public String getBranchFirstNode(String nodeID, String branchName) {
		// 获取节点的所有出弧，然后获取包含指定branchName权值的弧
		String key = nodeID + ":" + branchName;
		List<String> arcs = graph.getOutArcs(nodeID);
		for (String arc : arcs) {
			Set<String> sets = graph.getArc(nodeID, arc);
			if (sets.contains(key)) {
				return arc;
			}
		}

		return null;
	}

	public boolean hasBranch(String nodeID, String branchName) {
		SortedSet<String> sets = branches.get(nodeID);
		if (sets != null) {
			return sets.contains(branchName);
		}
		return false;
	}

	@Override
	public List<String> getBranchNodeSequences(String nodeID, String branchName) {
		List<String> list = new ArrayList<String>();
		String key = nodeID + ":" + branchName;
		List<String> arcs = graph.getOutArcs(nodeID);
		boolean hasBranch;// 该节点的出弧是否包含该分支
		do {
			hasBranch = false;// 先假定该节点的出弧不包含分支
			for (String s : arcs) {
				// 获取弧的权值
				Set<String> sets = graph.getArc(nodeID, s);
				if (sets.contains(key)) {
					// 该弧的权值中包含该值，则说明该弧包含该分支
					list.add(s);
					hasBranch = true;
					// 再获取该节点的出弧
					arcs = graph.getOutArcs(s);
					nodeID = s;// 当前节点指向分支中的下一个节点
					break;
				}
			}
		} while (hasBranch);
		return list;
	}

	public NodeInfo getStartNode() {
		List<NodeInfo> startNodes = Linq.extEqualsList(this.getNodes(), "IsStart", true);
		int count = startNodes.size();
		if (count == 0) {
			throw new CannotFlowException("WorkflowDoesNotHaveStartNode");
		} else if (count > 1) {
			throw new CannotFlowException("WorkflowDoesNotHaveMultiStartNode");
		} else {
			return startNodes.get(0);
		}
	}

	public List<NodeInfo> getEndNodes() {
		Collection<NodeInfo> collections;
		collections = Collections.unmodifiableCollection(Linq.extEqualsList(this.getNodes(), "IsEnd", true));
		List<NodeInfo> list = new ArrayList<NodeInfo>();
		list.addAll(collections);
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see phalaenopsis.workflowEngine.core.IWorkflowMap#getMapID()
	 */
	@Override
	public String getMapID() {
		return this.mapID;
	}
}
