package ru.croc.team4.cinema.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record MovieResponseDto(UUID id, String title, Integer durationInMinutes, String description) {
}
