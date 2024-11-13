package ru.romanorlov.amiibo_aggregator.model.entity;

public class GameSeries {
    private String title;

    public GameSeries(String title) {
        this.title = title;
    }

    public GameSeries() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
