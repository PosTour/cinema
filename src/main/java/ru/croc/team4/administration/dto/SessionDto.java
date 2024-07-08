package ru.croc.team4.administration.dto;

import ru.croc.team4.administration.domain.Hall;

import java.sql.Time;
import java.util.UUID;

public record SessionDto(
        UUID id
        ,String movie
        ,Hall hall
        ,Time startTime
        ,Integer price) {
}
