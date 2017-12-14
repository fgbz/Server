package phalaenopsis.common.method;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.expression.spel.ast.FunctionReference;

import phalaenopsis.common.entity.Point;

/**
 * Douglas—Peucker 矢量数据压缩算法
 */
public final class DouglasPeucker {

	/**
	 * Uses the Douglas Peucker algorithm to reduce the number of points.
	 * 
	 * @return
	 */
	public static List<Point> reduction(List<Point> points, Double Tolerance) {
		if (points == null || points.size() < 3) {
			return points;
		}

		int firstPoint = 0;
		int lastPoint = points.size() - 1;
		List<Integer> pointIndexsToKeep = new ArrayList<Integer>();
		// Add the first and last index to the keepers
		pointIndexsToKeep.add(firstPoint);
		pointIndexsToKeep.add(lastPoint);

		// The first and the last point cannot be the same
		while (points.get(firstPoint).equals(points.get(lastPoint))) {
			lastPoint--;
		}

		pointIndexsToKeep = douglasPeuckerReduction(points, firstPoint, lastPoint, Tolerance, pointIndexsToKeep);

		List<Point> returnPoints = new ArrayList<Point>();
		Collections.sort(pointIndexsToKeep);

		for (Integer index : pointIndexsToKeep) {
			returnPoints.add(points.get(index));
		}
		return returnPoints;
	}

	/**
	 *  Douglases the peucker reduction.
	 * @param points
	 * @param firstPoint
	 * @param lastPoint
	 * @param tolerance
	 * @param pointIndexsToKeep The point index to keep.
	 * @return
	 */
	private static List<Integer> douglasPeuckerReduction(List<Point> points, int firstPoint, int lastPoint,
			Double tolerance, List<Integer> pointIndexsToKeep) {
		Double maxDistance = 0.0;
		int indexFarthest = 0;

		for (int index = firstPoint; index < lastPoint; index++) {
			Double distance = perpendicularDistance(points.get(firstPoint), points.get(lastPoint), points.get(index));

			if (distance > maxDistance) {
				maxDistance = distance;
				indexFarthest = index;
			}
		}

		if (maxDistance > tolerance && indexFarthest != 0) {
			// Add the largest point that exceeds the tolerance
			pointIndexsToKeep.add(indexFarthest);

			pointIndexsToKeep = douglasPeuckerReduction(points, firstPoint, indexFarthest, tolerance,
					pointIndexsToKeep);

			pointIndexsToKeep = douglasPeuckerReduction(points, indexFarthest, lastPoint, tolerance,
					pointIndexsToKeep);
			
		}

		return pointIndexsToKeep;
	}

	/**
	 * The distance of a point from a line made from point1 and point2.
	 * @param point1
	 * @param point2
	 * @param point
	 * @return
	 */
	private static Double perpendicularDistance(Point point1, Point point2, Point point) {

		Double area = Math.abs(0.5 * (point1.x * point2.y + point2.x * point.y + point.x * point1.y
				- point2.x * point1.y - point.x * point2.y - point1.x * point.y));
		Double bottom = Math.sqrt(Math.pow(point1.x - point2.x, 2) + Math.pow(point1.y - point2.y, 2));
		Double height = area / bottom * 2;
		
		return height;
	}

}
