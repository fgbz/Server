package phalaenopsis.allWeather.service;

import com.vividsolutions.jts.index.quadtree.Quadtree;

import phalaenopsis.allWeather.dao.IallWeatherdao;
import phalaenopsis.allWeather.entity.SwSde;
import phalaenopsis.common.method.init.ILoad;
import phalaenopsis.common.method.secrteKey.EncryptHelper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Polygon;

@Service("polygonRegionService")
public class PolygonRegionService implements ILoad {

//	@Autowired
//	private IallWeatherdao dao;

//	Quadtree tree = new Quadtree();

	public void loadAllPolygons() {

//		String season = dao.getCurrentPeriod().get(0);

//		List<SwSde> list = dao.getSwSde(null, null, season.split("-")[0], season.split("-")[1]);
//
//		try {
//			for (SwSde swSde : list) {
//				String shape = EncryptHelper.decryptDES(swSde.getShape(), EncryptHelper.key);
//				String[] splits = shape.split(",");
//				double minx = Double.parseDouble(splits[0]), miny = Double.parseDouble(splits[1]),
//						maxx = Double.parseDouble(splits[0]), maxy = Double.parseDouble(splits[1]);
//				for (int i = 0; i < splits.length / 2; i = i + 2) {
//					double x = Double.parseDouble(splits[i]), y = Double.parseDouble(splits[i + 1]);
//					if (minx > x)
//						minx = x;
//					if (miny > y)
//						miny = y;
//					if (maxx < x)
//						maxx = x;
//					if (maxy < y)
//						maxy = y;
//				}
//
//				Envelope envelope = new Envelope(minx, maxx, miny, maxy);
//				tree.insert(envelope, swSde);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	@SuppressWarnings("unchecked")
	public List<SwSde> query(double minx, double maxx, double miny, double maxy) {
//		Envelope envelope = new Envelope(minx, maxx, miny, maxy);
//		List<SwSde> list = tree.query(envelope);
		return null;
	}

	@Override
	public void load() {
		loadAllPolygons();
	}

}
