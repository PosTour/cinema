package ru.croc.team4.cinema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.croc.team4.cinema.domain.Row;
import ru.croc.team4.cinema.repository.RowRepository;

import java.util.List;
import java.util.UUID;

@Service
public class RowServiceImpl implements RowService {

    private final RowRepository rowRepository;

    @Autowired
    public RowServiceImpl(RowRepository rowRepository) {
        this.rowRepository = rowRepository;
    }

    @Override
    public List<Row> getRowsBy(UUID sessionId) {
        return rowRepository.findAllBySession_id(sessionId);
    }
}
