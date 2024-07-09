package ru.croc.team4.administration.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.croc.team4.administration.domain.Place;
import ru.croc.team4.administration.domain.Row;
import ru.croc.team4.administration.mapper.PlaceMapper;
import ru.croc.team4.administration.repository.PlaceRepository;
import ru.croc.team4.administration.repository.RowRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;

    @Override
    public Optional<Place> findById(int id) {
        return placeRepository.findById(id);
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
    public int countFreeInRow(Row row) {
        int counter = 0;
        List<Place> places = placeRepository.findAllByRow(row);
        for (Place place : places) {
            if (!place.isOccupied()) counter++;
        }
        return counter;
    }
}
