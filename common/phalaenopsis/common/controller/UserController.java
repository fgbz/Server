package phalaenopsis.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.flow.ReturnNode;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import phalaenopsis.common.entity.Page;
import phalaenopsis.common.entity.Paging;
import phalaenopsis.common.entity.User;
import phalaenopsis.common.method.Basis;
import phalaenopsis.common.method.Tools.SHA;
import phalaenopsis.common.service.UserService;

@Controller
@RequestMapping("/Sys/Mgr/User")
public class UserController extends Basis {

	@Autowired
	private UserService userService;

	/**
	 * 获取用户详情   已验证
	 * 
	 * @param id
	 * 根据id获取用户
	 */
	@RequestMapping(value = "/GetUser", method = RequestMethod.GET)
	@ResponseBody
	public User getUser(@PathParam("id") String id) {

		return userService.getUser(id);
	}

	/**
	 * 添加用户信息
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/AddUser", method = RequestMethod.POST)
	@ResponseBody
	public int addUser(@RequestBody User user) {
		return userService.addUser(user);
	}

	/**
	 * 修改用户信息   
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/UpdateUser", method = RequestMethod.PUT)
	@ResponseBody
	public int updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}

	/**
	 * 批量删除用户
	 * 
	 * @param list
	 * @return
	 */
	@RequestMapping(value = "/DeleteUsers", method = RequestMethod.POST)
	@ResponseBody
	public boolean deleteUsers(@RequestBody List<String> list) {
		return userService.deleteUsers(list);
	}

	/**
	 * 用户自己修改密码  已验证
	 * 
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	@RequestMapping(value = "/UpdateSelfPassword", method = RequestMethod.PUT)
	@ResponseBody
	public int updateSelfPassword(@RequestBody Map<String, String> map) {
		String oldPwd = map.get("oldPwd");
		String newPwd = map.get("newPwd");
		User user =  getCurrentUser(); 
		user.password = SHA.encryptSHA1(oldPwd);
		String newPassWord = SHA.encryptSHA1(newPwd);
		int result=userService.updateSelfPassword(user, newPassWord);
		return result;
	}

	/**
	 * 分页获取用户
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param conditions
	 * @return
	 */
	@RequestMapping(value = "/GetUsers", method = RequestMethod.POST)
	@ResponseBody
	Paging<User> GetUsers(@RequestBody String string) {
		return userService.GetUsers(JSON.parseObject(string, Page.class));
	}

	/**
	 * 管理员修改密码
	 * 
	 * @param userID
	 * @param newPwd
	 * @return
	 */
	@RequestMapping(value = "/UpdateUserPassword", method = RequestMethod.PUT)
	@ResponseBody
    //@PathParam("userID") String userID, @PathParam("newPwd") String newPwd
	public int updateUserPassword(@RequestBody Map<String, String> map,HttpServletRequest request) {
		User user = new User();
		user.setId(map.get("userID"));
		user.setPassword(SHA.encryptSHA1(map.get("newPwd")));
		int result = userService.updateUserPassword(user);
		return result;
	}
	/**
	 * 获取组织机构下所有用户
	 * @return
	 */
	@RequestMapping(value = "/GetAllUsers", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getAllUser(){
		return userService.getAllUser();
	}
	/**
	 * 获取当前登陆用的签名照
	 * @return
	 */
	@RequestMapping(value = "/GetSignaturePhoto", method = RequestMethod.GET)
	@ResponseBody
	public User GetSignaturePhoto(){
		return userService.getSignaturePhoto();
	}
	/**
	 * 获取指定用户的签名照
	 * @param userIDs
	 * @return
	 */
	@RequestMapping(value = "/GetSignaturePhotos", method = RequestMethod.POST)
	@ResponseBody
	public  List<User> GetSignaturePhotos(@RequestBody List<String> userIDs){
		
		return userService.getSignaturePhotos(userIDs);
	}
}
