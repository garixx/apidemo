package asserts;

import io.qameta.allure.Step;
import model.megogo.dtos.TimeResponseDTO;

public class TimeResponseAsserts extends BaseAsserts{
    private TimeResponseDTO timeResponseDTO;

    private TimeResponseAsserts() {}

    public TimeResponseAsserts(final TimeResponseDTO timeResponseDTO) {
        this.timeResponseDTO = timeResponseDTO;
    }

    @Step
    public TimeResponseAsserts hasResult(String expected) {
        verifyEquals(expected, timeResponseDTO.getResult(), "result");
        return this;
    }

    @Step
    public TimeResponseAsserts hasTimezone(String expected) {
        verifyEquals(expected, timeResponseDTO.getData().getTimezone(), "timezone");
        return this;
    }
}
