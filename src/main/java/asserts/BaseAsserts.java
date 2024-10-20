package asserts;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;

@Slf4j
public class BaseAsserts {
    @Step
    protected void verifyEquals(Object expected, Object actual, String verifiedField) {
        log.info("Verify data <{}> expected: [{}], actual: [{}]", verifiedField, expected, actual);
        Assertions.assertThat(actual)
                .as(verifiedField)
                .isEqualTo(expected);
    }
}