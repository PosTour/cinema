package ru.croc.team4.administration.dto;

import ru.croc.team4.administration.domain.Hall;
import ru.croc.team4.administration.domain.Movie;

import java.sql.Time;

public record SessionCreationDto(
        Movie movie
        ,Hall hall
        ,Time startTime
        ,Integer price) {
}
