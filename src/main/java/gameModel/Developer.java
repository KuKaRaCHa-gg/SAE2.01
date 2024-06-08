package gameModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Developer {
    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }
}
