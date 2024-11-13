package ru.romanorlov.amiibo_aggregator.repository;

import ru.romanorlov.amiibo_aggregator.model.entity.Amiibo;
import ru.romanorlov.amiibo_aggregator.model.entity.AmiiboSeries;
import ru.romanorlov.amiibo_aggregator.model.entity.GameSeries;

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
