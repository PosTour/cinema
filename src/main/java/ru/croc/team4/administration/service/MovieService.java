package ru.croc.team4.administration.service;

import ru.croc.team4.administration.domain.Movie;
import ru.croc.team4.administration.dto.MovieDto;

import java.util.Optional;
import java.util.UUID;

public interface MovieService {
    Movie createMovie(MovieDto movie);

    Optional<Movie> findMovie(UUID movieId);

    void updateMovie(UUID movieId, Movie movie);

    void deleteMovie(UUID movieId);
}
