package ru.croc.team4.cinema.utils;

import org.springframework.stereotype.Component;
import ru.croc.team4.cinema.domain.Session;

@Component
public class SessionUtils {

    public boolean hasFreePlaces(Session session) {
        return true;
    }
}
