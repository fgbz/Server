package phalaenopsis.systemmanagement.dao;

import java.util.List;
import java.util.Map;

import javax.xml.crypto.KeySelector.Purpose;

import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.Permission;
import phalaenopsis.common.entity.Role;

public interface IRoleDao {
	
	public List<Role> getRoles();
	
	public int getRoleListCount(Map<String, Object> map);
	
	public List<Role> getRoleList(Map<String, Object> map);
	
	public Role getRole(String id);

	public int updateRole(Role role);
	
	public int addRole(Role role);
	
	public int addRolePermissions(Role map);
	
	public int deleteRole(String role);
	
	public int deleteRolePermissions(String role);
	
	public List<Role> getRoleByUserId(String id);
	
	public int duplicateRoleName(Role id);
	
	public List<Permission> getPermissions();
	
	public List<String> getRolesFromUserId(String ID);
	
	public Role getRolesFromRoleId(String ID); 
}

