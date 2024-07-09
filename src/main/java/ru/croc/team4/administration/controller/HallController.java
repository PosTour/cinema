package ru.croc.team4.administration.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.croc.team4.administration.domain.Hall;
import ru.croc.team4.administration.dto.HallDto;
import ru.croc.team4.administration.dto.MovieDto;
import ru.croc.team4.administration.mapper.HallMapper;
import ru.croc.team4.administration.service.HallService;


import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/hall{id}")
public class HallController {

    private final HallService hallService;
    private final HallMapper hallMapper;

    @Autowired
    public HallController(HallService hallService, HallMapper hallMapper) {
        this.hallService = hallService; //??
        this.hallMapper = hallMapper; //???
    }


    @GetMapping()
    public ResponseEntity<HallDto> getHall(@PathVariable UUID id) {
        Optional<Hall> hall = hallService.findHallById(id);
        if (hall.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(hallMapper.hallToHallDto(hall.get()));
    }

    @PutMapping()
    public ResponseEntity<Void> updateHall(@PathVariable UUID id, @RequestBody HallDto hallDto) {
        hallService.UpdateHall(id, hallDto.name(), hallDto.capacity(), hallDto.seats());
        return ResponseEntity.noContent().build();
    }
}
