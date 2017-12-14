/**
 * 签字流程链
 */
package phalaenopsis.lawcase.entity;

import static org.springframework.util.Assert.isTrue;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonProperty;
import phalaenopsis.common.method.Tools.Linq;;

/**
 * @author yuhangc 签字流程链
 */
public class SignLink {
	/**
	 * 签字的环节节点顺序
	 */
	@JsonProperty("SignList")
	private List<SignNode> signList = new ArrayList<SignNode>();

	/**
	 * 签字流程链的ID
	 */
	@JsonProperty("SignLinkID")
	public String signLinkID;

	/**
	 * 签字流程链对应的流程节点
	 */
	@JsonProperty("FlowNode")
	public String flowNode;

	/**
	 * 签字链中节点的个数
	 */
	@JsonProperty("Count")
	public int count;

	public SignLink(String signLinkID, String flowNode) {
		this.signLinkID = signLinkID;
		this.flowNode = flowNode;
	}

	/**
	 * 按照顺序设置签字的流程环节节点ID
	 * 
	 * @param SignNode
	 */
	public void Set(SignNode... nodes) {
		if (null != nodes && 0 != nodes.length) {
			for (int j = 0; j < nodes.length; j++) {
				isTrue(!Linq.extExists(signList, "node", nodes[j].getNode()));
				nodes[j].setSequence(j);
				signList.add(nodes[j]);
			}
		}
	}

	/**
	 * 获取签字流程链中指定环节ID的节点
	 * 
	 * @param node
	 * @return
	 */
	public SignNode Get(String node) {
		return  Linq.extEquals(signList, "node", node);
	}

	/**
	 * 获取签字流程链中第一个环节的节点
	 * 
	 * @return
	 */
	public SignNode GetFirst() {
		return  Linq.firstOrDefault(signList);
	}

	/**
	 * 获取签字流程链中最后一个环节的节点
	 * 
	 * @return
	 */
	public SignNode GetLast() {
		return Linq.getLast(signList);
	}

	public String getSignLinkID() {
		return signLinkID;
	}

	public void setSignLinkID(String signLinkID) {
		this.signLinkID = signLinkID;
	}

	public String getFlowNode() {
		return flowNode;
	}

	public void setFlowNode(String flowNode) {
		this.flowNode = flowNode;
	}

	public int getCount() {
		count = signList.size();
		return count;
	}

}
