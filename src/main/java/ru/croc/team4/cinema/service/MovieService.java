package ru.croc.team4.cinema.service;

import ru.croc.team4.cinema.domain.Movie;
import ru.croc.team4.cinema.dto.MovieDto;
import ru.croc.team4.cinema.dto.MovieResponseDto;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

public interface MovieService {

    Iterable<MovieResponseDto> findAllMovies();
    MovieResponseDto createMovie(MovieDto movie);

    Optional<Movie> findMovie(UUID movieId);

    Optional<MovieResponseDto> updateMovie(UUID movieId, String title, Duration duration, String description);

    void deleteMovie(UUID movieId);
}
