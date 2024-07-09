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

import java.util.List;

@RestController
@RequestMapping("/api/row")
public class RowController {

    private final RowMapper rowMapper;
    private final RowRepository rowRepository;

    @Autowired
    public RowController(RowRepository rowRepository) {
        this.rowMapper = new RowMapperImpl();
        this.rowRepository = rowRepository;
    }

    @PostMapping()
    public ResponseEntity<RowDto> createRow(@RequestBody RowDto rowDto) {
            var row = rowMapper.rowDtoToRow(rowDto);
            row = rowRepository.save(row);
            return ResponseEntity.ok(rowMapper.rowToDto(row));
    }

    //todo возможна смена на DTO, обсудить
    @GetMapping()
    public ResponseEntity<List<Row>> getAllRows() {
        var rows = rowRepository.findAll();
        return !rows.isEmpty()
                ? new ResponseEntity<>(rows, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
