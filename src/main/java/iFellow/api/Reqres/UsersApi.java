package iFellow.api.Reqres;

import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class UsersApi {
    public String postCreate (JSONObject requestBody) {
        return given()
                .when()
                .request().body(requestBody.toString())
                .post("/api/users")
                .then()
                .statusCode(201)
                .extract()
                .response().asString();
    }
}