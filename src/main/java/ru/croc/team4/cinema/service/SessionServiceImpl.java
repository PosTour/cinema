package ru.croc.team4.cinema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.croc.team4.cinema.domain.Session;
import ru.croc.team4.cinema.dto.SessionCreationDto;
import ru.croc.team4.cinema.dto.SessionDto;
import ru.croc.team4.cinema.mapper.SessionMapper;
import ru.croc.team4.cinema.mapper.SessionMapperImpl;
import ru.croc.team4.cinema.repository.MovieRepository;
import ru.croc.team4.cinema.repository.SessionRepository;
import ru.croc.team4.cinema.utils.SessionUtils;

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
        var movie = movieRepository.findById(sessionDto.movie().getId());
        if (movie.isEmpty()) {
            throw new IllegalArgumentException("Movie not found");
        }
        var movieDuration = movie.get().getDuration();

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
                ,sessionDto.price());

        sessionRepository.save(session);
        return sessionMapper.sessionToSessionCreationDto(session);
    }

    @Override
    public List<SessionDto> getSessions(UUID movieId) {
        var movie = movieRepository.findById(movieId);
        if (movie.isEmpty()) {
            throw new IllegalArgumentException("Movie not found");
        }
        var sessions = sessionRepository
                .findAllByMovie(movie.get())
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

//    @Override
//    public boolean deleteSession(UUID sessionId) {
//        var sessionExistence = sessionRepository.findById(sessionId);
//        if (sessionExistence.isEmpty()) {
//            return false;
//        }
//
//        sessionRepository.delete(sessionExistence.get());
//        return true;
//    }
}
