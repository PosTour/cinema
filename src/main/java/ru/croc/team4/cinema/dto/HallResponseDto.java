package ru.croc.team4.cinema.dto;

import java.util.UUID;


public record HallResponseDto(UUID id, String title, Integer durationInMinutes, String description) {}

