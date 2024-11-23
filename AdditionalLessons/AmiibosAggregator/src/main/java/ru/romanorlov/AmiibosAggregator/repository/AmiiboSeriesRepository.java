package ru.romanorlov.AmiibosAggregator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.romanorlov.AmiibosAggregator.model.entity.AmiiboSeries;

public interface AmiiboSeriesRepository extends JpaRepository<AmiiboSeries, String> {
}
