package phalaenopsis.visitSystem.enums;

/**
 * 信访方式枚举
 * @author dongdongt
 *
 */
public enum XftypeEnum {
	
	LFGRZJ(1,"来访个人直接"),
	LFGRCF(2,"来访个人重复"),
	LFGRSZ(3,"来访个人省转"),
	LFGRBZ(4,"来访个人部转"),
	LFGRYJ(5,"来访个人越级"),
	LFJTZJ(6,"来访集体直接"),
	LFJTCF(7,"来访集体重复"),
	LFJTSZ(8,"来访集体省转"),
	LFJTBZ(9,"来访集体部转"),
	LFJTYJ(10,"来访集体越级"),
	WSXFZJ(11,"网上信访直接"),
	WSXFCF(12,"网上信访重复"),
	WSXFSZ(13,"网上信访省转"),
	WSXFBZ(14,"网上信访部转"),
	LXGRZJ(15,"来信个人直接"),
	LXGRCF(16,"来信个人重复"),
	LXGRSZ(17,"来信个人省转"),
	LXGRBZ(18,"来信个人部转"),
	LFLMZJ(19,"来信联名直接"),
	LFLMCF(20,"来信联名重复"),
	LFLMSZ(21,"来信联名省转"),
	LFLMBZ(22,"来信联名部转");
	
	
	private XftypeEnum(int v,String name) {
		this.value = v;
		this.name = name;
	}
	private int value;
	private String name;
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
