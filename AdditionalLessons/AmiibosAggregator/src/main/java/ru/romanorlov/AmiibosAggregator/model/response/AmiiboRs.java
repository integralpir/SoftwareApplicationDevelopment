package ru.romanorlov.AmiibosAggregator.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AmiiboRs(String amiiboSeriesTitle,
                       String gameSeriesTitle,
                       String character,
                       String imageLink,
                       String name,
                       String type) {
}
