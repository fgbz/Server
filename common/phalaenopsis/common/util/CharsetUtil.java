package phalaenopsis.common.util;

import java.nio.charset.Charset;

/**
 * 字符集工具类
 * @author chunl
 *
 */
public final class CharsetUtil {

	protected CharsetUtil() {
		/* 保护 */
	}

	public static final Charset US_ASCII = Charset.forName("US-ASCII");
	public static final Charset ISO_8859_1 = Charset.forName("ISO_8859_1");
	public static final Charset UTF_8 = Charset.forName("UTF_8");
	public static final Charset UTF_16BE = Charset.forName("UTF_16BE");
	public static final Charset UTF_16LE = Charset.forName("UTF_16LE");
	public static final Charset UTF_16 = Charset.forName("UTF_16");

}
