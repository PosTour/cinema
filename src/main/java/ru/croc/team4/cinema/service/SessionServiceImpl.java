package ru.croc.team4.cinema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.croc.team4.cinema.domain.Place;
import ru.croc.team4.cinema.domain.Row;
import ru.croc.team4.cinema.domain.Session;
import ru.croc.team4.cinema.dto.PlaceDto;
import ru.croc.team4.cinema.dto.RowDto;
import ru.croc.team4.cinema.dto.SessionCreationDto;
import ru.croc.team4.cinema.dto.SessionResponseDto;
import ru.croc.team4.cinema.mapper.PlaceMapper;
import ru.croc.team4.cinema.mapper.SessionMapper;
import ru.croc.team4.cinema.mapper.SessionMapperImpl;
import ru.croc.team4.cinema.repository.*;
import ru.croc.team4.cinema.utils.SessionUtils;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

@Service
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    private final MovieRepository movieRepository;
    private final SessionMapper sessionMapper;
    private final SessionUtils sessionUtils;
    private final HallRepository hallRepository;
    private final RowService rowService;
    private final PlaceServiceImpl placeService;
    private final RowRepository rowRepository;
    private final PlaceRepository placeRepository;

    @Autowired
    public SessionServiceImpl(SessionRepository sessionRepository, MovieRepository movieRepository, SessionUtils sessionUtils, HallRepository hallRepository, RowService rowService, PlaceServiceImpl placeService, RowRepository rowRepository, PlaceRepository placeRepository) {
        this.sessionRepository = sessionRepository;
        this.movieRepository = movieRepository;
        this.rowService = rowService;
        this.placeService = placeService;
        this.rowRepository = rowRepository;
        this.placeRepository = placeRepository;
        this.sessionMapper = new SessionMapperImpl();
        this.sessionUtils = sessionUtils;
        this.hallRepository = hallRepository;

    }

    @Override
    public Iterable<SessionResponseDto> findAllSessions() {
        return sessionMapper.sessionsToSessionDto(sessionRepository.findAll());
    }

    @Override
    public SessionResponseDto createSession(SessionCreationDto sessionDto) {
        var movie = movieRepository.findById(sessionDto.movieId());
        var hall = hallRepository.findById(sessionDto.hallId());
        if (movie.isEmpty()) {
            throw new IllegalArgumentException("Данного фильма не существует");
        }
        if (hall.isEmpty()) {
            throw new IllegalArgumentException("Данного зала не существует");
        }
        var movieDuration = movie.get().getDuration();
        var endTime = Time.valueOf(sessionDto
                .startTime()
                .plus(movieDuration));

        var session = new Session(
                movie.get(), hall.get(), Time.valueOf(sessionDto.startTime()), endTime, Date.valueOf(sessionDto.startDate()), sessionDto.prices(), false);
        sessionRepository.save(session);
        var seats = session.getHall().getSeats();
        for ( var row:seats.entrySet()){
            var thisRow = rowService.createRow(new Row(null, row.getKey(), session));
            rowRepository.save(thisRow);

            for (var place: row.getValue().entrySet()){
                var thisPlace = placeService.createPlace(new Place(thisRow, Place.Status.FREE, place.getValue(),place.getKey()));
                placeRepository.save(thisPlace);
            }
        }
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
            return sessionMapper.sessionsToSessionDto(sessions);
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
        var movie = movieRepository.findById(sessionCreationDto.movieId());
        var hall = hallRepository.findById(sessionCreationDto.hallId());
        if (movie.isEmpty()) {
            throw new IllegalArgumentException("Данного фильма не существует");
        }
        if (hall.isEmpty()) {
            throw new IllegalArgumentException("Данного зала не существует");
        }
        var session = sessionExistence.get();
        session.setMovie(movie.get());
        session.setHall(hall.get());
        session.setStartTime(Time.valueOf(sessionCreationDto.startTime()));
        session.setPrices(sessionCreationDto.prices());
        return Optional.ofNullable(sessionMapper.sessionToSessionResponseDto(sessionRepository.save(session)));
    }

    @Override
    public void deleteSession(UUID sessionId) {
        Optional<Session> sessionOptional = sessionRepository.findById(sessionId);
        if (sessionOptional.isPresent()) {
            Session session = sessionOptional.get();
            session.setIsDeleted(true);
            sessionRepository.save(session);
        }
    }
}
