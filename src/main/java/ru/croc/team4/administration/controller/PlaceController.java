package ru.croc.team4.administration.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.croc.team4.administration.dto.PlaceDto;
import ru.croc.team4.administration.mapper.PlaceMapper;
import ru.croc.team4.administration.mapper.PlaceMapperImpl;
import ru.croc.team4.administration.repository.PlaceRepository;

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

    @PutMapping
    public PlaceDto create(@RequestBody PlaceDto placeDto) {
        var place = placeMapper.placeDtoToPlace(placeDto);
        place = placeRepository.save(place);
        return placeMapper.placeToPlaceDto(place);
    }

    @PutMapping
    public ResponseEntity<PlaceDto> update(@RequestParam int id) {
        var placeExists = placeRepository.findById(id);
        if (placeExists.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var place = placeExists.get();
        place.setOccupied(true);
        place = placeRepository.save(place);
        return ResponseEntity.ok(placeMapper.placeToPlaceDto(place));
    }
}
