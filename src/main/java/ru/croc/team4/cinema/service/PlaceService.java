package ru.croc.team4.cinema.service;

import ru.croc.team4.cinema.domain.Place;
import ru.croc.team4.cinema.domain.Row;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlaceService {

    Optional<Place> findById(UUID id);
    List<Place> findAllByRowId(UUID rowId);
    boolean updatePlace(UUID id, Place.Status status);
    int countFreeInRow(UUID id);
}
