package phalaenopsis.common.util;

/**
 * 大地基准面。
 * @author chunl
 * 2017年8月4日下午1:44:47
 */
public class Datum {

	public Datum(Double MajorAxis, Double MinorAxis, Double E2) {
		_MajorAxis = MajorAxis;
		_MinorAxis = MinorAxis;
		_E2 = E2;
	}

	/**
	 * 长轴
	 */
	private Double MajorAxis;

	public Double getMajorAxis() {
		return _MajorAxis;
	}

	/**
	 * 短轴
	 */
	private Double MinorAxis;

	public Double getMinorAxis() {
		return _MinorAxis;
	}

	/**
	 * 偏心率的平方
	 */
	private Double E2;

	public Double getE2() {
		return _E2;
	}

	private Double _MajorAxis = 0.0;
	private Double _MinorAxis = 0.0;
	private Double _E2 = 0.0;

}
