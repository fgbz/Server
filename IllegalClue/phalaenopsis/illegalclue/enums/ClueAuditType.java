package phalaenopsis.illegalclue.enums;

/**
 *  线索审核类型枚举
 * @author dongdongt
 *
 */
public enum ClueAuditType {
	ALL(0,"全部"),
	CHUUN(1,"初判-无效审核"),
	BEN(2,"本级办理审核"),
	ZHUAN(3,"转交办审核"),
	NEXT(4,"下级提请审核"),
	TRANSFER(5,"移送审核"),
	OVER(6,"办结审核"),
	CHUBEN(7,"初判-本级办理审核"),
	CHUGOUPSTAIRS(8,"初判-提请上级审核"),
	REPEAT(9,"重复线索审核"),
	MERAG(10,"合并办理线索审核");

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
	private ClueAuditType(int index,String name) {
	 	this.index = index;
		this.name = name;
	}
}
