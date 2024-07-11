package ru.croc.team4.cinema.dto;

import ru.croc.team4.cinema.domain.Category;

import java.util.Map;
import java.util.UUID;


public record HallResponseDto(UUID id, String name,Map<Integer, Map<Integer, Category>> seats) {
}