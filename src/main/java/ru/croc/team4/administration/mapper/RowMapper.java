package ru.croc.team4.administration.mapper;

import org.mapstruct.Mapper;
import ru.croc.team4.administration.domain.Row;
import ru.croc.team4.administration.dto.RowDto;

@Mapper
public interface RowMapper {
    Row rowDtoToRow(RowDto rowDto);
    RowDto rowToDto(Row row);
}
