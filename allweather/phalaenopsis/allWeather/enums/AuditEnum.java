package phalaenopsis.allWeather.enums;

import phalaenopsis.common.method.Enum.IntEnum;
import phalaenopsis.common.method.Enum.StatusEnum;

public enum AuditEnum implements IntEnum<AuditEnum> {
	
	/**
	 * 未操作 
	 */
	UnHandle(0),
	/**
	 * 通过
	 */
	Pass(1),
	/**
	 * 未通过
	 */
	UnPass(2);
	
	private int value;
	
	private AuditEnum(int v)
	{
		this.value = v;
	}

	@Override
	public int getIntValue() {
		return this.value;
	}

}
