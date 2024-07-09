package ru.croc.team4.cinema.service;

import org.springframework.stereotype.Service;
import ru.croc.team4.cinema.domain.Row;

import java.util.List;
import java.util.UUID;

@Service
public interface RowService {
    List<Row> getRowsBy(UUID sessionID);
}
