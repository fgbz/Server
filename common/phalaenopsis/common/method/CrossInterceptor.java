package phalaenopsis.common.method;

import javax.imageio.metadata.IIOMetadataFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import phalaenopsis.common.method.cache.UserCache;

public class CrossInterceptor extends HandlerInterceptorAdapter {

    private String ignoreUrls;

    public String getIgnoreUrls() {
        return ignoreUrls;
    }

    public void setIgnoreUrls(String ignoreUrls) {
        this.ignoreUrls = ignoreUrls;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "*");
        response.addHeader("Access-Control-Max-Age", "100");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type");
        response.addHeader("Access-Control-Allow-Credentials", "false");

        String authid = request.getHeader("auth_id");
        if (null == authid) {
            authid = request.getParameter("AUTH_ID");
        }

        String url = request.getRequestURI();
        if (url.indexOf("/swagger-") > -1 || url.indexOf("/v2/api-docs") > -1 || url.indexOf("/metrics") > -1) {
            return super.preHandle(request, response, handler);
        }

        String[] Urls = ignoreUrls.split(";");

//        if(authid!=null&&authid.equals("1")){
//            return super.preHandle(request, response, handler);
//        }

        if (null == authid) {
            for (String string : Urls) {
                if (url.equals(string)) {
                    return super.preHandle(request, response, handler);
                }
            }
            response.setStatus(403);
            return false;
        } else {
            for (String string : Urls) {
                if (url.equals(string)) {
                    return super.preHandle(request, response, handler);
                }
            }

            Object user = UserCache.get(authid); // CacheUtils.getUserCache(authid);
            if (null == user) {
                response.setStatus(403);
                return false;
            }
        }

        return super.preHandle(request, response, handler);
    }

}
