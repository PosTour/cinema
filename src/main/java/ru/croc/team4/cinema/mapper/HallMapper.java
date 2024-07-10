package ru.croc.team4.cinema.mapper;

import org.mapstruct.Mapper;
import ru.croc.team4.cinema.domain.Hall;
import ru.croc.team4.cinema.domain.Movie;
import ru.croc.team4.cinema.dto.HallDto;
import ru.croc.team4.cinema.dto.HallResponseDto;
import ru.croc.team4.cinema.dto.MovieResponseDto;


@Mapper
public interface HallMapper {
    HallDto hallToHallDto(Hall hall);

    HallResponseDto hallToHallResponseDto(Hall hall);

    Iterable<HallResponseDto> hallListToHallResponseDto(Iterable<Hall> halls);

    Hall hallDtoToHall(HallDto dto);
}
