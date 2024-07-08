package ru.croc.team4.administration.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.croc.team4.administration.domain.Movie;
import ru.croc.team4.administration.dto.MovieDto;
import ru.croc.team4.administration.dto.MovieResponseDto;
import ru.croc.team4.administration.mapper.MovieMapper;
import ru.croc.team4.administration.service.MovieService;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@RestController("/api/movie/{id}")//TODO check regex for UUID
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @GetMapping()
    public ResponseEntity<MovieResponseDto> getMovie(@PathVariable UUID id) {
        Optional<Movie> movie = movieService.findMovie(id);
        if (movie.isEmpty()) {
            throw new NoSuchElementException("Movie not found"); //TODO error response entity OR exceptionHandler!
        }
        return ResponseEntity.ok(movieMapper.movieToResponseDto(movie.get()));
    }

    @PostMapping
    public ResponseEntity<MovieResponseDto> createMovie(@RequestBody MovieDto movieDto) {
        return ResponseEntity.ok(movieService.createMovie(movieDto));
    }

    @PutMapping()
    public ResponseEntity<Void> updateMovie(@PathVariable UUID id, @RequestBody MovieDto movieDto) {
        movieService.updateMovie(id, movieDto.title(), movieDto.duration(), movieDto.description());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteMovie(@PathVariable UUID id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
