package com.example.sae_201;

import javafx.scene.paint.Color;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Tag implements Serializable {
    private String name;
    private String description;
    @JsonIgnore
    private transient Color color; // Color cannot be directly serialized

    public Tag() {
        // Default constructor needed for Jackson
    }

    public Tag(String name, String description, Color color) {
        this.name = name;
        this.description = description;
        this.color = color;
    }

    // Getters and setters for name, description, and color

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnore
    public Color getColor() {
        return color;
    }

    @JsonIgnore
    public void setColor(Color color) {
        this.color = color;
    }

    @JsonProperty("color")
    public String getColorAsString() {
        return color.toString();
    }

    @JsonProperty("color")
    public void setColorAsString(String colorString) {
        this.color = Color.valueOf(colorString);
    }
}
