package ru.romanorlov.AmiibosAggregator.config.web.integration;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Configuration
@ConfigurationProperties(value = "client.amiibo")
public class AmiiboApiProperties {
    @NotBlank
    private String baseUrl;
    @NotBlank
    private String amiiboPath;
}
