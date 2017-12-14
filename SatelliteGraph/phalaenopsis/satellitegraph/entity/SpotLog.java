package phalaenopsis.satellitegraph.entity;

import java.util.Date;

//图斑操作记录表 审核是否通过记录  套合记录  
public class SpotLog {
	
    /// 主键（自增长）
    public long ID;
   
    /// 操作人
    public String UserID;

	/// 创建时间
    public Date CreateTime;
   
    /// 操作记录
    public String Note;

    /// 操作类型
    public int SpotLogType;

	/// 关联图斑ID
	public long MapSpotId;

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public Date getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}

	public int getSpotLogType() {
		return SpotLogType;
	}

	public void setSpotLogType(int spotLogType) {
		SpotLogType = spotLogType;
	}

	public long getMapSpotId() {
		return MapSpotId;
	}

	public void setMapSpotId(long mapSpotId) {
		MapSpotId = mapSpotId;
	}
	
	
}
