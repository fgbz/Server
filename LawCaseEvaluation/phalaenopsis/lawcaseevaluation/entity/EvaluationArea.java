/**
 * Description 评查区域实体类
 * Author lih
 * Date 2017-04-06
 */
package phalaenopsis.lawcaseevaluation.entity;
/**
 * 评查区域实体类
 * @author lih
 *
 */
public class EvaluationArea {
	/**
	 * 主键id
	 */
	private long id;
	/**
	 * 市名
	 */
	private String regionName;
	/**
	 * 代码
	 */
	private String regionCode;
	/**
	 * 区域下人员
	 */
	private String names;
	/**
	 * 区域下人员id
	 */
	private String ids;
	/**
	 * 区域下人员行政区划
	 */
	private String userRegionNames;
	/**
	 * 页面上展示的评审人员集合
	 */
	private String pageShowNames;

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getPageShowNames() {
		return pageShowNames;
	}

	public void setPageShowNames(String pageShowNames) {
		this.pageShowNames = pageShowNames;
	}

	public String getUserRegionNames() {
		return userRegionNames;
	}

	public void setUserRegionNames(String userRegionNames) {
		this.userRegionNames = userRegionNames;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
