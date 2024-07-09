package ru.croc.team4.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.croc.team4.cinema.domain.Hall;

import java.util.UUID;

public interface HallRepository extends JpaRepository<Hall, UUID> {
}