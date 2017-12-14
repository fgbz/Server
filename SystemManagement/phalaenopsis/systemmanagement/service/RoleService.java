package phalaenopsis.systemmanagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.OpResult;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.common.entity.Permission;
import phalaenopsis.common.entity.Role;
import phalaenopsis.common.method.Tools.GuidHelper;
import phalaenopsis.systemmanagement.dao.IRoleDao;

@Service("roleService")
public class RoleService {
	
	@Autowired
	private IRoleDao roleDao;


	/**
	 * 获取分页的角色列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param conditions
	 * @return
	 */
	public PagingEntity<Role> getRoleList(Page page) {
		
		Map<String, Object> conditions = new HashMap<String, Object>();
		for (Condition condition : page.getConditions()) {
			if (condition.getKey().equals("SearchText")) {
				conditions.put("SearchText", (String) condition.getValue());
			}
		}

		int count = roleDao.getRoleListCount(conditions);
		
		conditions.put("startRow", page.getPageSize() * (page.getPageNo() - 1) + 1);
		conditions.put("endRow", page.getPageSize() * page.getPageNo());
		
		List<Role> roles = roleDao.getRoleList(conditions);
		
		int pageCount = 0 == count ? 1 : (count - 1) / page.getPageSize() + 1; // 由于计算pageCount存在整除的情况，所以计算的时候先减1在除以pageSize
		
		PagingEntity<Role> entity = new PagingEntity<>(
				page.getPageNo(), 
				pageCount,
				page.getPageSize(),
				count,
				roles
				);
		
		return entity;
	}
	/**
	 *  获取所有权限
	 * @return
	 */
	public List<Role> GetRoles(){
		return roleDao.getRoles();
	}
	/**
	 * 获取角色信息
	 * 
	 * @param id
	 * @return
	 */
	public Role getRole(String id) {
		return roleDao.getRole(id);
	}

	/**
	 * 修改角色信息
	 * 
	 * @param role
	 * @return
	 */
	public int updateRole(Role role) {
		int result = roleDao.updateRole(role);
		if (result == 0)
		{
			return OpResult.PreconditionFailed;
		}
		roleDao.deleteRolePermissions(role.getId());
		roleDao.addRolePermissions(role);
		return OpResult.Success;
	}

	/**
	 * 删除角色信息
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteRole(String id) {
		
		roleDao.deleteRole(id);
		roleDao.deleteRolePermissions(id);
		return true;
	}

	/**
	 * 获取所有的权限列表
	 * 
	 * @return
	 */
	public List<Permission> getPermissions() {
		return roleDao.getPermissions();
	}

	/**
	 * 添加角色
	 * 
	 * @param role
	 * @return
	 */
	@Transactional
	public int addRole(Role role) {
		
		String id = GuidHelper.getGuid();
		role.setId(id);

		// 1, 判断名称是否重复
		int count = roleDao.duplicateRoleName(role);
		if (count > 0) {
			return OpResult.PreconditionFailed;
		}

		// 2,向数据库中添加新记录
		int result = roleDao.addRole(role);
		if (1 ==  result) {
			if (role.getPermissions() != null) {
				roleDao.addRolePermissions(role);
			}
			return OpResult.Success;
		}
		return OpResult.Failed;
	}

}
