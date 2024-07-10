package ru.croc.team4.cinema.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MovieDto(
        @NotBlank
        @Size(min = 1, max = 32)
        String title,
        @Min(1)
        @Max(Integer.MAX_VALUE)
        Integer durationInMinutes,
        @Size(max = 512)
        String description) {
}
