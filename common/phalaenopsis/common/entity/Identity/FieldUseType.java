package phalaenopsis.common.entity.Identity;

import phalaenopsis.common.method.Enum.IntEnum;

/**
 * 
 * 字段用途
 * 
 * @author chunhongl
 *
 * 2017年5月3日下午3:56:22
 */
public enum FieldUseType  implements IntEnum<FieldUseType>{

	/**
	 * 地图搜索
	 */
	MapSearch(0),

	/**
	 * 地图识别，用于移动端和浏览器
	 */
	IdentifyInAll(123),

	/**
	 * 地图识别，用于浏览器
	 */
	IdentifyInBrowser(1),

	/**
	 * 地图识别，用于平板端
	 */
	IdentifyInPad(2),

	/**
	 * 地图识别，用于手机端
	 */
	IdentifyInPhone(3),

	/**
	 * 地图识别，用于浏览器和平板端
	 */
	IdentifyInBrowserAndPad(12),

	/**
	 * 地图识别，用于浏览器和手机端
	 */
	IdentifyInBrowserAndPhone(13),

	/**
	 * 地图识别，用于手机端和平板端
	 */
	IdentifyInMobile(23);

	private FieldUseType(int v) {
		this.value = v;
	}

	private int value;

	public int getIntValue() {
		return this.value;
	}

	public static FieldUseType getFieldUseType(int v) {
		for (FieldUseType item : FieldUseType.values()) {
			if (item.getIntValue() == v)
				return item;
		}
		
		return null;
	}
}
