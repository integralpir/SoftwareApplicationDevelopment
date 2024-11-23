package ru.romanorlov.AmiibosAggregator.model.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AmiiboApiResponse {
    private List<AmiiboApiInfo> amiibo;
}
