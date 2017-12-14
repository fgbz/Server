package phalaenopsis.illegalclue.enums;

/**
 * 线索反馈类型枚举类
 * @author dongdongt
 *
 */
public enum ClueFKType {
	NO(0,"全部"),
	INTERIM(1,"中期反馈"),
	FINAL(2,"最终反馈");
	
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
	private ClueFKType(int index,String name) {
	 	this.index = index;
		this.name = name;
	}
}
