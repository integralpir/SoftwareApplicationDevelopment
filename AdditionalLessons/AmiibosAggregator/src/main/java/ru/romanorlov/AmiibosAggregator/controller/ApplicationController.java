package ru.romanorlov.AmiibosAggregator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.romanorlov.AmiibosAggregator.model.entity.AmiiboSeries;
import ru.romanorlov.AmiibosAggregator.model.response.AmiiboRs;
import ru.romanorlov.AmiibosAggregator.model.response.AmiiboSeriesRs;
import ru.romanorlov.AmiibosAggregator.model.response.GameSeriesRs;
import ru.romanorlov.AmiibosAggregator.service.ApplicationService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "${app.name}/v1")
public class ApplicationController {
    private final ApplicationService service;

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/game_series")
    public List<GameSeriesRs> gameSeries() {
        return service.getAllGameSeries();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/game_series", params = {"title", "full"})
    public GameSeriesRs gameSeries(@RequestParam(value = "title") String title,
                                   @RequestParam(value = "full") Boolean fullnessFlag) {
        return service.getGameSeriesByTitle(title, fullnessFlag);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/amiibo_series")
    public List<AmiiboSeriesRs> amiiboSeries() {
        return service.getAllAmiiboSeries();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/amiibo_series", params = {"title", "full"})
    public AmiiboSeriesRs amiiboSeries(@RequestParam(value = "title") String title,
                                       @RequestParam(value = "full") Boolean fullnessFlag) {
        return service.getAmiiboSeriesByTitle(title, fullnessFlag);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/amiibo")
    public List<AmiiboRs> amiiboRs() {
        return service.getAllAmiibos();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/amiibo", params = {"character"})
    public List<AmiiboRs> amiiboRsByCharacter(@RequestParam(name = "character") String character) {
        return service.getAmiibosByCharacter(character);
    }
}
