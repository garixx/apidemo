package api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;

import model.megogo.dtos.TimeResponseDTO;
import model.megogo.records.TimeResponse;

public class TimeApi extends AbstractApi {
    @Step
    public TimeResponseDTO getCurrentTime() {
        return RestAssured.given()
                .spec(getDefaultSpecification())
                .when()
                .get("time")
                .then()
                .statusCode(200)
                .extract()
                .as(TimeResponseDTO.class);
    }

    // demo for records usage
    @Step
    public TimeResponse getCurrent() {
        return RestAssured.given()
                .spec(getDefaultSpecification())
                .when()
                .get("time")
                .then()
                .statusCode(200)
                .extract()
                .as(TimeResponse.class);
    }
}
