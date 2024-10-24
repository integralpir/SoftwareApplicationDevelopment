package ru.romanorlov.relational_data_model.model.entity;

public class AmiiboSeries {
    private int id;
    private int gameSeriesId;
    private String title;

    public AmiiboSeries(int id, int gameSeriesId, String title) {
        this.id = id;
        this.gameSeriesId = gameSeriesId;
        this.title = title;
    }

    public AmiiboSeries() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGameSeriesId() {
        return gameSeriesId;
    }

    public void setGameSeriesId(int gameSeriesId) {
        this.gameSeriesId = gameSeriesId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
