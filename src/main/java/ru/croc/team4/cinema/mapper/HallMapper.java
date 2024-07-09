package ru.croc.team4.cinema.mapper;

import org.mapstruct.Mapper;
import ru.croc.team4.cinema.domain.Hall;
import ru.croc.team4.cinema.dto.HallDto;


@Mapper
public interface HallMapper {
    HallDto hallToHallDto(Hall hall);
    Hall hallDtoToHall(HallDto dto);
}
