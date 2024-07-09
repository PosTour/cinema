package ru.croc.team4.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.croc.team4.cinema.domain.Row;

import java.util.Optional;
import java.util.UUID;

public interface RowRepository extends JpaRepository<Row, Integer> {
    Optional<Row> findById(UUID id);
}
