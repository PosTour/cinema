package ru.croc.team4.cinema.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.croc.team4.cinema.domain.Place;
import ru.croc.team4.cinema.domain.Row;
import ru.croc.team4.cinema.repository.PlaceRepository;
import ru.croc.team4.cinema.repository.RowRepository;

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
        return placeRepository.findByPlaceId(id);
    }

    @Override
    public Optional<List<Place>> findAllInRow(Row row) {
        return Optional.ofNullable(placeRepository.findAllByRow(row));
    }

    @Override
    public boolean updatePlace(int id) {
        var placeExists = placeRepository.findById(id);
        if (placeExists.isEmpty()) {
            return false;
        }
        var place = placeExists.get();
        place.setOccupied(true);
        placeRepository.save(place);
        return true;
    }

    @Override
    public int countFreeInRow(int id) {
        int counter = 0;
        var row = rowRepository.findById(id).get();
        List<Place> places = placeRepository.findAllByRow(row);
        for (Place place : places) {
            if (!place.isOccupied()) counter++;
        }
        return counter;
    }
}
