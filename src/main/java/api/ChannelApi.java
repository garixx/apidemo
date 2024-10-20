package api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import model.megogo.dtos.ChannelsResponseDTO;

public class ChannelApi extends AbstractApi {
    @Step
    public ChannelsResponseDTO getByVideoIds(Integer... videoIds) {
        var sb = new StringBuilder();
        for(Integer i: videoIds) {
            sb.append(i).append(",");
        }
        return RestAssured.given()
                .spec(getDefaultSpecification().queryParam("video_ids", sb.toString()))
                .when()
                .get("channel")
                .then()
                .statusCode(200)
                .extract()
                .as(ChannelsResponseDTO.class);
    }
}
