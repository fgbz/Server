package phalaenopsis.common.entity;


public class Device {

	/// <summary>
	/// 设备ID
	/// </summary>
	public String ID;

	/// <summary>
	/// 设备名称
	/// </summary>
	public String Name;

	/// <summary>
	/// 电话号码
	/// </summary>
	public String Phone;

	/// <summary>
	/// 所属组织机构/部门ID
	/// </summary>
	public String OrganizationID;

	/// <summary>
	/// 所属组织机构/部门名称
	/// </summary>
	public String OrganizationName;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getOrganizationID() {
		return OrganizationID;
	}

	public void setOrganizationID(String organizationID) {
		OrganizationID = organizationID;
	}

	public String getOrganizationName() {
		return OrganizationName;
	}

	public void setOrganizationName(String organizationName) {
		OrganizationName = organizationName;
	}

}
