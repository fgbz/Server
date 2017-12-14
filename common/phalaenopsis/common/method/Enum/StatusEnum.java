package phalaenopsis.common.method.Enum;

public enum StatusEnum implements IntEnum<StatusEnum> {

	FAILURE(0, "failure"), SUCCESS(1, "success");

	private int index;
	private String name;

	private StatusEnum(int index, String name) {
		this.index = index;
		this.name = name;
	}

	public static String fromIndex(int index) {
		for (StatusEnum p : StatusEnum.values()) {
			if (index == p.getIntValue())
				return p.name;
		}
		return null;
	}

	@Override
	public int getIntValue() {
		return this.index;
	}

}
