package ru.croc.team4.administration.dto;

import java.time.Duration;
import java.util.UUID;

public record MovieResponseDto (UUID id, String title, Duration duration, String description){
}
