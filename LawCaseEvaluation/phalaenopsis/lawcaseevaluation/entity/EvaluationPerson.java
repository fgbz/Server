/**
 * Description 评查人员实体类
 * Author lih
 * Date 2017-04-06
 */
package phalaenopsis.lawcaseevaluation.entity;

import java.util.List;

public class EvaluationPerson {
    
    public long id;
    
    public long pcbzId;

    public int year;

    public String regionCode;

    public String userAccount;

    public String duty;

    public String contract;

    public String remark;
    
    public String name;
    
    public String cityName;
    
    public String regionNames;
    
    public int belongedYear;
    
    public List<String> regionAreaCode;
    
    public String regionids;
    /**
     * 系统人员表主键
     */
    public String ssuserid;
    /**
     * 人员关联区域id
     */
    private Long mapid;
    /**
     * 是否被选中
     */
    public boolean isChecked=false;
    
	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public String getSsuserid() {
		return ssuserid;
	}

	public void setSsuserid(String ssuserid) {
		this.ssuserid = ssuserid;
	}

	public String getRegionids() {
		return regionids;
	}

	public void setRegionids(String regionids) {
		this.regionids = regionids;
	}

	public List<String> getRegionAreaCode() {
		return regionAreaCode;
	}

	public void setRegionAreaCode(List<String> regionAreaCode) {
		this.regionAreaCode = regionAreaCode;
	}

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public long getPcbzId() {
        return pcbzId;
    }

    public void setPcbzId(long pcbzId) {
        this.pcbzId = pcbzId;
    }
    
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegionNames() {
        return regionNames;
    }

    public void setRegionNames(String regionNames) {
        this.regionNames = regionNames;
    }

    public int getBelongedYear() {
        return belongedYear;
    }

    public void setBelongedYear(int belongedYear) {
        this.belongedYear = belongedYear;
    }

	public Long getMapid() {
		return mapid;
	}

	public void setMapid(Long mapid) {
		this.mapid = mapid;
	}
    
}
