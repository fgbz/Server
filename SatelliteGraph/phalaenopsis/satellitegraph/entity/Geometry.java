package phalaenopsis.satellitegraph.entity;

public abstract class Geometry implements Cloneable{

	private final double DECDEG_TO_METERS = 111319.491;

	private final double INCHES_PER_METER = 39.37;

	private static double[] values;

	private static int[][] WkidUnitRanges;

	public String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public SpatialReference spatialReference;

	static {
		double[] numArray = new double[] { 1, 0.2011661949, 0.304799710181509, 0.304800609601219, 0.3048, 0.304797265,
				0.914398530744441, 20.1167824943759, 0.914398414616029, 20.1167651215526, 0.304799471538676, 0.91439523,
				50000, 150000 };
		Geometry.values = numArray;
		int[][] numArray1 = new int[][] { { 0x7d0, 0x7fd, 0 }, { 0x808, 0x811, 0 }, { 0x812, 0x812, 1 },
				{ 0x813, 0x857, 0 }, { 0x858, 0x858, 2 }, { 0x859, 0x86a, 0 }, { 0x86b, 0x86b, 3 }, { 0x86d, 0x86e, 0 },
				{ 0x86f, 0x870, 2 }, { 0x871, 0x87a, 0 }, { 0x87c, 0x88e, 0 }, { 0x890, 0x891, 0 }, { 0x893, 0x896, 0 },
				{ 0x898, 0x89b, 0 }, { 0x89c, 0x89c, 3 }, { 0x89d, 0x8a9, 0 }, { 0x8ab, 0x8ac, 0 }, { 0x8ae, 0x8b0, 4 },
				{ 0x8b1, 0x8ca, 3 }, { 0x8cb, 0x8cd, 4 }, { 0x8ce, 0x8cf, 3 }, { 0x8d0, 0x8d0, 4 }, { 0x8d1, 0x8d8, 3 },
				{ 0x8d9, 0x8da, 4 }, { 0x8db, 0x8dc, 3 }, { 0x8dd, 0x8de, 4 }, { 0x8df, 0x8e0, 3 }, { 0x8e1, 0x8e1, 4 },
				{ 0x8e2, 0x8e7, 3 }, { 0x8e8, 0x8ea, 4 }, { 0x8eb, 0x8f1, 3 }, { 0x8f2, 0x8f4, 0 }, { 0x8f6, 0x8f7, 0 },
				{ 0x904, 0x909, 0 }, { 0x90a, 0x90a, 5 }, { 0x90b, 0x99e, 0 }, { 0x9db, 0xb32, 0 }, { 0xb33, 0xb35, 4 },
				{ 0xb36, 0xb48, 3 }, { 0xb4b, 0xb4f, 3 }, { 0xb50, 0xb52, 4 }, { 0xb53, 0xb54, 3 }, { 0xb55, 0xb55, 4 },
				{ 0xb56, 0xb5c, 3 }, { 0xb5d, 0xb5e, 4 }, { 0xb5f, 0xb60, 3 }, { 0xb61, 0xb62, 4 }, { 0xb63, 0xb68, 3 },
				{ 0xb69, 0xb6b, 4 }, { 0xb6c, 0xb72, 3 }, { 0xb73, 0xb75, 0 }, { 0xb77, 0xb92, 0 }, { 0xb94, 0xb98, 3 },
				{ 0xb99, 0xb9d, 0 }, { 0xb9f, 0xba6, 0 }, { 0xba8, 0xbad, 0 }, { 0xbaf, 0xbaf, 0 }, { 0xbb0, 0xbb0, 4 },
				{ 0xbb1, 0xbb1, 0 }, { 0xbb2, 0xbb2, 4 }, { 0xbb3, 0xbdd, 0 }, { 0xbee, 0xc07, 0 }, { 0xc08, 0xc08, 4 },
				{ 0xc09, 0xc10, 0 }, { 0xc11, 0xc11, 3 }, { 0xc12, 0xc12, 0 }, { 0xc13, 0xc13, 3 }, { 0xc14, 0xc1d, 0 },
				{ 0xc1e, 0xc1e, 3 }, { 0xc22, 0xc42, 0 }, { 0xc45, 0xc46, 0 }, { 0xc4c, 0xc4d, 0 }, { 0xc51, 0xc5e, 0 },
				{ 0xc61, 0xc64, 0 }, { 0xc66, 0xc83, 0 }, { 0xcde, 0xcde, 0 }, { 0xce0, 0xd1e, 0 }, { 0xd1f, 0xd1f, 3 },
				{ 0xd20, 0xd20, 0 }, { 0xd21, 0xd21, 4 }, { 0xd22, 0xd22, 0 }, { 0xd23, 0xd23, 3 }, { 0xd24, 0xd24, 0 },
				{ 0xd25, 0xd25, 3 }, { 0xd26, 0xd26, 5 }, { 0xd27, 0xd3c, 0 }, { 0xd3f, 0xd4b, 0 }, { 0xd4c, 0xd4c, 3 },
				{ 0xd4d, 0xd4e, 0 }, { 0xd4f, 0xd4f, 5 }, { 0xd50, 0xd58, 0 }, { 0xd59, 0xd6e, 3 }, { 0xd6f, 0xd70, 0 },
				{ 0xd71, 0xd76, 3 }, { 0xd77, 0xd7a, 0 }, { 0xd7d, 0xd7d, 3 }, { 0xd80, 0xd83, 3 }, { 0xd84, 0xd88, 0 },
				{ 0xde8, 0xdf2, 3 }, { 0xdf3, 0xdfd, 0 }, { 0xe8f, 0xe8f, 0 }, { 0xe96, 0xe9b, 3 }, { 0xea9, 0xeb0, 3 },
				{ 0xeb1, 0xeb3, 0 }, { 0xf11, 0xf11, 0 }, { 0xf50, 0xf50, 0 }, { 0xf97, 0xf98, 3 },
				{ 0x4e22, 0x4e40, 0 }, { 0x4e5e, 0x4e7c, 0 }, { 0x4ea7, 0x4eaa, 0 }, { 0x4f18, 0x4f22, 0 },
				{ 0x4f7c, 0x4f86, 0 }, { 0x4fd4, 0x4fd8, 0 }, { 0x5013, 0x5013, 0 }, { 0x503a, 0x503b, 0 },
				{ 0x5136, 0x5136, 0 }, { 0x5156, 0x5158, 0 }, { 0x51c6, 0x51c8, 0 }, { 0x522b, 0x522d, 0 },
				{ 0x5267, 0x5269, 0 }, { 0x529c, 0x529e, 0 }, { 0x532b, 0x532c, 0 }, { 0x53a5, 0x53af, 0 },
				{ 0x53e1, 0x53eb, 0 }, { 0x53fc, 0x53fc, 0 }, { 0x5514, 0x5515, 0 }, { 0x5539, 0x553a, 0 },
				{ 0x5583, 0x5586, 0 }, { 0x5588, 0x558b, 0 }, { 0x5610, 0x5611, 0 }, { 0x564b, 0x564c, 0 },
				{ 0x569b, 0x56a1, 0 }, { 0x56a5, 0x56ab, 0 }, { 0x56af, 0x56b5, 0 }, { 0x56da, 0x56dc, 0 },
				{ 0x573c, 0x573c, 0 }, { 0x5777, 0x5778, 0 }, { 0x57f9, 0x57fd, 0 }, { 0x58ac, 0x58ac, 0 },
				{ 0x58f2, 0x58f2, 0 }, { 0x58fc, 0x58fc, 0 }, { 0x5930, 0x5930, 0 }, { 0x59cf, 0x59d2, 0 },
				{ 0x59f4, 0x59fe, 0 }, { 0x5a32, 0x5a32, 0 }, { 0x5a37, 0x5a37, 0 }, { 0x5ac7, 0x5ac8, 0 },
				{ 0x5b89, 0x5b89, 0 }, { 0x5c94, 0x5c94, 0 }, { 0x5d16, 0x5d2d, 0 }, { 0x5d3a, 0x5d40, 0 },
				{ 0x5d45, 0x5d4c, 0 }, { 0x5d4e, 0x5d56, 0 }, { 0x5d8a, 0x5d8c, 0 }, { 0x5def, 0x5df0, 0 },
				{ 0x5e24, 0x5e24, 0 }, { 0x5e88, 0x5e88, 0 }, { 0x5ef1, 0x5ef2, 0 }, { 0x5ef7, 0x5ef9, 0 },
				{ 0x5f16, 0x5f1b, 0 }, { 0x5f32, 0x5f36, 6 }, { 0x5f37, 0x5f3d, 0 }, { 0x5f3e, 0x5f3e, 6 },
				{ 0x5f3f, 0x5f3f, 0 }, { 0x5fb4, 0x5fb4, 0 }, { 0x5fe3, 0x5fe4, 0 }, { 0x5ffb, 0x5ffb, 7 },
				{ 0x6018, 0x6018, 0 }, { 0x608e, 0x6091, 0 }, { 0x60f1, 0x60f5, 0 }, { 0x612d, 0x6132, 0 },
				{ 0x613b, 0x613d, 0 }, { 0x61a8, 0x61a8, 0 }, { 0x628f, 0x628f, 0 }, { 0x632f, 0x6333, 0 },
				{ 0x64e4, 0x64ee, 0 }, { 0x651c, 0x651c, 0 }, { 0x654c, 0x654c, 0 }, { 0x664f, 0x6653, 0 },
				{ 0x667d, 0x667d, 0 }, { 0x66db, 0x66dc, 0 }, { 0x6717, 0x6719, 0 }, { 0x6740, 0x6740, 0 },
				{ 0x67df, 0x67e0, 0 }, { 0x6808, 0x6808, 0 }, { 0x6844, 0x6844, 0 }, { 0x684d, 0x6862, 0 },
				{ 0x6869, 0x68af, 3 }, { 0x68b1, 0x68b3, 3 }, { 0x68bb, 0x68bd, 3 }, { 0x6915, 0x692b, 0 },
				{ 0x6931, 0x6942, 0 }, { 0x6944, 0x6976, 0 }, { 0x699d, 0x69a0, 0 }, { 0x69f0, 0x69f0, 0 },
				{ 0x6a40, 0x6a40, 0 }, { 0x6a45, 0x6a60, 0 }, { 0x6a7a, 0x6a7c, 0 }, { 0x6a9b, 0x6a9c, 8 },
				{ 0x6aff, 0x6b06, 0 }, { 0x6b25, 0x6b25, 0 }, { 0x6b64, 0x6b64, 0 }, { 0x6b6c, 0x6b6c, 0 },
				{ 0x6ba9, 0x6bac, 0 }, { 0x6bb3, 0x6bb6, 0 }, { 0x6bbd, 0x6bc0, 0 }, { 0x6bc7, 0x6bca, 0 },
				{ 0x6c34, 0x6c34, 0 }, { 0x6e1f, 0x6e21, 0 }, { 0x6e48, 0x6e48, 0 }, { 0x6ebc, 0x6ec6, 0 },
				{ 0x6ef2, 0x6f10, 0 }, { 0x6f2e, 0x6f4c, 0 }, { 0x6fb8, 0x6fb8, 0 }, { 0x713f, 0x7140, 0 },
				{ 0x71ac, 0x71ad, 0 }, { 0x71be, 0x71c2, 0 }, { 0x71f0, 0x71f4, 0 }, { 0x71f9, 0x7201, 0 },
				{ 0x7203, 0x720b, 0 }, { 0x7224, 0x7225, 0 }, { 0x7295, 0x7295, 0 }, { 0x73c3, 0x73c4, 0 },
				{ 0x742a, 0x742b, 0 }, { 0x7499, 0x749a, 0 }, { 0x74af, 0x74af, 9 }, { 0x74b0, 0x74b0, 10 },
				{ 0x74b1, 0x74b1, 0 }, { 0x74cc, 0x74cf, 0 }, { 0x75d1, 0x75e3, 0 }, { 0x75f8, 0x75f8, 1 },
				{ 0x7683, 0x7684, 0 }, { 0x771b, 0x771e, 0 }, { 0x777f, 0x7780, 0 }, { 0x7809, 0x780c, 0 },
				{ 0x7847, 0x7848, 0 }, { 0x7850, 0x7850, 0 }, { 0x7934, 0x7934, 0 }, { 0x7991, 0x7991, 0 },
				{ 0x79b2, 0x79b2, 0 }, { 0x79c2, 0x79c3, 0 }, { 0x7a13, 0x7a1b, 0 }, { 0x7a21, 0x7a24, 0 },
				{ 0x7a2b, 0x7a2f, 0 }, { 0x7a31, 0x7a41, 0 }, { 0x7a8a, 0x7a8a, 0 }, { 0x7ae5, 0x7aed, 0 },
				{ 0x7b03, 0x7b07, 0 }, { 0x7b28, 0x7b29, 0 }, { 0x7b70, 0x7b70, 0 }, { 0x7bd4, 0x7bd4, 0 },
				{ 0x7c5e, 0x7c5f, 0 }, { 0x7c9d, 0x7c9d, 0 }, { 0x7cad, 0x7cb2, 0 }, { 0x7ce3, 0x7d00, 0 },
				{ 0x7d01, 0x7d03, 3 }, { 0x7d05, 0x7d1f, 3 }, { 0x7d21, 0x7d3c, 3 }, { 0x7d3d, 0x7d3e, 0 },
				{ 0x7d40, 0x7d43, 3 }, { 0x7d4a, 0x7d4d, 3 }, { 0x7d51, 0x7d56, 0 }, { 0x7d62, 0x7d62, 0 },
				{ 0x7d63, 0x7d63, 3 }, { 0x7d64, 0x7d64, 0 }, { 0x7d68, 0x7d68, 0 }, { 0x7d6b, 0x7d82, 0 },
				{ 0x7d85, 0x7d9e, 0 }, { 0x7da1, 0x7da1, 0 }, { 0x7da4, 0x7da7, 3 }, { 0x7db4, 0x7dc7, 0 },
				{ 0x7dc9, 0x7e04, 0 }, { 0x7e2d, 0x7e68, 0 }, { 0x7f59, 0x7f96, 0 }, { 0x7f98, 0x7f9b, 3 },
				{ 0x7fbd, 0x7ff9, 0 }, { 0x7ffe, 0x7ffe, 0 }, { 0xcf09, 0xcf0c, 0 }, { 0xcf10, 0xcf1b, 0 },
				{ 0xcf1d, 0xcf28, 0 }, { 0xcf2a, 0xcf2a, 0 }, { 0xcf32, 0xcf36, 0 }, { 0xcf38, 0xcf39, 0 },
				{ 0xd2f1, 0xd2f4, 0 }, { 0xd2f8, 0xd303, 0 }, { 0xd305, 0xd310, 0 }, { 0xd312, 0xd312, 0 },
				{ 0xd31a, 0xd31e, 0 }, { 0xd320, 0xd325, 0 }, { 0xfe25, 0xfe26, 3 }, { 0xfe89, 0xfe89, 0 },
				{ 0xfe8b, 0xfe8b, 0 }, { 0x18e71, 0x18e97, 0 }, { 0x18eac, 0x18eaf, 0 }, { 0x18eb0, 0x18eb0, 11 },
				{ 0x18eb1, 0x18eb3, 0 }, { 0x18eb4, 0x18eb4, 12 }, { 0x18eb5, 0x18eb5, 13 }, { 0x18eb6, 0x18ebf, 0 },
				{ 0x18eca, 0x18ee5, 0 }, { 0x18ee6, 0x18ee6, 3 }, { 0x18ee7, 0x18ee7, 4 }, { 0x18ee8, 0x18ee9, 3 },
				{ 0x18eea, 0x18f3b, 0 }, { 0x18f3d, 0x18f43, 0 }, { 0x18f4a, 0x18f4a, 0 }, { 0x18f4b, 0x18f4c, 3 },
				{ 0x18f4d, 0x18f66, 0 }, { 0x18f68, 0x18f9a, 0 }, { 0x18f9c, 0x18f9c, 0 }, { 0x18fa0, 0x18fa0, 0 },
				{ 0x18fa3, 0x18fae, 0 }, { 0x18fb0, 0x18fb7, 0 }, { 0x18fba, 0x18fba, 0 }, { 0x18fbe, 0x18fd6, 0 },
				{ 0x18fd9, 0x18fd9, 0 }, { 0x18fdb, 0x18fdb, 0 }, { 0x19015, 0x19026, 0 }, { 0x19028, 0x1902c, 0 },
				{ 0x1903d, 0x19044, 3 }, { 0x19045, 0x19045, 0 }, { 0x1905b, 0x1905c, 0 }, { 0x190aa, 0x190b8, 0 },
				{ 0x190bf, 0x190c0, 0 }, { 0x190c9, 0x190cb, 0 }, { 0x190cc, 0x190cc, 3 }, { 0x190cd, 0x190d1, 0 },
				{ 0x190e5, 0x190f6, 3 }, { 0x190f8, 0x1912a, 3 }, { 0x1912c, 0x1912c, 3 }, { 0x19130, 0x19130, 3 },
				{ 0x19133, 0x1914a, 3 }, { 0x1914d, 0x19166, 3 }, { 0x19169, 0x19169, 3 }, { 0x1916b, 0x1916b, 3 },
				{ 0x1916e, 0x1916e, 3 }, { 0x19384, 0x193cb, 0 }, { 0x193e8, 0x1942f, 3 }, { 0x19468, 0x19472, 0 },
				{ 0x194a0, 0x194a0, 0 }, { 0x194b0, 0x1950d, 0 }, { 0x19514, 0x19571, 3 }, { 0x19578, 0x195bf, 0 },
				{ 0x195dc, 0x19623, 3 } };
		Geometry.WkidUnitRanges = numArray1;
	}

