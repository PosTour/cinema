package ru.croc.team4.cinema.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.croc.team4.cinema.dto.MovieDto;
import ru.croc.team4.cinema.dto.MovieResponseDto;
import ru.croc.team4.cinema.service.MovieService;

@RestController
@RequestMapping("/api/movie/all")
@RequiredArgsConstructor
public class MoviesController {
    private final MovieService movieService;

    @GetMapping()
    public ResponseEntity<Iterable<MovieResponseDto>> getMovies() {
        Iterable<MovieResponseDto> movies = movieService.findAllMovies();
        return ResponseEntity.ok(movies);
    }
}
