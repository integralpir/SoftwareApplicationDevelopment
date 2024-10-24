package ru.romanorlov.relational_data_model.model.entity;

public class GameSeries {
    private int id;
    private String title;

    public GameSeries(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public GameSeries() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
