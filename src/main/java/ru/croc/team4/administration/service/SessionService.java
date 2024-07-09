package ru.croc.team4.administration.service;

import ru.croc.team4.administration.domain.Movie;
import ru.croc.team4.administration.dto.SessionCreationDto;
import ru.croc.team4.administration.dto.SessionDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SessionService {
    SessionCreationDto createSession(SessionCreationDto sessionDto);

    List<SessionDto> getSessions(String movie);

    Optional<SessionDto> updateSession(SessionDto sessionDto);

    boolean deleteSession(UUID sessionId);
}
