package ru.romanorlov.relational_data_model.model.request;

import java.util.List;

public class AmiiboApiResponse {
    private List<AmiiboInfo> amiibo;

    public AmiiboApiResponse(List<AmiiboInfo> amiibo) {
        this.amiibo = amiibo;
    }

    public AmiiboApiResponse() {
    }

    public List<AmiiboInfo> getAmiibo() {
        return amiibo;
    }

    public void setAmiibo(List<AmiiboInfo> amiibo) {
        this.amiibo = amiibo;
    }
}
