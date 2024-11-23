package ru.romanorlov.AmiibosAggregator.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record GameSeriesRs(String title,
                           List<AmiiboSeriesRs> amiiboSeries,
                           List<AmiiboRs> amiibos) {
}
