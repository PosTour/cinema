package ru.croc.team4.administration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.croc.team4.administration.domain.Session;
import ru.croc.team4.administration.dto.SessionCreationDto;
import ru.croc.team4.administration.dto.SessionDto;
import ru.croc.team4.administration.mapper.SessionMapper;
import ru.croc.team4.administration.repository.SessionRepository;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/session")
public class SessionController {

    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;

    @Autowired
    public SessionController(SessionRepository sessionRepository, SessionMapper sessionMapper) {
        this.sessionRepository = sessionRepository;
        this.sessionMapper = sessionMapper;
    }

//    @PostMapping
//    public ResponseEntity<SessionCreationDto> create(@RequestBody SessionCreationDto sessionDto) {
//        var movie =
//
//        var session = new Session()
//    }

    @GetMapping
    public ResponseEntity<List<SessionDto>> getSessionsBy(@RequestParam(value = "movie", required = false) String movie) {
        var sessions = sessionRepository.findAllByMovie(movie);

        if (sessions.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(sessionMapper.sessionsToSessionDtos(sessions));
        }
    }

    @PutMapping
    public ResponseEntity<SessionDto> createSession(@RequestBody SessionDto sessionDto) {
        var sessionExistence = sessionRepository.findBySessionId(sessionDto.id());
        if (sessionExistence.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var session = sessionExistence.get();

        session.setMovie(sessionDto.movie());
        session.setHall(sessionDto.hall());
        session.setStartTime(sessionDto.startTime());
        session.setPrice(sessionDto.price());

        return ResponseEntity.ok(sessionMapper.sessionToSessionDto(sessionRepository.save(session)));
    }

    @DeleteMapping
    public ResponseEntity<SessionDto> deleteSession(@RequestParam(value = "id", required = false) UUID id) {
        var sessionExistence = sessionRepository.findBySessionId(id);
        if (sessionExistence.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        sessionRepository.delete(sessionExistence.get());

        return ResponseEntity.ok().build();
    }
}
