package ru.croc.team4.cinema.dto;

import ru.croc.team4.cinema.domain.Hall;
import ru.croc.team4.cinema.domain.Movie;

import java.sql.Time;

public record SessionCreationDto(
        Movie movie
        ,Hall hall
        ,Time startTime
        ,Integer price) {
}
