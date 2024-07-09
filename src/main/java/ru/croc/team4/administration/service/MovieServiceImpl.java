package ru.croc.team4.administration.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.croc.team4.administration.domain.Movie;
import ru.croc.team4.administration.dto.MovieDto;
import ru.croc.team4.administration.dto.MovieResponseDto;
import ru.croc.team4.administration.mapper.MovieMapper;
import ru.croc.team4.administration.mapper.MovieMapperImpl;
import ru.croc.team4.administration.repository.MovieRepository;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
        this.movieMapper = new MovieMapperImpl();
    }

    @Override
    public Iterable<MovieResponseDto> findAllMovies() {
        return movieMapper.movieListToMovieResponseDto(movieRepository.findAll());
    }

    @Override
    public MovieResponseDto createMovie(MovieDto movieDto) {
        Movie movie = movieRepository.save(new Movie(movieDto.title(), movieDto.duration(), movieDto.description()));
        return movieMapper.movieToResponseDto(movie);
    }

    @Override
    public Optional<Movie> findMovie(UUID movieId) {
        return movieRepository.findById(movieId);
    }

    @Override
    public void updateMovie(UUID movieId, String title, Duration duration, String description) {
        movieRepository.findById(movieId).ifPresentOrElse(movie -> {
            movie.setTitle(title);
            movie.setDuration(duration);
            movie.setDescription(description);
        }, () -> {
            throw new NoSuchElementException("Movie not found");
        });
    }

    @Override
    public void deleteMovie(UUID movieId) {
        movieRepository.deleteById(movieId);
    }
}
