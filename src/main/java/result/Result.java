package result;

import com.fasterxml.jackson.annotation.JsonProperty;
import gameModel.Developer;
import gameModel.Editor;
import gameModel.Platform;

public class Result {
    private int id;
    private String name;
    private String backgroundImage;
    private String description;
    private double rating;
    private Platform[] platforms;
    private Editor[] publishers;
    private Developer[] developers;

    // Getters and setters

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("background_image")
    public String getBackgroundImage() {
        return backgroundImage;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("rating")
    public double getRating() {
        return rating;
    }

    @JsonProperty("platforms")
    public Platform[] getPlatforms() {
        return platforms;
    }

    @JsonProperty("publishers")
    public Editor[] getPublishers(){
        return publishers;
    }

    @JsonProperty("developers")
    public Developer[] getDevelopers() {
        return developers;
    }

}