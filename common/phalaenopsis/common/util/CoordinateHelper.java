package phalaenopsis.common.util;

/**
 * 坐标转换
 * @author chunl
 * 2017年8月4日下午1:39:48
 */
public class CoordinateHelper {
	final static Double PI = 3.14159265359;

	public static final Datum WGS84Datum = new Datum(6378137.0, 6356752.0, 0.00669437999014);// WGS84大地水准面
	public static final Datum Xian80Datum = new Datum(6378140.0, 6356755.0, 0.00669438499958795);// 西安80大地水准面

	/**
	 *  中央经线
	 */
	public static final int Centerlon = 111;

	/**
	 * 高斯投影
	 * @param blhCoordinate
	 * @param datum
	 * @param centerlon  中央精线
	 * @return
	 */
	public static XYZCoordinate GaussProjForward(BLHCoordinate blhCoordinate, Datum datum, Double centerlon) {
		if (blhCoordinate.getLatitude() < 0 || blhCoordinate.getLatitude() > 90 || blhCoordinate.getLongitude() < 0
				|| blhCoordinate.getLongitude() > 180 || blhCoordinate.getAltitude() < -datum.getMajorAxis()) {
			return new XYZCoordinate(-1.0, -1.0, -1.0);
		}

		// 3度带
		// 1:1万的地形图采用3度分带
		// 从东经 1.5度的经线开始，每隔3度为1带，用1、2、3……表示。
		// 全球共划分为120个投影带。
		// 即：东经 1.5 ～ 4.5 为 1带，中央经线的经度为东经3度。
		// 东经 4.5 ～ 7.5 为 2带，中央经线的经度为东经6度。
		final int ZoneDivision = 3;

		// 投影带编号(东经)
		int zone = (int) (Math.abs((blhCoordinate.getLongitude() + 1.5) / ZoneDivision));

		if (blhCoordinate.getLongitude() < 0) {
			// 投影带编号(西经)
			zone = 60 + 1 - zone;
		}

		// 东西向(Y)偏移量
		// Int32 false_east = (Int32)(500000.0 + zone * 1000000);
		int false_east = (int) (500000.0);

		// 中央经线
		Double lon_center = (Double.NaN == centerlon) ? (Double.valueOf(ZoneDivision * zone)) * PI / 180.0
				: centerlon * PI / 180.0;

		Double orglon = blhCoordinate.getLongitude() * PI / 180.0; // 以弧度表示的经度
		Double lat = blhCoordinate.getLatitude() * PI / 180.0; // 以弧度表示的纬度
		Double lon = orglon - lon_center; // 给定经度与中央经线间的经差
		Double r_major = datum.getMajorAxis(); // 长半轴
		Double e2 = datum.getE2(); // .E2;
		// Double M = (r_major * (1.0 - e2)) / Math.Sqrt((Math.Pow(1.0 - e2 *
		// Math.Sin(lat), 3) * Math.Sin(lat))); //子午圈曲率半径
		Double N = r_major / (Math.sqrt(1.0 - e2 * (Math.pow(Math.sin(lat), 2)))); // 卯酉圈曲率半径
		// Double yeta2 = N / M - 1.0; //yeta的平方
		Double t2 = Math.pow(Math.tan(lat), 2);
		Double t = Math.tan(lat);
		Double A = Math.cos(lat) * lon;
		Double e22 = e2 / (1 - e2);
		Double yeta2 = e22 * Math.cos(lat) * Math.cos(lat);

		// Double C0 = r_major * (1.0 - e2) * (1.0 + 3 / 4.0 * e2 + 45 / 64.0 *
		// e2 * e2 + 157 / 256.0 * e2 * e2 * e2 + 11025 / 16384.0 *
		// Math.Pow(e2, 4) + 43659 / 65536.0 * Math.Pow(e2, 5)) + 50;
		// Double C1 = r_major * (1.0 - e2) * (3 / 4.0 * e2 + 45 / 64.0 * e2 *
		// e2 + 157 / 256.0 * e2 * e2 * e2 + 11025 / 16384.0 * Math.Pow(e2, 4)
		// + 43659 / 65536.0 * Math.Pow(e2, 5));
		// Double C2 = r_major * (1 - e2) * (15 / 32.0 * e2 * e2 + 175 / 256.0 *
		// e2 * e2 * e2 + 3675 / 8192.0 * Math.Pow(e2, 4) +
		// 14553 / 32768.0 * Math.Pow(e2, 5));
		// Double C3 = r_major * (1 - e2) * (35 / 96.0 * e2 * e2 * e2 + 735 /
		// 2048.0 * Math.Pow(e2, 4) + 14553 / 40960.0 * Math.Pow(e2, 5));
		// Double C4 = r_major * (1 - e2) * (315 / 1024.0 * Math.Pow(e2, 4) +
		// 6237 / 20480.0 * Math.Pow(e2, 5));
		//// Double s = r_major * (1.0 - e2) * (A * lat - B * Math.Sin(2.0 *
		// lat) / 2.0 + //给定纬度与赤道间的子午线弧长
		//// C * Math.Sin(4.0 * lat) / 4.0 - D * Math.Sin(6.0 * lat) / 6.0);
		// Double X0 = C0 * lat - Math.Cos(lat) * (C1 * Math.Sin(lat) + C2 *
		// Math.Pow(Math.Sin(lat), 3) + C3 * Math.Pow(Math.Sin(lat), 5) +
		// C4 * Math.Pow(Math.Sin(lat), 7));
		Double X0 = r_major * ((1 - e2 / 4 - 3 * Math.pow(e2, 2) / 64 - 5 * Math.pow(e2, 3) / 256) * lat
				- (3 * e2 / 8 + 3 * Math.pow(e2, 2) / 32 + 45 * Math.pow(e2, 3) / 1024) * Math.sin(2 * lat)
				+ (15 * Math.pow(e2, 2) / 256 + 45 * Math.pow(e2, 3) / 1024) * Math.sin(4 * lat)
				- (35 * Math.pow(e2, 3) / 3072) * Math.sin(6 * lat));

		Double X = X0 + N * t * A * A / 2.0
				+ N * t * Math.pow(A, 4) * (5.0 - t2 + 9.0 * yeta2 + 4.0 * (Math.pow(yeta2, 2))) / 24.0
				+ N * t * Math.pow(A, 6) * (61 - 58 * t2 + t2 * t2) / 720;

		Double Y = N * A + N * Math.pow(A, 3) * (1.0 - t2 + yeta2) / 6.0
				+ N * Math.pow(A, 5) * (5.0 - 18.0 * t2 + Math.pow(t2, 2) + 14 * yeta2 - 58 * yeta2 * t2) / 120.0;
		Y = Y + false_east;
		Double Z = blhCoordinate.getAltitude();
		return new XYZCoordinate(X, Y, Z);
	}

