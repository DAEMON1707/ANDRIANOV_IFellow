package iFellow.tests;

import iFellow.api.CharacterApi;
import iFellow.api.EpisodeApi;
import iFellow.functions.Functions;
import iFellow.hooks.Hooks;
import iFellow.properties.Props;
import io.cucumber.java.ru.Когда;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.path.json.JsonPath.from;

public class TestApi {

    Functions functions = new Functions();
    CharacterApi characterApi = new CharacterApi();
    EpisodeApi episodeApi = new EpisodeApi();

    @Test
    @Когда("Проверка API")
    public void checkCreateUserApi () {
        String responseCharacterApi = characterApi.getSingleCharacter(Props.props.characterId());
        Integer lastEpisodeId = functions.getIdLastElements(from(responseCharacterApi).getList("episode"));

        String responseEpisodeApi = episodeApi.getSingleEpisode(lastEpisodeId);
        Integer lastCharacterId = functions.getIdLastElements(from(responseEpisodeApi).getList("characters"));

        String responseLastCharacterApi = characterApi.getSingleCharacter(lastCharacterId);

        Assertions.assertEquals(from(responseLastCharacterApi).getString("species"),
                from(responseCharacterApi).getString("species"));
        Assertions.assertNotEquals(from(responseLastCharacterApi).getString("location.name"),
                from(responseCharacterApi).getString("location.name"));
    }


}