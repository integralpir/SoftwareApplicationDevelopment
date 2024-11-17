package ru.romanorlov.amiibo_aggregator.service;

import ru.romanorlov.amiibo_aggregator.model.entity.Amiibo;

import java.util.List;

public interface ApplicationService {
    List<Amiibo> getAllAmiibos();

    Amiibo getAmiiboByCharacter(String character);
}
