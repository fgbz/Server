/**
 * 
 */
package phalaenopsis.satellitegraph.entity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.CoordinateList;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.index.quadtree.Quadtree;

import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.secrteKey.CryptoHelper;

/**
 * @author gaofengt
 *
 *         2017年4月25日下午4:47:58
 */
@Component
public class PolygonRegion extends Basis {

	@Resource
	private SqlSession sqlSession;

	Quadtree tree = new Quadtree();
	
//	 public void LoadAllPolygons()
//     {
//             int total = session.QueryOver<SpotSDE>().RowCount();
//             int perTake = 100;
//             int page = total % perTake > 0 ? total / perTake + 1 : total / perTake;
//             for (int i = 0; i < page; i++) {
//                 IList<SpotSDE> list = session.QueryOver<SpotSDE>()
//                     .Skip(i * perTake)
//                     .Take(perTake)
//                     .List();
//                 list.ForEach(item =>
//                     {
//                         SdePolygon sde = MapSpot2016.GetDecryptShape(item.Shape);
//                         GeoPolygon[] geos = ToGeo(sde);
//                         foreach (GeoPolygon geo in geos) {
//                             geo.UserData = item.MID;
//                             tree.Insert(geo.EnvelopeInternal, geo);
//                         }
//                     });
//             }
//         }
//     }

	public void LoadPolygon(SpotSDE spot) {
		phalaenopsis.satellitegraph.entity.Polygon sde = MapSpot2016.getDecryptShape(spot.Shape);
		Polygon[] geos = toGeo(sde);
		for (Polygon geo : geos) {
			geo.setUserData(spot.MID);
			 tree.insert(geo.getEnvelopeInternal(), geo);
		}
	}

	public List<SpotSDE> query(phalaenopsis.satellitegraph.entity.Polygon rect) {
		Polygon geo = toGeo(rect)[0];
		List<Polygon> list = tree.query(geo.getEnvelopeInternal());
		byte[] rndKey = (byte[]) getCurrentUser().getSession().get("MobileShapeKey");
		byte[] iv = (byte[]) getCurrentUser().getSession().get("MobileShapeIV");
		List<SpotSDE> result = new ArrayList<>();
		for (Polygon polygon : list) {
			phalaenopsis.satellitegraph.entity.Polygon sde = toSde(polygon);
			SpotSDE spot = new SpotSDE();
			spot.MID = Long.parseLong(polygon.getUserData().toString());
			byte[] data = SerializationUtils.serialize(sde);
			spot.Shape = CryptoHelper.to3DES(rndKey, iv, data);
			result.add(spot);
		}
		return result;
	}

	/**
	 * @param geo
	 */
	private phalaenopsis.satellitegraph.entity.Polygon toSde(Polygon geo) {
		phalaenopsis.satellitegraph.entity.Polygon sde = new phalaenopsis.satellitegraph.entity.Polygon();
		PointArray outer = new PointArray();
		for (Coordinate point : geo.getExteriorRing().getCoordinates()) {
			outer.add(new Double[] { point.x, point.y });
		}
		sde.getRings().add(outer);
		// 利用反射获取holes属性值
		try {
			Field holes = geo.getClass().getDeclaredField("holes");
			holes.setAccessible(true);
			LinearRing[] object = (LinearRing[]) holes
					.get(Class.forName("com.vividsolutions.jts.geom.Polygon").newInstance());
			if (object != null && object.length > 0) {
				for (LinearRing linearRing : object) {
					PointArray inner = new PointArray();
					for (Coordinate point : linearRing.getCoordinates()) {
						inner.add(new Double[] { point.x, point.y });
					}
					sde.rings.add(inner);
				}
			}
			return sde;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sde;
	}

	/**
	 * @param rect
	 * @return
	 */
	private Polygon[] toGeo(phalaenopsis.satellitegraph.entity.Polygon sde) {
		List<LinearRing> rings = new ArrayList<>();
		for (ArrayList<Double[]> item : sde.getRings()) {
			CoordinateList list = new CoordinateList();
			for (Double[] d : item) {
				Coordinate point = new Coordinate(d[0], d[1]);
				list.add(point);
			}
			LinearRing r = new LinearRing(list.toCoordinateArray(), null, 0);
			rings.add(r);
		}
		if (rings.size() == 1) {
			LinearRing shell = rings.get(0);
			Polygon polygon = new Polygon(shell, null, null);
			Polygon[] arr = { polygon };
			return arr;
		}
		List<Polygon> ringRanges = new ArrayList<>();
		for (LinearRing linearRing : rings) {
			ringRanges.add(new Polygon(linearRing, null, null));
		}
		// ringRanges.sort((a, b) => a.Area.CompareTo(b.Area));
		List<SinglePolygon> polygons = new ArrayList<SinglePolygon>();
		Stack<Polygon> current = new Stack<Polygon>();
		Stack<Polygon> other = new Stack<Polygon>();
		// ringRanges
		for (Polygon polygon : ringRanges) {
			current.push(polygon);
		}
		do {
			SinglePolygon s0 = new SinglePolygon();
			s0.Outer = current.pop();
			while (current.size() > 0) {
				Polygon r = current.pop();
				if (s0.Outer.contains(r)) {
					if (s0.Inners == null)
						s0.Inners = new ArrayList<LinearRing>();
					s0.Inners.add((LinearRing) r.getExteriorRing());
				} else if (!s0.Outer.intersects(r) || s0.Outer.touches(r)) {
					other.push(r);
				} else
					// throw new Exception("坐标错误。");
					try {

					} catch (Exception e) {
						String message = e.getMessage();
						message = "坐标错误。";
					}
			}
			polygons.add(s0);
			while (other.size() > 0) {
				Polygon r = other.pop();
				current.push(r);
			}
		} while (current.size() > 0);
		// return polygons.se
		List<Polygon> temp = new ArrayList<>();
		for (SinglePolygon item : polygons) {
			Polygon p = new Polygon((LinearRing) item.Outer.getExteriorRing(),
					item.Inners == null ? null : (LinearRing[]) item.Inners.toArray(), null);
			temp.add(p);
		}
		return (Polygon[]) temp.toArray();
	}

	public SpotSDE single(long id) {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from sg_sde t where t.mid=" + id);
		SpotSDE spot = sqlSession.selectOne("satellitegraphservice.getSpotSDE", sb.toString());
		if (spot == null) {
			return null;
		}
		phalaenopsis.satellitegraph.entity.Polygon sde = MapSpot2016.getDecryptShape(spot.getShape());
		// 序列化为byte数组
		byte[] data = SerializationUtils.serialize(sde);
		byte[] rndKey = (byte[]) getCurrentUser().getSession().get("MobileShapeKey");
		byte[] iv = (byte[]) getCurrentUser().getSession().get("MobileShapeIV");
		spot.Shape = CryptoHelper.to3DES(rndKey, iv, data);
		return spot;
	}

	class SinglePolygon {
		public Polygon Outer;
		public List<LinearRing> Inners;
	}

}
