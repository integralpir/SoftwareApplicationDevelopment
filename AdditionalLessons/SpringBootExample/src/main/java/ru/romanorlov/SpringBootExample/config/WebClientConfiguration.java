package ru.romanorlov.SpringBootExample.config;

import io.netty.channel.ChannelOption;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.boot.autoconfigure.codec.CodecProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class WebClientConfiguration {
    private final WebClientProperties webClientProperties;
    private final CodecProperties codecProperties;

    private static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            StringBuilder request = new StringBuilder()
                    .append("Request: ")
                    .append(clientRequest.method())
                    .append(" ")
                    .append(clientRequest.url())
                    .append(" ")
                    .append("Headers: ");

            clientRequest.headers().forEach((name, values) ->
                    values.forEach(value -> request.append(name).append(" : ").append(value).append(";")));

            log.info(request.toString());
            return Mono.just(clientRequest);
        });
    }

    private static ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            StringBuilder response = new StringBuilder()
                    .append("Response: ")
                    .append(clientResponse.statusCode())
                    .append(" ")
                    .append("Headers: ");

            clientResponse.headers().asHttpHeaders().forEach((name, values) ->
                    values.forEach(value -> response.append(name).append(" : ").append(value).append(";")));

            log.info(response.toString());
            return Mono.just(clientResponse);
        });
    }

    @Bean
    public WebClient webClient() {
        log.info("Configuring WebClient with maxMemorySize: {}", codecProperties.getMaxInMemorySize());
        return WebClient.builder()
                .codecs(configurer -> configurer.defaultCodecs()
                        .maxInMemorySize(Math.toIntExact(codecProperties.getMaxInMemorySize().toBytes())))
                .filters(exchangeFilterFunctions -> {
                    exchangeFilterFunctions.add(logRequest());
                    exchangeFilterFunctions.add(logResponse());
                })
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(new ReactorClientHttpConnector(createHttpClient()))
                .build();
    }

    private HttpClient createHttpClient() {
        return HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, webClientProperties.getConnectionTimeout())
                .responseTimeout(Duration.ofMillis(webClientProperties.getResponseTimeout()));
    }
}