	private static double GetMetersPerInch(SpatialReference sref) {
		if (null == sref)
			return Double.NaN;
		else {
			if (sref.WKID <= 0) {
				return Geometry.GetUnitFactor(sref.WKT);
			} else {
				return Geometry.GetUnitFactor(String.valueOf(sref.WKID));
			}
		}
	}

	public static double GetScale(double unitsPerPixel, SpatialReference spatialReference, double pixelsPerInch) {
		double metersPerInch = Geometry.GetMetersPerInch(spatialReference);
		if (Double.isNaN(metersPerInch)) {
			metersPerInch = 111319.491;
		}
		return Geometry.GetScale(unitsPerPixel, metersPerInch, pixelsPerInch);
	}

	public static double GetScale(double unitsPerPixel, double metersPerUnit, double pixelsPerInch) {
		return unitsPerPixel * metersPerUnit * 39.37 * pixelsPerInch;
	}

	public void SetSpatialReference(SpatialReference SpatialReference) {
		this.spatialReference = SpatialReference;
	}

	private static double GetUnitFactor(String wkt) {
		if (wkt != null && wkt.startsWith("PROJCS")) {
			int num = wkt.lastIndexOf("UNIT");
			if (num > 0) {
				String str = wkt.substring(num);
				if (str != null && str.length() > 0) {
					String[] strArray = str.split(",");
					if ((int) strArray.length > 1 && strArray[1] != null) {
						char[] chrArray1 = new char[1];
						chrArray1[0] = ']';
						String str1 = strArray[1];
						if (strArray[1].endsWith("]")) {
							str1 = str.substring(0, str1.length() - 1);
						}

						double num1 = Double.NaN;
						return num1;
					}
				}
			}
		}

		return Double.NaN;
	}
	
	@Override
	public Object clone(){
		Geometry geometry = null;
		try {
			return geometry = (Geometry)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return geometry;
	}
}
