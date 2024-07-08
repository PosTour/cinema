package ru.croc.team4.administration.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.croc.team4.administration.domain.Movie;
import ru.croc.team4.administration.dto.MovieDto;
import ru.croc.team4.administration.repository.MovieRepository;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements  MovieService{

    private final MovieRepository movieRepository;

    @Override
    public Movie createMovie(MovieDto movie) {

        return null;
    }

    @Override
    public Optional<Movie> findMovie(UUID movieId) {
        return Optional.empty();
    }

    @Override
    public void updateMovie(UUID movieId, Movie movie) {

    }

    @Override
    public void deleteMovie(UUID movieId) {

    }
}
