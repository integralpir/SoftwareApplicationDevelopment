package ru.romanorlov.relational_data_model.model.request;

import java.util.List;

public class AmiiboApiResponse {
    private List<AmiiboApiInfo> amiibo;

    public AmiiboApiResponse(List<AmiiboApiInfo> amiibo) {
        this.amiibo = amiibo;
    }

    public AmiiboApiResponse() {
    }

    public List<AmiiboApiInfo> getAmiibo() {
        return amiibo;
    }

    public void setAmiibo(List<AmiiboApiInfo> amiibo) {
        this.amiibo = amiibo;
    }
}
