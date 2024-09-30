package iFellow.functions;

import java.util.List;

public class Functions {
    public Integer getIdLastElements(List<String> Elements) {
        String lastElement = Elements.get(Elements.size() - 1);
        return Integer.parseInt(lastElement.substring(lastElement.lastIndexOf("/") + 1));
    }
}