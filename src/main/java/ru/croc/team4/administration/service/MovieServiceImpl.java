package ru.croc.team4.administration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.croc.team4.administration.domain.Movie;
import ru.croc.team4.administration.dto.MovieDto;
import ru.croc.team4.administration.dto.MovieResponseDto;
import ru.croc.team4.administration.mapper.MovieMapper;
import ru.croc.team4.administration.mapper.MovieMapperImpl;
import ru.croc.team4.administration.repository.MovieRepository;

import java.time.Duration;
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
        Movie movie = movieMapper.movieDtoToMovie(movieDto);
        return movieMapper.movieToResponseDto(movie);
    }

    @Override
    public Optional<Movie> findMovie(UUID movieId) {
        return movieRepository.findById(movieId);
    }

    @Override
    public Optional<MovieResponseDto> updateMovie(UUID movieId, MovieDto movieDto) {
        Movie movie = movieMapper.movieDtoToMovie(movieDto);
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if (movieOptional.isEmpty()) {
            return Optional.empty();
        }
        movieOptional.get().setTitle(movie.getTitle());
        movieOptional.get().setDuration(movie.getDuration());
        movieOptional.get().setDescription(movie.getDescription());
        return Optional.ofNullable(movieMapper.movieToResponseDto(movie));
    }

    @Override
    public void deleteMovie(UUID movieId) {
        movieRepository.deleteById(movieId);
    }
}
