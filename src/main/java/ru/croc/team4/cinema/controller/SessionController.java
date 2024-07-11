package ru.croc.team4.cinema.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.croc.team4.cinema.domain.Session;
import ru.croc.team4.cinema.dto.SessionCreationDto;
import ru.croc.team4.cinema.dto.SessionResponseDto;
import ru.croc.team4.cinema.mapper.SessionMapper;
import ru.croc.team4.cinema.mapper.SessionMapperImpl;
import ru.croc.team4.cinema.service.SessionServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/session")
public class SessionController {

    private final SessionServiceImpl sessionService;
    private final SessionMapper sessionMapper;

    @Autowired
    public SessionController(SessionServiceImpl sessionServiceImpl) {
        this.sessionService = sessionServiceImpl;
        this.sessionMapper = new SessionMapperImpl();
    }

    @PostMapping
    public ResponseEntity<SessionResponseDto> createSession(@Valid @RequestBody SessionCreationDto sessionCreationDto) {
        SessionResponseDto sessionResponseDto = sessionService.createSession(sessionCreationDto);
        return ResponseEntity.ok(sessionResponseDto);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<SessionResponseDto>> getMovies() {
        Iterable<SessionResponseDto> sessions = sessionService.findAllSessions();
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/findByMovieId/{movieId}")
    public ResponseEntity<List<SessionResponseDto>> getSessionsByMovie(@PathVariable ("movieId") UUID movieId) {
        var sessionDtos = sessionService.getSessions(movieId);

        if (sessionDtos == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(sessionDtos);
        }
    }

    @GetMapping("/findById/{sessionId}")
    public ResponseEntity<SessionResponseDto> getSessionById(@PathVariable ("sessionId") UUID sessionId) {
        Optional<Session> session = sessionService.findSession(sessionId);
        return session.map(value -> ResponseEntity.ok(sessionMapper.sessionToSessionResponseDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{sessionId}")
    public ResponseEntity<SessionResponseDto> updateSession(@PathVariable UUID sessionId, @Valid @RequestBody SessionCreationDto sessionCreationDto) {
        var session = sessionService.updateSession(sessionId, sessionCreationDto);
        return session.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable UUID id) {
        sessionService.deleteSession(id);
        return ResponseEntity.noContent().build();
    }
}
