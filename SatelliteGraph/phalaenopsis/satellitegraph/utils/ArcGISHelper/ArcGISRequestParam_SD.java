package phalaenopsis.satellitegraph.utils.ArcGISHelper;

public abstract class ArcGISRequestParam_SD extends BaseArcGISRequestParam {
	/**
	 * 图形类型，默认为 esriGeometryPolygon
	 */
	protected String _geometryType = "";

	/**
	 * 数据格式，一般为json
	 */
	protected String _f = "";

	/**
	 * sr 编码
	 */
	protected String _inSR = "";

	public ArcGISRequestParam_SD() {
		_geometryType = "esriGeometryPolygon";
		_f = "json";
		_inSR = "4610";

	}
}
