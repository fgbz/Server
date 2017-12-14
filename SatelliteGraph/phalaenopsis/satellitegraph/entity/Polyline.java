package phalaenopsis.satellitegraph.entity;

import java.util.ArrayList;
import java.util.List;

public class Polyline extends Geometry {

	public List<List<MapPoint>> Paths;

	public List<List<MapPoint>> getPaths() {
		return Paths;
	}

	public void setPaths(List<List<MapPoint>> paths) {
		Paths = paths;
	}

	public Polyline() {
		this.Paths = new ArrayList<List<MapPoint>>();
	}

	@SuppressWarnings({ "unchecked", "unused" })
	private void AddPointCollectionEvents(List<Object> items) {
		for (Object item : items) {
			 List<MapPoint> pointCollection = (List<MapPoint>) item ;
			if (pointCollection == null) {
				continue;
			}
		}
	}

	public Polyline Clone() {
		Polyline polyline = new Polyline();
		if (this.Paths != null) {
			for (List<MapPoint> path : this.Paths) {
				if (path == null) {
					polyline.Paths.add(null);
				} else {
					List<MapPoint> pointCollection = new ArrayList<MapPoint>();
					for (MapPoint mapPoint : path) {
						if (mapPoint == null) {
							continue;
						}
						pointCollection.add(mapPoint.Clone());
					}
					polyline.Paths.add(pointCollection);
				}
			}
		}
		if (spatialReference != null) {
			polyline.spatialReference = this.spatialReference.Clone();
		}
		return polyline;
	}
}
