package phalaenopsis.common.method;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 统一验证用户是否登录
 * @author gongchengw
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private static final String[] IGNORE_URI = {"/login.do", "/Login/","/getNum.do","/get.do"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = false;
        String url = request.getRequestURL().toString();
        System.out.println(">>>: " + url);
        for (String s : IGNORE_URI) {
            if (url.contains(s)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
        	  
        	String strUser=getAuthId(request.getSession(),request);
            if(strUser!=null){
               flag = true;
            }
        	
            if(!flag){
                response.setContentType("application/json");  
                response.setCharacterEncoding("UTF-8");  
                PrintWriter writer=response.getWriter();  
                Map<String, Object> ma=new HashMap<String, Object>();
                ma.put("msg", "请登录");
                writer.println(ma.toString());  
                writer.close(); 
            }
        }
        
        return flag;
    }

    /**
     * 获取http Header，Parameter 。cookie 用户验签
     * @param session
     * @param request
     * @return  authId(用户验签)
     */
    public  String getAuthId(HttpSession session,HttpServletRequest request){
    	//获取header
        String authId= request.getHeader("auth_id");
        
        if(authId==null){
           authId= request.getParameter("auth_id");	
           if(authId==null){
        	   Cookie[] cookies=request.getCookies();
        	   for(Cookie cookie : cookies){
        		  String cookieName= cookie.getName();
        		    if(cookieName!=null&&cookieName.toLowerCase().equals("auth_id")){
        		    	authId= cookie.getValue();
        		    }
        		}
           }	   
        }
        String struser= checkUserLoginStatus(session,authId);
        if(struser!=null){
        	return struser;
        }
    	return null;
    }
    
    
    /**
     * 验证用户在线状态是否超时
     * @param session
     * @param authId
     * @return  用户信息
     */
	public String checkUserLoginStatus(HttpSession session, String authId) {
		String strUser = (String) session.getAttribute(authId);
		if (strUser != null) {
			return strUser;
		}

		return null;
	}
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
	
}
