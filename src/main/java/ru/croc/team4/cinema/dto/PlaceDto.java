package ru.croc.team4.cinema.dto;

import jakarta.validation.constraints.*;
import ru.croc.team4.cinema.domain.Row;

import java.util.UUID;

public record PlaceDto(
        UUID id,
        @NotBlank(message = "Номер места не может быть пустым или содержать только пробелы")
        @Min(value = 1, message = "Номер места должен быть не менее 1")
        @Max(value = 512, message = "Номер места не может быть больше 512")
        Integer placeNumber,
        boolean occupied,
        @NotNull(message = "Информация о ряде не может быть пустой")
        Row row) {
}
