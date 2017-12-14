package phalaenopsis.systemmanagement.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.PagingEntity;
import phalaenopsis.common.entity.Permission;
import phalaenopsis.common.entity.Role; 
import phalaenopsis.systemmanagement.service.RoleService;

@Controller
@RequestMapping("/Sys/Mgr/Role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	private static Logger Log = LoggerFactory.getLogger(RoleController.class);
	

	/**
	 * 获取分页的角色列表
	 * @param pageNo 页码
	 * @param pageSize 每页记录数
	 * @param conditions 查询条件
	 * @return
	 */
	@RequestMapping(value = "/GetRoleList",method = RequestMethod.POST) 
    @ResponseBody
	public PagingEntity<Role> getRoleList(@RequestBody Page page){
		return roleService.getRoleList(page);
	}
	/**
	 * 获取所有权限
	 * @return
	 */
	@RequestMapping(value = "/GetRoles",method = RequestMethod.GET) 
    @ResponseBody
	public List<Role> GetRoles(){
		return roleService.GetRoles();
	}
	
	/**
	 * 获取角色信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/GetRole",method = RequestMethod.GET) 
    @ResponseBody
    public Role getRole(@PathParam("id") String id){
		Role role = roleService.getRole(id);
		return role;
	}
	
	/**
	 * 添加角色
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/AddRole",method = RequestMethod.POST) 
    @ResponseBody
	public  int addRole(@RequestBody Role role){
		return roleService.addRole(role);
	}
	
	/**
	 * 修改角色
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/UpdateRole",method = RequestMethod.PUT) 
    @ResponseBody
	public int updateRole(@RequestBody Role role){
		return roleService.updateRole(role);
	}
	
	/**
	 * 删除角色信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/DeleteRole",method = RequestMethod.DELETE) 
    @ResponseBody
	public boolean deleteRole(@PathParam("id") String id){
		return roleService.deleteRole(id);
	}
	
	/**
	 * 获取所以权限列表
	 * @return
	 */
	@RequestMapping(value = "/GetPermissions",method = RequestMethod.GET) 
    @ResponseBody
	public List<Permission> getPermissions(){
		return roleService.getPermissions();
	}
	
}
