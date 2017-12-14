package phalaenopsis.illegalclue.enums;

/**
 * 线索 转交办 之后的状态 枚举
 * @author dongdongt
 *
 */
public enum IlleaglApproachType {
	ALL(0,"全部"),
	ZHUANBAN(1,"转办"),
	ASSIGN(2,"交办"),
	GOUPSTAIRS(3,"提请上级"),
	YISONG(4,"移送"),
	BENJI(5,"本级办理");


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
	private IlleaglApproachType(int index,String name) {
	 	this.index = index;
		this.name = name;
	}
}
