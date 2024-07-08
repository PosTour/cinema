package ru.croc.team4.administration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.croc.team4.administration.domain.Row;

public interface RowRepository extends JpaRepository<Row, Integer> {
}
