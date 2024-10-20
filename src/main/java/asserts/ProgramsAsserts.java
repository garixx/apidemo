package asserts;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import model.megogo.dtos.ProgramDTO;

import java.util.Comparator;
import java.util.List;

@Slf4j
public class ProgramsAsserts extends BaseAsserts {
    private List<ProgramDTO> programDTOs;
    private ProgramsAsserts() {}
    public ProgramsAsserts(final List<ProgramDTO> programDTOs) {
        this.programDTOs = programDTOs;
    }

    @Step("Programs sorted by start_timestamp")
    public ProgramsAsserts isSortedByStartTimeStamp(boolean expected) {
        var sortedByStartTimeStamp = programDTOs.stream()
                .sorted(Comparator.comparingLong(ProgramDTO::getStartTimestamp))
                .toList();
        verifyEquals(sortedByStartTimeStamp, programDTOs, "sorting by start_timestamp");
        return this;
    }

    @Step("Program exist for current time")
    public ProgramsAsserts hasProgramForTimeStamp(Long timestamp) {
        log.info("Verify program exist for timestamp: {}", timestamp);
        var programs = programDTOs.stream()
                .filter(p -> p.getStartTimestamp() < timestamp && p.getEndTimestamp() > timestamp)
                .toList();
        verifyEquals(programs.size(), 1, "program at timestamp");
        return this;
    }

    @Step("Program finished in past not returned")
    public ProgramsAsserts hasZeroProgramInPastForPeriod(Long timestamp) {
        log.info("Verify program not exist for timestamp: {}", timestamp);
        var programs = programDTOs.stream()
                .filter(p -> p.getEndTimestamp() < timestamp)
                .toList();
        verifyEquals(programs.size(), 0, "program in past for timestamp");
        return this;
    }

    @Step("Program starting after 24 hours not returned")
    public ProgramsAsserts hasProgramsStartedInNext24HoursForPeriod(Long timestamp) {
        log.info("Verify program not returned after 24 hours for timestamp: {}", timestamp);
        var a24plusHoursTimeStamp = timestamp + (24 * 60 * 60);
        var programs = programDTOs.stream()
                .filter(p -> p.getStartTimestamp() > a24plusHoursTimeStamp)
                .toList();
        verifyEquals(programs.size(), 0, "programs 24+ hours for timestamp");
        return this;
    }
}
