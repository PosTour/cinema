package ru.croc.team4.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.croc.team4.cinema.domain.Row;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RowRepository extends JpaRepository<Row, UUID> {
    List<Row> findAllBySession_id(UUID cinemaId);
    Row getRowById(UUID id);
}
