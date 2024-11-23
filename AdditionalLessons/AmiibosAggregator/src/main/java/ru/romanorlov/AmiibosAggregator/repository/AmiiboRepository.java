package ru.romanorlov.AmiibosAggregator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.romanorlov.AmiibosAggregator.model.entity.Amiibo;

import java.util.List;

public interface AmiiboRepository extends JpaRepository<Amiibo, Integer> {
    List<Amiibo> findAmiibosByCharacter(String character);
}