	/**
	 * 高斯投影 含带号
	 * @param blhCoordinate 中央精线
	 * @return
	 */
	public static XYZCoordinate GaussProjForwardDH(BLHCoordinate blhCoordinate) {
		if (blhCoordinate.getLatitude() < 0 || blhCoordinate.getLatitude() > 90 || blhCoordinate.getLongitude() < 0
				|| blhCoordinate.getLongitude() > 180 || blhCoordinate.getAltitude() < -WGS84Datum.getMajorAxis()) {
			return new XYZCoordinate(-1.0, -1.0, -1.0);
		}

		int zone = (int) (Centerlon / 3);

		// 东西向(Y)偏移量
		int false_east = (int) (zone * 1000000);
		// Int32 false_east = (Int32)(500000.0);
		XYZCoordinate result = GaussProjForward(blhCoordinate, WGS84Datum, Double.valueOf(Centerlon));
		return new XYZCoordinate(result.getX(), result.getY() + false_east, result.getZ());
	}

	/**
	 * 高斯反算
	 * @param X
	 * @param Y
	 * @param Z
	 * @return
	 */
	public static BLHCoordinate GaussProjInvCal(double X, double Y, double Z) {
		int ProjNo = 0;
		int ZoneWide = 3;
		double longitude0, X0, Y0, xval, yval, B, L, H;
		double e1, e2, f, a, ee, NN, T, C, M, D, R, u, fai, iPI;
		iPI = 0.0174532925199433; // π/180
		a = 6378140.0;
		f = 1.0 / 298.257; // 西安80参数
		// a=6378245.0;f=1.0/298.3 ; //(北京54参数)
		ProjNo = (int) (X / 1000000);
		if (ProjNo == 0) // 坐标不含带号
		{
			ProjNo = (int) (Centerlon / 3);
		}
		if (ProjNo < 24) {
			ZoneWide = 6;
			longitude0 = ProjNo * ZoneWide - 3;
			longitude0 = longitude0 * iPI;
		} else {
			ZoneWide = 3;
			longitude0 = ProjNo * ZoneWide;
			longitude0 = longitude0 * iPI;
		}
		X0 = ProjNo * 1000000 + 500000;
		Y0 = 0;
		xval = X - X0;
		yval = Y - Y0;
		e2 = 2 * f - f * f;
		e1 = (1.0 - Math.sqrt(1 - e2)) / (1.0 + Math.sqrt(1 - e2));
		ee = e2 / (1.0 - e2);
		M = yval;
		u = M / (a * (1 - e2 / 4 - 3 * e2 * e2 / 64 - 5 * e2 * e2 * e2 / 256));
		fai = u + (3 * e1 / 2 - 27 * Math.pow(e1, 3) / 32) * Math.sin(2 * u)
				+ (21 * e1 * e1 / 16 - 55 * Math.pow(e1, 4) / 32) * Math.sin(4 * u)
				+ (151 * Math.pow(e1, 4) / 32) * Math.sin(6 * u) + (1097 * Math.pow(e1, 4) / 512) * Math.sin(8 * u);
		C = ee * Math.pow(Math.cos(fai), 2);
		T = Math.pow(Math.tan(fai), 2);
		NN = a / Math.sqrt(1.0 - e2 * Math.pow(Math.sin(fai), 2));
		R = a * (1 - e2) / Math.pow((1 - e2 * Math.pow(Math.sin(fai), 2)), 3 / 2);
		D = xval / NN;

		L = longitude0 + (D - (1 + 2 * T + C) * Math.pow(D, 3) / 6
				+ (5 - 2 * C + 28 * T - 3 * C * C + 8 * ee + 24 * T * T) * Math.pow(D, 5) / 120) / Math.cos(fai);

		B = fai - (NN * Math.tan(fai) / R)
				* (D * D / 2 - (5 + 3 * T + 10 * C - 4 * C * C - 9 * ee) * Math.pow(D, 4) / 24
						+ (61 + 90 * T + 298 * C + 45 * T * T - 256 * ee - 3 * C * C) * Math.pow(D, 6) / 720);

		L = L / iPI;
		B = B / iPI;
		H = Z;
		return new BLHCoordinate(B, L, H);
	}

