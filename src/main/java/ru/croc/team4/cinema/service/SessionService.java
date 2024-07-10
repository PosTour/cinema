package ru.croc.team4.cinema.service;

import ru.croc.team4.cinema.domain.Session;
import ru.croc.team4.cinema.dto.MovieDto;
import ru.croc.team4.cinema.dto.SessionCreationDto;
import ru.croc.team4.cinema.dto.SessionResponseDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SessionService {
    SessionResponseDto createSession(SessionCreationDto sessionDto);

    List<SessionResponseDto> getSessions(UUID movieId);

    Optional<Session> findSession(UUID sessionId);

    Optional<SessionResponseDto> updateSession(UUID sessionId, SessionCreationDto sessionCreationDto);
}
