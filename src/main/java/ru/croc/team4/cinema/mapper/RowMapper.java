package ru.croc.team4.cinema.mapper;

import org.mapstruct.Mapper;
import ru.croc.team4.cinema.domain.Row;
import ru.croc.team4.cinema.dto.RowDto;

@Mapper
public interface RowMapper {
    Row rowDtoToRow(RowDto rowDto);
    RowDto rowToDto(Row row);
}
