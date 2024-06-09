package gameModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Editor {
    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }
}
