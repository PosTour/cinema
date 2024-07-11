package ru.croc.team4.cinema.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record SessionCreationDto(
        @NotNull(message = "Фильм не может быть пустым")
        UUID movieId,
        @NotNull(message = "Зал не может быть пустым")
        UUID hallId,
        @NotNull(message = "Дата начала сеанса не может быть пустой")
        @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Дата начала сеанса должна быть в формате 'гггг-мм-дд'")
        LocalDate startDate,
        @NotNull(message = "Время начала сеанса не может быть пустым")
        @Pattern(regexp = "\\d{2}:\\d{2}", message = "Время начала сеанса должно быть в формате 'чч:мм'")
        LocalTime startTime,
        @Positive(message = "Цена должна быть положительной")
        Integer price) {
}
