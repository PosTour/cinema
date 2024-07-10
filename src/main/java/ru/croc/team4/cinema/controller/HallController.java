package ru.croc.team4.cinema.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.croc.team4.cinema.domain.Hall;
import ru.croc.team4.cinema.dto.HallDto;
import ru.croc.team4.cinema.dto.HallResponseDto;
import ru.croc.team4.cinema.dto.MovieResponseDto;
import ru.croc.team4.cinema.mapper.HallMapper;
import ru.croc.team4.cinema.mapper.HallMapperImpl;
import ru.croc.team4.cinema.service.HallService;
import ru.croc.team4.cinema.service.HallServiceImpl;


import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/hall")
public class HallController {

    private final HallService hallService;
    private final HallMapper hallMapper;

    @Autowired
    public HallController(HallServiceImpl hallServiceImpl) {
        this.hallService = hallServiceImpl;
        this.hallMapper = new HallMapperImpl();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HallDto> getHall(@PathVariable UUID id) {
        Optional<Hall> hall = hallService.findHallById(id);
        return hall.map(value -> ResponseEntity.ok(hallMapper.hallToHallDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<HallResponseDto>> getHalls() {
        Iterable<HallResponseDto> halls = hallService.findAllHalls();
        return ResponseEntity.ok(halls);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateHall(@PathVariable UUID id, @Valid @RequestBody HallDto hallDto) {
        hallService.UpdateHall(id, hallDto.name(), hallDto.seats());
        return ResponseEntity.noContent().build();
    }
}
