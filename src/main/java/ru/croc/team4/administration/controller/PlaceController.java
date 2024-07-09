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
import ru.croc.team4.administration.service.PlaceServiceImpl;

import java.util.List;

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

    public ResponseEntity<List<PlaceDto>> getPlacesInRow(@RequestBody Row row) {
        var result = placeMapper.placeListToPlaceDtoList(placeServiceImpl.findAllInRow(row).get());
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<Boolean> updatePlace(@RequestParam int id) {
        return ResponseEntity.ok(placeServiceImpl.updatePlace(id));
    }

    //todo возможна смена на DTO, обсудить
    @GetMapping()
    public ResponseEntity<List<PlaceDto>> getAllPlaces() {
        var places = placeMapper.placeListToPlaceDtoList(placeRepository.findAll());
        return !places.isEmpty()
                ? new ResponseEntity<>(places, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
