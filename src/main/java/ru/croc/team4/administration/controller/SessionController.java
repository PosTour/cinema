package ru.croc.team4.administration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.croc.team4.administration.dto.SessionDto;
import ru.croc.team4.administration.mapper.SessionMapper;
import ru.croc.team4.administration.repository.SessionRepository;

import java.util.List;

@RestController
@RequestMapping("session")
public class SessionController {

    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;

    @Autowired
    public SessionController(SessionRepository sessionRepository, SessionMapper sessionMapper) {
        this.sessionRepository = sessionRepository;
        this.sessionMapper = sessionMapper;
    }

    @GetMapping
    public ResponseEntity<List<SessionDto>> getSessionsBy(@RequestParam(value = "movie", required = false) String movie) {
        var sessions = sessionRepository.findAllByMovie(movie);
        if (sessions.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(sessionMapper.sessionsToSessionDtos(sessions));
        }
    }
}
