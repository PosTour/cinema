package ru.croc.team4.cinema.dto;

import jakarta.validation.constraints.*;
import ru.croc.team4.cinema.domain.Row;

import java.util.UUID;

public record PlaceDto(
        UUID id,
        @NotBlank
        @Min(1)
        @Max(512)
        Integer placeNumber,
        boolean occupied,
        @NotNull
        Row row) {
}
