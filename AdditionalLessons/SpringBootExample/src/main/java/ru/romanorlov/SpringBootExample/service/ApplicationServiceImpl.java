package ru.romanorlov.SpringBootExample.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.romanorlov.SpringBootExample.client.AmiiboClientImpl;
import ru.romanorlov.SpringBootExample.model.entity.Amiibo;
import ru.romanorlov.SpringBootExample.model.entity.AmiiboSeries;
import ru.romanorlov.SpringBootExample.model.entity.GameSeries;
import ru.romanorlov.SpringBootExample.model.request.AmiiboApiInfo;
import ru.romanorlov.SpringBootExample.model.request.AmiiboApiResponse;
import ru.romanorlov.SpringBootExample.repository.AmiiboRepository;
import ru.romanorlov.SpringBootExample.repository.AmiiboSeriesRepository;
import ru.romanorlov.SpringBootExample.repository.GameSeriesRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final AmiiboClientImpl client;
    private final AmiiboRepository amiiboRepository;
    private final GameSeriesRepository gameSeriesRepository;
    private final AmiiboSeriesRepository amiiboSeriesRepository;

    @PostConstruct
    private void preloadingData() {
        log.info("Старт загрузки данных из API.");

        log.info("Чистим БД от старых данных.");
        gameSeriesRepository.deleteAll();
        amiiboSeriesRepository.deleteAll();
        amiiboRepository.deleteAll();

        log.info("Запрос данных из API.");
        AmiiboApiResponse response = client.getAmiibosFromInternalApi();
        List<AmiiboApiInfo> amiibosApi = response.getAmiibo();

        log.info("Сохранение данных в репозиторий.");
        log.info("Сохранение информации о игровых сериях.");
        loadGameSeriesInfo(amiibosApi);
        log.info("Сохранение информации о сериях amiibo.");
        loadAmiiboSeriesInfo(amiibosApi);
        log.info("Сохранение информации о amiibo.");
        loadAmiiboInfo(amiibosApi);

        log.info("Информация загружена.");
    }

    private void loadGameSeriesInfo(List<AmiiboApiInfo> amiibosApi) {
        List<GameSeries> gameSeriesList = new ArrayList<>();

        for (AmiiboApiInfo amiiboApi : amiibosApi) {
            GameSeries gameSeries = new GameSeries();
            gameSeries.setTitle(amiiboApi.getGameSeries());
            gameSeriesList.add(gameSeries);
        }

        gameSeriesRepository.saveAll(gameSeriesList);
    }

    private void loadAmiiboSeriesInfo(List<AmiiboApiInfo> amiibosApi) {
        List<AmiiboSeries> amiiboSeriesList = new ArrayList<>();

        for (AmiiboApiInfo amiiboApi : amiibosApi) {
            AmiiboSeries amiiboSeries = new AmiiboSeries();
            amiiboSeries.setTitle(amiiboApi.getAmiiboSeries());
            amiiboSeries.setGameSeriesTitle(amiiboApi.getGameSeries());
            amiiboSeriesList.add(amiiboSeries);
        }

        amiiboSeriesRepository.saveAll(amiiboSeriesList);
    }

    private void loadAmiiboInfo(List<AmiiboApiInfo> amiibosApi) {
        List<Amiibo> amiibos = new ArrayList<>();

        for (AmiiboApiInfo amiiboApi : amiibosApi) {
            Amiibo amiibo = new Amiibo();
            amiibo.setAmiiboSeriesTitle(amiiboApi.getAmiiboSeries());
            amiibo.setGameSeriesTitle(amiiboApi.getGameSeries());
            amiibo.setCharacter(amiiboApi.getCharacter());
            amiibo.setImageLink(amiiboApi.getImage());
            amiibo.setName(amiiboApi.getName());
            amiibo.setType(amiiboApi.getType());

            amiibos.add(amiibo);
        }

        amiiboRepository.saveAll(amiibos);
    }
}
