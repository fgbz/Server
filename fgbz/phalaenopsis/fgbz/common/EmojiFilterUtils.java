package phalaenopsis.fgbz.common;

import phalaenopsis.common.method.Tools.StrUtil;

/**
 * Created by 13260 on 2018/3/29.
 */
public class EmojiFilterUtils {

    /**
     * 将emoji表情替换成*
     *
     * @param source
     * @return 过滤后的字符串
     */
    public static String filterEmoji(String source) {
        if(!StrUtil.isNullOrEmpty(source)){
            return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*");
        }else{
            return source;
        }
    }
}
