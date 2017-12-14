package phalaenopsis.satellitegraph.entity;

import java.util.ArrayList;
import java.util.List;

public class MultiPolygon extends Geometry
{
    public String geometryType;
    public List<Polygon> Geometries ;


    public List<Polygon> getGeometries() {
		return Geometries;
	}


	public void setGeometries(List<Polygon> geometries) {
		Geometries = geometries;
	}


	public MultiPolygon()
    {
        Geometries = new ArrayList<Polygon>();
    }
}
