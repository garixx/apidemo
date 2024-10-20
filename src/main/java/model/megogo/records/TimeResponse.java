package model.megogo.records;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

// Could be used instead of DTOs
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = false)
public record TimeResponse(
        @JsonProperty
        String result,
        @JsonProperty
        CurrentTime data
) {
}
