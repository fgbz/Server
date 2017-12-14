package phalaenopsis.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phalaenopsis.common.dao.AuthDao;
//import phalaenopsis.common.entity.AppSettings;
import phalaenopsis.common.entity.Menu;
import phalaenopsis.common.entity.OrganizationGrade;
import phalaenopsis.common.entity.Region;
import phalaenopsis.common.entity.Role;
import phalaenopsis.common.entity.User;
import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.cache.RegionCache;

@Service("authService")
public class AuthService extends Basis {

	@Autowired
	private AuthDao userDao;
 
	/**
	 * 用户登录
	 * 
	 * @param account
	 * @param password
	 * @return
	 */
	public User login(String account, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("account", account.toUpperCase());
		map.put("password", password);
		User user = userDao.login(map);
		// 用户名或密码错误，登录失败
		if (user == null)
			return null;
		
		user.setOrgGrade(userDao.getUserGrade(user.getOrganizationID()));
		
		List<Region> regions = null;
		if (user != null) {
			// 获取用户对应的权限
			List<Role> list = userDao.getRoleList(user.getId());
			if (list != null) {
				user.setRoles(list);
			}

			// 获取用户对应的区域
			regions = userDao.getUserRegion(user.getOrganizationID());
			List<Region> regionList=new ArrayList<>();
			if(user.getOrgGrade()== OrganizationGrade.City){
				regionList=userDao.getRegionsByUser(regions.get(0).getParentID());
				user.setRegionList(regionList);
				//设置当前登录用户所在的区域ID
				user.setRegionId(regions.get(0).getParentID());
			}else if(user.getOrgGrade()== OrganizationGrade.Province){
				Region r=(Region) RegionCache.get(String.valueOf(regions.get(0).getParentID()));
				regionList=userDao.getRegionsByUser(r.getParentID());
				//设置当前登录用户所在的区域ＩＤ
				user.setRegionId(r.getParentID());
				user.setRegionList(regionList);
			}else{
				user.setRegionId(regions.get(0).getRegionID());
			}
			Region[] regs = new Region[regions.size()];
			user.setRegions(regions.toArray(regs));
		}
		return user;
	}
	
	/**
	 * 用户登录（浪潮平台）
	 * 
	 * @param account
	 * @param password
	 * @return
	 */
	public User login(String account) {
		User user = userDao.loginByAccount(account);
		// 用户名或密码错误，登录失败
		if (user == null){
			return null;
		}
		user.setOrgGrade(userDao.getUserGrade(user.getOrganizationID()));
		
		List<Region> regions = null;
		if (user != null) {
			// 获取用户对应的权限
			List<Role> list = userDao.getRoleList(user.getId());
			if (list != null) {
				user.setRoles(list);
			}

			// 获取用户对应的区域
			regions = userDao.getUserRegion(user.getOrganizationID());
			List<Region> regionList=new ArrayList<>();
			if(user.getOrgGrade()== OrganizationGrade.City){
				regionList=userDao.getRegionsByUser(regions.get(0).getParentID());
				user.setRegionList(regionList);
				//设置当前登录用户所在的区域ID
				user.setRegionId(regions.get(0).getParentID());
			}else if(user.getOrgGrade()== OrganizationGrade.Province){
				Region r=(Region) RegionCache.get(String.valueOf(regions.get(0).getParentID()));
				regionList=userDao.getRegionsByUser(r.getParentID());
				//设置当前登录用户所在的区域ＩＤ
				user.setRegionId(r.getParentID());
				user.setRegionList(regionList);
			}else{
				user.setRegionId(regions.get(0).getRegionID());
			}
			Region[] regs = new Region[regions.size()];
			user.setRegions(regions.toArray(regs));
		}
		return user;
	}

	/**
	 * 验证用户是否有系统管理的角色权限
	 * 
	 * @param request
	 */
	public boolean isAdmin() {
		User user =  getCurrentUser();
		List<Role> list = user.getRoles();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getId().equals("admin")) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 获取系统管理菜单
	 * 
	 * @param request
	 * @return
	 */
	public List<Menu> getSysMenus() {
		// TODO Auto-generated method stub
		List<Menu> list = new ArrayList<Menu>();
		if (isAdmin()) {
			list = userDao.getSysMenus();
		}
		return list;
	}

	/**
	 * 获取当前用户的菜单
	 * 
	 * @param request
	 * @return
	 */
	public List<Menu> getMenus() {
		User user = getCurrentUser();
		List<Menu> list = userDao.getMenus(user.getId());
		List<Menu> menus = new ArrayList<Menu>();
		// 添加父级菜单
		for (Menu menu : list) {
			if (menu.parentMenuId == null || menu.parentMenuId.equals("")) {
				menus.add(menu);
			}
		}
		// 添加子集菜单
		for (Menu menu : list) {
			if (menu.parentMenuId != null && !menu.parentMenuId.equals("")) {
				for (int i = 0; i < menus.size(); i++) {
					if (menus.get(i).MenuID.equals(menu.parentMenuId)) {
						menus.get(i).itemMenus.add(menu);
					}
				}
			}
		}
		return menus;
	}

	/**
	 * 获取页面的授权信息
	 * 
	 * @param url
	 * @param request
	 */
	public Map<String, Object> getAuthInfo(String url) {
		User user = getCurrentUser();
		List<Role> role = user.getRoles();
		if (role != null) {

		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("url", url);
		map.put("id", user.getId());
		return userDao.getAuthInfo(map);
	}

}
