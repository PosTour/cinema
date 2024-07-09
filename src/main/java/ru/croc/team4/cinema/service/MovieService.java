package ru.croc.team4.cinema.service;

import ru.croc.team4.cinema.domain.Movie;
import ru.croc.team4.cinema.dto.MovieDto;
import ru.croc.team4.cinema.dto.MovieResponseDto;

import java.util.Optional;
import java.util.UUID;

public interface MovieService {

    Iterable<MovieResponseDto> findAllMovies();

    MovieResponseDto createMovie(MovieDto movie);

    Optional<Movie> findMovie(UUID movieId);

    Optional<MovieResponseDto> updateMovie(UUID movieId, MovieDto movieDto);

    void deleteMovie(UUID movieId);
}
