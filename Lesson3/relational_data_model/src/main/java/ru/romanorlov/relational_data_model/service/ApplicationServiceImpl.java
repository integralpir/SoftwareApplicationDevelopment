package ru.romanorlov.relational_data_model.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.romanorlov.relational_data_model.client.AmiiboClient;
import ru.romanorlov.relational_data_model.model.entity.Amiibo;
import ru.romanorlov.relational_data_model.model.entity.AmiiboSeries;
import ru.romanorlov.relational_data_model.model.entity.GameSeries;
import ru.romanorlov.relational_data_model.model.request.AmiiboApiResponse;
import ru.romanorlov.relational_data_model.model.request.AmiiboApiInfo;
import ru.romanorlov.relational_data_model.repository.ApplicationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final AmiiboClient client;
    private final Logger applicationLogger;
    private final ApplicationRepository repository;

    @Autowired
    public ApplicationServiceImpl(AmiiboClient client, ApplicationRepository repository, Logger applicationLogger) {
        this.client = client;
        this.repository = repository;
        this.applicationLogger = applicationLogger;
    }

    @PostConstruct
    private void preloadingData() {
        applicationLogger.info("Старт загрузки данных из API.");

        applicationLogger.info("Чистим БД от старых данных.");
        repository.deleteInfoFromTables();

        applicationLogger.info("Запрос данных из API.");
        AmiiboApiResponse response = client.getAmiibos();
        List<AmiiboApiInfo> amiibosApi = response.getAmiibo();

        applicationLogger.info("Полученные данные.");
        for (AmiiboApiInfo amiibo : amiibosApi) {
            applicationLogger.info(amiibo.toString());
        }

        applicationLogger.info("Сохранение данных в репозиторий.");
        applicationLogger.info("Сохранение информации о игровых сериях.");
        loadGameSeriesInfo(amiibosApi);
        applicationLogger.info("Сохранение информации о сериях amiibo.");
        loadAmiiboSeriesInfo(amiibosApi);
        applicationLogger.info("Сохранение информации о amiibo.");
        loadAmiiboInfo(amiibosApi);

        applicationLogger.info("Информация загружена.");
    }

    private void loadGameSeriesInfo(List<AmiiboApiInfo> amiibosApi) {
        List<GameSeries> gameSeriesList = new ArrayList<>();

        for (AmiiboApiInfo amiiboApi : amiibosApi) {
            GameSeries gameSeries = new GameSeries();
            gameSeries.setTitle(amiiboApi.getGameSeries());
            gameSeriesList.add(gameSeries);
        }

        repository.insertGameSeries(gameSeriesList);
    }

    private void loadAmiiboSeriesInfo(List<AmiiboApiInfo> amiibosApi) {
        List<AmiiboSeries> amiiboSeriesList = new ArrayList<>();

        for (AmiiboApiInfo amiiboApi : amiibosApi) {
            AmiiboSeries amiiboSeries = new AmiiboSeries();
            amiiboSeries.setTitle(amiiboApi.getAmiiboSeries());
            amiiboSeries.setGameSeriesTitle(amiiboApi.getGameSeries());
            amiiboSeriesList.add(amiiboSeries);
        }

        repository.insertAmiiboSeries(amiiboSeriesList);
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

        repository.insertAmiibo(amiibos);
    }


}
