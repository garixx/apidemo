package model.megogo.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true, setterPrefix = "with")
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = false)
public class ChannelDTO extends AbstractDTO {
    @JsonProperty
    private Integer id;
    @JsonProperty(value = "external_id")
    private Integer externalId;
    @JsonProperty
    private String title;
    private Object pictures;
    @JsonProperty(value = "video_id")
    private Integer video_id;
    @JsonProperty
    private List<ProgramDTO> programs;

}
