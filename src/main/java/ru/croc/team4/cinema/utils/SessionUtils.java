package ru.croc.team4.cinema.utils;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.croc.team4.cinema.domain.Row;
import ru.croc.team4.cinema.domain.Session;
import ru.croc.team4.cinema.service.PlaceServiceImpl;
import ru.croc.team4.cinema.service.RowServiceImpl;

import java.util.List;

@Component
public class SessionUtils {
    @Lazy
    private final RowServiceImpl rowService;
    @Lazy
    private final PlaceServiceImpl placeService;

    @Autowired
    public SessionUtils(RowServiceImpl rowService, PlaceServiceImpl placeService) {
        this.rowService = rowService;
        this.placeService = placeService;
    }

    private int countFreePlaces(Session session) {
        int freePlaces = 0;

        List<Row> rows = rowService.getRowsBy(session.getId());
        for (Row row : rows) {
            freePlaces += placeService.countFreeInRow(row.getId());
        }

        return freePlaces;
    }

    public boolean hasFreePlaces(Session session) {
        return countFreePlaces(session) != 0;
    }
}
