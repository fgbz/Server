package phalaenopsis.satellitegraph.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


public class Envelope extends Geometry {
	private double xmax;

	private double xmin;

	private double ymax;

	private double ymin;

	public double Height;

	public double getHeight() {
		return this.ymax - this.ymin;
	}

	public double Width;

	public double getWidth() {
		return this.xmax - this.xmin;
	}

	@JsonProperty("xmax")
	public double XMax;

	@JsonProperty("xmin")
	public double XMin;

	@JsonProperty("ymax")
	public double YMax;

	@JsonProperty("ymin")
	public double YMin;

	public double getXMax() {
		return XMax;
	}

	public void setXMax(double xMax) {
		XMax = xMax;
	}

	public double getXMin() {
		return XMin;
	}

	public void setXMin(double xMin) {
		XMin = xMin;
	}

	public double getYMax() {
		return YMax;
	}

	public void setYMax(double yMax) {
		YMax = yMax;
	}

	public double getYMin() {
		return YMin;
	}

	public void setYMin(double yMin) {
		YMin = yMin;
	}

	public Envelope() {
		this.xmin = Double.NaN;
		this.xmax = Double.NaN;
		this.ymin = Double.NaN;
		this.ymax = Double.NaN;
	}

	public Envelope(double x1, double y1, double x2, double y2) {
		this.xmin = Double.NaN;
		this.xmax = Double.NaN;
		this.ymin = Double.NaN;
		this.ymax = Double.NaN;
		this.xmin = Math.min(x1, x2);
		this.ymin = Math.min(y1, y2);
		this.xmax = Math.max(x1, x2);
		this.ymax = Math.max(y1, y2);
	}

	public Envelope(MapPoint p1, MapPoint p2) {
		this.xmin = Double.NaN;
		this.xmax = Double.NaN;
		this.ymin = Double.NaN;
		this.ymax = Double.NaN;
		if (p1 != null || p2 != null) {
			if (p1 == null || p2 == null) {
				if (p1 != null) {
					if (p2 == null) {
						this.xmin = p1.X;
						this.ymin = p1.Y;
						this.xmax = p1.X;
						this.ymax = p1.Y;
					}
					return;
				} else {
					this.xmin = p2.X;
					this.ymin = p2.Y;
					this.xmax = p2.X;
					this.ymax = p2.Y;
					return;
				}
			} else {
				this.xmin = Math.min(p1.X, p2.X);
				this.ymin = Math.min(p1.Y, p2.Y);
				this.xmax = Math.max(p1.X, p2.X);
				this.ymax = Math.max(p1.Y, p2.Y);
				return;
			}
		} else {
			return;
		}
	}

	Polyline AsPolyline() {
		Polyline polyline = new Polyline();
		List<MapPoint> pointCollection = new ArrayList<MapPoint>();
		pointCollection.add(new MapPoint(this.XMin, this.YMin));
		pointCollection.add(new MapPoint(this.XMin, this.YMax));
		pointCollection.add(new MapPoint(this.XMax, this.YMax));
		pointCollection.add(new MapPoint(this.XMax, this.YMin));
		pointCollection.add(new MapPoint(this.XMin, this.YMin));
		polyline.Paths.add(pointCollection);
		return polyline;
	}

	public Envelope Clone() {
		Envelope envelope = (Envelope) clone();
		if (spatialReference != null) {
			envelope.spatialReference = this.spatialReference.Clone();
		}
		return envelope;
	}

	public MapPoint GetCenter() {
		MapPoint mapPoint = new MapPoint();
		if (!Double.isNaN(this.xmin) && !Double.isNaN(this.xmax)) {
			mapPoint.X = (this.xmin + this.xmax) / 2;
		}
		if (!Double.isNaN(this.ymin) && !Double.isNaN(this.ymax)) {
			mapPoint.Y = (this.ymin + this.ymax) / 2;
		}
		mapPoint.SetSpatialReference(this.spatialReference);
		return mapPoint;
	}

	static int GetFrame(double x, double Dateline) {
		double num = (x + Dateline) / Dateline * 2;
		if (num % 2 == 1) {
			num = num - 1;
		}
		return (int) Math.floor(num);
	}

	static List<Envelope> NormalizeExtent(Envelope e1, double dateline) {
		List<Envelope> list = new ArrayList<Envelope>();
		if (e1 != null) {
			double num = Envelope.NormalizeX(e1.XMin, dateline);
			double num1 = Envelope.NormalizeX(e1.XMax, dateline);
			if (e1.Width < 2 * dateline) {
				if (num <= num1) {
					list.add(new Envelope(num, e1.YMin, num1, e1.YMax));
				} else {
					list.add(new Envelope(num, e1.YMin, dateline, e1.YMax));
					list.add(new Envelope(-dateline, e1.YMin, num1, e1.YMax));
				}
				return list;
			} else {
				list.add(new Envelope(num, e1.YMin, dateline, e1.YMax));
				for (double i = e1.Width - dateline - num; i > 2 * dateline; i = i - 2 * dateline) {
					list.add(new Envelope(-dateline, e1.YMin, dateline, e1.YMax));
				}
				list.add(new Envelope(-dateline, e1.YMin, num1, e1.YMax));
				return list;
			}
		} else {
			list.add(e1);
			return list;
		}
	}

	static double NormalizeX(double x, double dateline) {
		if (x >= -dateline) {
			if (x <= dateline) {
				return x;
			} else {
				return (x - dateline) % dateline * 2 - dateline;
			}
		} else {
			return (x + dateline) % 2 * dateline + dateline;
		}
	}

	public String ToString() {
		Object[] objArray = new Object[4];
		objArray[0] = this.xmin;
		objArray[1] = this.ymin;
		objArray[2] = this.xmax;
		objArray[3] = this.ymax;
		String coor = objArray[0] + "," + objArray[1] + "," + objArray[2] + "," + objArray[3];
		return coor;
	}

	boolean Within(Envelope other) {
		if (this.xmin < other.XMin || this.xmax > other.XMax || this.ymin < other.YMin) {
			return false;
		} else {
			return this.ymax <= other.YMax;
		}
	}
}