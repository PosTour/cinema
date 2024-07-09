package ru.croc.team4.administration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.croc.team4.administration.domain.Movie;
import ru.croc.team4.administration.domain.Session;
import ru.croc.team4.administration.dto.SessionCreationDto;
import ru.croc.team4.administration.dto.SessionDto;
import ru.croc.team4.administration.mapper.SessionMapper;
import ru.croc.team4.administration.mapper.SessionMapperImpl;
import ru.croc.team4.administration.repository.MovieRepository;
import ru.croc.team4.administration.repository.SessionRepository;
import ru.croc.team4.administration.utils.SessionUtils;

import java.sql.Time;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    private final MovieRepository movieRepository;
    private final SessionMapper sessionMapper;
    private final SessionUtils sessionUtils;

    @Autowired
    public SessionServiceImpl(SessionRepository sessionRepository, MovieRepository movieRepository, SessionUtils sessionUtils) {
        this.sessionRepository = sessionRepository;
        this.movieRepository = movieRepository;
        this.sessionMapper = new SessionMapperImpl();
        this.sessionUtils = sessionUtils;
    }

    @Override
    public SessionCreationDto createSession(SessionCreationDto sessionDto) {
        var movieDuration = movieRepository
                .findByTitle(sessionDto.movie().getTitle())
                .getDuration();

        var endTime = Time.valueOf(sessionDto
                        .startTime()
                        .toLocalTime()
                        .plus(movieDuration));

        var session = new Session(
                UUID.randomUUID()
                ,sessionDto.movie()
                ,sessionDto.hall()
                ,sessionDto.startTime()
                ,endTime
                ,sessionDto.price()
        );

        sessionRepository.save(session);
        return sessionMapper.sessionToSessionCreationDto(session);
    }

    @Override
    public List<SessionDto> getSessions(String movieName) {
        Movie movie = movieRepository.findByTitle(movieName);
        var sessions = sessionRepository
                .findAllByMovie(movie)
                .stream()
                .filter(sessionUtils::hasFreePlaces)
                .toList();

        if (sessions.isEmpty()) {
            return null;
        } else {
            return sessionMapper.sessionsToSessionDtos(sessions);
        }
    }

    @Override
    public Optional<SessionDto> updateSession(SessionDto sessionDto) {
        var sessionExistence = sessionRepository.findById(sessionDto.id());
        if (sessionExistence.isEmpty()) {
            return Optional.empty();
        }

        var session = sessionExistence.get();

        session.setMovie(sessionDto.movie());
        session.setHall(sessionDto.hall());
        session.setStartTime(sessionDto.startTime());
        session.setPrice(sessionDto.price());

        return Optional.ofNullable(sessionMapper.sessionToSessionDto(sessionRepository.save(session)));
    }

    @Override
    public boolean deleteSession(UUID sessionId) {
        var sessionExistence = sessionRepository.findById(sessionId);
        if (sessionExistence.isEmpty()) {
            return false;
        }

        sessionRepository.delete(sessionExistence.get());
        return true;
    }
}
