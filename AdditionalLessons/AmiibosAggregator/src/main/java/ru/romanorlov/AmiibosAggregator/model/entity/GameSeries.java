package ru.romanorlov.AmiibosAggregator.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "game_series")
public class GameSeries {
    @Id
    @Column(name = "title", nullable = false)
    private String title;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "game_series_title")
    private List<AmiiboSeries> amiiboSeries;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "game_series_title")
    private List<Amiibo> amiibos;
}
