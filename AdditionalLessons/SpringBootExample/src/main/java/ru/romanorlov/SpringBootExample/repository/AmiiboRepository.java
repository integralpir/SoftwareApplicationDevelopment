package ru.romanorlov.SpringBootExample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.romanorlov.SpringBootExample.model.entity.Amiibo;

public interface AmiiboRepository extends JpaRepository<Amiibo, Integer> {
}
