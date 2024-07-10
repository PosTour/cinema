package ru.croc.team4.cinema.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import ru.croc.team4.cinema.domain.Row;

import java.util.UUID;

public record PlaceDto(
        UUID id,
        @NotBlank
        @Size(min = 1, max = 32)
        String placeNumber,
        boolean occupied,
        @NotNull
        Row row) {
}
