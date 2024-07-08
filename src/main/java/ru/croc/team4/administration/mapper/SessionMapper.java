package ru.croc.team4.administration.mapper;

import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
import ru.croc.team4.administration.domain.Session;
import ru.croc.team4.administration.dto.SessionDto;

import java.util.List;

@Mapper
public interface SessionMapper {
    SessionDto sessionToSessionDto(Session session);

    List<SessionDto> sessionsToSessionDtos(List<Session> sessions);
}
