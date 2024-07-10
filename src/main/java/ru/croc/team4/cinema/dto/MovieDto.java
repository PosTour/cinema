package ru.croc.team4.cinema.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MovieDto(
        @NotBlank(message = "Название фильма не может быть пустым или содержать только пробелы")
        @Size(min = 1, max = 32, message = "Название фильма должно быть от 1 до 32 символов")
        String title,
        @Min(value = 1, message = "Продолжительность фильма должна быть не менее 1 минуты")
        @Max(value = 512, message = "Продолжительность фильма не может превышать 512 минут")
        Integer durationInMinutes,
        @Size(max = 512, message = "Описание фильма не может превышать 512 символов")
        String description) {
}