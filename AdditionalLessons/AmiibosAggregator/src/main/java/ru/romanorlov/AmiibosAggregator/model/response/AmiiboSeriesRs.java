package ru.romanorlov.AmiibosAggregator.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AmiiboSeriesRs(String title,
                             String gameSeriesTitle,
                             List<AmiiboRs> amiibos) {
}
