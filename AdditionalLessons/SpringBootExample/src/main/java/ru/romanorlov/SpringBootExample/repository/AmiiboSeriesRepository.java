package ru.romanorlov.SpringBootExample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.romanorlov.SpringBootExample.model.entity.AmiiboSeries;

public interface AmiiboSeriesRepository extends JpaRepository<AmiiboSeries, String> {
}
