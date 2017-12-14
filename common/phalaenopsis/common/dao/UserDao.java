package phalaenopsis.common.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
//import org.geotools.filter.IsNotEqualToImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import phalaenopsis.common.entity.Condition;
import phalaenopsis.common.entity.OpResult;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.Paging;
import phalaenopsis.common.entity.Role;
import phalaenopsis.common.entity.User;
import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.Tools.SHA;
import phalaenopsis.common.method.cache.UserCache;
import phalaenopsis.systemmanagement.dao.IRoleDao;

@Repository("userDao")
public class UserDao extends Basis{
	@Resource
	private SqlSession sqlSession;
	
	@Autowired
	private IRoleDao roleDao;

	public Paging<User> GetUsers(Page page1) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		for (Condition condition : page1.getConditions()) {
			if (condition.getKey().equals("Org"))
			{
				map.put("Org", condition.getValue());
			}
			else if (condition.getKey().equals("SearchText")){
				map.put("SearchText", condition.getValue());
			}
		}

		int count = sqlSession.selectOne("user.getUsersCount", map);

		map.put("startNum", page1.getPageSize() * (page1.getPageNo() - 1) + 1);
		map.put("endNum", page1.getPageSize() * page1.getPageNo());

		List<User> list = sqlSession.selectList("user.getUsers", map);

		Paging<User> page = new Paging<User>();
		page.PageNo = page1.getPageNo();
		page.PageSize = page1.getPageSize();
		page.PageCount = count == 0 ? 0 : (count - 1) / page1.getPageSize() + 1; // 由于计算pageCount存在整除的情况，所以计算的时候先减1在除以pageSize
		page.RecordCount = count;
		page.CurrentList = list;
		page.PageCount = (page.RecordCount / page.PageSize) + (page.RecordCount % page.PageSize > 0 ? 1 : 0);

