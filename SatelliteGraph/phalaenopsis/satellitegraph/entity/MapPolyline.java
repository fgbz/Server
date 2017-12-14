package phalaenopsis.satellitegraph.entity;

import java.util.ArrayList;
import java.util.List;

import phalaenopsis.satellitegraph.entity.PointArray;

public class MapPolyline  extends Geometry {
	private List<PointArray> paths;

	public List<PointArray> getPaths() {
		return paths;
	}

	public void setPaths(List<PointArray> paths) {
		this.paths = paths;
	}

	public MapPolyline() {
		this.paths = new ArrayList<PointArray>();
	}
}
