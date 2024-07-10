package ru.croc.team4.cinema.service;

import ru.croc.team4.cinema.domain.Hall;
import ru.croc.team4.cinema.dto.HallDto;
import ru.croc.team4.cinema.dto.MovieResponseDto;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface HallService {

    Iterable<HallDto> findAllHalls();

    Optional<Hall> findHallById(UUID hallId);

    void UpdateHall(UUID id, String name, Map<String, String> seats);
}
