package iFellow.functions;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Functions {
    public Integer getIdLastElements(List<String> Elements) {
        String lastElement = Elements.get(Elements.size() - 1);
        return Integer.parseInt(lastElement.substring(lastElement.lastIndexOf("/") + 1));
    }

    public JSONObject parseJSONFile(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        return new JSONObject(content);
    }
}