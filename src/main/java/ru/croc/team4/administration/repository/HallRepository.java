package ru.croc.team4.administration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.croc.team4.administration.domain.Hall;

import java.util.UUID;

public interface HallRepository extends JpaRepository<Hall, UUID> {
}