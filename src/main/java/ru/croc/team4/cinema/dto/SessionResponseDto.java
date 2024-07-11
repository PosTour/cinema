package ru.croc.team4.cinema.dto;

import ru.croc.team4.cinema.domain.Category;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.UUID;

public record SessionResponseDto(
        UUID id
        , UUID movieId
        , String hallName
        , LocalDate startDate
        , LocalTime startTime
        , Map<Category, Integer> prices,
        Boolean isDeleted) {
}
