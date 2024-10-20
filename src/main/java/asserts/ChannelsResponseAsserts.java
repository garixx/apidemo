package asserts;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import model.megogo.dtos.ChannelsResponseDTO;

@Slf4j
public class ChannelsResponseAsserts extends BaseAsserts {
    private ChannelsResponseDTO channelsResponseDTO;
    private ChannelsResponseAsserts() {}

    public ChannelsResponseAsserts(final ChannelsResponseDTO channelsResponseDTO) {
        this.channelsResponseDTO = channelsResponseDTO;
    }

    @Step("Has result status")
    public ChannelsResponseAsserts hasResult(String expected) {
        verifyEquals(expected, channelsResponseDTO.getResult(), "result");
        return this;
    }

    @Step
    public ChannelsResponseAsserts hasSize(int expected) {
        verifyEquals(expected, channelsResponseDTO.getData().size(), "amount");
        return this;
    }

    @Step("Has channels with videoIds")
    public ChannelsResponseAsserts hasChannel(int videoId) {
        var count = channelsResponseDTO.getData().stream().filter(ch -> ch.getId() == videoId).count();
        verifyEquals(1L, count, "channel id present");
        return this;
    }
}
