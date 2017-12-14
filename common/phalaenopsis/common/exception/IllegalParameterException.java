package phalaenopsis.common.exception;

/**
 * 项目名称：Phalaenopsis
 * 创建日期：2017/10/30
 * 修改历史：
 * 1. [2017/10/30]创建文件
 *
 * 参数异常。
 * service层要对多有传递的参数进行检验，如果没有验证通过抛出<exception>IllegalParameterException</exception>
 * @author chunl
 */
public class IllegalParameterException extends RuntimeException {

    public IllegalParameterException(){
        super();
    }

    public IllegalParameterException(String msg){
        super(msg);
    }

    public IllegalParameterException(Throwable throwable){
        super(throwable);
    }

    public IllegalParameterException(String message, Throwable throwable){
        super(message, throwable);
    }
}
