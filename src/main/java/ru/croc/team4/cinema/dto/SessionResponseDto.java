package ru.croc.team4.cinema.dto;

import ru.croc.team4.cinema.domain.Hall;
import ru.croc.team4.cinema.domain.Movie;

import java.sql.Time;
import java.util.UUID;

public record SessionResponseDto(
        UUID id
        , UUID movieId
        , String hallName
        , Time startTime
        , Integer price) {
}
