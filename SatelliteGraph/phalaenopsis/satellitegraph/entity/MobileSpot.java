package phalaenopsis.satellitegraph.entity;

import java.io.Serializable;

/**
 * 轻量化移动端接受数据,只要5个属性和id
 * 
 * @author gaofengt
 *
 */
public class MobileSpot implements Serializable {

	/**
	 * 主键
	 */
	private long ID;

	/**
	 * 监测编号
	 */
	private String SpotNumber;

	/**
	 * 区县级区划代码（管理区）
	 */
	private int County;

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getSpotNumber() {
		return SpotNumber;
	}

	public void setSpotNumber(String spotNumber) {
		SpotNumber = spotNumber;
	}

	public int getCounty() {
		return County;
	}

	public void setCounty(int county) {
		County = county;
	}

	public String getCountyName() {
		return CountyName;
	}

	public void setCountyName(String countyName) {
		CountyName = countyName;
	}

	public double getSpotAreaMu() {
		return SpotAreaMu;
	}

	public void setSpotAreaMu(double spotAreaMu) {
		SpotAreaMu = spotAreaMu;
	}

	public double getConstructionAcreageMu() {
		return ConstructionAcreageMu;
	}

	public void setConstructionAcreageMu(double constructionAcreageMu) {
		ConstructionAcreageMu = constructionAcreageMu;
	}

	public double getArableAcreageMu() {
		return ArableAcreageMu;
	}

	public void setArableAcreageMu(double arableAcreageMu) {
		ArableAcreageMu = arableAcreageMu;
	}

	/**
     * 区县名称（管理区）
     */
    private String CountyName;

	/**
	 * 监测面积
	 */
	private double SpotAreaMu;

	/**
	 * 建设用地面积
	 */
	private double ConstructionAcreageMu;

	/**
	 * 耕地面积
	 */
	private double ArableAcreageMu;

}
