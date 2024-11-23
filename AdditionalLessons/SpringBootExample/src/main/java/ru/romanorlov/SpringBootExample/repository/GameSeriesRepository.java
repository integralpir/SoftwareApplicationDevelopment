package ru.romanorlov.SpringBootExample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.romanorlov.SpringBootExample.model.entity.GameSeries;

public interface GameSeriesRepository extends JpaRepository<GameSeries, String> {
}
