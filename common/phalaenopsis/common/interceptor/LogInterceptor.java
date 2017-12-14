package phalaenopsis.common.interceptor;

import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import phalaenopsis.common.entity.Ss_HITS;
import phalaenopsis.common.entity.User;
import phalaenopsis.common.method.cache.UserCache;
import phalaenopsis.common.service.LogService;
import phalaenopsis.common.util.HttpUtil;

/**
 * interceptor is singleton
 * 
 * @author langl
 *
 */
public class LogInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private LogService logService;

	/**
	 * spring命名本地线程变量。
	 */
	private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("WatchTime");
	
	
	
	

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// ticket == sessionid
//		String ticket = request.getHeader("auth_id");
//		if(ticket  == null || ticket.equals("")){
//			
//			ticket = "unknown";
//			
//			
//		}
//		long endTime = System.currentTimeMillis();
//		long beginTime = startTimeThreadLocal.get();
//		long consumeTime = endTime - beginTime;
//
//		String ua = request.getHeader("User-Agent");
//		String ip = request.getRemoteAddr();
//		String username = "unknown";
//		String name = "unknown";
//
//		// get user from cache
//		User user = (User) UserCache.get(ticket);
//
//		if (user != null) {
//			username = user.getAccount();
//			name = user.getName();
//
//		}
//		String uri = request.getRequestURI();
//		java.sql.Timestamp operatetime = new java.sql.Timestamp(beginTime);
//
//		String side = "";
//
//		if (ip != null && !ip.equals("")) {
//			boolean isInterIp = HttpUtil.isInnerIP(ip);
//			if (isInterIp) {
//				side = "in";
//			} else {
//				side = "ex";
//			}
//		}
//
//		Ss_HITS record = new Ss_HITS();
//		record.setProcesstime(new BigDecimal(consumeTime));
//		record.setUrl(uri);
//		record.setHittime(operatetime);
//		record.setSide(side);
//		record.setUseragent(ua);
//		record.setSessionid(ticket);
//		record.setIp(ip);
//		record.setUsername(username);
//		this.logService.addLog(record);

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		long beginTime = System.currentTimeMillis();
		startTimeThreadLocal.set(beginTime);
		return true;

	}

	
}
