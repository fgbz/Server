package phalaenopsis.satellitegraph.entity;
/**
 * 
 * @author chunhongl
 * 审核状态
 * 2017年4月6日下午2:03:34
 */
public class ApproveState {
	/**
	 * 市级审核
	 */

	// 通过
	public static final int Pass = 1;

	// 不通过
	public static final int NoPass = 2;

	/**
	 * 省级审核
	 */

	// 通过审查，纳入合法
	public static final int IegalPass = 3;

	// 通过审查，纳入违法
	public static final int IllegalPass = 4;

	// 不通过审查，纳入违法
	public static final int IllegalNotPass = 5;

	// 不通过审查，纳入违法非立案
	public static final int IllegalNotCase = 6;

	// 通过审查，纳入非新增
	public static final int NotNewSpotPass = 7;

	// 不通过审查，纳入非新增
	public static final int NotNewSpotNotPass = 8;

}
