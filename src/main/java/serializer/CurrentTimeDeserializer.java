package serializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

// input time format looks like "Sunday, October 20, 2024 11:30:45 AM +03:00"
public class CurrentTimeDeserializer extends JsonDeserializer<ZonedDateTime> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, LLLL dd, yyyy h:mm:ss a VV")
            .withLocale(Locale.US);
    @Override
    public ZonedDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        return ZonedDateTime.parse(jsonParser.getText(), formatter);
    }
}
