package result;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultGame {
    private Result[] results;

    // Constructeur public par défaut
    public ResultGame() {}

    // Constructeur annoté pour Jackson
    @JsonCreator
    public ResultGame(@JsonProperty("results") Result[] results) {
        this.results = results;
    }

    public Result[] getResults() {
        return results;
    }

    public void setResults(Result[] results) {
        this.results = results;
    }
}
