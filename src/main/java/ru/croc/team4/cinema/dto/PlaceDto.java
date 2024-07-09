package ru.croc.team4.cinema.dto;

import ru.croc.team4.cinema.domain.Row;

import java.util.UUID;

public record PlaceDto(UUID id, int inRowId, boolean occupied, Row row) {
}
