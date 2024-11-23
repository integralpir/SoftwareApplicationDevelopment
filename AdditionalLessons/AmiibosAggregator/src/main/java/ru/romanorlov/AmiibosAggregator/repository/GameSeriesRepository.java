package ru.romanorlov.AmiibosAggregator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.romanorlov.AmiibosAggregator.model.entity.GameSeries;

public interface GameSeriesRepository extends JpaRepository<GameSeries, String> {
}
