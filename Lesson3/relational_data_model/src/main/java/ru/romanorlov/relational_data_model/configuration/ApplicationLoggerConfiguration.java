package ru.romanorlov.relational_data_model.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class ApplicationLoggerConfiguration {

    @Bean
    public Logger applicationLogger() {
        return Logger.getLogger("relation_data_model_logger");
    }
}
