package ru.croc.team4.cinema.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import ru.croc.team4.cinema.domain.Category;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.UUID;

public record SessionCreationDto(
        @NotNull(message = "Фильм не может быть пустым")
        UUID movieId,
        @NotNull(message = "Зал не может быть пустым")
        UUID hallId,
        @NotNull(message = "Дата начала сеанса не может быть пустой")
        @Future(message = "Дата должна быть в будущем")
        LocalDate startDate,
        @NotNull(message = "Время начала сеанса не может быть пустым")
        LocalTime startTime,
        @NotNull(message = "Цены должны быть установлены")
        Map<Category,Integer> prices) {
}
