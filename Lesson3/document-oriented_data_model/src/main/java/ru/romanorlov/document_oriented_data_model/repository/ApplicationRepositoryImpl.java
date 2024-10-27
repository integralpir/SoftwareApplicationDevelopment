package ru.romanorlov.document_oriented_data_model.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ru.romanorlov.document_oriented_data_model.model.document.Amiibo;

import java.util.List;

@Repository
public class ApplicationRepositoryImpl implements ApplicationRepository {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public ApplicationRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void insert(Amiibo amiibo) {
        mongoTemplate.insert(amiibo);
    }

    @Override
    public void insert(List<Amiibo> amiibos) {
        mongoTemplate.insertAll(amiibos);
    }

    @Override
    public Amiibo findById(String id) {
        return mongoTemplate.findById(id, Amiibo.class);
    }

    @Override
    public List<Amiibo> findByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));

        return mongoTemplate.find(query, Amiibo.class);
    }

    @Override
    public void deleteAmiiboDocuments() {
        mongoTemplate.dropCollection(Amiibo.class);
    }
}
