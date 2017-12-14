/**
 * Copyright 2017 www.kotei-info.com
 * 
 * All rights reserved.
 * 	
 */
package phalaenopsis.common.entity;

/**
 * @author yangw786
 *
 * 2017年3月7日下午2:55:30
 */
public class OrganizationRegion {
	/// <summary>
    /// 主键ID
    /// </summary>
    public String ID;

    /// <summary>
    /// 组织ID
    /// </summary>
    public String OrganizationID;

    /// <summary>
    /// 区域ID
    /// </summary>
    public int RegionID;

	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(String iD) {
		ID = iD;
	}

	/**
	 * @return the organizationID
	 */
	public String getOrganizationID() {
		return OrganizationID;
	}

	/**
	 * @param organizationID the organizationID to set
	 */
	public void setOrganizationID(String organizationID) {
		OrganizationID = organizationID;
	}

	/**
	 * @return the regionID
	 */
	public int getRegionID() {
		return RegionID;
	}

	/**
	 * @param regionID the regionID to set
	 */
	public void setRegionID(int regionID) {
		RegionID = regionID;
	} 
}