	public static BLHCoordinate GaussProjInvCal(double X, double Y, String FromSrid, String ToSrid) {
		if (FromSrid == ToSrid)
			return new BLHCoordinate(X, Y, 0.0);

		if (ToSrid == "4610")
			return GaussProjInvCal(X, Y, 0);

		if (FromSrid == "4610" && ToSrid == "2361") {
			XYZCoordinate _rs = GaussProjForwardDH(new BLHCoordinate(X, Y, 0.0));
			return new BLHCoordinate(_rs.getX(), _rs.getY(), _rs.getZ());
		}

		return new BLHCoordinate(X, Y, 0.0);

	}

	/**
	 * 同一椭球下经纬度坐标转到空间直角坐标
	 * @param blhCoordinate
	 * @param datum
	 * @return
	 */
	public static XYZCoordinate BLHtoXYZ(BLHCoordinate blhCoordinate, Datum datum) {
		if (blhCoordinate.getLatitude() < 0 || blhCoordinate.getLatitude() > 90 || blhCoordinate.getLongitude() < 0
				|| blhCoordinate.getLongitude() > 180 || blhCoordinate.getAltitude() < -datum.getMajorAxis()) {
			return new XYZCoordinate(-1.0, -1.0, -1.0);
		}
		XYZCoordinate xyzCoordinate = new XYZCoordinate(0.0, 0.0, 0.0);
		Double a, e2, B, L, H, N;
		a = datum.getMajorAxis();// .MajorAxis; // 椭球长半轴
		e2 = datum.getE2(); // 第一偏心率的平方
		B = blhCoordinate.getLatitude() * PI / 180;
		L = blhCoordinate.getLongitude() * PI / 180;
		H = blhCoordinate.getAltitude();
		N = a / Math.sqrt(1 - e2 * Math.pow(Math.sin(B), 2)); // 卯酉圈曲率半径
		xyzCoordinate.setX((N + H) * Math.cos(B) * Math.cos(L));
		xyzCoordinate.setY((N + H) * Math.cos(B) * Math.sin(L));
		xyzCoordinate.setZ((N * (1 - e2) + H) * Math.sin(B));

		return xyzCoordinate;
	}

