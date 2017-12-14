package phalaenopsis.illegalclue.enums;

/**
 * 线索 转交办 之后的状态 枚举
 * @author dongdongt
 *
 */
public enum ClueAssignStatus {
	ALL(0,"全部"),
	WAIT(1,"待反馈"),
	ALREADT(2,"本级办理审核"),
	CONFIRMED(3,"已确认"),
	RETURNED(4,"已退回"),
	OBJECTION(5,"异议中"),
	OBJECTIONBACK(6,"异议已反馈");

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
	private ClueAssignStatus(int index,String name) {
	 	this.index = index;
		this.name = name;
	}
}
