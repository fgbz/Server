package phalaenopsis.statistics.entity;

/**
 * 全身分用途违法用地面积比例
 * @author chunl
 * 2017年8月7日下午2:32:13
 */
public class ProviceIllegalLand {

	/**
	 * 土地分类
	 */
	private String landType;
	
	/**
	 * 面积
	 */
	private Integer area;

	public String getLandType() {
		return landType;
	}

	public void setLandType(String landType) {
		this.landType = landType;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}	
	
}