	/**
	 *  同一椭球下空间直角坐标转到经纬度坐标
	 * @param xyzCoordinate
	 * @param datum
	 * @return
	 */
	public static BLHCoordinate XYZtoBLH(XYZCoordinate xyzCoordinate, Datum datum) {
		if (xyzCoordinate.getZ() <= 0) {
			return new BLHCoordinate(-1.0, -1.0, -1.0);
		}
		Double a, e2, X, Y, Z, N, a0, b0, B;
		a = datum.getMajorAxis(); // .MajorAxis; // 椭球长半轴
		e2 = datum.getE2(); // .E2; // 第一偏心率的平方
		X = xyzCoordinate.getX(); // .X;
		Y = xyzCoordinate.getY(); // .Y;
		Z = xyzCoordinate.getZ(); // .Z;
		Double Longitude = 180 + Math.atan(Y / X) * 180 / PI;

		// 二分迭代法求纬度
		a0 = 0.0; // 纬度下限
		b0 = PI / 2; // 纬度上限
		B = (a0 + b0) / 2;
		N = a / Math.sqrt(1 - e2 * Math.pow(Math.sin(B), 2)); // 卯酉圈曲率半径
		while (Math.abs((Z + N * e2 * Math.sin(B)) / Math.sqrt(X * X + Y * Y) - Math.tan(B)) > 0.0000000001) {
			if ((Z + N * e2 * Math.sin(B)) / Math.sqrt(X * X + Y * Y) - Math.tan(B) > 0)
				a0 = B;
			else if ((Z + N * e2 * Math.sin(B)) / Math.sqrt(X * X + Y * Y) - Math.tan(B) < 0)
				b0 = B;
			B = (a0 + b0) / 2;
			N = a / Math.sqrt(1 - e2 * Math.pow(Math.sin(B), 2));
		}
		Double Latitude = B * 180 / PI;
		Double Altitude = Math.sqrt(X * X + Y * Y) / Math.cos(B) - N;

		return new BLHCoordinate(Longitude, Latitude, Altitude);
	}

	/**
	 * 不同参考椭球之间空间直角坐标的转换，7参数法
	 * @param xyzCoordinate
	 * @param deltaX
	 * @param deltaY
	 * @param deltaZ
	 * @param Ax
	 * @param Ay
	 * @param Az
	 * @param S
	 * @return
	 */
	public static XYZCoordinate Param7(XYZCoordinate xyzCoordinate, Double deltaX, Double deltaY, Double deltaZ,
			Double Ax, Double Ay, Double Az, Double S) {
		XYZCoordinate xyzC = new XYZCoordinate(0.0, 0.0, 0.0);
		Double X, Y, Z;
		X = xyzCoordinate.getX();
		Y = xyzCoordinate.getY();
		Z = xyzCoordinate.getZ();
		// xyzC.X = deltaX + (1 + S) * (X + Az * Y - Ay * Z);
		// xyzC.Y = deltaY + (1 + S) * (Y - Az * X + Ax * Z);
		// xyzC.Z = deltaZ + (1 + S) * (Ay * X - Ax * Y + Z);
		xyzC.setX(deltaX + S * (X + Az * Y - Ay * Z));
		xyzC.setY(deltaY + S * (Y - Az * X + Ax * Z));
		xyzC.setZ(deltaZ + S * (Ay * X - Ax * Y + Z));
		return xyzC;
	}

	/**
	 * 同一椭球下平面直角坐标之间的转换，4参数法
	 * @param xyzCoordinate
	 * @param deltaX
	 * @param deltaY
	 * @param A
	 * @param M
	 * @return
	 */
	public static XYZCoordinate Param4(XYZCoordinate xyzCoordinate, Double deltaX, Double deltaY, Double A, Double M) {
		XYZCoordinate xyzC = new XYZCoordinate(0.0, 0.0, 0.0);
		Double X, Y;
		X = xyzCoordinate.getX();
		Y = xyzCoordinate.getY();
		// xyzC.X = M * (deltaX + Math.Cos(A) * X + Math.Sin(A) * Y);
		// xyzC.Y = M * (deltaY - Math.Sin(A) * X + Math.Cos(A) * Y);
		xyzC.setX(deltaX + M * Math.cos(A) * X + M * Math.sin(A) * Y);
		xyzC.setY(deltaY - M * Math.sin(A) * X + M * Math.cos(A) * Y);
		// xyzC.X = deltaX + M * X + A * Y;
		// xyzC.Y = deltaY - A * X + M * Y;
		xyzC.setZ(xyzCoordinate.getZ());
		return xyzC;
	}

}
