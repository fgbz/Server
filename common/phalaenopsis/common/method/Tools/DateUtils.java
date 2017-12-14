package phalaenopsis.common.method.Tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期工具类.
 * @author chunl
 *
 */
public final class DateUtils {

	/**
	 * 私有构造器.
	 */
	private DateUtils() {
	}

	/**
	 * 标准日期（不含时间）格式化器.
	 */
	private static final SimpleDateFormat NORM_DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");

	/**
	 * 标准日期时间格式化器.
	 */
	private static final SimpleDateFormat NORM_DATETIME_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * HTTP日期时间格式化器.
	 */
	private static final SimpleDateFormat HTTP_DATETIME_FORMAT = new SimpleDateFormat(
			"EEE, dd MMM yyyy HH:mm:ss z", Locale.US);

	private static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

	

	
	public static String formatDate(final Date date){
		if (date == null)
			return "";
		
		String dateStr = "";
		try{
			dateStr = DATETIME_FORMAT.format(date);
		}catch(Exception e){}
		return dateStr;
	}
	
	/**
	 * 日期格式转换.
	 * @param date
	 *            传递的日期.
	 * @return 返回格式化的日期.
	 */
	public static String formatDateStr(final Date date) {
		if (date == null) {
			return "";
		}
		String dateStr = "";
		try {
			dateStr = NORM_DATE_FORMAT.format(date);
		} catch (Exception e) {
			// 不需要处理
		}
		return dateStr;
	}

}
