package ru.croc.team4.cinema.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.croc.team4.cinema.domain.Row;
import ru.croc.team4.cinema.dto.RowDto;
import ru.croc.team4.cinema.mapper.RowMapper;
import ru.croc.team4.cinema.repository.RowRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RowServiceImpl implements RowService {

    private final RowRepository rowRepository;
    private final RowMapper rowMapperMy;

    @Override
    public List<Row> getRowsBy(UUID sessionId) {
        return rowRepository.findAllBySession_id(sessionId);
    }

    @Override
    public Row getRowById(UUID rowId) {
        return rowRepository.getRowById(rowId);
    }

    @Override
    public Row createRow(Row row) {
        return rowRepository.save(row);
    }
}

