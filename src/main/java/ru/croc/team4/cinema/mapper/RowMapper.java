package ru.croc.team4.cinema.mapper;

import ru.croc.team4.cinema.domain.Row;
import ru.croc.team4.cinema.domain.Session;
import ru.croc.team4.cinema.dto.RowDto;

import java.util.Optional;
import java.util.UUID;

//@Mapper
//public interface RowMapper {
//    @Mapping(target = "session", expression = "java(sessionServiceImpl.findSession(rowDto.sessionId()).get())")
//    Row rowDtoToRow(RowDto rowDto, @Context SessionServiceImpl sessionServiceImpl);
//    @Mapping(target = "sessionId", expression = "java(row.getSession().getId())")
//    RowDto rowToDto(Row row);
//}
public interface RowMapper {
    default Row rowDtoToRow(RowDto rowDto) {
        UUID uuid =rowDto.sessionId();
        Optional<Session> session = findSessionById(uuid);
        if (session.isEmpty()) {
            return null;
        }
        return new Row(rowDto.id(), rowDto.rowNumber(),session.get());
    }

    default RowDto rowToDto(Row row) {
        UUID uuid =row.getSession().getId();
        return new RowDto(row.getId(), row.getRowNumber(), uuid);
    }

    Optional<Session> findSessionById(UUID sessionId);
}

