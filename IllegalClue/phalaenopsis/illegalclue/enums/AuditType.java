package phalaenopsis.illegalclue.enums;

/**
 * 线索审核类型区分
 * @author dongdongt
 *
 */
public enum AuditType {
	CHU(1,"初判审核"),
	BANJIE(2,"办结审核");
	
	private int index;
	private String name;
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	// 构造方法
	private AuditType(int index,String name) {
	 	this.index = index;
		this.name = name;
	}
}
