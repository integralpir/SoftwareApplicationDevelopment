package ru.romanorlov.relational_data_model.model.entity;

public class Amiibo {
    private int id;
    private int amiiboSeriesId;
    private int gameSeriesId;
    private String character;
    private String imageLink;
    private String name;
    private String type;

    public Amiibo(int id, int amiiboSeriesId, int gameSeriesId, String character, String imageLink, String name, String type) {
        this.id = id;
        this.amiiboSeriesId = amiiboSeriesId;
        this.gameSeriesId = gameSeriesId;
        this.character = character;
        this.imageLink = imageLink;
        this.name = name;
        this.type = type;
    }

    public Amiibo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmiiboSeriesId() {
        return amiiboSeriesId;
    }

    public void setAmiiboSeriesId(int amiiboSeriesId) {
        this.amiiboSeriesId = amiiboSeriesId;
    }

    public int getGameSeriesId() {
        return gameSeriesId;
    }

    public void setGameSeriesId(int gameSeriesId) {
        this.gameSeriesId = gameSeriesId;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
