package ru.croc.team4.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.croc.team4.cinema.domain.Row;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RowRepository extends JpaRepository<Row, UUID> {
    List<Row> findAllBySession_id(UUID cinemaId);
    Row getRowById(UUID id);
}
