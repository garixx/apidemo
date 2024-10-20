package asserts;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import model.megogo.dtos.TimeResponseDTO;
import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.within;

@Slf4j
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

    @Step
    public TimeResponseAsserts hasTimestamp(Long expected, Long delta) {
        var actual = System.currentTimeMillis()/1000;
        log.info("Verify data <{}> expected: [{}], actual: [{}], with delta: [{}]", "timestamp", expected, actual, delta);
        // equalTo() could be flaky so closeTo() used
        Assertions.assertThat(actual).isCloseTo(expected, within(delta));
        return this;
    }
}
