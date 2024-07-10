package ru.croc.team4.cinema.mapper;

import org.mapstruct.Mapper;
import ru.croc.team4.cinema.domain.Hall;
import ru.croc.team4.cinema.domain.Movie;
import ru.croc.team4.cinema.dto.HallDto;
import ru.croc.team4.cinema.dto.MovieResponseDto;


@Mapper
public interface HallMapper {
    HallDto hallToHallDto(Hall hall);

    Iterable<HallDto> hallListToHallDto(Iterable<Hall> halls);

    Hall hallDtoToHall(HallDto dto);
}
