package ru.croc.team4.cinema.service;

import ru.croc.team4.cinema.domain.Place;
import ru.croc.team4.cinema.domain.Row;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlaceService {

    Optional<Place> findById(UUID id);
    Optional<List<Place>> findAllInRow(Row row);
    boolean updatePlace(UUID id);
    int countFreeInRow(UUID id);
}
