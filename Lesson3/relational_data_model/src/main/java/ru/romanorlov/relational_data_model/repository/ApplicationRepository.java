package ru.romanorlov.relational_data_model.repository;

import ru.romanorlov.relational_data_model.model.entity.GameSeries;

import java.util.List;

public interface ApplicationRepository {
    void loadData();

    void insertGameSeries(GameSeries gameSeries);
    void insertGameSeries(List<GameSeries> gameSeries);
    GameSeries findById(int id);
}
