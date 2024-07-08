package ru.croc.team4.administration.dto;

import ru.croc.team4.administration.domain.Row;

public record PlaceDto(int id, int inRowId, boolean occupied, Row row) {
}
