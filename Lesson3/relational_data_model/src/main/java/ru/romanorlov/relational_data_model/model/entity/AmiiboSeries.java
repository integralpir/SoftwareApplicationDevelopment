package ru.romanorlov.relational_data_model.model.entity;

public class AmiiboSeries {
    private String title;
    private String gameSeriesTitle;

    public AmiiboSeries(String title, String gameSeriesTitle) {
        this.title = title;
        this.gameSeriesTitle = gameSeriesTitle;
    }

    public AmiiboSeries() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGameSeriesTitle() {
        return gameSeriesTitle;
    }

    public void setGameSeriesTitle(String gameSeriesTitle) {
        this.gameSeriesTitle = gameSeriesTitle;
    }
}
