package ru.croc.team4.administration.dto;

import ru.croc.team4.administration.domain.Hall;

import java.sql.Time;

public record SessionCreationDto(
        String movie
        , Hall hall
        , Time startTime
        , Integer price) {
}
