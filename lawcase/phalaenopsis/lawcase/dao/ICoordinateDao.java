package phalaenopsis.lawcase.dao;

import org.apache.ibatis.annotations.Param;

public interface ICoordinateDao {

	String getLawCaseShape(@Param("realShape") Boolean realShape, @Param("id") String id);

	int updateLawCaseShape(@Param("realShape") boolean realShape, @Param("strCoordinate") String strCoordinate, @Param("id") String id);
	
	int deleteLawCaseShape(@Param("realShape") boolean realShape, @Param("id") String id);
	
	//int testupdate(@Param("realShape") boolean realShape, @Param("geometry") oracle.spatial.geometry.JGeometry geometry);
}
