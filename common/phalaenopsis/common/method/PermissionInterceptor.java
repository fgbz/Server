package phalaenopsis.common.method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import phalaenopsis.common.entity.User;
import phalaenopsis.common.method.cache.UserCache;

public class PermissionInterceptor implements HandlerInterceptor {

	String[] ignoreUrls = { "/Sys/Auth/Login", "/Foundation/DataDictionary/DownloadApp",
			"/Foundation/DataDictionary/GetLatestVersion", "/SatelliteGraph/Mobile/CheckConnection" };

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURI().toLowerCase();

		// 1，判断URL请求是否在忽略列表中
		for (String string : ignoreUrls) {
			if (url.contains(string.toLowerCase())) {
				return true;
			}
		}

		// 2,判断请求是否由已经登录的用户发出的
		String ticket = request.getHeader("auth_id");
		User user = (User) UserCache.get(ticket);
		if (null == user) {
			return false;
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
