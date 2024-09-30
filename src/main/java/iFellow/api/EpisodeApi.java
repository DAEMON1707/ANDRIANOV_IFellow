package iFellow.api;

import static io.restassured.RestAssured.given;

public class EpisodeApi {
    public String getSingleEpisode (Integer id) {
        return given()
                .when()
                .get("/api/episode/" + id)
                .then()
                .statusCode(200)
                .extract()
                .response().asString();
    }
}