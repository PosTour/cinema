package ru.croc.team4.administration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.croc.team4.administration.domain.Movie;

import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {
    public Movie findByTitle(String title);
}
