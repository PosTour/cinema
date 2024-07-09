package ru.croc.team4.cinema.dto;

import java.util.UUID;

public record MovieResponseDto(UUID id, String title, Integer durationInMinutes, String description) {
}
