package phalaenopsis.satellitegraph.entity;

public class GeometryPolygon extends Geometry {
	public String geometryType;
	public Polygon Geometry;

	public String getGeometryType() {
		return geometryType;
	}

	public void setGeometryType(String geometryType) {
		this.geometryType = geometryType;
	}

	public Polygon getGeometry() {
		return Geometry;
	}

	public void setGeometry(Polygon geometry) {
		Geometry = geometry;
	}

	public GeometryPolygon() {
		Geometry = new Polygon();
	}
}