package model.megogo.records;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import serializer.CurrentTimeDeserializer;

import java.time.ZonedDateTime;

// Could be used instead of DTOs
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = false)
public record CurrentTime(
        @JsonProperty(value = "utc_offset")
        Integer utcOffset,
        @JsonProperty(value = "timestamp_gmt")
        Long timestampGmt,
        @JsonProperty(value = "timestamp_local")
        Long timestampLocal,
        @JsonProperty
        String timezone,
        @JsonProperty
        Long timestamp,
        @JsonProperty(value = "time_local")
        @JsonDeserialize(using = CurrentTimeDeserializer.class)
        //String timeLocal
        ZonedDateTime timeLocal
) {
}
