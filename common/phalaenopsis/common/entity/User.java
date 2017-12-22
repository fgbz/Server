package phalaenopsis.common.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;



public class User implements java.io.Serializable{
	

	/// <summary>
	/// 用户ID
	/// </summary>
	@JsonProperty("ID")
	public String id;

	/// <summary>
	/// 登录账号
	/// </summary>
	@JsonProperty("Account")
	public String account;

	/// <summary>
	/// 用户姓名
	/// </summary>
	@JsonProperty("Name")
	public String name;

	/// <summary>
	/// 密码
	/// </summary>
	@JsonIgnore
	public String password;

	/// <summary>
	/// 所属组织机构/部门ID
	/// </summary>
	@JsonProperty("OrganizationID")
	public String organizationID;

	/// <summary>
	/// 所属组织机构/部门名称
	/// </summary>
	@JsonProperty("OrganizationName")
	public String organizationName;

	/**
	 * 组织类型
	 */
	public int OrgGrade;

	/// <summary>
	/// 组织类型
	/// </summary>

	// public OrganizationGrade OrgGrade ;

	/// <summary>
	/// 用户的角色
	/// </summary>
	@JsonProperty("Roles")
	public List<Role> roles;

	/// <summary>
	/// 用户所属区域
	/// </summary>
	@JsonProperty("Regions")
	public Region[] regions;
	
	/**
	 * 地图图层级别（市级/省级）
	 */
	public Integer MapLayerGrade;


	/**
     * 获取登录用户的下级用户列表
     */
	public List<Region> regionList;
	public List<Region> getRegionList() {
		return regionList;
	}

	public void setRegionList(List<Region> regionList) {
		this.regionList = regionList;
	}

	/// <summary>
	/// 备注
	/// </summary>
	@JsonProperty("Remarks")
	public String remarks;

	/// <summary>
	/// 是否有签名照
	/// </summary>
	@JsonProperty("HasPhoto")
	public boolean hasPhoto;

	/// <summary>
	/// 如果有签名照，则为签名照的base64格式数据
	/// </summary>
	@JsonProperty("SignaturePhoto")
	public String signaturePhoto;
	/**
	 * 最后更新时间
	 */
	@JsonProperty("LastUpdate")
	public String lastUpdate;
	
	@JsonProperty("intHashPhoto")
	public int intHashPhoto;
	/**
	 * 当前登录用户所属的区域ID(辅助)
	 */
	private Integer regionId;
	private Map<String, Object> session;
	
	@JsonIgnore
	private String mobilePublicKey;
	

	private String mobilePrivateKey;

	/**
	 * 是否在线
	 */
	//@JsonProperty("isOnline")
	public String isOnline = "on";
	
	private List<String> organizationsID = null;
	

	
	
	public String getMobilePublicKey() {
		return mobilePublicKey;
	}

	public void setMobilePublicKey(String mobilePublicKey) {
		this.mobilePublicKey = mobilePublicKey;
	}

	public String getMobilePrivateKey() {
		return mobilePrivateKey;
	}

	public void setMobilePrivateKey(String mobilePrivateKey) {
		this.mobilePrivateKey = mobilePrivateKey;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public int getIntHashPhoto() {
		return intHashPhoto;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOrganizationID() {
		return organizationID;
	}

	public void setOrganizationID(String organizationID) {
		this.organizationID = organizationID;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Region[] getRegions() {
		return regions;
	}

	public void setRegions(Region[] regions) {
		this.regions = regions;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public boolean isHasPhoto() {
		return hasPhoto;
	}

	public void setHasPhoto(boolean hasPhoto) {
		this.hasPhoto = hasPhoto;
	}

	public String getSignaturePhoto() {
		return signaturePhoto;
	}

	public void setSignaturePhoto(String signaturePhoto) {
		this.signaturePhoto = signaturePhoto;
	}

	/**
	 * @return the orgGrade
	 */
	public int getOrgGrade() {
		return OrgGrade;
	}

	/**
	 * @param orgGrade
	 *            the orgGrade to set
	 */
	public void setOrgGrade(int orgGrade) {
		OrgGrade = orgGrade;
	}

	public String getLastUpdate() {
		return lastUpdate == null ? "": lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	
    public Integer getMapLayerGrade() {
		return MapLayerGrade;
	}

	public void setMapLayerGrade(Integer mapLayerGrade) {
		MapLayerGrade = mapLayerGrade;
	}

	public String getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}


	public List<String> getOrganizationsID() {
		return organizationsID;
	}

	public void setOrganizationsID(List<String> organizationsID) {
		this.organizationsID = organizationsID;
	}

	
	
}
