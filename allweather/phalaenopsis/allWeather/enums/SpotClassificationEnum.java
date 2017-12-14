package phalaenopsis.allWeather.enums;

import phalaenopsis.common.method.Enum.IntEnum;
import phalaenopsis.common.method.Enum.StatusEnum;

/**
 * 图斑分类
 * @author chunl
 *
 */
public enum SpotClassificationEnum implements IntEnum<SpotClassificationEnum>{

	/**
	 * 设施农用地
	 */
	AgriculturalLand(1),

	/**
	 * 临时用地
	 */
	TemporaryLand(2),
	/**
	 * 农村道路 
	 */
	CountryRoad(3),
	/**
	 * 实地未变化
	 */
	FieldNoChange(4);

	private SpotClassificationEnum(int v) {
		this.value = v;
	}

	private int value;

	@Override
	public int getIntValue() {
		return this.value;
	}

}
