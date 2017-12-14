package phalaenopsis.lawcase.entity.serializer;

import java.io.IOException;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import phalaenopsis.common.method.Tools.StrUtil;
import phalaenopsis.lawcase.entity.document.DocumentType;

/**
 * 反序列化Json数据。将Json数据转换成DocumentType格式的数据
 * @author chunl
 *
 */
public class DocumentTypeDeserializer extends JsonDeserializer<DocumentType> {

	@Override
	public DocumentType deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {

		if (!StrUtil.isNullOrEmpty(jp.getText())) {
			if (StrUtil.isInteger(jp.getText())) {
				Integer integer = Integer.parseInt(jp.getText());
				return DocumentType.forValue(integer);
			}
		}
		
		return null;
	}

}
