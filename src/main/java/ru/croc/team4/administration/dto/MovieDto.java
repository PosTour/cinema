package ru.croc.team4.administration.dto;

import java.time.Duration;
import java.util.UUID;

public record MovieDto (String title, Duration duration, String description){
}
