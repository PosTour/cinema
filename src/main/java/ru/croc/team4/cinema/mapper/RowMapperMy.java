package ru.croc.team4.cinema.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.croc.team4.cinema.domain.Session;
import ru.croc.team4.cinema.repository.SessionRepository;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RowMapperMy implements  RowMapper{

    private final SessionRepository sessionRepository;

    @Override
    public Optional<Session> findSessionById(UUID sessionId) {
        return sessionRepository.findById(sessionId);
    }
}
