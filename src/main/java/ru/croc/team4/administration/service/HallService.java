package ru.croc.team4.administration.service;

import ru.croc.team4.administration.domain.Hall;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface HallService {

    Optional<Hall> findHallById(UUID hallId);

    void UpdateHall(UUID id, String name, int capacity, Map<Integer, Integer> seats);
}
