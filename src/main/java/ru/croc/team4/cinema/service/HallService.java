package ru.croc.team4.cinema.service;

import ru.croc.team4.cinema.domain.Category;
import ru.croc.team4.cinema.domain.Hall;
import ru.croc.team4.cinema.dto.HallDto;
import ru.croc.team4.cinema.dto.HallResponseDto;
import ru.croc.team4.cinema.dto.MovieResponseDto;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface HallService {

    Iterable<HallResponseDto> findAllHalls();

    Optional<Hall> findHallById(UUID hallId);

    HallResponseDto createHall(HallDto hallDto);

    void UpdateHall(UUID id, String name, Map<Integer, Map<Integer, Category>> seats);
}
