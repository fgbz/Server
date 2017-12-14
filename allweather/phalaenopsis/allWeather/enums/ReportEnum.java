package phalaenopsis.allWeather.enums;

import org.apache.poi.hwpf.model.UnhandledDataStructure;

import phalaenopsis.common.method.Enum.IntEnum;

/**
 * 操作枚举。0表示没有上报，1表示上报操作，2表示枚举
 * @author chunl
 *
 */
public enum ReportEnum implements IntEnum<ReportEnum> {

	/**
	 * 没有上报
	 */
	UnReport(0),
	/**
	 * 上报
	 */
	Report(1),
	/**
	 * 回退
	 */
	Back(2);

	private ReportEnum(int v) {
		this.value = v;
	}

	private int value;

	@Override
	public int getIntValue() {
		return value;
	}

}
