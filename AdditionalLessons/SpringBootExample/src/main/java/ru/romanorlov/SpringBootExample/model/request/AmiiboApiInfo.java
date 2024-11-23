package ru.romanorlov.SpringBootExample.model.request;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AmiiboApiInfo {
    private String amiiboSeries;
    private String character;
    private String gameSeries;
    private String head;
    private String image;
    private String name;
    private String tail;
    private String type;
}
