package phalaenopsis.statistics.entity;

import java.io.Serializable;

/**
 * 矿产违法案件处分情况
 * @author chunhongl
 * 2017年8月7日下午3:17:02
 */
public class MineralCasePunish implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 级别
	 */
	private int type;
	/**
	 * 类别名称
	 */
	private String typeName;
	// 合计
	private int count;
	/**
	 * 国土资源主管部门人员
	 */
	// 厅级
	private int leadOffice;
	// 处级
	private int leadPart;
	// 科级及以下
	private int leadDivision;
	/**
	 * 其他部门人员
	 */
	// 厅级
	private int otherOffice;
	// 处级
	private int otherPart;
	// 科级及以下
	private int otherDivision;
	
	/**
	 * 采矿权人
	 */
	private int miningPerson;
	/**
	 * 个体采矿
	 */
	private int individualMining;
	/**
	 * 其他人员
	 */
	private int otherPerson;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getMiningPerson() {
		return miningPerson;
	}

	public void setMiningPerson(int miningPerson) {
		this.miningPerson = miningPerson;
	}

	public int getIndividualMining() {
		return individualMining;
	}

	public void setIndividualMining(int individualMining) {
		this.individualMining = individualMining;
	}

	public int getOtherPerson() {
		return otherPerson;
	}

	public void setOtherPerson(int otherPerson) {
		this.otherPerson = otherPerson;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getLeadOffice() {
		return leadOffice;
	}

	public void setLeadOffice(int leadOffice) {
		this.leadOffice = leadOffice;
	}

	public int getLeadPart() {
		return leadPart;
	}

	public void setLeadPart(int leadPart) {
		this.leadPart = leadPart;
	}

	public int getLeadDivision() {
		return leadDivision;
	}

	public void setLeadDivision(int leadDivision) {
		this.leadDivision = leadDivision;
	}

	public int getOtherOffice() {
		return otherOffice;
	}

	public void setOtherOffice(int otherOffice) {
		this.otherOffice = otherOffice;
	}

	public int getOtherPart() {
		return otherPart;
	}

	public void setOtherPart(int otherPart) {
		this.otherPart = otherPart;
	}

	public int getOtherDivision() {
		return otherDivision;
	}

	public void setOtherDivision(int otherDivision) {
		this.otherDivision = otherDivision;
	}

}
