//package ru.croc.team4.administration.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import ru.croc.team4.administration.dto.SessionCreationDto;
//import ru.croc.team4.administration.dto.SessionDto;
//import ru.croc.team4.administration.service.SessionServiceImpl;
//
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("api/session")
//public class SessionController {
//
//    private final SessionServiceImpl sessionServiceImpl;
//
//    @Autowired
//    public SessionController(SessionServiceImpl sessionServiceImpl) {
//        this.sessionServiceImpl = sessionServiceImpl;
//    }
//
//    @PostMapping
//    public ResponseEntity<SessionCreationDto> createSession(@RequestBody SessionCreationDto sessionCreationDto) {
//        SessionCreationDto sessionDto = sessionServiceImpl.createSession(sessionCreationDto);
//        return ResponseEntity.ok(sessionDto);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<SessionDto>> getSessionsBy(@RequestParam(value = "movie", required = false) String movie) {
//        var sessionDtos = sessionServiceImpl.getSessions(movie);
//
//        if (sessionDtos == null) {
//            return ResponseEntity.notFound().build();
//        } else {
//            return ResponseEntity.ok(sessionDtos);
//        }
//    }
//
//    @PutMapping
//    public ResponseEntity<SessionDto> updateSession(@RequestBody SessionDto sessionDto) {
//        var session = sessionServiceImpl.updateSession(sessionDto);
//
//        return session.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @DeleteMapping
//    public ResponseEntity<SessionDto> deleteSession(@RequestParam(value = "id", required = false) UUID id) {
//        if (sessionServiceImpl.deleteSession(id)) {
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}
