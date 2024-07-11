package ru.croc.team4.cinema.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.croc.team4.cinema.domain.Row;
import ru.croc.team4.cinema.dto.RowDto;
import ru.croc.team4.cinema.service.RowServiceImpl;
import ru.croc.team4.cinema.service.SessionServiceImpl;

@Mapper
public interface RowMapper {
    @Mapping(target = "session", expression = "java(sessionServiceImpl.findSession(rowDto.sessionId()).get())")
    Row rowDtoToRow(RowDto rowDto, @Context SessionServiceImpl sessionServiceImpl);
    @Mapping(target = "sessionId", expression = "java(row.getSession().getId())")
    RowDto rowToDto(Row row);
}
