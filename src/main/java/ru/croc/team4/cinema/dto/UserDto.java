package ru.croc.team4.cinema.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserDto(
        @NotBlank
        @Pattern(regexp = "(^8|7|\\+7)((\\d{10})|[\\s-][(]\\d{3}[)][\\s-]\\d{3}[\\s-]\\d{2}[\\s-]\\d{2})")
        String phone) {
}
