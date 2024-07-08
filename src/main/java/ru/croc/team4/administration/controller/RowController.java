package ru.croc.team4.administration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.croc.team4.administration.dto.RowDto;
import ru.croc.team4.administration.mapper.RowMapper;
import ru.croc.team4.administration.repository.RowRepository;

@RestController
@RequestMapping("/api/row")
public class RowController {

    private final RowMapper rowMapper;
    private final RowRepository rowRepository;

    @Autowired
    public RowController(RowMapper rowMapper, RowRepository rowRepository) {
        this.rowMapper = rowMapper;
        this.rowRepository = rowRepository;
    }

    @PutMapping()
    public RowDto create(@RequestBody RowDto rowDto) {
            var row = rowMapper.rowDtoToRow(rowDto);
            row = rowRepository.save(row);
            return rowMapper.rowToDto(row);
    }
}
