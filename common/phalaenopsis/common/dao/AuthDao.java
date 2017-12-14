package phalaenopsis.common.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import phalaenopsis.common.entity.Menu;
import phalaenopsis.common.entity.Region;
import phalaenopsis.common.entity.Role;
import phalaenopsis.common.entity.User;
import phalaenopsis.common.util.SqlHelper;

@Repository("authDao")
public class AuthDao {

	@Resource
	private SqlSession sqlSession;
	
//	@Resource 
//	private SqlHelper sqlHelper;
	/**
	 * 用户登录
	 * @param map
	 * @return
	 */
	public User login(Map<String, Object> map) {
		return sqlSession.selectOne("auth.login", map);
	}

	/**
	 * 获取对应的角色和权限
	 * @param id
	 * @return
	 */
	public List<Role> getRoleList(String id) {
//		String sql = sqlHelper.getNamespaceSql("auth.getRoleList", id);
		return sqlSession.selectList("auth.getRoleList", id);
	}

	/**
	 * 获取系统菜单
	 * @return
	 */
	public List<Menu> getSysMenus() {
		return sqlSession.selectList("auth.getSysMenus");
	}

	/**
	 *  获取当前用户的菜单
	 * @param id
	 * @return
	 */
	public List<Menu> getMenus(String id) {
		return sqlSession.selectList("auth.getMenus", id);
	}

	/**
	 * 获取页面的授权信息
	 * @param url
	 * @param id
	 * @return
	 */
	public Map<String, Object> getAuthInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Region> getUserRegion(String organizationid)
	{
		return sqlSession.selectList("auth.getUserRegion",organizationid);
	}
	/**
	 * 获取登录区域的下级区域信息列表
	 * @param id
	 * @return
	 */
	public List<Region> getRegionsByUser(Integer id)
	{
		return sqlSession.selectList("auth.getRegionsByUser",id);
	}
	
	public Integer getUserGrade(String id){
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("gid", id);
		sqlSession.selectOne("auth.getUserGrade", map);
		return ((BigDecimal) map.get("grade")).intValue();
	}
	
	/**
	 * 用户登录(浪潮平台)
	 * @param map
	 * @return
	 */
	public User loginByAccount(String account) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("auth.loginByAccount", account);
	}
}
