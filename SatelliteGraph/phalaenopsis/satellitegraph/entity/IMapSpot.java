package phalaenopsis.satellitegraph.entity;

/**
 * 图斑
 * @author chunl
 *
 */
public interface IMapSpot {
	
	 long getID();
	 
	 void setID(long id);
	
	/**
	 * 业务类型
	 */
	String getBizType();
	
	void setBizType(String s);
	
	Polygon getShape();
	void setShape(Polygon p);
}
