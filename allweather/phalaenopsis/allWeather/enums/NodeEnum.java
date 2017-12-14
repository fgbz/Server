package phalaenopsis.allWeather.enums;

import phalaenopsis.common.method.Enum.IntEnum;

public enum NodeEnum implements IntEnum<NodeEnum> {

	/**
	 * 导入
	 */
	Import(0),

	/**
	 * 违法定性
	 */
	IllegalSpot(1),
	/**
	 * 举证中-合法
	 */
	ProofingLegalSpot(2),
	/**
	 * 举证中-非新增建设
	 */
	ProofingNotNewSpot(3),
	/**
	 * 举证完-合法
	 */
	ProofLegalSpot(4),
	/**
	 * 举证完-非新增建设
	 */
	ProofNotNewSpot(5),
	/**
	 * 上报给市级-合法
	 */
	ProofLegalSpotCity(6),
	/**
	 * 上报给市级-非新增建设
	 */
	ProofNotNewSpotCity(7),
	/**
	 * 上报给省级-合法
	 */
	ProofLegalSpotProvince(8),
	/**
	 * 上报给省级-非新增建设
	 */
	proofNotNewSpotProvince(9),
	/**
	 * 合法定性
	 */
	LegalSpot(10),
	/**
	 * 非新增定性
	 */
	NotNewSpot(11);

	private NodeEnum(int v) {
		this.value = v;
	}

	private int value;

	@Override
	public int getIntValue() {
		return this.value;
	}
}
