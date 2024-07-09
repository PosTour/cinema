package ru.croc.team4.administration.dto;

import ru.croc.team4.administration.domain.Row;

import java.util.UUID;

public record PlaceDto(UUID id, int inRowId, boolean occupied, Row row) {
}
