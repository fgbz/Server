package phalaenopsis.common.method;

import org.apache.commons.lang.StringUtils;
import phalaenopsis.common.annotation.Parameter;
import phalaenopsis.common.exception.IllegalParameterException;
import phalaenopsis.common.method.Tools.StrUtil;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

/**
 * 项目名称：Phalaenopsis
 * 创建日期：2017/11/2
 * 修改历史：
 * 1. [2017/11/2]创建文件
 *
 * @author chunl
 */
public class Validator {

    /**
     *  校验
     * @param validator
     */
    public void validate(Validator validator) {

        String errorMsg = "";
        Field[] fields = validator.getClass().getDeclaredFields();

        try {

            for (Field field : fields) {
                Parameter parameter = field.getAnnotation(Parameter.class);
                field.setAccessible(true);

                if (null != parameter) {
                    if (parameter.nullable() == false) {
                        Object obj = field.get(validator);
                        if (null == obj) {
                            errorMsg += field.getName() + "字段不能为空;";
                            continue;
                        }
                    }

                    if (parameter.isInt() == true) {
                        Object obj = field.get(validator);
                        Integer i = getInteger(obj);
                        if (null == obj) {
                            errorMsg += field.getName() + "字段不是有效的整数;";
                            continue;
                        }
                    }

                    if (parameter.maxInt() < Integer.MAX_VALUE) {
                        Object obj = field.get(validator);
                        Integer i = getInteger(obj);
                        if (null != obj) {
                            if (i.compareTo(parameter.maxInt()) > 0) {
                                errorMsg += field.getName() + "超过最大值" + parameter.maxInt() + ";";
                                continue;
                            }
                        }
                    }


                    if (parameter.minInt() > Integer.MIN_VALUE) {
                        Object obj = field.get(validator);
                        Integer i = getInteger(obj);
                        if (null != obj) {
                            if (i.compareTo(parameter.minInt()) < 0) {
                                errorMsg += field.getName() + "超过最小值" + parameter.minInt() + ";";
                                continue;
                            }
                        }
                    }


                    if (parameter.isDouble() == true) {
                        Object obj = field.get(validator);
                        Double d = getDouble(obj);
                        if (null == obj) {
                            errorMsg += field.getName() + "字段不是有效的浮点数;";
                            continue;
                        }
                    }

                    if (parameter.maxDouble() < Double.MAX_VALUE) {
                        Object obj = field.get(validator);
                        Double d = getDouble(obj);
                        if (null != d) {
                            if (d.compareTo(parameter.maxDouble()) > 0) {
                                errorMsg += field.getName() + "超过最大值" + parameter.maxDouble() + ";";
                                continue;
                            }
                        }
                    }


                    if (parameter.minDouble() > Double.MIN_VALUE) {
                        Object obj = field.get(validator);
                        Double d = getDouble(obj);
                        if (null != d) {
                            if (d.compareTo(parameter.minDouble()) < 0) {
                                errorMsg += field.getName() + "超过最小值" + parameter.minDouble() + ";";
                                continue;
                            }
                        }
                    }

                    if (!StringUtils.isWhitespace(parameter.pattern())) {
                        Object obj = field.get(validator);
                        if (null != obj) {
                            if (!Pattern.matches(parameter.pattern(), obj.toString())) {
                                errorMsg += field.getName() + "不符合正则匹配" + parameter.pattern() + ";";
                                continue;
                            }
                        }
                    }
                }
            }

            if (!StringUtils.isWhitespace(errorMsg)) {
                throw new IllegalParameterException(errorMsg);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    /**
     * 将Obj值转为Double值
     *
     * @param obj Object值
     * @return 返回的Double值
     */
    private Double getDouble(Object obj) {
        if (null == obj) {
            return null;
        }

        if (StrUtil.isNumeric(obj.toString())) {
            return Double.parseDouble(obj.toString());
        }
        return null;
    }

    /**
     * 将Obj值转为Integer值
     *
     * @param obj Object值
     * @return 返回的Integer值
     */
    private Integer getInteger(Object obj) {
        if (null == obj) {
            return null;
        }

        if (StrUtil.isInteger(obj.toString())) {
            return Integer.parseInt(obj.toString());
        }

        return null;
    }


}
