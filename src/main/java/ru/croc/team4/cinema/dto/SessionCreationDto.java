package ru.croc.team4.cinema.dto;

import jakarta.validation.constraints.NotNull;
import ru.croc.team4.cinema.domain.Hall;
import ru.croc.team4.cinema.domain.Movie;

import java.sql.Time;

public record SessionCreationDto(
        @NotNull(message = "Фильм не может быть пустым")
        Movie movie,
        @NotNull(message = "Зал не может быть пустым")
        Hall hall,
        Time startTime,
        Integer price) {
}
