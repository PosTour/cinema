package ru.croc.team4.administration.service;

import ru.croc.team4.administration.domain.Place;
import ru.croc.team4.administration.domain.Row;

import java.util.List;
import java.util.Optional;

public interface PlaceService {

    Optional<Place> findById(int id);
    Optional<List<Place>> findAllInRow(Row row);
    boolean updatePlace(int id);
    int countFreeInRow(Row row);
}
