package ru.romanorlov.relational_data_model.model.request;

public class AmiiboApiInfo {
    private final String amiiboSeries;
    private final String character;
    private final String gameSeries;
    private final String head;
    private final String image;
    private final String name;
    private final String tail;
    private final String type;

    public AmiiboApiInfo(String amiiboSeries, String character, String gameSeries, String head, String image, String name, String tail, String type) {
        this.amiiboSeries = amiiboSeries;
        this.character = character;
        this.gameSeries = gameSeries;
        this.head = head;
        this.image = image;
        this.name = name;
        this.tail = tail;
        this.type = type;
    }

    public String getAmiiboSeries() {
        return amiiboSeries;
    }

    public String getCharacter() {
        return character;
    }

    public String getGameSeries() {
        return gameSeries;
    }

    public String getHead() {
        return head;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getTail() {
        return tail;
    }

    public String getType() {
        return type;
    }

    public String toString() {
        return "AmiiboInfo{" + "amiboSeries='" + amiiboSeries + '\'' +
                ", character='" + character + '\'' +
                ", gameSeries='" + gameSeries + '\'' +
                ", head='" + head + '\'' +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", tail='" + tail + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
