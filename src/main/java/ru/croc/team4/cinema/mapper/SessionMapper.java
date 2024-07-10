package ru.croc.team4.cinema.mapper;

import org.mapstruct.Mapper;
import ru.croc.team4.cinema.domain.Session;
import ru.croc.team4.cinema.dto.SessionCreationDto;
import ru.croc.team4.cinema.dto.SessionResponseDto;

import java.util.List;

@Mapper
public interface SessionMapper {
    SessionResponseDto sessionToSessionResponseDto(Session session);

    SessionCreationDto sessionToSessionCreationDto(Session session);

    List<SessionResponseDto> sessionsToSessionDtos(List<Session> sessions);
}
