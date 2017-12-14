package phalaenopsis.common.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import phalaenopsis.common.annotation.Validate;

import javax.validation.Valid;

/**
 * 项目名称：Phalaenopsis
 * 创建日期：2017/11/21
 * 修改历史：
 * 1. [2017/11/21]创建文件
 *
 * @author chunl
 */
public class ControllerMethodInterceptor implements MethodInterceptor {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(ControllerMethodInterceptor.class);

    private static final ObjectMapper jsonMapper = new ObjectMapper();

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

//        logger.info("Before:intercepter name:{}", invocation.getMethod().getName());
//
//        logger.info("Arguments:{}", jsonMapper.writeValueAsString(invocation.getArguments()));
//
        Validate validate = invocation.getMethod().getAnnotation(Validate.class);
        if (null != validate){

        }

        Object reuslt = invocation.proceed();
//
//        logger.info("After:result:{}", jsonMapper.writeValueAsString(reuslt));

        return reuslt;
    }
}
