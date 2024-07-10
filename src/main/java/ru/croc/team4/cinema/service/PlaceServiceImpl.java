package ru.croc.team4.cinema.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.croc.team4.cinema.domain.Place;
import ru.croc.team4.cinema.domain.Row;
import ru.croc.team4.cinema.repository.PlaceRepository;
import ru.croc.team4.cinema.repository.RowRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final RowRepository rowRepository;

    @Override
    public Optional<Place> findById(UUID id) {
        return placeRepository.findById(id);
    }

    @Override
    public List<Place> findAllByRowId(UUID rowId) {
        Optional<Row> row = rowRepository.findById(rowId);
        if (row.isEmpty()) {
            return Collections.emptyList();
        }
        return placeRepository.findAllByRow(row.get());
    }

    @Override
    public boolean updatePlace(UUID id, Place.Status status) {
        var placeExists = placeRepository.findById(id);
        if (placeExists.isEmpty()) {
            return false;
        }
        var place = placeExists.get();
        place.setStatus(status);
        placeRepository.save(place);
        return true;
    }

    @Override
    public int countFreeInRow(UUID id) {
        int counter = 0;
        var row = rowRepository.findById(id).get();
        List<Place> places = placeRepository.findAllByRow(row);
        for (Place place : places) {
            if (place.getStatus() == Place.Status.FREE) counter++;
        }
        return counter;
    }
}
