package gameModel;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Platform {
    @JsonProperty("platform")
    private InfoPlatform platform;
    @JsonProperty("requirements")
    private InfoRequirement requirement;

    public String getPlatformName(){
        if (platform == null) {
            return "";
        }
        return platform.getName();
    }

    public String getRequirementMinimum(){
        if (requirement == null) {
            return "";
        }
        return requirement.getMinimum();
    }

    public String getRequirementRecommended(){
        if (requirement == null) {
            return "";
        }
        return requirement.getRecommended();
    }

    public String getRequirementName(){
        if (requirement == null) {
            return "";
        }
        return requirement.getName();
    }
}

