package iFellow.tests;

import iFellow.api.UsersApi;
import iFellow.functions.Functions;
import iFellow.hooks.Hooks;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.restassured.path.json.JsonPath.from;

public class TestApi extends Hooks {

    Functions functions = new Functions();
    UsersApi usersApi = new UsersApi();

    @Test
    public void checkCreateUserApi () throws IOException {
        String name = "Tomato";
        String job = "Eat maket";

        JSONObject requestBody = functions.parseJSONFile("src/test/resources/json/user.json");
        requestBody.putOpt("name", name);
        requestBody.putOnce("job", job);

        String response = usersApi.postCreate(requestBody);

        Assertions.assertEquals(from(response).getString("name"), name);
        Assertions.assertEquals(from(response).getString("job"), job);
    }
}