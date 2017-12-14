package phalaenopsis.lawcase.entity;

import java.util.Date;

/*import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;*/
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import phalaenopsis.common.method.Json.NetDateJsonSerializer;

public class Signature {

	/**
	 * 主键ID
	 */
	@JsonProperty("ID")
	private String id;

	/**
	 * 签字流程模板ID
	 */
	@JsonProperty("TemplateID")
	private String templateID;

	/**
	 * 签字流程实例ID
	 */
	@JsonProperty("InstanceID")
	private String InstanceID;

	/**
	 * 已经签字的环节节点
	 */
	@JsonProperty("SignedNode")
	private String signedNode;

	/**
	 * 签字人名称
	 */
	@JsonProperty("SignedUser")
	private String signedUser;

	/**
	 * 签字日期
	 */
	@JsonProperty("SignDate")
	@JsonSerialize(using = NetDateJsonSerializer.class)
	private Date signDate;

	/**
	 * 意见
	 */
	@JsonProperty("Idea")
	private String idea;

	/**
	 * 是否退回（0：否，1：是）
	 */
	@JsonProperty("IsSendBack")
	private int isSendBack;

	/**
	 * 退回原因
	 */
	private String sendBackReason;

	/**
	 * 签字人ID
	 */
	@JsonProperty("SignedUserID")
	private String signedUserID;

	/**
	 * 签字操作时间
	 */
	private Date operateTime;

	/**
	 * 签字状态（0：待签字，1：已签字未提交，2：已签字已提交）
	 */
	@JsonProperty("Status")
	private Integer status;

	/**
	 * 签字节点状态（0：节点签字未完成，1：节点签字已完成）
	 */
	private Integer nodeStatus;
    /**
     * 辅助字段
     */
	private Integer count;
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTemplateID() {
		return templateID;
	}

	public void setTemplateID(String templateID) {
		this.templateID = templateID;
	}

	public String getInstanceID() {
		return InstanceID;
	}

	public void setInstanceID(String instanceID) {
		InstanceID = instanceID;
	}

	public String getSignedNode() {
		return signedNode;
	}

	public void setSignedNode(String signedNode) {
		this.signedNode = signedNode;
	}

	public String getSignedUser() {
		return signedUser;
	}

	public void setSignedUser(String signedUser) {
		this.signedUser = signedUser;
	}

	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public String getIdea() {
		return idea;
	}

	public void setIdea(String idea) {
		this.idea = idea;
	}

	public int getIsSendBack() {
		return isSendBack;
	}

	public void setIsSendBack(int isSendBack) {
		this.isSendBack = isSendBack;
	}

	public String getSendBackReason() {
		return sendBackReason;
	}

	public void setSendBackReason(String sendBackReason) {
		this.sendBackReason = sendBackReason;
	}

	public String getSignedUserID() {
		return signedUserID;
	}

	public void setSignedUserID(String signedUserID) {
		this.signedUserID = signedUserID;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getNodeStatus() {
		return nodeStatus;
	}

	public void setNodeStatus(Integer nodeStatus) {
		this.nodeStatus = nodeStatus;
	}

	public Signature() {
		super();
	}

	public Signature(String id, String templateID, String instanceID, String signedNode, String signedUserID,
			int isSendBack, Integer status, Integer nodeStatus) {
		super();
		this.id = id;
		this.templateID = templateID;
		InstanceID = instanceID;
		this.signedNode = signedNode;
		this.isSendBack = isSendBack;
		this.signedUserID = signedUserID;
		this.status = status;
		this.nodeStatus = nodeStatus;
	}
}
