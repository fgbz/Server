package phalaenopsis.common.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;


public class Role implements Serializable {
    
    public Role() {
        
    }
    
    public Role(String id, String name, boolean issys, List<Permission> permissions) {
        this.id = id;
        this.name = name;
        this.issys = issys;
        this.permissions = permissions;
    }
	
	 /// <summary>
    /// 角色ID
    /// </summary>
	@JsonProperty("ID")
    public String id;

    /// <summary>
    /// 角色名称
    /// </summary>
	@JsonProperty("Name")
    public String name;

    /// <summary>
    /// 是否为系统角色
    /// </summary>
	@JsonProperty("IsSys")
    public boolean issys;

    /// <summary>
    /// 角色权限列表
    /// </summary>
	@JsonProperty("Permissions")
    public List<Permission> permissions ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isIssys() {
		return issys;
	}

	public void setIssys(boolean issys) {
		this.issys = issys;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
    
}
