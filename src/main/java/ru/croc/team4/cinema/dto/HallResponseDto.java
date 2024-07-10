package ru.croc.team4.cinema.dto;

import java.util.Map;
import java.util.UUID;


public record HallResponseDto(UUID id, String name, Map<Integer, Integer> seats) {
}