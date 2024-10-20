package com.task;

import api.ChannelApi;
import asserts.ChannelsResponseAsserts;
import asserts.ProgramsAsserts;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import lombok.extern.slf4j.Slf4j;
import model.megogo.dtos.ChannelsResponseDTO;
import model.megogo.dtos.ProgramDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

@Slf4j
@Epic("Megogo API")
@Feature("Channel")
@Execution(CONCURRENT)
public class RetrieveChannelsInfoTest extends BaseTest {
    private static final int FIRST_CHANNEL = 1639111;
    private static final int SECOND_CHANNEL = 1585681;
    private static final int THIRD_CHANNEL = 1639231;
    private ChannelsResponseDTO response;
    private long currentTimeStamp;

    @BeforeEach
    void getData() {
        if (Objects.isNull(response)) {
            response = new ChannelApi().getByVideoIds(FIRST_CHANNEL, SECOND_CHANNEL, THIRD_CHANNEL);
            currentTimeStamp = System.currentTimeMillis() / 1000;
        }
    }

    @Tag("api")
    @Tag("channel")
    @Test
    void verifyAbilityToRetrieveChannelsByVideoIdsTest() {
        var responseAsserts =  new ChannelsResponseAsserts(response);
        assertAll("channels by videoIds response",
                () -> responseAsserts.hasResult("ok"),
                () -> responseAsserts.hasSize(3),
                () -> responseAsserts.hasChannel(FIRST_CHANNEL),
                () -> responseAsserts.hasChannel(SECOND_CHANNEL),
                () -> responseAsserts.hasChannel(THIRD_CHANNEL)
        );
    }

    @Tag("api")
    @Tag("channel")
    @ParameterizedTest
    @ValueSource(ints = { FIRST_CHANNEL, SECOND_CHANNEL, THIRD_CHANNEL })
    void verifyProgramsSortedByStartTimestampTest(int channelId) {
        var programs = getProgramsForChannel(channelId);
        var programsAsserts = new ProgramsAsserts(programs);

        programsAsserts.isSortedByStartTimeStamp(true);

    }

    @Tag("api")
    @Tag("channel")
    @ParameterizedTest
    @ValueSource(ints = { FIRST_CHANNEL, SECOND_CHANNEL, THIRD_CHANNEL })
    void verifyProgramExistForCurrentTimeTest(int channelId)  {
        var programs = getProgramsForChannel(channelId);
        var programsAsserts = new ProgramsAsserts(programs);

        programsAsserts.hasProgramForTimeStamp(currentTimeStamp);
    }

    @Tag("api")
    @Tag("channel")
    @ParameterizedTest
    @ValueSource(ints = { FIRST_CHANNEL, SECOND_CHANNEL, THIRD_CHANNEL })
    void verifyZeroPastProgramsDisplayedTest(int channelId)  {
        var programs = getProgramsForChannel(channelId);
        var programsAsserts = new ProgramsAsserts(programs);

        programsAsserts.hasZeroProgramInPastForPeriod(currentTimeStamp);
    }

    @Tag("api")
    @Tag("channel")
    @ParameterizedTest
    @ValueSource(ints = { FIRST_CHANNEL, SECOND_CHANNEL, THIRD_CHANNEL })
    void verifyProgramsReturnedOnlyForNext24HoursTest(int channelId)  {
        var programs = getProgramsForChannel(channelId);
        var programsAsserts = new ProgramsAsserts(programs);

        programsAsserts.hasProgramsStartedInNext24HoursForPeriod(currentTimeStamp);
    }

    private List<ProgramDTO> getProgramsForChannel(int channelId) {
        return response.getData().stream()
                .filter(ch -> ch.getId() == channelId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Channel not found in response"))
                .getPrograms();
    }
}
