package ru.croc.team4.cinema.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserDto(
        @NotBlank(message = "Номер телефона не может быть пустым")
        @Pattern(regexp = "(^8|7|\\+7)((\\d{10})|[\\s-][(]\\d{3}[)][\\s-]\\d{3}[\\s-]\\d{2}[\\s-]\\d{2})",
                message = "Номер телефона должен быть в формате +7XXXXXXXXXX, 7XXXXXXXXXX или 8XXXXXXXXXX")
        String phone,
        @NotNull(message = "ID чата не может быть пустым")
        Long chatId) {
}
