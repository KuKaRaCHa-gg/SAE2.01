package result;

import gameModel.Platform;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Result {
    @JsonProperty("name")
    private String name;
    @JsonProperty("background_image")
    private String backgroundImage;
    @JsonProperty("slug")
    private String slug;
    @JsonProperty("metacritic")
    private String metacritic;
    @JsonProperty("playtime")
    private String playtime;
    @JsonProperty("rating")
    private String rating;
    @JsonProperty("platforms")
    private Platform[] platforms;
    @JsonProperty("id")
    private int id;
    @JsonProperty("description")
    private String description;


    public String getName() { return name; }
    public String getBackgroundImage() { return backgroundImage; }
    public String getSlug() { return slug; }
    public String getMetacritic() { return metacritic; }
    public String getPlaytime() { return playtime; }
    public String getRating() { return rating; }
    public Platform[] getPlatforms() { return platforms; }
    public int getId() { return id;}
    public String getDescription() { return description;}

}
