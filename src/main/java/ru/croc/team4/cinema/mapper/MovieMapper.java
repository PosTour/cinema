package ru.croc.team4.cinema.mapper;

import org.mapstruct.Mapper;
import ru.croc.team4.cinema.domain.Movie;
import ru.croc.team4.cinema.dto.MovieDto;
import ru.croc.team4.cinema.dto.MovieResponseDto;

@Mapper
public interface MovieMapper {
    MovieDto movieToMovieDto(Movie movie);
    MovieResponseDto movieToResponseDto(Movie movie);

    Iterable<MovieDto> movieListToMovieDtoList(Iterable<Movie> movies);
    Iterable<MovieResponseDto> movieListToMovieResponseDto(Iterable<Movie> movies);

}
