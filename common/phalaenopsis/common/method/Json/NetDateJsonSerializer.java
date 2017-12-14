package phalaenopsis.common.method.Json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * 使用时，在属性上添加@JsonSerialize(using=NetDateJsonSerializer.class)
 *
 * @author chunl
 */
public class NetDateJsonSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString("/Date(" + date.getTime() + "+0800)/");
    }
}
