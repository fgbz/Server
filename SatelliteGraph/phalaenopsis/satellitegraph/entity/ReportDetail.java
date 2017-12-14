package phalaenopsis.satellitegraph.entity;

/**
 * 上报情况
 * 
 * @author chunl
 *
 */
public class ReportDetail {

	/**
	 * 行政区Id
	 */
	public int RegionId;

	/**
	 * 行政区名称
	 */
	public String Name;

	/**
	 * 未处理图斑数量
	 */
	public int Amount;

	/**
	 * 已审核通过图斑数量
	 */
	public int PassCount;

	/**
	 * 图斑总数
	 */
	public int Total;

	/**
	 * 操作类型（OperationType）
	 */
	public String Type;

	/**
	 * 已处理百分比
	 */
	public int Percent;

	/**
	 * 是否已经上报
	 */
	public boolean IsReport;
}
