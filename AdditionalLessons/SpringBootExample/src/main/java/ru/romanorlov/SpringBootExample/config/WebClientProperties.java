package ru.romanorlov.SpringBootExample.config;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Configuration
@ConfigurationProperties(value = "client")
public class WebClientProperties {
    @NotNull
    private Integer connectionTimeout;
    @NotNull
    private Integer responseTimeout;
}
