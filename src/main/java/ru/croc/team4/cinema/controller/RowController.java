package ru.croc.team4.cinema.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.croc.team4.cinema.domain.Row;
import ru.croc.team4.cinema.dto.RowDto;

import ru.croc.team4.cinema.mapper.RowMapperMy;
import ru.croc.team4.cinema.repository.RowRepository;
import ru.croc.team4.cinema.service.RowServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/row")
public class RowController {

    private final RowRepository rowRepository;
    private final RowServiceImpl rowService;
    private final RowMapperMy rowMapperMy;


    @PostMapping()
    public ResponseEntity<RowDto> createRow(@RequestBody RowDto rowDto) {
        var row = rowMapperMy.rowDtoToRow(rowDto);
        row = rowRepository.save(row);
        return ResponseEntity.ok(rowMapperMy.rowToDto(row));
    }

    @GetMapping("/{sessionId}")
    public ResponseEntity<List<RowDto>> getAllRowsInSession(@PathVariable("sessionId") UUID sessionId) {
        var rows = rowService.getRowsBy(sessionId);
        return !rows.isEmpty()
                ? new ResponseEntity<>(rows.stream().map(rowMapperMy::rowToDto).toList(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
