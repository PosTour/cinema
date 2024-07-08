package ru.croc.team4.administration.mapper;

import org.mapstruct.Mapper;
import ru.croc.team4.administration.domain.Session;
import ru.croc.team4.administration.dto.SessionCreationDto;
import ru.croc.team4.administration.dto.SessionDto;

import java.util.List;

@Mapper
public interface SessionMapper {
    SessionDto sessionToSessionDto(Session session);

    SessionCreationDto sessionToSessionCreationDto(Session session);

    List<SessionDto> sessionsToSessionDtos(List<Session> sessions);
}
