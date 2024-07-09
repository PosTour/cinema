package ru.croc.team4.administration.service;

import ru.croc.team4.administration.domain.Place;
import ru.croc.team4.administration.domain.Row;
import ru.croc.team4.administration.dto.PlaceDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlaceService {

    Optional<Place> findById(UUID id);
    Optional<List<Place>> findAllInRow(Row row);
    boolean updatePlace(int id);
    int countFreeInRow(int id);
}
