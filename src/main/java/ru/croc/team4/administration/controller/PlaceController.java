package ru.croc.team4.administration.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.croc.team4.administration.domain.Place;
import ru.croc.team4.administration.domain.Row;
import ru.croc.team4.administration.dto.PlaceDto;
import ru.croc.team4.administration.mapper.PlaceMapper;
import ru.croc.team4.administration.mapper.PlaceMapperImpl;
import ru.croc.team4.administration.repository.PlaceRepository;

import java.util.List;

@RestController
@RequestMapping("/api/place")
public class PlaceController {
    private final PlaceRepository placeRepository;
    private final PlaceMapper placeMapper;

    @Autowired
    public PlaceController(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
        this.placeMapper = new PlaceMapperImpl();
    }

    @PostMapping
    public ResponseEntity<PlaceDto> createPlace(@RequestBody PlaceDto placeDto) {
        var place = placeMapper.placeDtoToPlace(placeDto);
        place = placeRepository.save(place);
        return ResponseEntity.ok(placeMapper.placeToPlaceDto(place));
    }

    @PutMapping
    public ResponseEntity<PlaceDto> updatePlace(@RequestParam int id) {
        var placeExists = placeRepository.findById(id);
        if (placeExists.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var place = placeExists.get();
        place.setOccupied(true);
        place = placeRepository.save(place);
        return ResponseEntity.ok(placeMapper.placeToPlaceDto(place));
    }

    //todo возможна смена на DTO, обсудить
    @GetMapping()
    public ResponseEntity<List<Place>> getAllPlaces() {
        var place = placeRepository.findAll();
        return !place.isEmpty()
                ? new ResponseEntity<>(place, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
