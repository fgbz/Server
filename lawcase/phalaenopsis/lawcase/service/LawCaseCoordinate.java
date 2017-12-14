package phalaenopsis.lawcase.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phalaenopsis.common.method.Tools.StrUtil;
import phalaenopsis.lawcase.dao.ICoordinateDao;
import phalaenopsis.lawcase.entity.CaseBaseInfo;
import phalaenopsis.satellitegraph.entity.PointArray;
import phalaenopsis.satellitegraph.entity.Polygon;
import phalaenopsis.satellitegraph.entity.SpatialReference;

@Service
public class LawCaseCoordinate {

	@Autowired
	private ICoordinateDao dao;

	/**
	 * 获取坐标信息
	 * 
	 * @param id
	 * @return
	 */
	public Polygon getCaseShape(String id) {
		return getCaseShape(id, false);
	}

	/**
	 * 获取坐标信息
	 * 
	 * @param id
	 * @param realShape
	 * @return
	 */
	public Polygon getCaseShape(String id, Boolean realShape) {
		Polygon py = new Polygon();
		py.setType("polygon");

		SpatialReference spatialReference = new SpatialReference();
		String wkt = "PROJCS[\"Transverse Mercator\",GEOGCS[\"Transverse Mercator\",DATUM[\"Krasovsky\",SPHEROID[\"Krasovsky\",6378245.0,298.3]],PRIMEM[\"Greenwich\",0.0],UNIT[\"Degree\",0.0174532925199433]],PROJECTION[\"Gauss_Kruger\"],PARAMETER[\"False_Easting\",500000.0],PARAMETER[\"False_Northing\",-3000000.0],PARAMETER[\"Central_Meridian\",114.0],PARAMETER[\"Scale_Factor\",1.0],PARAMETER[\"Latitude_Of_Origin\",0.0],UNIT[\"Meter\",1.0]]";
		spatialReference.WKT = wkt;
		py.SetSpatialReference(spatialReference);

		String result = dao.getLawCaseShape(realShape, id);

		if (StrUtil.isNullOrEmpty(result))
			return null;

		py.setRings(new ArrayList<ArrayList<Double[]>>());

		result = StrUtil.toCoordinateString(result);

		String[] points = result.split(",");
		PointArray array = new PointArray();
		for (int index = 0; index + 1 < points.length; index = index + 2) {
			Double[] point = new Double[] { Double.parseDouble(points[index]), Double.parseDouble(points[index + 1]) };
			array.add(point);
		}
		py.getRings().add(array);

		return py;
	}

	/**
	 * 导入坐标信息
	 * 
	 * @param info
	 * @return
	 */
	public boolean importCoordinate(CaseBaseInfo info) {
		return importCaseCoordinate(info, false);
	}

	/**
	 * 导入坐标信息
	 * 
	 * @param info
	 * @param realShape
	 * @return
	 */
	public boolean importCaseCoordinate(CaseBaseInfo info, boolean realShape) {

		// String coordinate = realShape ? info.getRealShape() :
		// info.getShape();
		// String[] splitStr = coordinate.split(",");
		// double[] dCorrdinate = new double[splitStr.length];
		// for (int i = 0; i < splitStr.length; i++) {
		// if (!StrUtil.isNullOrEmpty(splitStr[i])) {
		// dCorrdinate[i] = Double.parseDouble(splitStr[i]);
		// }
		// }
		// int[] elemInfo = new int[] { 1, 1003, 1 };
		// oracle.spatial.geometry.JGeometry geometry = new
		// oracle.spatial.geometry.JGeometry(2003, 4610, elemInfo,
		// dCorrdinate);
		// dao.testupdate(true, geometry);
		// return true;

		 String strCoordinate = realShape ?
		 StrUtil.toGeometryPlaneString(info.getRealShape())
		 : StrUtil.toGeometryPlaneString(info.getShape());
		 if (StrUtil.isNullOrEmpty(strCoordinate))
		 throw new IllegalArgumentException("坐标格式不正确");
		
		 int result = dao.updateLawCaseShape(realShape, strCoordinate,
		 info.getId());
		 if (1 == result)
		 return true;
		 return false;
	}

	/**
	 * 清空坐标数据
	 * 
	 * @param info
	 * @return
	 */
	public boolean deleteCoordinate(CaseBaseInfo info) {
		return deleteCoordinate(info, false);
	}

	/**
	 * 清空坐标数据
	 * 
	 * @param info
	 * @param realShape
	 * @return
	 */
	public boolean deleteCoordinate(CaseBaseInfo info, boolean realShape) {
		int result = dao.deleteLawCaseShape(realShape, info.getId());

		if (1 == result)
			return true;
		return false;
	}
}
