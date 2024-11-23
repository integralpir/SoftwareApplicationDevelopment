package ru.romanorlov.AmiibosAggregator.client;

import ru.romanorlov.AmiibosAggregator.model.request.AmiiboApiResponse;

public interface AmiiboClient {
    AmiiboApiResponse getAmiibosFromInternalApi();
}
