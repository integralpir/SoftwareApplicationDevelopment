package ru.romanorlov.document_oriented_data_model.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.romanorlov.document_oriented_data_model.client.AmiiboClient;
import ru.romanorlov.document_oriented_data_model.model.document.Amiibo;
import ru.romanorlov.document_oriented_data_model.model.request.AmiiboApiResponse;
import ru.romanorlov.document_oriented_data_model.model.request.AmiiboApiInfo;
import ru.romanorlov.document_oriented_data_model.repository.ApplicationRepository;

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
        repository.deleteAmiiboDocuments();

        applicationLogger.info("Запрос данных из API.");
        AmiiboApiResponse response = client.getAmiibos();
        List<AmiiboApiInfo> amiibosApi = response.getAmiibo();
        List<Amiibo> amiiboDocs = new ArrayList<>();

        applicationLogger.info("Полученные данные.");
        for (AmiiboApiInfo amiibo : amiibosApi) {
            applicationLogger.info(amiibo.toString());
            amiiboDocs.add(new Amiibo(amiibo.getAmiiboSeries(), amiibo.getCharacter(), amiibo.getGameSeries(), amiibo.getImage(), amiibo.getName(), amiibo.getType()));
        }

        applicationLogger.info("Загрузка информации в БД...");
        repository.insert(amiiboDocs);

        applicationLogger.info("Информация загружена.");
    }
}
