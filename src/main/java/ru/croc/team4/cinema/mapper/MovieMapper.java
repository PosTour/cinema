package ru.croc.team4.cinema.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.croc.team4.cinema.domain.Movie;
import ru.croc.team4.cinema.dto.MovieDto;
import ru.croc.team4.cinema.dto.MovieResponseDto;

import java.time.Duration;

@Mapper
public interface MovieMapper {
    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    @Mapping(target = "durationInMinutes", source = "duration", qualifiedByName = "durationToMinutes")
    MovieDto movieToMovieDto(Movie movie);

    @Mapping(target = "duration", source = "durationInMinutes", qualifiedByName = "minutesToDuration")
    Movie movieDtoToMovie(MovieDto movieDto);

    @Mapping(target = "durationInMinutes", source = "duration", qualifiedByName = "durationToMinutes")
    MovieResponseDto movieToResponseDto(Movie movie);

    Iterable<MovieResponseDto> movieListToMovieResponseDto(Iterable<Movie> movies);

    @Named("minutesToDuration")
    default Duration minutesToDuration(Integer durationInMinutes) {
        return Duration.ofMinutes(durationInMinutes);
    }

    @Named("durationToMinutes")
    default Integer durationToMinutes(Duration duration) {
        return (int) duration.toMinutes();
    }
}
