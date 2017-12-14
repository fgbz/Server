package phalaenopsis.satellitegraph.entity;

/**
 * 影像点类型
 * 
 * @author chunl
 *
 */
public interface ShadowType {

	/**
	 * 原始图斑点
	 */
	static final int Origin = 0;

	/**
	 * 影子图斑点（经过分、并宗产生的新的图斑点）
	 */
	static final int Shadow = 1;
}
