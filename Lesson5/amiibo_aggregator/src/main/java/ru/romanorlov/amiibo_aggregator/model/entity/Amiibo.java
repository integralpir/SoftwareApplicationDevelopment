package ru.romanorlov.amiibo_aggregator.model.entity;

public class Amiibo {
    private int id;
    private String amiiboSeriesTitle;
    private String gameSeriesTitle;
    private String character;
    private String imageLink;
    private String name;
    private String type;

    public Amiibo(int id, String amiiboSeriesTitle, String gameSeriesTitle, String character, String imageLink, String name, String type) {
        this.id = id;
        this.amiiboSeriesTitle = amiiboSeriesTitle;
        this.gameSeriesTitle = gameSeriesTitle;
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

    public String getAmiiboSeriesTitle() {
        return amiiboSeriesTitle;
    }

    public void setAmiiboSeriesTitle(String amiiboSeriesTitle) {
        this.amiiboSeriesTitle = amiiboSeriesTitle;
    }

    public String getGameSeriesTitle() {
        return gameSeriesTitle;
    }

    public void setGameSeriesTitle(String gameSeriesTitle) {
        this.gameSeriesTitle = gameSeriesTitle;
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
