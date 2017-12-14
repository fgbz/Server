package phalaenopsis.illegalclue.enums;

/**
 * 线索类型枚举
 * @author dongdongt
 *
 */
public enum ClueType {
	ALL(0,"全部"),
	COMMONLY(1,"一般线索"),
	INVALID(2,"无效线索"),
	ILLEGAL(3,"违法线索");
	
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
	private ClueType(int index,String name) {
	 	this.index = index;
		this.name = name;
	}
}
