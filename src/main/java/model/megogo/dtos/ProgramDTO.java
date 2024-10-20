package model.megogo.dtos;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true, setterPrefix = "with")
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProgramDTO extends AbstractDTO {
    @JsonProperty(value = "external_id")
    private Integer externalId;
    @JsonProperty
    private String title;
    @JsonProperty
    private Long id;
    @JsonProperty(value = "start_timestamp")
    private Long startTimestamp;
    @JsonProperty(value = "end_timestamp")
    private Long endTimestamp;
    // Other fields not required for test task and skipped
}