package model.megogo.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import serializer.CurrentTimeDeserializer;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true, setterPrefix = "with")
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = false)
public class CurrentTimeDTO extends AbstractDTO {
    @JsonProperty(value = "utc_offset")
    private Integer utcOffset;
    @JsonProperty(value = "timestamp_gmt")
    private Long timestampGmt;
    @JsonProperty(value = "timestamp_local")
    private Long timestampLocal;
    @JsonProperty
    private String timezone;
    @JsonProperty
    private Long timestamp;
    @JsonProperty(value = "time_local")
    @JsonDeserialize(using = CurrentTimeDeserializer.class)
    //private String timeLocal;
    private ZonedDateTime timeLocal;
}
