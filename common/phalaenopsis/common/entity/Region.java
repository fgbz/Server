package phalaenopsis.common.entity;

import java.io.Serializable;

public class Region implements Serializable {

	 /// <summary>
    /// 区域ID
    /// </summary>
    
    public int RegionID ;

    /// <summary>
    /// 区域名称
    /// </summary>
    
    public String RegionName ;

    /// <summary>
    /// 父级区域ID
    /// </summary>
    
    public int ParentID ;

    /// <summary>
    /// 区域类型（只针对区县）
    /// </summary>
    
    public int regionType;

    /// <summary>
    /// 层级
    /// </summary>
    
    public int Rank ;

	public int getRegionID() {
		return RegionID;
	}

	public void setRegionID(int regionID) {
		RegionID = regionID;
	}

	public String getRegionName() {
		return RegionName;
	}

	public void setRegionName(String regionName) {
		RegionName = regionName;
	}

	public int getParentID() {
		return ParentID;
	}

	public void setParentID(int parentID) {
		ParentID = parentID;
	}

	public int getRank() {
		return Rank;
	}

	public void setRank(int rank) {
		Rank = rank;
	}
    
    
}
