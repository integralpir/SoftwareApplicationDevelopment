package ru.romanorlov.relational_data_model.repository;

import ru.romanorlov.relational_data_model.model.entity.Amiibo;
import ru.romanorlov.relational_data_model.model.entity.AmiiboSeries;
import ru.romanorlov.relational_data_model.model.entity.GameSeries;

import java.util.List;

public interface ApplicationRepository {
    void insertGameSeries(GameSeries gameSeries);
    void insertGameSeries(List<GameSeries> gameSeriesList);
    GameSeries findGameSeriesById(String title);

    void insertAmiiboSeries(AmiiboSeries amiiboSeries);
    void insertAmiiboSeries(List<AmiiboSeries> amiiboSeriesList);
    AmiiboSeries findAmiiboSeriesByTitle(String title);

    void insertAmiibo(Amiibo amiibo);
    void insertAmiibo(List<Amiibo> amiiboList);
    Amiibo findAmiiboById(int id);

    void deleteInfoFromTables();
}
