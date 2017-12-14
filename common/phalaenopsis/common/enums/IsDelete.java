package phalaenopsis.common.enums;

/**
 *  是否删除
 * @author dongdongt
 *
 */
public enum IsDelete {
	VALID(0,"未删除"),
	INVALID(1,"已删除");
	
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
	private IsDelete(int index,String name) {
	 	this.index = index;
		this.name = name;
	}
}
