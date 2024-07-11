package ru.croc.team4.cinema.dto;

import jakarta.validation.constraints.*;
import ru.croc.team4.cinema.domain.Session;

import java.util.UUID;

public record RowDto(
        UUID id,
        @NotBlank(message = "Номер ряда не может быть пустым или содержать только пробелы")
        @Min(value = 1, message = "Номер ряда должен быть не менее 1")
        @Max(value = 512, message = "Номер ряда не может быть больше 512")
        Integer rowNumber,
        @NotNull(message = "Информация о сеансе не может быть пустой")
        UUID sessionId) {
}
