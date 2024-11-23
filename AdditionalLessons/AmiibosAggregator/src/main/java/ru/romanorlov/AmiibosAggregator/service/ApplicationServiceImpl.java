package ru.romanorlov.AmiibosAggregator.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.romanorlov.AmiibosAggregator.client.AmiiboClientImpl;
import ru.romanorlov.AmiibosAggregator.model.entity.Amiibo;
import ru.romanorlov.AmiibosAggregator.model.entity.AmiiboSeries;
import ru.romanorlov.AmiibosAggregator.model.entity.GameSeries;
import ru.romanorlov.AmiibosAggregator.model.request.AmiiboApiInfo;
import ru.romanorlov.AmiibosAggregator.model.request.AmiiboApiResponse;
import ru.romanorlov.AmiibosAggregator.model.response.AmiiboRs;
import ru.romanorlov.AmiibosAggregator.model.response.AmiiboSeriesRs;
import ru.romanorlov.AmiibosAggregator.model.response.GameSeriesRs;
import ru.romanorlov.AmiibosAggregator.repository.AmiiboRepository;
import ru.romanorlov.AmiibosAggregator.repository.AmiiboSeriesRepository;
import ru.romanorlov.AmiibosAggregator.repository.GameSeriesRepository;

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
        amiiboRepository.deleteAll();
        amiiboSeriesRepository.deleteAll();
        gameSeriesRepository.deleteAll();

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

    @Override
    public List<GameSeriesRs> getAllGameSeries() {
        return gameSeriesRepository.findAll().stream()
                .map(repositoryGameSeries -> new GameSeriesRs(repositoryGameSeries.getTitle(), null, null))
                .toList();
    }

    @Override
    public GameSeriesRs getGameSeriesByTitle(String title, Boolean fullnessFlag) {
        return fullnessFlag ? getFullInfoAboutGameSeries(title) : getMainInfoAboutGameSeries(title);
    }

    private GameSeriesRs getMainInfoAboutGameSeries(String title) {
        GameSeries repositoryGameSeries = gameSeriesRepository.findById(title)
                .orElseThrow(() -> new RuntimeException("Unknown game series title: " + title));
        return new GameSeriesRs(repositoryGameSeries.getTitle(), null, null);
    }

    private GameSeriesRs getFullInfoAboutGameSeries(String title) {
        GameSeries repositoryGameSeries = gameSeriesRepository.findById(title)
                .orElseThrow(() -> new RuntimeException("Unknown game series title: " + title));

        String gameSeriesTitle = repositoryGameSeries.getTitle();
        List<AmiiboSeriesRs> amiiboSeriesList = repositoryGameSeries.getAmiiboSeries().stream()
                .map(repositoryAmiiboSeries -> new AmiiboSeriesRs(repositoryAmiiboSeries.getTitle(), null, null))
                .toList();
        List<AmiiboRs> amiibosList = repositoryGameSeries.getAmiibos().stream()
                .map(repositoryAmiibo -> new AmiiboRs(repositoryAmiibo.getAmiiboSeriesTitle(), null,
                        repositoryAmiibo.getCharacter(), repositoryAmiibo.getImageLink(),
                        repositoryAmiibo.getName(), repositoryAmiibo.getType()))
                .toList();

        return new GameSeriesRs(gameSeriesTitle, amiiboSeriesList, amiibosList);
    }

    @Override
    public List<AmiiboSeriesRs> getAllAmiiboSeries() {
        return amiiboSeriesRepository.findAll().stream()
                .map(repositoryAmiiboSeries -> new AmiiboSeriesRs(repositoryAmiiboSeries.getTitle(), null, null))
                .toList();
    }

    @Override
    public AmiiboSeriesRs getAmiiboSeriesByTitle(String title, Boolean fullnessFlag) {
        return fullnessFlag ? getFullInfoAboutAmiiboSeries(title) : getMainInfoAboutAmiiboSeries(title);
    }

    private AmiiboSeriesRs getMainInfoAboutAmiiboSeries(String title) {
        AmiiboSeries repositoryAmiiboSeries = amiiboSeriesRepository.findById(title)
                .orElseThrow(() -> new RuntimeException("Unknown game series title: " + title));
        return new AmiiboSeriesRs(repositoryAmiiboSeries.getTitle(), null, null);
    }

    private AmiiboSeriesRs getFullInfoAboutAmiiboSeries(String title) {
        AmiiboSeries repositoryAmiiboSeries = amiiboSeriesRepository.findById(title)
                .orElseThrow(() -> new RuntimeException("Unknown game series title: " + title));

        String amiiboSeriesTitle = repositoryAmiiboSeries.getTitle();
        String gameSeriesTitle = repositoryAmiiboSeries.getGameSeriesTitle();
        List<AmiiboRs> amiibosList = repositoryAmiiboSeries.getAmiibos().stream()
                .map(repositoryAmiibo -> new AmiiboRs(null, repositoryAmiibo.getGameSeriesTitle(),
                        repositoryAmiibo.getCharacter(), repositoryAmiibo.getImageLink(),
                        repositoryAmiibo.getName(), repositoryAmiibo.getType()))
                .toList();

        return new AmiiboSeriesRs(amiiboSeriesTitle, gameSeriesTitle, amiibosList);
    }

    @Override
    public List<AmiiboRs> getAllAmiibos() {
        return amiiboRepository.findAll().stream()
                .map(repositoryAmiibo -> new AmiiboRs(repositoryAmiibo.getAmiiboSeriesTitle(),
                        repositoryAmiibo.getGameSeriesTitle(), repositoryAmiibo.getCharacter(),
                        repositoryAmiibo.getImageLink(), repositoryAmiibo.getName(), repositoryAmiibo.getType()))
                .toList();
    }

    @Override
    public List<AmiiboRs> getAmiibosByCharacter(String character) {
        return amiiboRepository.findAmiibosByCharacter(character).stream()
                .map(repositoryAmiibo -> new AmiiboRs(repositoryAmiibo.getAmiiboSeriesTitle(),
                        repositoryAmiibo.getGameSeriesTitle(), repositoryAmiibo.getCharacter(),
                        repositoryAmiibo.getImageLink(), repositoryAmiibo.getName(), repositoryAmiibo.getType()))
                .toList();
    }
}
