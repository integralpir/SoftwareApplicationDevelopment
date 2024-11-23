package ru.romanorlov.AmiibosAggregator.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.romanorlov.AmiibosAggregator.config.web.integration.AmiiboApiProperties;
import ru.romanorlov.AmiibosAggregator.model.request.AmiiboApiResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class AmiiboClientImpl implements AmiiboClient {
    private final AmiiboApiProperties properties;
    private final WebClient webClient;

    @Override
    public AmiiboApiResponse getAmiibosFromInternalApi() {
        String uri = properties.getBaseUrl() + properties.getAmiiboPath();
        return webClient
                .method(HttpMethod.GET)
                .uri(uri)
                .retrieve()
                .bodyToMono(AmiiboApiResponse.class)
                .block();
    }
}