		return page;
	}

	/**
	 * 获取用户详情
	 */
	public User getUser(String id) {
		User user = sqlSession.selectOne("user.getUserDetail", id);
		// 获取用户组织部门
		user.organizationName = sqlSession.selectOne("organization.getOrgName", user.organizationID);
		// 获取角色列表
//		List<Role> list = new ArrayList<Role>();
//		List<Role> list=sqlSession.selectList("role.getRoles");
//		user.roles = list;
		//获取用户对应的角色
		List<String> roleIds =  roleDao.getRolesFromUserId(id);//sqlSession.selectList("role.getRolesFromUserId", id);
		List<Role> roles = new ArrayList<>();
		for (String string : roleIds) {
			//Role role = sqlSession.selectOne("role.getRolesFromRoleId", string);
			Role role = roleDao.getRolesFromRoleId(string);
			roles.add(role);
		}
		user.roles = roles;
		return user;
	}

	/**
	 * 添加用户信息
	 * 
	 * @param user
	 * @return
	 */
	@Transactional
	public synchronized int addUser(User user) {
		// 1,判断账号是否存在
		int count = sqlSession.selectOne("user.duplicateUserAccount", user.account);
		if (count > 0) {
			return OpResult.PreconditionFailed;
		}

		// 2,添加用户信息
		user.id = UUID.randomUUID().toString();
		user.password = SHA.encryptSHA1("8888888");
		int result = sqlSession.insert("user.insertUser", user);
		if (result > 0) {
			for (Role role : user.roles) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("ID", UUID.randomUUID().toString());
				map.put("USERID", user.id);
				map.put("ROLEID", role.id);
				sqlSession.insert("user.insertUserRole",map);
			}
		}
		return OpResult.Success;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public synchronized int addPcUser(User user) {
		// 1,判断账号是否存在
		int count = sqlSession.selectOne("user.duplicateUserAccount", user.account);
		if (count > 0) {
			return OpResult.PreconditionFailed;
		}

		// 2,添加用户信息
		user.id = UUID.randomUUID().toString();
		user.password = SHA.encryptSHA1("Pc8888888");
		int result = sqlSession.insert("user.insertUser", user);
		if (result > 0) {
			for (Role role : user.roles) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("ID", UUID.randomUUID().toString());
				map.put("USERID", user.id);
				map.put("ROLEID", role.id);
				sqlSession.insert("user.insertUserRole",map);
			}
		}
		return OpResult.Success;
	}

	/**
	 * 修改用户信息
	 * 
	 * @param user
	 * @return
	 */
	public int updateUser(User user) {
		//判断账号
		int count = sqlSession.selectOne("user.checkUserAccount", user);
		if (count > 0) {
			return OpResult.PreconditionFailed;
		}
		// 获取用户密码
		sqlSession.update("user.updateUser", user);
		UserCache.remove(user.getId());
		UserCache.put(user.getId(), user);
		// 删除用户角色
		// session.Delete("from UserRole where UserID = ?", s,
		// NHibernateUtil.String);
		// 更新角色
		// session.Delete("from UserRole where UserID = ?", user.ID,
		// NHibernateUtil.String);
		// foreach (Role r in user.Roles) {
		// UserRole role = new UserRole()
		// {
		// UserID = user.ID,
		// RoleID = r.ID
		// };
		// session.Save(role);
		// }
		//更新角色
//		sqlSession.update("user.updateUserRole", user);
		//根据用户id删除角色
		int result = sqlSession.delete("user.deleteFromUserRoleId", user);
//		sqlSession.insert("user.insertFromUserRoleId", user);
		for (Role role : user.roles) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("ID", UUID.randomUUID().toString());
			map.put("USERID", user.id);
			map.put("ROLEID", role.id);
			sqlSession.insert("user.insertUserRole",map);
		}
		return OpResult.Success;
	}

	/**
	 * 批量删除用户
	 * 
	 * @param list
	 * @return
	 */
	public boolean deleteUsers(List<String> list) {
		int result = sqlSession.delete("user.deleteUsers", list);
		// 根据用户id删除用户角色
		// session.Delete("from UserRole where UserID = ?", s,
		// NHibernateUtil.String);
		return result > 0 ? true : false;
	}

	/**
	 * 用户修改自己密码
	 * 
	 * @param user
	 * @param newPwd
	 * @return
	 */
	public int updateSelfPassword(User user, String newPwd) {
		User userData = sqlSession.selectOne("user.getUserByPassword", user);
		if (userData == null) {
			return OpResult.PreconditionFailed;
		}
		user.setPassword(newPwd);
		int result = sqlSession.update("user.updateUserPassword", user);
		if (result > 0) {
			//更新用户缓存
			UserCache.remove(user.getId());
			UserCache.put(user.getId(), user);
			return OpResult.Success;
		}
		return OpResult.Failed;
	}

	
	/**
	 * 更新是否在线
	 * 
	 * @param userID
	 * @param isOnline
	 * @return
	 */
	public int updateUserIsOnline(User user) {
		int num= sqlSession.update("user.updateUserIsOnline", user);
		if(num>0){
			return OpResult.Success;
		}else{
			return OpResult.Failed;
		}
	}
	
	/**
	 * 管理员修改密码
	 * 
	 * @param userID
	 * @param newPwd
	 * @return
	 */
	public int updateUserPassword(User user) {
		User userData = sqlSession.selectOne("user.getUserByPassword", user);
		if(userData == null){
			int num= sqlSession.update("user.updateUserPassword", user);
			if(num>0){
				return OpResult.Success;
			}
		}else{
			int isRepeat = 461;
			OpResult opResult = new OpResult(isRepeat);
			return opResult.Code;
		}
		
		return OpResult.Failed;
	}
	/**
	 * 获取组织机构下所有用户
	 * @return
	 */
	public List<User> getAllUser(){
		List<User> userlist = sqlSession.selectList("user.getAllUser");
		return userlist;
	}
	/**
	 * 获取当前登陆用的签名照
	 * @return
	 */
	public User GetSignaturePhoto(){
		User user= getCurrentUser();//获取当前登陆用户信息
		return user;
	}
	/**
	 * 获取指定用户的签名照
	 * @param userIDs
	 * @return
	 */
	public  List<User> GetSignaturePhotos(List<String> userIDs){
		List<User> users=new ArrayList<User>();
		if(userIDs.size()<0){
			//当传进来的id为null时
			return users;
		}else{
			users=getUserByIDs(userIDs);//获取用户
			if(users.size()>0){
				for(User user:users){
					if(String.valueOf(user.getIntHashPhoto())!=null)//判断用户是否有签名照
					{
						users.add(user);
					}
				}
			}
			return users;
		}
	}
	/**
	 * 根据用户ids查询所有用户
	 * @param userIds
	 * @return
	 */
	public List<User> getUserByIDs(List<String> userIDs){
		
		return sqlSession.selectList("user.getUserByIDs", userIDs);
	}
	
	public User getUserById(String id){
		User user = sqlSession.selectOne("user.getUserById", id);
		return user;
	}
	
	public List<User> getUserByName(String name){
		
		return sqlSession.selectList("user.getUserByName", name);
	}
	
	public List<User> getUserByOrganizationID(String organizationID){
		
		return sqlSession.selectList("user.getUserByOrganizationID", organizationID);
	}

	public List<User> getUserByOrganizationsID(User user){
		
		return sqlSession.selectList("user.getUserByOrganizationsID", user);
	}

}
