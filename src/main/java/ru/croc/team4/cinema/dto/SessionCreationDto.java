package ru.croc.team4.cinema.dto;

import jakarta.validation.constraints.NotNull;
import ru.croc.team4.cinema.domain.Hall;
import ru.croc.team4.cinema.domain.Movie;

import java.sql.Time;

public record SessionCreationDto(
        @NotNull
        Movie movie,
        @NotNull
        Hall hall,
        Time startTime,
        Integer price) {
}
