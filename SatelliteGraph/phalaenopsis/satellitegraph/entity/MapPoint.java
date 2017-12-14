package phalaenopsis.satellitegraph.entity;

public class MapPoint extends Geometry implements Cloneable {
	public double X;

	public double Y;

	public double getX() {
		return X;
	}

	public void setX(double x) {
		X = x;
	}

	public double getY() {
		return Y;
	}

	public void setY(double y) {
		Y = y;
	}

	public MapPoint() {
	}

	public MapPoint(double x, double y) {
		this.X = x;
		this.Y = y;
	}

	public MapPoint Clone() {
		MapPoint mapPoint = null;
		mapPoint = (MapPoint) super.clone();
		if (spatialReference != null) {
			mapPoint.spatialReference = this.spatialReference.Clone();
		}
		return mapPoint;
	}
}