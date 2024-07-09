package ru.croc.team4.administration.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.croc.team4.administration.domain.Movie;
import ru.croc.team4.administration.dto.MovieDto;
import ru.croc.team4.administration.dto.MovieResponseDto;
import ru.croc.team4.administration.mapper.MovieMapper;
import ru.croc.team4.administration.mapper.MovieMapperImpl;
import ru.croc.team4.administration.service.MovieService;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/movie/{id}")//TODO check regex for UUID
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
        this.movieMapper = new MovieMapperImpl();
    }

    @GetMapping()
    public ResponseEntity<MovieResponseDto> getMovie(@PathVariable UUID id) {
        Optional<Movie> movie = movieService.findMovie(id);
        return movie.map(value -> ResponseEntity.ok(movieMapper.movieToResponseDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MovieResponseDto> createMovie(@RequestBody MovieDto movieDto) {
        return ResponseEntity.ok(movieService.createMovie(movieDto));
    }

    @PutMapping()
    public ResponseEntity<MovieResponseDto> updateMovie(@PathVariable UUID id, @RequestBody MovieDto movieDto) {
        Optional<MovieResponseDto> movie = movieService.updateMovie(id, movieDto);
        return movie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteMovie(@PathVariable UUID id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
