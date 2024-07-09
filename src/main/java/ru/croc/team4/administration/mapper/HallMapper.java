package ru.croc.team4.administration.mapper;

import org.mapstruct.Mapper;
import ru.croc.team4.administration.domain.Hall;
import ru.croc.team4.administration.dto.HallDto;

import java.util.Optional;


@Mapper
public interface HallMapper {
    HallDto hallToHallDto(Hall hall);
    Hall hallDtoToHall(HallDto dto);
}
