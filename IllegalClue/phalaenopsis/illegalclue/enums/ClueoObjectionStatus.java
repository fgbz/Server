package phalaenopsis.illegalclue.enums;

/**
 * 线索审核是否读取状态枚举类
 * @author dongdongt
 *
 */
public enum ClueoObjectionStatus {
	ALL(0,"全部"),
	UNREAD(1,"未读"),
	READ(2,"已读");
	
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
	private ClueoObjectionStatus(int index,String name) {
	 	this.index = index;
		this.name = name;
	}
}
