package phalaenopsis.common.annotation;

import java.lang.annotation.*;

/**
 * 项目名称：Phalaenopsis
 * 创建日期：2017/11/2
 * 修改历史：
 * 1. [2017/11/2]创建文件
 *
 * @author chunl
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface Parameter {

    /**
     * 是否可为空。默认值可为空
     *
     * @return
     */
    boolean nullable() default true;

    /**
     * 是否为整数
     *
     * @return
     */
    boolean isInt() default false;

    /**
     * 是否为浮点数
     *
     * @return
     */
    boolean isDouble() default false;

    /**
     * 设置int类型最小值
     *
     * @return
     */
    int maxInt() default Integer.MAX_VALUE;

    /**
     * 设置int类型最小值
     *
     * @return
     */
    int minInt() default Integer.MIN_VALUE;

    /**
     * 设置double类型最大值
     *
     * @return
     */
    double maxDouble() default  Double.MAX_VALUE;

    /**
     * 设置double类型最小值
     *
     * @return
     */
    double minDouble() default  Double.MIN_VALUE;

    /**
     * 设置正则表达式
     *
     * @return
     */
    String pattern() default "";
}
