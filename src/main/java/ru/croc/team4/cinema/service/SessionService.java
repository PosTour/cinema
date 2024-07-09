package ru.croc.team4.cinema.service;

import ru.croc.team4.cinema.dto.SessionCreationDto;
import ru.croc.team4.cinema.dto.SessionDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SessionService {
    SessionCreationDto createSession(SessionCreationDto sessionDto);

    List<SessionDto> getSessions(String movie);

    Optional<SessionDto> updateSession(SessionDto sessionDto);

    boolean deleteSession(UUID sessionId);
}
