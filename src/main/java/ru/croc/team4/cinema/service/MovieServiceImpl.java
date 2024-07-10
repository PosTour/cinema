package ru.croc.team4.cinema.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.croc.team4.cinema.domain.Movie;
import ru.croc.team4.cinema.dto.AuditDto;
import ru.croc.team4.cinema.dto.MovieDto;
import ru.croc.team4.cinema.dto.MovieResponseDto;
import ru.croc.team4.cinema.mapper.MovieMapper;
import ru.croc.team4.cinema.mapper.MovieMapperImpl;
import ru.croc.team4.cinema.repository.MovieRepository;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    //private final AuditSenderService auditSenderService;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
        //this.auditSenderService = auditSenderService;
        this.movieMapper = new MovieMapperImpl();
    }

    @Override
    public Iterable<MovieResponseDto> findAllMovies() {
        return movieMapper.movieListToMovieResponseDto(movieRepository.findAll());
    }

    @Override
    public MovieResponseDto createMovie(MovieDto movieDto) {
        Movie movie = movieMapper.movieDtoToMovie(movieDto);
        MovieResponseDto response = movieMapper.movieToResponseDto(movie);
        //AuditDto auditDto = new AuditDto( response.id(), "create", "movie", new Date(), movie.toString());
        //auditSenderService.sendToAudit(auditDto);
        return response;
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
