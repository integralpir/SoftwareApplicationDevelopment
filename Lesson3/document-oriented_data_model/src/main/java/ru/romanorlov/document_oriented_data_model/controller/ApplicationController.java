package ru.romanorlov.document_oriented_data_model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.romanorlov.document_oriented_data_model.service.ApplicationService;

@RestController
@RequestMapping("/document_oriented_data_model/api/")
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

