package ru.croc.team4.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.croc.team4.cinema.domain.Row;

public interface RowRepository extends JpaRepository<Row, Integer> {
}
