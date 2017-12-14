package phalaenopsis.common.method.Json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import phalaenopsis.common.method.Tools.StrUtil;

/**
 * 
 * 使用时，在属性上添加@JsonDeserialize(using=DateJsonDeserializer.class)
 * 
 * @author chunl
 */
public class DateJsonDeserializer extends JsonDeserializer<Date> {

	private static final SimpleDateFormat format  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static final SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");

	private Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");

	@Override
	public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException, JsonProcessingException {
		try {

			if (StrUtil.isInteger(jsonParser.getText())) {
				return new Date(jsonParser.getLongValue());
			}

			String dateStr = jsonParser.getText();

			Matcher matcher = pattern.matcher(dateStr);
			if (matcher.matches()) {
				return simpleFormat.parse(dateStr);
			}
			else{
				return format.parse(dateStr);
			}

		} catch (java.text.ParseException e) {
			throw new RuntimeException(e);
		}
	}

}
