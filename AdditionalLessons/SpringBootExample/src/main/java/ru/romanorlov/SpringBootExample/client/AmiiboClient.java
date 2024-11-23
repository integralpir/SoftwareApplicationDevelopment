package ru.romanorlov.SpringBootExample.client;

import ru.romanorlov.SpringBootExample.model.request.AmiiboApiResponse;

public interface AmiiboClient {
    AmiiboApiResponse getAmiibosFromInternalApi();
}
