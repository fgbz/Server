package phalaenopsis.common.method;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import phalaenopsis.common.exception.IllegalParameterException;

/**
 * 异常统一处理
 *
 * @author gongchengw
 */
public class MyExceptionHandler implements HandlerExceptionResolver {

    private static Logger logger = LoggerFactory.getLogger("ERROR-APPENDER");

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        //1,将错误信息打印到日志中
        logger.error(ex.toString());
        StackTraceElement[] s = ex.getStackTrace();
        for (StackTraceElement sinfo : s) {
            logger.error(sinfo.getClassName() + " (" + sinfo.getMethodName() + " " + sinfo.getLineNumber() + ")");
        }

        //2,返回错误信息给调用者
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            if (ex instanceof IllegalParameterException) {
                response.getWriter().write("参数出现异常：" + ex.getMessage());
            } else {
                response.getWriter().write("程序出现问题，请联系管理员");
            }
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
