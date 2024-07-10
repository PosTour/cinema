package ru.croc.team4.cinema.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import ru.croc.team4.cinema.domain.Session;

import java.util.UUID;

public record RowDto(
        UUID id,
        @NotBlank
        @Size(min = 1, max = 32)
        String rowNumber,
        @NotNull
        Session session) {
}
