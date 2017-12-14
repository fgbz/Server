package phalaenopsis.common.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import phalaenopsis.common.entity.AppSettings;
import phalaenopsis.common.entity.Menu;
import phalaenopsis.common.entity.User;
import phalaenopsis.common.method.Tools.JMUtilBase64;
import phalaenopsis.common.method.Tools.SHA;
import phalaenopsis.common.method.cache.UserCache;
import phalaenopsis.common.method.secrteKey.CryptoHelper;
import phalaenopsis.common.service.AuthService;
import phalaenopsis.common.service.UserService;
import phalaenopsis.common.util.CheckUserUtil;

/**
 * 账号服务
 * 
 * @author gongchengw
 *
 */
@Controller
@RequestMapping("/Sys/Auth")
public class AuthController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthService authService;

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private AppSettings appSettting;

	private static Logger logger = Logger.getLogger(AuthController.class);

	/**
	 * 添加 移动端测试连接服务
	 */
	@RequestMapping(value = "/Test", method = RequestMethod.GET)
	@ResponseBody
	public boolean test() {
		return true;
	}

	/**
	 * 用户登录(暂时未考虑移动端业务)
	 * 
	 * @param account
	 *            用户登录名
	 * @param password
	 *            用户密码
	 * @param endpoint
	 *            终端类型。客户端（client）或移动端（mobile）
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(@RequestBody Map<String, String> map1) throws UnsupportedEncodingException {

		final String accountJm = (String) map1.get("account");
		String account = new String(Base64.decodeBase64(accountJm.getBytes("utf-8")), "utf-8");
		// byte[] accountByte
		// =Base64.decodeBase64(accountJm.getBytes("UTF-16"),"utf-8");//对用户进行解密，乱码问题规避
		// final String account=new String(accountByte);//对前台传过来的密码加密数据进行解密
		final String passwordJm = (String) map1.get("password");// 解密前前台传过来的密码
		final String password = new String(JMUtilBase64.decode(passwordJm));// 对前台传过来的密码加密数据进行解密
		final String endpoint = (String) map1.get("endpoint");
		// 获取服务端加密后的密码
		Map<String, Object> map = new HashMap<String, Object>();
		User user = authService.login(account, SHA.encryptSHA1(password));
	
//		if (endpoint.equals("mobile")) {
//			if("on".equals(user.getIsOnline())){
//				Map<String, Object> m = new HashMap<String, Object>();
//				m.put("code", "用户已经登录！");
//				return m;
//			}
//		}
		
		map.put("LoginResult", user);
		String ticket = UUID.randomUUID().toString();
		map.put("ticket", ticket);
		UserCache.put(ticket, user);
		// CacheUtils.putUserCache(ticket, user);
		if (endpoint.equals("mobile")) {
			//byte[] bs = CryptoHelper.newKey(128);
			Map<String, Object> mapKey = new HashMap<>();
			//mapKey.put("MobileShapeKey", bs);
			if (user == null) {
				map = new HashMap<String, Object>();
				// 登录失败
				map.put("LoginState", false);
				return map;
			}
			user.setSession(mapKey);
			byte[] bs2 = CryptoHelper.newKey(128);
			Map<String, Object> mapIv = new HashMap<>();
			mapIv.put("MobileShapeIV", bs2);
			user.setSession(mapIv);
			// 如果是移动端则返回登录成功标识
			map.put("LoginState", true);
			map.put("MinDistance", appSettting.getMinDistance());
			
//			try {
//				Map<String, String> rsaMap = RSASecrte.getRSAKey();
//				user.setMobilePublicKey(rsaMap.get(RSASecrte.PUBLIC_KEY));
//				user.setMobilePrivateKey(rsaMap.get(RSASecrte.PRIVATE_KEY));
//				
//			} catch (NoSuchAlgorithmException e) {
//				e.printStackTrace();
//			}
			
			user.setIsOnline("on");
			int code = userService.updateUserIsOnline(user);
			//map.put("code", code);
			
			
		}
		return map;
	}
	
	
	/**
	 * 用户登录(与海潮平台集成)
	 * 
	 * @param account
	 *            用户登录名
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/loginForLC", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> loginForLC(String accountJm) throws UnsupportedEncodingException {

		String account = new String(Base64.decodeBase64(accountJm.getBytes("utf-8")), "utf-8");
		// byte[] accountByte
		// =Base64.decodeBase64(accountJm.getBytes("UTF-16"),"utf-8");//对用户进行解密，乱码问题规避
		// final String account=new String(accountByte);//对前台传过来的密码加密数据进行解密
		// 获取服务端加密后的密码
		Map<String, Object> map = new HashMap<String, Object>();
		account = account.toUpperCase();
		User user = authService.login(account);
		
		map.put("LoginResult", user);
		String ticket = UUID.randomUUID().toString();
		map.put("ticket", ticket);
		UserCache.put(ticket, user);
		// CacheUtils.putUserCache(ticket, user);
		
		return map;
	}

	/**
	 * 移动端退出
	 * 
	 * @param map1
	 */
	@RequestMapping(value = "/LogoutUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> LogoutUser(@RequestBody Map<String, String> map1) throws UnsupportedEncodingException{
		final String accountJm = (String) map1.get("account");
		String account = new String(Base64.decodeBase64(accountJm.getBytes("utf-8")), "utf-8");
		final String passwordJm = (String) map1.get("password");// 解密前前台传过来的密码
		final String password = new String(JMUtilBase64.decode(passwordJm));// 对前台传过来的密码加密数据进行解密
		// 获取服务端加密后的密码
		User user = authService.login(account, SHA.encryptSHA1(password));
		user.setIsOnline("off");
		int code = userService.updateUserIsOnline(user);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		return map;
	}

	/**
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/CheckUser", method = RequestMethod.POST)
	@ResponseBody
	public boolean CheckUser(@RequestBody Map<String, String> map){
		try {
			final String id = (String) map.get("id");
			String value =String.valueOf(new Date().getTime());
//			User user = new User();
//			user.setId(id);
//			user.setIsOnline("on");
//			userService.updateUserIsOnline(user);
			Map<String,String> maps = CheckUserUtil.newDataOnline;
			maps.put(id, value);
			logger.info("CheckUser在线离线：["+value+"]");
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * 注销 用户登出
	 * 
	 * @param request
	 */
	@RequestMapping(value = "/Logout", method = RequestMethod.GET)
	@ResponseBody
	public void loginOut() {
		String authId = request.getHeader("auth_id");
		UserCache.remove(authId);
		// CacheUtils.removeUserCache(authId);
	}

	/**
	 * 验证用户是否有系统管理的角色权限
	 * 
	 * @return
	 */
	@RequestMapping(value = "/IsAdmin", method = RequestMethod.GET)
	@ResponseBody
	public boolean isAdmin() {
		return authService.isAdmin();
	}

	/**
	 * 获取系统管理菜单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/GetSysMenus", method = RequestMethod.GET)
	@ResponseBody
	public List<Menu> getSysMenus() {
		List<Menu> result = authService.getSysMenus();
		return result;
	}

	/**
	 * 获取当前用户的菜单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/GetMenus", method = RequestMethod.GET)
	@ResponseBody
	public List<Menu> getMenus() {
		return authService.getMenus();
	}

	/**
	 * 获取页面的授权信息
	 * 
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/getAuthInfo", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAuthInfo(@PathParam("url") String url) {
		Map<String, Object> map = new HashMap<String, Object>();
		authService.getAuthInfo(url);
		return map;
	}
}
