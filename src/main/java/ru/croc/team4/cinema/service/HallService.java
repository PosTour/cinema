package ru.croc.team4.cinema.service;

import ru.croc.team4.cinema.domain.Hall;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface HallService {

    Optional<Hall> findHallById(UUID hallId);

    void UpdateHall(UUID id, String name, Map<String, String> seats);
}
