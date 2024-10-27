package ru.romanorlov.document_oriented_data_model.model.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("amiibos")
public class Amiibo {
    @Id
    private String id;
    @Field("amiibos_series")
    private String amiiboSeries;
    @Field("character")
    private String character;
    @Field("game_series")
    private String gameSeries;
    @Field("image")
    private String image;
    @Field("name")
    private String name;
    @Field("type")
    private String type;

    public Amiibo(String amiiboSeries, String character, String gameSeries, String image, String name, String type) {
        this.amiiboSeries = amiiboSeries;
        this.character = character;
        this.gameSeries = gameSeries;
        this.image = image;
        this.name = name;
        this.type = type;
    }

    public Amiibo() {
    }

    public String getAmiiboSeries() {
        return amiiboSeries;
    }

    public void setAmiiboSeries(String amiiboSeries) {
        this.amiiboSeries = amiiboSeries;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getGameSeries() {
        return gameSeries;
    }

    public void setGameSeries(String gameSeries) {
        this.gameSeries = gameSeries;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Amiibo{" + "id='" + id + '\'' +
                ", amiiboSeries='" + amiiboSeries + '\'' +
                ", character='" + character + '\'' +
                ", gameSeries='" + gameSeries + '\'' +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
