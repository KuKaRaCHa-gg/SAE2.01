package result;

import com.fasterxml.jackson.annotation.JsonProperty;
import gameModel.Platform;

public class Result {
    private int id;
    private String name;
    private String backgroundImage;
    private String description;
    private double rating;
    private Platform[] platforms;

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

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("background_image")
    public String getBackgroundImage() {
        return backgroundImage;
    }

    @JsonProperty("background_image")
    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("rating")
    public double getRating() {
        return rating;
    }

    @JsonProperty("rating")
    public void setRating(double rating) {
        this.rating = rating;
    }
/*
    @JsonProperty("platforms")
    public Platform[] getPlatforms() {
        return platforms;
    }
/*
    @JsonProperty("platforms")
    public void setPlatforms(Platform[] platforms) {
        this.platforms = platforms;
    }

 */
}