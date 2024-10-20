package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

//TODO: ensure thread safe using ThreadLocal container
public class AbstractApi {
    protected RequestSpecification getDefaultSpecification() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("https://epg.megogo.net/")
                .build();
    }
}
