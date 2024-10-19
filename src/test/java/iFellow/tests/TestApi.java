package iFellow.tests;

import iFellow.api.Reqres.UsersApi;
import iFellow.api.RickAndMorty.CharacterApi;
import iFellow.api.RickAndMorty.EpisodeApi;
import iFellow.functions.Functions;
import iFellow.hooks.Hooks;
import iFellow.properties.Props;
import io.cucumber.java.ru.Когда;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.restassured.path.json.JsonPath.from;

public class TestApi {

    Hooks hooks = new Hooks();
    Functions functions = new Functions();
    CharacterApi characterApi = new CharacterApi();
    EpisodeApi episodeApi = new EpisodeApi();
    UsersApi usersApi = new UsersApi();

    @Test
    @Когда("Проверка API [Rick and Morty]")
    public void checkApiRickAndMorty() {
        hooks.initApiRickAndMorty();

        String responseCharacterApi = characterApi.getSingleCharacter(Props.props.characterId());
        System.out.println(responseCharacterApi);
        Integer lastEpisodeId = functions.getIdLastElements(from(responseCharacterApi).getList("episode"));

        String responseEpisodeApi = episodeApi.getSingleEpisode(lastEpisodeId);
        Integer lastCharacterId = functions.getIdLastElements(from(responseEpisodeApi).getList("characters"));

        String responseLastCharacterApi = characterApi.getSingleCharacter(lastCharacterId);

        Assertions.assertEquals(from(responseLastCharacterApi).getString("species"),
                from(responseCharacterApi).getString("species"));
        Assertions.assertNotEquals(from(responseLastCharacterApi).getString("location.name"),
                from(responseCharacterApi).getString("location.name"));
    }

    @Test
    @Когда("Проверка API [Reqres]")
    public void checkApiReqres() throws IOException {
        hooks.initApiReqres();

        String name = "Tomato";
        String job = "Eat maket";

        JSONObject requestBody = functions.parseJSONFile("src/test/resources/iFellow/json/user.json");
        requestBody.putOpt("name", name);
        requestBody.putOnce("job", job);

        String response = usersApi.postCreate(requestBody);

        Assertions.assertEquals(from(response).getString("name"), name);
        Assertions.assertEquals(from(response).getString("job"), job);
    }

}