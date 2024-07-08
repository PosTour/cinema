package ru.croc.team4.administration.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.croc.team4.administration.domain.Hall;
import ru.croc.team4.administration.dto.HallDto;
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
        this.hallService = hallService;
        this.hallMapper = hallMapper;
    }


    @GetMapping()
    public ResponseEntity<HallDto> getHall(@PathVariable UUID id) {
        Optional<Hall> hall = hallService.findHall(id);
        if (hall.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(hallMapper.hallToHallDto(hall.get()));
    }
}
