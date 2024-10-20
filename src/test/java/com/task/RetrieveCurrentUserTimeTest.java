package com.task;

import api.TimeApi;
import asserts.TimeResponseAsserts;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

@Epic("Megogo API")
@Feature("Time")
@Execution(CONCURRENT)
public class RetrieveCurrentUserTimeTest extends BaseTest {

    @Tag("api")
    @Tag("time")
    @Test
    void verifyAbilityToRetrieveCurrentUserTimeTest() {
        var response = new TimeApi().getCurrentTime();
        var asserts = new TimeResponseAsserts(response);

        assertAll("current time response",
                () -> asserts.hasResult("ok"),
                () -> asserts.hasTimestamp(System.currentTimeMillis()/1000, 5L),
                () -> asserts.hasTimezone(getUserTimezone())
        );
    }

    //it's stub for report
    private String getUserTimezone() {
        return "Europe/Kyiv"; //TODO: provide api call to external service to get user timezone
    }
}
