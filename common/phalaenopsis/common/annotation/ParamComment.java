package phalaenopsis.common.annotation;

import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.*;

/**
 * 项目名称：Phalaenopsis
 * 创建日期：2017/11/16
 * 修改历史：
 * 1. [2017/11/16]创建文件
 *
 * @author chunl
 */
@Document
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Inherited
@Import({MethodComment.class})
public @interface ParamComment {

    public String value();

    public Class<?> returnClazz() default void.class;
}
