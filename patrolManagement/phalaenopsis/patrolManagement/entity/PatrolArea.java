package phalaenopsis.patrolManagement.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 巡查区域
 * @author jund
 * 2017-9-14 
 */
public class PatrolArea implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;					//主键id
	
	private String areaName;			//管辖区域名称
	
	private String patrolOrg;			//管辖单位名称
	
	private String patrolOrgid;			//管辖单位id
	
	private Integer areaType;			//巡查区域类型		 0-点 1-线 2-面
	
	private Integer patrolLevel;		//巡查级别		 0-低 1-中 2-高
	
	private Date createDate;			//区域创建时间
	
	private String coordi;				//坐标点
	
	private String patrolAreaCoordi;	//坐标点

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getPatrolOrg() {
		return patrolOrg;
	}

	public void setPatrolOrg(String patrolOrg) {
		this.patrolOrg = patrolOrg;
	}

	public String getPatrolOrgid() {
		return patrolOrgid;
	}

	public void setPatrolOrgid(String patrolOrgid) {
		this.patrolOrgid = patrolOrgid;
	}

	public Integer getAreaType() {
		return areaType;
	}

	public void setAreaType(Integer areaType) {
		this.areaType = areaType;
	}

	public Integer getPatrolLevel() {
		return patrolLevel;
	}

	public void setPatrolLevel(Integer patrolLevel) {
		this.patrolLevel = patrolLevel;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCoordi() {
		return coordi;
	}

	public void setCoordi(String coordi) {
		this.coordi = coordi;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPatrolAreaCoordi() {
		return patrolAreaCoordi;
	}

	public void setPatrolAreaCoordi(String patrolAreaCoordi) {
		this.patrolAreaCoordi = patrolAreaCoordi;
	}
	
	
}
