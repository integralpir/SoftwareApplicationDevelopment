package ru.romanorlov.SpringBootExample.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.romanorlov.SpringBootExample.model.request.AmiiboApiResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class AmiiboClientImpl implements AmiiboClient {
    private final WebClient webClient;

    @Override
    public AmiiboApiResponse getAmiibosFromInternalApi() {
        return webClient
                .method(HttpMethod.GET)
                .uri("https://amiiboapi.com/api/amiibo/")
                .retrieve()
                .bodyToMono(AmiiboApiResponse.class)
                .block();
    }
}
