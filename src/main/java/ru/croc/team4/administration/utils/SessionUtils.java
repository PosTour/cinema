package ru.croc.team4.administration.utils;

import org.springframework.stereotype.Component;
import ru.croc.team4.administration.domain.Session;

@Component
public class SessionUtils {

    public boolean hasFreePlaces(Session session) {
        return true;
    }
}
