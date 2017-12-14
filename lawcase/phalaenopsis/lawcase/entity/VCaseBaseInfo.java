package phalaenopsis.lawcase.entity;

/** 
 CaseBaseInfo查询列表视图
 
*/
public class VCaseBaseInfo
{
	/** 
	 主键ID
	*/
	private String id;
	/** 
	 案件ID
	*/
	private String caseID;
	/** 
	 最大版本号
	*/
	private int version;
	/** 
	 案件的当前流程节点
	*/
	private String nodeID;
	/** 
	 案件创建人的部门ID
	*/
	private String orgID;
	/** 
	 案件创建人的部门名称
	*/
	private String orgName;
	/** 
	 案发地点完整地址
	*/
	private String casePlace;
	/** 
	 视图引用的CaseBaseInfo
	*/
	private CaseBaseInfo ref;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCaseID() {
		return caseID;
	}
	public void setCaseID(String caseID) {
		this.caseID = caseID;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getNodeID() {
		return nodeID;
	}
	public void setNodeID(String nodeID) {
		this.nodeID = nodeID;
	}
	public String getOrgID() {
		return orgID;
	}
	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getCasePlace() {
		return casePlace;
	}
	public void setCasePlace(String casePlace) {
		this.casePlace = casePlace;
	}
	public CaseBaseInfo getRef() {
		return ref;
	}
	public void setRef(CaseBaseInfo ref) {
		this.ref = ref;
	}
	
}