package ru.croc.team4.cinema.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record SessionResponseDto(
        UUID id
        , UUID movieId
        , String hallName
        , LocalDate startDate
        , LocalTime startTime
        , Integer price,
        Boolean isDeleted) {
}
