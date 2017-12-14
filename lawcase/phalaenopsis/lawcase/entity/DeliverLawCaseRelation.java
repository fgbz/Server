package phalaenopsis.lawcase.entity;
/**
 * 转违法案件关联关系表
 * @author dongdongt
 *
 */
public class DeliverLawCaseRelation {
	/**
	 * 主键
	 */
	private  String id;
	/**
	 * 关联数据ID
	 */
	private String relationID;
	/**
	 * 案件ID
	 */
	private String caseID;
	/**
	 * 关联数据类型
	 */
	private String type;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRelationID() {
		return relationID;
	}
	public void setRelationID(String relationID) {
		this.relationID = relationID;
	}
	public String getCaseID() {
		return caseID;
	}
	public void setCaseID(String caseID) {
		this.caseID = caseID;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 与业务无关
	 */
	private int count;

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

}
