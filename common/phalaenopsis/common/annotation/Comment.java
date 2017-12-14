package phalaenopsis.common.annotation;

import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.*;

/**
 * 项目名称：Phalaenopsis
 * 创建日期：2017/11/9
 * 修改历史：
 * 1. [2017/11/9]创建文件
 * 添加注释注解
 *
 * @author chunl
 */
@Document
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Inherited
@Import({MethodComment.class})
public @interface Comment {

    public String value();

    public String argName() default "";

    public String returnVal() default "";

    public Class<?> returnClazz() default void.class;

}
