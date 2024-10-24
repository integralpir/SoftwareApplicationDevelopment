package ru.romanorlov.relational_data_model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.romanorlov.relational_data_model.service.ApplicationService;

@RestController
@RequestMapping("/relational_data_app/api/")
public class ApplicationController {

    private final ApplicationService service;

    @Autowired
    public ApplicationController(ApplicationService service) {
        this.service = service;
    }

    @GetMapping(value = "test")
    public ResponseEntity<Void> test() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

