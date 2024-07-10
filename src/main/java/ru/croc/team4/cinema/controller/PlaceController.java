package ru.croc.team4.cinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.croc.team4.cinema.domain.Row;
import ru.croc.team4.cinema.dto.PlaceDto;
import ru.croc.team4.cinema.mapper.PlaceMapper;
import ru.croc.team4.cinema.mapper.PlaceMapperImpl;
import ru.croc.team4.cinema.repository.PlaceRepository;
import ru.croc.team4.cinema.service.PlaceServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/place")
public class PlaceController {
    private final PlaceRepository placeRepository;
    private final PlaceMapper placeMapper;
    private final PlaceServiceImpl placeServiceImpl;

    @Autowired
    public PlaceController(PlaceRepository placeRepository, PlaceServiceImpl placeServiceImpl) {
        this.placeRepository = placeRepository;
        this.placeMapper = new PlaceMapperImpl();
        this.placeServiceImpl = placeServiceImpl;
    }

    @PostMapping
    public ResponseEntity<PlaceDto> createPlace(@RequestBody PlaceDto placeDto) {
        var place = placeMapper.placeDtoToPlace(placeDto);
        place = placeRepository.save(place);
        return ResponseEntity.ok(placeMapper.placeToPlaceDto(place));
    }

    @GetMapping("/{rowId}")
    public ResponseEntity<List<PlaceDto>> getPlacesByRowId(@PathVariable UUID rowId) {
        var result = placeMapper.placeListToPlaceDtoList(placeServiceImpl.findAllByRowId(rowId));
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> updatePlace(@PathVariable UUID id) {
        return ResponseEntity.ok(placeServiceImpl.updatePlace(id));
    }

    @GetMapping()
    public ResponseEntity<List<PlaceDto>> getAllPlaces() {
        var places = placeMapper.placeListToPlaceDtoList(placeRepository.findAll());
        return !places.isEmpty()
                ? new ResponseEntity<>(places, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
