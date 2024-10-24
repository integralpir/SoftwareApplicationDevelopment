package ru.romanorlov.relational_data_model.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.romanorlov.relational_data_model.client.AmiiboClient;
import ru.romanorlov.relational_data_model.model.request.AmiiboApiResponse;
import ru.romanorlov.relational_data_model.model.request.AmiiboInfo;
import ru.romanorlov.relational_data_model.repository.ApplicationRepositoryImpl;

import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final AmiiboClient client;
    private final ApplicationRepositoryImpl repository;

    @Autowired
    public ApplicationServiceImpl(AmiiboClient client, ApplicationRepositoryImpl repository) {
        this.client = client;
        this.repository = repository;
    }

    @Override
    @PostConstruct
    public void loadDataFromApi() {
        AmiiboApiResponse response = client.getAmiibos();
        List<AmiiboInfo> amiibos = response.getAmiibo();

        for (AmiiboInfo amiibo : amiibos) {
            System.out.println(amiibo.toString());
        }

        repository.loadData();
    }
}
