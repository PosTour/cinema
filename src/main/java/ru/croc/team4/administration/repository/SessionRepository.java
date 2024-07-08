package ru.croc.team4.administration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.croc.team4.administration.domain.Session;

import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {
}
