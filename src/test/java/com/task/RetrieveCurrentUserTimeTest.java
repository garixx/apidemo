package com.task;

import api.TimeApi;
import asserts.TimeResponseAsserts;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;

@Epic("Megogo API")
@Feature("Time")
public class RetrieveCurrentUserTimeTest extends BaseTest {

    @Tag("api")
    @Tag("time")
    @Test
    void verifyAbilityToRetrieveCurrentUserTimeTest() {
        var response = new TimeApi().getCurrentTime();
        var asserts = new TimeResponseAsserts(response);

        assertAll("current time response",
                () -> asserts.hasResult("ok"),
                () -> asserts.hasTimezone("Europe/Kyiv")
        );
    }
}
