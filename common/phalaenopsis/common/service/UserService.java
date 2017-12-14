package phalaenopsis.common.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import phalaenopsis.common.dao.UserDao;
import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.Paging;
import phalaenopsis.common.entity.User;

@Service("userService")
public class UserService {
	@Autowired
	private UserDao userDao;

	public Paging<User> GetUsers(Page page) {
		return userDao.GetUsers(page);
	}

	/**
	 * 获取用户详情
	 */
	public User getUser(String id) {
		return userDao.getUser(id);
	}
	
	/**
	 * 获取用户
	 */
	public User getUserById(String id) {
		return userDao.getUserById(id);
	}

	/**
	 * 添加用户信息
	 * 
	 * @param user
	 * @return
	 */
	public int addUser(User user) {
		return userDao.addUser(user);
	}

	/**
	 * 更新是否在线
	 * 
	 * @param userID
	 * @param isOnline
	 * @return
	 */
	public int updateUserIsOnline(User user) {
		return userDao.updateUserIsOnline(user);
	}
	
	/**
	 * 修改用户信息
	 * 
	 * @param user
	 * @return
	 */
	public int updateUser(User user) {
		return userDao.updateUser(user);
	}


	/**
	 * 批量删除用户
	 * 
	 * @param list
	 * @return
	 */
	public boolean deleteUsers(List<String> list) {
		return userDao.deleteUsers(list);
	}

	/**
	 * 用户修改自己密码
	 * 
	 * @param user
	 * @param newPwd
	 * @return
	 */
	public int updateSelfPassword(User user, String newPwd) {
		return userDao.updateSelfPassword(user, newPwd);
	}

	/**
	 * 管理员修改密码
	 * 
	 * @param userID
	 * @param newPwd
	 * @return
	 */
	public int updateUserPassword(User user) {
		return userDao.updateUserPassword(user);
	}

	/**
	 * 获取当前登陆用的签名照
	 * 
	 * @return
	 */
	public User getSignaturePhoto() {
		return userDao.GetSignaturePhoto();
	}

	/**
	 * 获取指定用户的签名照
	 * 
	 * @param userIDs
	 * @return
	 */
	@SuppressWarnings("null")
	public List<User> getSignaturePhotos(List<String> userIDs) {
		List<User> users=new ArrayList<>();
		if(userIDs!=null && userIDs.size()>0) {
			users=userDao.getUserByIDs(userIDs);
		}
		return users;
	}

	/**
	 * 获取组织机构下所有用户
	 * 
	 * @return
	 */
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}
	
	public List<User> getUserByName(String name) {
		return userDao.getUserByName(name);
	}
	
	public List<User> getUserByOrganizationID(String organizationID) {
		return userDao.getUserByOrganizationID(organizationID);
	}
	
	public List<User> getUserByOrganizationsID(User user) {
		return userDao.getUserByOrganizationsID(user);
	}
}
