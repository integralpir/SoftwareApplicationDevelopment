package ru.romanorlov.document_oriented_data_model.repository;

import ru.romanorlov.document_oriented_data_model.model.document.Amiibo;

import java.util.List;

public interface ApplicationRepository {
    void insert(Amiibo amiibo);
    void insert(List<Amiibo> amiibos);
    Amiibo findById(String id);
    List<Amiibo> findByName(String name);
    void deleteAmiiboDocuments();
}
