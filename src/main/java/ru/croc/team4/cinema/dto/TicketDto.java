package ru.croc.team4.cinema.dto;

import jakarta.validation.constraints.*;

import java.util.UUID;

public record TicketDto(
        @NotBlank(message = "Код бронирования не может быть пустым или содержать только пробелы")
        @Size(min = 6, max =6)
        String bookingCode,
        @NotNull(message = "Сеанс не может быть пустым")
        UUID sessionId,
        @NotNull(message = "Место не может быть пустым")
        UUID placeId) {
}
