package ru.croc.team4.cinema.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import ru.croc.team4.cinema.domain.Category;

import java.util.Map;

public record HallDto(
        @NotBlank(message = "Название зала не может быть пустым или содержать только пробелы")
        @Size(min = 2, max = 32, message = "Название зала должно быть от 2 до 32 символов")
        String name,
        @NotEmpty(message = "Информация о местах в зале не может быть пустой")
        //@Size(min = 1, message = "В зале должно быть хотя бы одно место")
        Map<Integer, Map<Integer, Category>> seats) {
}