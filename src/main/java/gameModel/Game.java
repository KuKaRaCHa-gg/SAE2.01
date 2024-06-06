package gameModel;

import java.io.Serializable;
import java.util.Comparator;

public class Game implements Comparator<Game>, Serializable {

    private String name;
    private String imageURL;
    private String description;
    private int id;
    private String rate;

    public Game() {
        super();
        this.name = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getRate(){
        return rate;
    }

    public void setRate(String rate){
        this.rate = rate;
    }

    @Override
    public String toString() {
        return name + "\n" + description + "\n" + rate;
    }

    @Override
    public int compare(Game o1, Game o2) {
        return o1.name.compareTo(o2.name);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }
}

