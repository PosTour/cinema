package ru.croc.team4.cinema.mapper;

import org.mapstruct.Mapper;
import ru.croc.team4.cinema.domain.Session;
import ru.croc.team4.cinema.dto.SessionCreationDto;
import ru.croc.team4.cinema.dto.SessionDto;

import java.util.List;

@Mapper
public interface SessionMapper {
    SessionDto sessionToSessionDto(Session session);

    SessionCreationDto sessionToSessionCreationDto(Session session);

    List<SessionDto> sessionsToSessionDtos(List<Session> sessions);
}
