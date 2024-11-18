package ru.romanorlov.amiibo_aggregator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.romanorlov.amiibo_aggregator.model.entity.Amiibo;
import ru.romanorlov.amiibo_aggregator.service.ApplicationService;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/amiibo_aggregator/api/")
public class ApplicationController {

    private final ApplicationService service;
    private final Logger applicationLogger;

    @Autowired
    public ApplicationController(ApplicationService service, Logger applicationLogger) {
        this.service = service;
        this.applicationLogger = applicationLogger;
    }

    @GetMapping("/amiibo")
    public ResponseEntity<Amiibo> amiibo(@RequestHeader(name = "user") String user,
                                         @RequestParam(name = "character") String character) {
        Amiibo amiibo = service.getAmiiboByCharacter(character);

        applicationLogger.info("Пользователь " + user + " ищет амиибо по названию персонажа.");

        return new ResponseEntity<>(amiibo, HttpStatus.OK);
    }

    @GetMapping("/amiibo")
    public ResponseEntity<List<Amiibo>> amiibo(@RequestHeader(name = "user") String user) {
        List<Amiibo> amiibos = service.getAllAmiibos();

        applicationLogger.info("Пользователь " + user + " ищет амиибо по названию персонажа.");

        return new ResponseEntity<>(amiibos, HttpStatus.OK);
    }

}

