package phalaenopsis.statistics.enums;


/**
 * 统计分析导出图表枚举
 * @author dongdongt
 *
 */
public enum ExportEnum  {

	/**
	 * 省信访导出
	 */
	ProvinceLetter(1,"省级信访情况"),
	/**
	 * 部省12336受理违法举报电话线索量
	 */
	MinistryPhoneNum(2,"部省12336受理违法举报电话线索量"),
	/**
	 * 全省发现土地违法案件数及面积
	 */
	ProvinceLandLawCase(3,"全省发现土地违法案件数及面积"),
	/**
	 * 全省份用途违法用地面积比例
	 */
	ProviceIllegalLand(4,"全省份用途违法用地面积比例"),
	/**
	 * 全省本年立案结案情况
	 */
	ProvinceLawCaseEnd(5,"全省本年立案结案情况"),
	/**
	 * 市结案情况以及比例
	 */
	CountyLawCaseEnd(6,"市结案情况以及比例"),
	/**
	 * 17市统计上报潍坊案件数及违法用地面积数比对
	 */
	ReportAndillegal(7,"17市统计上报潍坊案件数及违法用地面积数比对"),
	/**
	 * 省级巡查与统计上报数据比对
	 */
	PatroAndReport(8,"省级巡查与统计上报数据比对"),
	/**
	 * 卫片数据与统计上报数据比对
	 */
	SatelliteAndReport(9,"卫片数据与统计上报数据比对"),
	/**
	 * 17市分年度数据比对
	 */
	ContrastYear(10,"17市分年度数据比对");

	private ExportEnum(int v,String name) {
		this.value = v;
		this.name = name;
	}
	private int value;
	private String name;
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
