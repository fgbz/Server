package phalaenopsis.common.util;

public class XYZCoordinate {

	public XYZCoordinate(Double X, Double Y, Double Z) {
		_X = X;
		_Y = Y;
		_Z = Z;
	}


	/**
	 *  把坐标转换为用于显示的字符串。
	 */
	@Override
	public String toString() {

		return String.format("{%s:.000},{%s:.000}", X, Y);
	}

	/**
	 * 向量减法
	 * 公 式:a-b = (ax-bx,ay-by)
	 * @param V1
	 * @param V2
	 * @return
	 */
	public static XYZCoordinate minus(XYZCoordinate V1, XYZCoordinate V2)

	{
		return new XYZCoordinate(V1.X - V2.X, V1.Y - V2.Y, 0.0);
	}

	/**
	 * 向量叉乘
	 * 公 式:a-b = (ax-bx,ay-by)
	 * @param V1
	 * @param V2
	 * @return 向量的向量积（外积，叉积）
	 */
	public static Double multiply(XYZCoordinate V1, XYZCoordinate V2)

	{
		return V1.X * V2.Y - V2.X * V1.Y;
	}

	private Double X;

	private Double Y;

	private Double Z;

	public Double getX() {
		return _X;
	}

	public void setX(Double x) {
		_X = x;
	}

	public Double getY() {
		return _Y;
	}

	public void setY(Double y) {
		_Y = y;
	}

	public Double getZ() {
		return _Z;
	}

	public void setZ(Double z) {
		_Z = z;
	}

	private Double _X = 0.0;
	private Double _Y = 0.0;
	private Double _Z = 0.0;

}
