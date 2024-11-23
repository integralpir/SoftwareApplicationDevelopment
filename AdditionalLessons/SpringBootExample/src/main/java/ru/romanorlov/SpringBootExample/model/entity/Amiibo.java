package ru.romanorlov.SpringBootExample.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "amiibo")
public class Amiibo {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "amiibo_series_title", nullable = false)
    private String amiiboSeriesTitle;

    @Column(name = "game_series_title", nullable = false)
    private String gameSeriesTitle;

    @Column(name = "character", nullable = false)
    private String character;

    @Column(name = "image_link", nullable = false)
    private String imageLink;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;
}
