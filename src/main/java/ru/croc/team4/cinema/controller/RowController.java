package ru.croc.team4.cinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.croc.team4.cinema.domain.Row;
import ru.croc.team4.cinema.dto.RowDto;
import ru.croc.team4.cinema.mapper.RowMapper;
import ru.croc.team4.cinema.mapper.RowMapperImpl;
import ru.croc.team4.cinema.repository.RowRepository;
import ru.croc.team4.cinema.service.RowServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/row")
public class RowController {

    private final RowMapper rowMapper;
    private final RowRepository rowRepository;
    private final RowServiceImpl rowService;

    @Autowired
    public RowController(RowRepository rowRepository, RowServiceImpl rowService) {
        this.rowMapper = new RowMapperImpl();
        this.rowRepository = rowRepository;
        this.rowService = rowService;
    }

    @PostMapping()
    public ResponseEntity<RowDto> createRow(@RequestBody RowDto rowDto) {
            var row = rowMapper.rowDtoToRow(rowDto);
            row = rowRepository.save(row);
            return ResponseEntity.ok(rowMapper.rowToDto(row));
    }

    @GetMapping()
    public ResponseEntity<List<Row>> getAllRowsInSession(UUID sessionId) {
        var rows = rowService.getRowsBy(sessionId);
        return !rows.isEmpty()
                ? new ResponseEntity<>(rows, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
