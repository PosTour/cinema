package ru.croc.team4.cinema.service;

import org.springframework.stereotype.Service;
import ru.croc.team4.cinema.domain.Row;
import ru.croc.team4.cinema.dto.MovieDto;
import ru.croc.team4.cinema.dto.MovieResponseDto;
import ru.croc.team4.cinema.dto.RowDto;

import java.util.List;
import java.util.UUID;

@Service
public interface RowService {
    List<Row> getRowsBy(UUID sessionID);
    Row getRowById(UUID rowId);
    Row createRow(Row row);
}
