package ru.croc.team4.cinema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.croc.team4.cinema.domain.Session;
import ru.croc.team4.cinema.dto.SessionCreationDto;
import ru.croc.team4.cinema.dto.SessionResponseDto;
import ru.croc.team4.cinema.mapper.SessionMapper;
import ru.croc.team4.cinema.mapper.SessionMapperImpl;
import ru.croc.team4.cinema.repository.MovieRepository;
import ru.croc.team4.cinema.repository.SessionRepository;
import ru.croc.team4.cinema.utils.SessionUtils;

import java.sql.Time;
import java.util.Collections;
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
    public SessionResponseDto createSession(SessionCreationDto sessionDto) {
        var movie = movieRepository.findById(sessionDto.movie().getId());
        if (movie.isEmpty()) {
            throw new IllegalArgumentException("Movie not found"); //TODO пересмотеть
        }
        var movieDuration = movie.get().getDuration();
        var endTime = Time.valueOf(sessionDto
                .startTime()
                .toLocalTime()
                .plus(movieDuration));
        var session = new Session(
                sessionDto.movie()
                , sessionDto.hall()
                , sessionDto.startTime()
                , endTime
                , sessionDto.price());

        sessionRepository.save(session);
        return sessionMapper.sessionToSessionResponseDto(session);
    }

    @Override
    public List<SessionResponseDto> getSessions(UUID movieId) {
        var movie = movieRepository.findById(movieId);
        if (movie.isEmpty()) {
            return Collections.emptyList();
        }
        var sessions = sessionRepository
                .findAllByMovie(movie.get())
                .stream()
                .filter(sessionUtils::hasFreePlaces)
                .toList();

        if (sessions.isEmpty()) {
            return Collections.emptyList();
        } else {
            return sessionMapper.sessionsToSessionDtos(sessions);
        }
    }

    @Override
    public Optional<Session> findSession(UUID sessionId) {
        return sessionRepository.findById(sessionId);
    }

    @Override
    public Optional<SessionResponseDto> updateSession(UUID sessionId, SessionCreationDto sessionCreationDto) {
        var sessionExistence = sessionRepository.findById(sessionId);
        if (sessionExistence.isEmpty()) {
            return Optional.empty();
        }
        var session = sessionExistence.get();
        session.setMovie(sessionCreationDto.movie());
        session.setHall(sessionCreationDto.hall());
        session.setStartTime(sessionCreationDto.startTime());
        session.setPrice(sessionCreationDto.price());
        return Optional.ofNullable(sessionMapper.sessionToSessionResponseDto(sessionRepository.save(session)));
    }

    @Override
    public void deleteSession(UUID sessionId) {
        Optional<Session> sessionOptional  = sessionRepository.findById(sessionId);
        if (sessionOptional.isPresent()) {
            Session session = sessionOptional.get();
            session.setIsDeleted(true);
            sessionRepository.save(session);
        }
    }
}
