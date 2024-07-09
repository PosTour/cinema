package ru.croc.team4.administration.service;

import ru.croc.team4.administration.domain.Movie;
import ru.croc.team4.administration.dto.MovieDto;
import ru.croc.team4.administration.dto.MovieResponseDto;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

public interface MovieService {

    Iterable<MovieResponseDto> findAllMovies();

    MovieResponseDto createMovie(MovieDto movie);

    Optional<Movie> findMovie(UUID movieId);

    Optional<MovieResponseDto> updateMovie(UUID movieId, MovieDto movieDto);

    void deleteMovie(UUID movieId);
}
