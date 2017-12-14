package phalaenopsis.illegalclue.enums;

/**
 * 线索反馈状态
 * @author dongdongt
 *
 */
public enum ClueFKStatus {
	NO(0,"-"),
	PASS(1,"审核通过"),
	BACK(2,"退回");
	
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
	private ClueFKStatus(int index,String name) {
	 	this.index = index;
		this.name = name;
	}
}
