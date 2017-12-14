/**
 * 项目名称：Phalaenopsis
 * 创建日期：2017年6月22日
 * 修改历史：
 * 		     1. [2017年6月22日]创建文件 by chunl
 */
package phalaenopsis.common.util;

public final class HtmlUtil {

	public static final String RE_HTML_MARK = "(<.*?>)|(<[\\s]*?/.*?>)|(<.*?/[\\s]*?>)";

	public static final String RE_SCRIPT = "<[\\s]*?script[^>]*?>.*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";

	/**
	 * 还原被转义的Html特殊字符
	 * 
	 * @param htmlStr
	 *            包含被转义Html内容
	 * @return 转换后的字符串
	 */
	public static String restoreEscaped(String htmlStr) {
		if (htmlStr == null || "".equals(htmlStr)) {
			return htmlStr;
		}
		return htmlStr.replace("&lt", "<").replace("&lt;", "<").replace("&gt;", ">").replace("&amp;", "&")
				.replace("&quot;", "\"").replace("&#39;", "'").replace("&nbsp;", " ");
	}

}
