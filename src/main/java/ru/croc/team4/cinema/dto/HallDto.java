package ru.croc.team4.cinema.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Map;

public record HallDto (
        @NotBlank
        @Size(min = 2, max = 32)
        String name,
        @NotEmpty
        @Size(min = 1)
        Map<String, String> seats){
}
