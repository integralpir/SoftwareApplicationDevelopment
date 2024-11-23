package ru.romanorlov.SpringBootExample.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "amiibo_series")
public class AmiiboSeries {
    @Id
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "game_series_title", nullable = false)
    private String gameSeriesTitle;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "amiibo_series_title")
    private List<Amiibo> amiibos;
}
