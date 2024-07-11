package ru.croc.team4.cinema.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import ru.croc.team4.cinema.domain.Session;
import ru.croc.team4.cinema.dto.SessionResponseDto;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Mapper
public interface SessionMapper {
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "movieId", source = "movie.id"),
            @Mapping(target = "hallName", source = "hall.name"),
            @Mapping(target = "startDate", source = "startDate", qualifiedByName = "toLocalDate"),
            @Mapping(target = "startTime", source = "startTime", qualifiedByName = "toLocalTime"),
            @Mapping(target = "price", source = "price"),
            @Mapping(target = "isDeleted", source = "isDeleted")
    })
    SessionResponseDto sessionToSessionResponseDto(Session session);

    @Named("toLocalDate")
    default LocalDate toLocalDate(Date date) {
        if (date != null) {
            return date.toLocalDate();
        }
        return null;
    }

    @Named("toLocalTime")
    default LocalTime toLocalTime(Time time) {
        if (time != null) {
            return time.toLocalTime();
        }
        return null;
    }

    List<SessionResponseDto> sessionsToSessionDto(List<Session> sessions);

}
