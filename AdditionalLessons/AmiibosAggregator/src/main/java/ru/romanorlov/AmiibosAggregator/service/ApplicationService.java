package ru.romanorlov.AmiibosAggregator.service;

import ru.romanorlov.AmiibosAggregator.model.response.AmiiboRs;
import ru.romanorlov.AmiibosAggregator.model.response.AmiiboSeriesRs;
import ru.romanorlov.AmiibosAggregator.model.response.GameSeriesRs;

import java.util.List;

public interface ApplicationService {
    List<GameSeriesRs> getAllGameSeries();
    GameSeriesRs getGameSeriesByTitle(String title, Boolean fullnessFlag);

    List<AmiiboSeriesRs> getAllAmiiboSeries();
    AmiiboSeriesRs getAmiiboSeriesByTitle(String title, Boolean fullnessFlag);

    List<AmiiboRs> getAllAmiibos();
    List<AmiiboRs> getAmiibosByCharacter(String character);
}
