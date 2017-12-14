package phalaenopsis.common.util;

import org.apache.log4j.Logger;  

public class LogUtil {  
    private static Logger cLogger = Logger.getLogger("CommonLog");  
    private static Logger eLogger = Logger.getLogger("ErrorLog");  
      
    public static void error(Class<?> clz,String message,Exception e){  
        eLogger.error(message+" ["+clz.getName()+"]",e);  
    }  
      
    public static void info(Class<?> clz,String message,Exception e){  
        cLogger.info(message+" ["+clz.getName()+"]",e);  
    }  
      
//    public static void debug(Class<?> clz,String message,Exception e){  
//        cLogger.debug(message+" ["+clz.getName()+"]",e);  
//    }  
//      
//    public static void warn(Class<?> clz,String message,Exception e){  
//        cLogger.warn(message+" ["+clz.getName()+"]",e);  
//    }  
      
//    public static void error(Class<?> clz,String message,Exception e){  
//        cLogger.error(message+" ["+clz.getName()+"]",e);  
//    }  
}  