package phalaenopsis.allWeather.enums;

import org.omg.CORBA.IMP_LIMIT;

import phalaenopsis.common.method.Enum.IntEnum;
import phalaenopsis.common.method.Enum.StatusEnum;

/**
 * 合法图斑举证类型
 * @author chunl
 *
 */
public enum LegalProofEnum implements  IntEnum<LegalProofEnum>{

	/**
	 * 增减挂钩
	 */
	IncreaseDecrease(1),
	/**
	 * 农田用地
	 */
	farmland(2),
	/**
	 *  其他
	 */
	Other(3);

	private LegalProofEnum(int v) {
		this.value = v;
	}

	private int value;

	@Override
	public int getIntValue() {
		return this.value;
	}

}
