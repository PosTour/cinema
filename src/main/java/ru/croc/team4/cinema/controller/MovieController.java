package ru.croc.team4.cinema.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.croc.team4.cinema.domain.Movie;
import ru.croc.team4.cinema.dto.MovieDto;
import ru.croc.team4.cinema.dto.MovieResponseDto;
import ru.croc.team4.cinema.mapper.MovieMapper;
import ru.croc.team4.cinema.mapper.MovieMapperImpl;
import ru.croc.team4.cinema.service.MovieService;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/movie")//TODO check regex for UUID
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
        this.movieMapper = new MovieMapperImpl();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDto> getMovie(@PathVariable("id") UUID id) {
        Optional<Movie> movie = movieService.findMovie(id);
        return movie.map(value -> ResponseEntity.ok(movieMapper.movieToResponseDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<MovieResponseDto>> getMovies() {
        Iterable<MovieResponseDto> movies = movieService.findAllMovies();
        return ResponseEntity.ok(movies);
    }

    @PostMapping
    public ResponseEntity<MovieResponseDto> createMovie(@Valid  @RequestBody MovieDto movieDto) {
        return ResponseEntity.ok(movieService.createMovie(movieDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDto> updateMovie(@PathVariable("id") UUID id, @Valid @RequestBody MovieDto movieDto) {
        Optional<MovieResponseDto> movie = movieService.updateMovie(id, movieDto);
        return movie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable("id") UUID id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
