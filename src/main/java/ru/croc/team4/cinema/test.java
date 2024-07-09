package ru.croc.team4.cinema;

import ru.croc.team4.cinema.domain.Movie;
import ru.croc.team4.cinema.dto.MovieDto;
import ru.croc.team4.cinema.mapper.MovieMapper;

import java.time.Duration;

public class test {
    public static void main(String[] args) {

        MovieDto movieDto = MovieMapper.INSTANCE.movieToMovieDto(new Movie("Title", Duration.ofMinutes(23), "dsfcsdvf"));
        System.out.println(movieDto);
        Movie movie = MovieMapper.INSTANCE.movieDtoToMovie(movieDto);
        System.out.println(movie.getDuration().);

    }
}
