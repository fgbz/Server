package phalaenopsis.satellitegraph.entity;

import java.util.List;

/**
 * 下级工作进度树
 * @author chunl
 *
 */
public class LowerTreeNode {

	/**
	 * 区域ID
	 */
    public int RegionID ;

    
    /**
     * 区域名称
     */
    public String Name ;

    /**
     * 下级区域
     */
    public List<LowerTreeLeaf> Children ;
}
