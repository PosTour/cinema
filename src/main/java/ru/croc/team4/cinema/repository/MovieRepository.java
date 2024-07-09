package ru.croc.team4.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.croc.team4.cinema.domain.Movie;

import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {
    public Movie findByTitle(String title);
}
