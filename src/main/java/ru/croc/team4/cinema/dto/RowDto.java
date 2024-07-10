package ru.croc.team4.cinema.dto;

import jakarta.validation.constraints.*;
import ru.croc.team4.cinema.domain.Session;

import java.util.UUID;

public record RowDto(
        UUID id,
        @NotBlank
        @Min(1)
        @Max(512)
        Integer rowNumber,
        @NotNull
        Session session) {
}
