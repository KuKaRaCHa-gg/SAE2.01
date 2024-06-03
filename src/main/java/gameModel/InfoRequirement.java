package gameModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InfoRequirement {

    @JsonProperty("minimum")
    private String minimum;
    @JsonProperty("recommended")
    private String recommended;
    @JsonProperty("name")
    private String name;

    public String getMinimum() {
        return minimum;
    }

    public String getRecommended() {
        return recommended;
    }

    public String getName() {
        return name;
    }
}
