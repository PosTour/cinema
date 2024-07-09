package ru.croc.team4.administration.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.croc.team4.administration.dto.MovieDto;
import ru.croc.team4.administration.dto.MovieResponseDto;
import ru.croc.team4.administration.service.MovieService;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MoviesController {
    private final MovieService movieService;

    @GetMapping()
    public ResponseEntity<Iterable<MovieResponseDto>> getMovies() {
        Iterable<MovieResponseDto> movies = movieService.findAllMovies();
        return ResponseEntity.ok(movies);
    }
    @PostMapping()
    public ResponseEntity<MovieResponseDto> createMovie(@RequestBody MovieDto movieDto) { //TODO validate??
       return ResponseEntity.ok(movieService.createMovie(movieDto));
    }
}
