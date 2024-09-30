package iFellow.api;

import static io.restassured.RestAssured.given;

public class CharacterApi {
    public String getSingleCharacter (Integer id) {
        return given()
                .when()
                .get("/api/character/" + id)
                .then()
                .statusCode(200)
                .extract()
                .response().asString();
    }
}